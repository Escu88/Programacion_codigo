package com.adesfe.calculatormvvm

import android.icu.text.DecimalFormat
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.adesfe.calculatormvvm.ViewModel.CalculatorViewModel
import com.adesfe.calculatormvvm.databinding.FragmentCalcBinding



class CalcFragment : Fragment() {

    private lateinit var binding: FragmentCalcBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_calc, container, false)
        return FragmentCalcBinding
            .inflate(inflater, container, false)
            .also { binding = it }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val calculatorViewModel:CalculatorViewModel = ViewModelProvider(this)[CalculatorViewModel::class.java]

        binding.btnCalculate.setOnClickListener {

            var mCapital = 0.0
            var mInteres = 0.0
            var mPeriodo = 0.0

            mCapital = binding.capital.text.toString().toDouble()

            mInteres = binding.interes.text.toString().toDouble()

            mPeriodo = binding.periodo.text.toString().toDouble()

            calculatorViewModel.calculate(mCapital, mInteres, mPeriodo)
        }

        calculatorViewModel.mutableHipo.observe(viewLifecycleOwner){
            resul -> val dec = DecimalFormat("#,###.00")

            binding.resultado.text = dec.format(resul).toString()
            binding.capital.error = null
            binding.interes.error = null
            binding.periodo.error = null

        }

        calculatorViewModel.loading.observe(viewLifecycleOwner){
             if (it)
                 binding.progressCircular.visibility = View.VISIBLE
             else
                 binding.progressCircular.visibility = View.GONE


        }

    }
/*
        val hipoCalculator = CalculatorViewModel()
        binding.btnCalculate.setOnClickListener {


            binding.progressCircular.visibility = View.VISIBLE


            val vCapital = binding.capital.text.toString().toDouble()
            val vInteres = binding.interes.text.toString().toDouble()
            val vPeriodo = binding.periodo.text.toString().toDouble()

            val hipoteca = hipoCalculator.calculate(Calculator.Request(vCapital, vInteres, vPeriodo))
            val dec = DecimalFormat("#,###.00")

            binding.resultado.text = dec.format(hipoteca).toString()

            binding.progressCircular.visibility = View.GONE

 */


        }


    // TODO: Rename and change types of parameters
/*
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }




}*/