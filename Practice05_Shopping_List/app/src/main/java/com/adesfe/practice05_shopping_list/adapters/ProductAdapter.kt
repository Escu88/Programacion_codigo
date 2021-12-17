package com.adesfe.practice05_shopping_list.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.adesfe.practice05_shopping_list.R
import com.adesfe.practice05_shopping_list.database.entities.ProductEntity
import java.util.*


class ProductAdapter (
    val products: List<ProductEntity>,
    val updateProduct:(ProductEntity)->Unit,
    val deleteProduct:(ProductEntity)->Unit): RecyclerView.Adapter<ProductAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_product, parent, false))
    }

    override fun onBindViewHolder(holder: ProductAdapter.ViewHolder, position: Int) {
        val item = products[position]
        holder.bind(item, updateProduct, deleteProduct)
    }

    override fun getItemCount() = products.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvProducto = view.findViewById<TextView>(R.id.tvProducto)
        val tvCantidad = view.findViewById<TextView>(R.id.tvCantidad)
        val tvCoste = view.findViewById<TextView>(R.id.tvCoste)
        val tvPrecioTotal = view.findViewById<TextView>(R.id.tvPrecioTotal)
        val imagendel = view.findViewById<ImageButton>(R.id.btnEliminar)

        fun bind(
            product: ProductEntity,
            updateProduct: (ProductEntity) -> Unit,
            deleteProduct: (ProductEntity) -> Unit
        ) {
            tvProducto.text = product.name
            tvCantidad.text = product.cantidad.toString()
            tvCoste.text = product.precio.toString()
            tvPrecioTotal.text = (product.precio*product.cantidad).toString()

            imagendel.setOnClickListener {
                deleteProduct(product)
                updateProduct(product)
            }

            }
        }
    }
