package com.adesfe.practice05_shopping_list.ui

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adesfe.practice05_shopping_list.R
import com.adesfe.practice05_shopping_list.adapters.ProductAdapter
import com.adesfe.practice05_shopping_list.database.entities.ProductEntity
import com.adesfe.practice05_shopping_list.databinding.ActivityMainBinding
import com.adesfe.practice05_shopping_list.viewmodel.ProductViewModel
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var recyclerView: RecyclerView
    var products: MutableList<ProductEntity> = mutableListOf()

    private lateinit var productViewModel:ProductViewModel

    lateinit var adapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivityMainBinding.inflate(layoutInflater).also{
            binding = it
        }.root)

        productViewModel = ViewModelProvider(this)[ProductViewModel::class.java]

        productViewModel.getAllProducts()

        productViewModel.productListLD.observe(this){
            products.clear()
            products.addAll(it)
            recyclerView.adapter?.notifyDataSetChanged()
        }

        productViewModel.precioTotal.observe(this){
            binding.tvPrecioFinal.text = it.toString()

        }

        productViewModel.updateProductLD.observe(this){productUpdated ->
            if (productUpdated != null){
                val product = products.filter{
                    it.id == productUpdated.id
                }[0]
                val pos = products.indexOf(product)
                recyclerView.adapter?.notifyItemChanged(pos)
            }
        }

        productViewModel.deleteProductLD.observe(this){ id ->
            if(id != -1){
                val product = products.filter{
                    it.id == id
                }[0]
                val pos = products.indexOf(product)
                products.removeAt(pos)
                recyclerView.adapter?.notifyItemRemoved(pos)
            }else{
                showMessage("Error deleting product")
            }
        }

        productViewModel.insertProductLD.observe(this){
            products.add(it)
            recyclerView.adapter?.notifyItemInserted(products.size)
        }

        binding.btnAddProduct.setOnClickListener{
            addProduct()
        }


        setUpRecyclerView()

    }

    private fun showMessage(s: String){
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show()
    }

    private fun addProduct(){
        var error = false
        var nombre = ""
        var cantidad = 0
        var precio = 0.0

        if (binding.etProducto.text.toString().equals("")){
            error = true
            binding.etProducto.error = "El campo no puede estar vacio."
        } else if(binding.etProducto.text.toString().length>10){
            error = true
            binding.etProducto.error = "El campo debe ser más corto de 10."
        }
        try {
            cantidad = binding.etCantidad.text.toString().toInt()
        }catch (e: Exception){
            binding.etCantidad.error = "El campo no puede estar vacio."
            error = true
        }
        try {
            if (binding.etPrecio.text.toString().contains(".")) {
                if (binding.etPrecio.text.toString().split(".")[1].length > 2) {
                    binding.etPrecio.error = "El número no puede tener más de 2 decimales"
                    error = true
                } else precio = binding.etPrecio.text.toString().toDouble()
            }else precio = binding.etPrecio.text.toString().toDouble()
        }catch (e: Exception){
            binding.etPrecio.error = "El campo no puede estar vacio."
            error = true
        }
        if (!error) {
            productViewModel.add(
                nombre,
                cantidad,
                precio
            )
            clearFocus()
            hideKeyboard()
        }
    }

    fun setUpRecyclerView() {
        adapter = ProductAdapter(products, {productEntity -> updateProduct(productEntity) }, {productEntity -> deleteProduct(productEntity)})
        recyclerView = binding.rvTask
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    private fun updateProduct(productEntity: ProductEntity){
        productViewModel.update(productEntity)
    }

    private fun deleteProduct(productEntity: ProductEntity){
        productViewModel.delete(productEntity)
    }

    private fun clearFocus(){
        binding.etProducto.setText("")
        binding.etCantidad.setText("")
        binding.etPrecio.setText("")

    }

    private fun Context.hideKeyboard(){
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }
}