package com.adesfe.practice05_shopping_list.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.adesfe.practice05_shopping_list.database.MyDao
import com.adesfe.practice05_shopping_list.database.entities.ProductDatabase
import com.adesfe.practice05_shopping_list.database.entities.ProductEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductViewModel(application: Application): AndroidViewModel(application) {
    val context = application

    val productListLD: MutableLiveData<MutableList<ProductEntity>> = MutableLiveData()
    val updateProductLD: MutableLiveData<ProductEntity?> = MutableLiveData()
    val deleteProductLD: MutableLiveData<Int> = MutableLiveData()
    val insertProductLD: MutableLiveData<ProductEntity> = MutableLiveData()

    val precioTotal: MutableLiveData<Double> = MutableLiveData()

    var myDao:MyDao = ProductDatabase.getInstance(context)

    fun getAllProducts(){
        CoroutineScope(Dispatchers.IO).launch{
            productListLD.postValue(myDao.getAllProducts())
            calcularTotal(myDao.getAllProducts())
        }
    }

    private fun calcularTotal(allProducts: MutableList<ProductEntity>) {
        var preciocalc = 0.0
        for (prod in allProducts) {
            preciocalc += prod.cantidad * prod.precio
        }
        precioTotal.postValue(preciocalc)

    }

    fun add(product:String, cant: Int, preci :Double){
        CoroutineScope(Dispatchers.IO).launch{
            val id = myDao.addProducts(ProductEntity(name = product, cantidad = cant, precio = preci))
            val recoveryProduct = myDao.getProductById(id)
            insertProductLD.postValue(recoveryProduct)
            calcularTotal(myDao.getAllProducts())
        }
    }

    fun delete(product:ProductEntity){
        CoroutineScope(Dispatchers.IO).launch{
            if (product.cantidad>1){
                product.cantidad--
                updateProductLD.postValue(product)

            }else {
                val res= myDao.deleteProduct(product)
                if (res>0)
                    deleteProductLD.postValue(product.id)
                else{
                    deleteProductLD.postValue(-1)
                }
            }
            calcularTotal(myDao.getAllProducts())


        }

    }

    fun update(product:ProductEntity){
        CoroutineScope(Dispatchers.IO).launch{
            val res = myDao.updateProduct(product)
            if(res>0) {
                updateProductLD.postValue(product)
                calcularTotal(myDao.getAllProducts())

            }else {
                updateProductLD.postValue(null)
                calcularTotal(myDao.getAllProducts())

            }

        }
    }



}