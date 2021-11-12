package com.adesfe.calculatormvvm.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adesfe.calculatormvvm.Model.Calculator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CalculatorViewModel: ViewModel() {

    private val calculator: Calculator = Calculator()


    val hipoteca : MutableLiveData<Double> = MutableLiveData()

    val mutableHipo : MutableLiveData<Double> = MutableLiveData()
    val capiError : MutableLiveData<String> = MutableLiveData()
    val intError : MutableLiveData<String> = MutableLiveData()
    val periodError : MutableLiveData<String> = MutableLiveData()
    val error : MutableLiveData<String> = MutableLiveData()
    val loading : MutableLiveData<Boolean> = MutableLiveData()


    fun calculate(capital: Double, interes: Double, periodo: Double) {
        //Using coroutine
        calculateSealed(capital, interes, periodo)
    }

    /************WITH SEALED*************/

    private fun calculateSealed(capital: Double, interes: Double, periodo: Double){
        CoroutineScope(Dispatchers.IO).launch {
            loading.postValue(true)

            calculator.calculateWithSealed(Calculator.Request(capital, interes, periodo)).also{res ->
                when(res){
                    is Calculator.Response.OKResult -> {
                        mutableHipo.postValue(res.result)
                        capiError.postValue("")
                        intError.postValue("")
                        periodError.postValue("") }
                    is Calculator.Response.WrongCapital -> {
                        capiError.postValue(res.error)
                    }
                    is Calculator.Response.WrongInteres -> {
                        intError.postValue(res.error)
                    }
                    is Calculator.Response.WrongPeriodo -> {
                        periodError.postValue(res.error)
                    }
                }
            }
            loading.postValue(false)
        }
        }
    }