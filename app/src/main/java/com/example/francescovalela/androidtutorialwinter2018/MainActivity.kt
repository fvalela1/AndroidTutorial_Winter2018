package com.example.francescovalela.androidtutorialwinter2018

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private val calc = Calculator()
    private val digitStack: Stack<Double> = Stack()
    private var lastOperator: Char? = null
    private var last: Double? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeListeners()
    }

    fun initializeListeners() {
        setOperatorListeners()
        setDigitListeners()

        btn_clear.setOnClickListener {
            calc.clear()
            digitStack.clear()
            last = null
            lastOperator = null
            refreshFormulaView()
            refreshResultView(calc.recalculate())
        }

        btn_equals.setOnClickListener{
            equalsListener()
        }
    }

    private fun equalsListener() {
        if(!digitStack.empty()) {
            calc.pushOperand(getDigitValueFromStack())
            digitStack.clear()
            refreshResultView(calc.recalculate())
            last = calc.recalculate()
            lastOperator = null
            refreshFormulaView()
        }
    }

    private fun pushOperator(op: Char) {
        if (!digitStack.empty()) {
            val completeDigitValue = getDigitValueFromStack()
            last = completeDigitValue
            lastOperator = op
            calc.pushOperand(completeDigitValue)
            calc.pushOperator(op)
            digitStack.clear()
        } else if (last != null) {
            calc.pushOperator(op)
        }
        lastOperator = op
        refreshFormulaView()
    }

    private fun getDigitValueFromStack(): Double{
        var ret = 0.0
        digitStack.reversed().forEachIndexed { i, d ->
            ret += d * Math.pow(10.0, (i).toDouble())
        }
        return ret
    }

    private fun refreshFormulaView() {
        val completeDigitalValue = getDigitValueFromStack()
        val curr: String = if (digitStack.empty()) "" else {
            if (completeDigitalValue - getDigitValueFromStack().toLong() == 0.0)
                completeDigitalValue.toLong().toString() else completeDigitalValue.toString()
        }

        when {
            lastOperator != null && last != null -> {
                val template = resources.getString(R.string.formula)
                val tmp: Double = last ?: 0.0
                val previous = if (tmp - tmp.toLong() == 0.0)
                    tmp.toLong().toString() else tmp.toString()
                formula.text = String.format(template, previous, lastOperator, curr)
            }
            last != null -> formula.text = last.toString()
            else -> formula.text = curr
        }
    }

    private fun refreshResultView(value: Double) {
        result.text = if (value - value.toLong() == 0.0)
            value.toLong().toString() else value.toString()
    }

    private fun genericDigitListener(digit: Double){
        digitStack.push(digit);
        refreshFormulaView()
    }

    private fun setDigitListeners() {
        btn_0.setOnClickListener{ genericDigitListener(0.0) }
        btn_1.setOnClickListener{ genericDigitListener(1.0) }
        btn_2.setOnClickListener{ genericDigitListener(2.0) }
        btn_3.setOnClickListener{ genericDigitListener(3.0) }
        btn_4.setOnClickListener{ genericDigitListener(4.0) }
        btn_5.setOnClickListener{ genericDigitListener(5.0) }
        btn_6.setOnClickListener{ genericDigitListener(6.0) }
        btn_7.setOnClickListener{ genericDigitListener(7.0) }
        btn_8.setOnClickListener{ genericDigitListener(8.0) }
        btn_9.setOnClickListener{ genericDigitListener(9.0) }
    }

    private fun setOperatorListeners() {
        btn_plus.setOnClickListener{ pushOperator('+'); }
        btn_minus.setOnClickListener{ pushOperator('-'); }
        btn_root.setOnClickListener{ pushOperator('√'); }
        btn_power.setOnClickListener{ pushOperator('^'); }
        btn_multiply.setOnClickListener{ pushOperator('*'); }
        btn_divide.setOnClickListener{ pushOperator('/'); }
        btn_modulo.setOnClickListener{ pushOperator('%'); }
    }
}
