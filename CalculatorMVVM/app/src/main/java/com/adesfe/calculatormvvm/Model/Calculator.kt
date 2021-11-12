package com.adesfe.calculatormvvm.Model

typealias OnLoading = (loading:Boolean)->Unit


class Calculator{

    data class Request(
        val capital:Double,
        val interes:Double,
        val retorno:Double
    )


    private fun calcHip(capital: Double, interes: Double, retorno: Double):Double = capital*interes/12/(1-Math.pow(1+(interes/12),-retorno*12))

    /*
    fun calculate(request: Request):Response{

        Thread.sleep(5000)
        println(Thread.currentThread().name)

        return Response.OKResult(calcHip(request.capital, request.interes, request.retorno))

    }*/

    /**************WHITH SEALED CLASS*************/
    sealed class Response{
        class OKResult(val result:Double):Response()
        class WrongCapital(val error:String):Response()
        class WrongInteres(val error:String):Response()
        class WrongPeriodo(val error:String):Response()
    }

    fun calculateWithSealed(request:Request):Response{
        val mincapital = 50
        val mininteres  = 2
        val minperiodo = 1

        if (mincapital > request.capital) return (Response.WrongCapital("El capital debería ser mayor"))
        if (mininteres > request.interes) return (Response.WrongInteres("Eñ interes debería ser mayor"))
        if (minperiodo > request.retorno) return (Response.WrongPeriodo("El periodo debería ser mayor"))

        Thread.sleep(5000)
        println("${Thread.currentThread().name}")

        //All works fine
        return Response.OKResult(calcHip(request.capital, request.interes, request.retorno))

    }

}