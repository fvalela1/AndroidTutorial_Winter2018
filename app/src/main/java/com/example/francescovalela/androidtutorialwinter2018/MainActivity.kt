package com.example.francescovalela.androidtutorialwinter2018

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    val calc = Calculator()
    val numstack: Stack<Double> = Stack()
    var lastOperator: Char? = null
    var last: Double? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeListeners()
    }

    fun initializeListeners(){
        btn_plus.setOnClickListener{ pushOperator('+'); }
        btn_minus.setOnClickListener{ pushOperator('-'); }
        btn_root.setOnClickListener{ pushOperator('√'); }
        btn_power.setOnClickListener{ pushOperator('^'); }
        btn_multiply.setOnClickListener{ pushOperator('*'); }
        btn_divide.setOnClickListener{ pushOperator('/'); }
        btn_modulo.setOnClickListener{ pushOperator('%'); }

        btn_0.setOnClickListener{ numstack.push(0.0); refreshNumView() }
        btn_1.setOnClickListener{ numstack.push(1.0); refreshNumView() }
        btn_2.setOnClickListener{ numstack.push(2.0); refreshNumView() }
        btn_3.setOnClickListener{ numstack.push(3.0); refreshNumView() }
        btn_4.setOnClickListener{ numstack.push(4.0); refreshNumView() }
        btn_5.setOnClickListener{ numstack.push(5.0); refreshNumView() }
        btn_6.setOnClickListener{ numstack.push(6.0); refreshNumView() }
        btn_7.setOnClickListener{ numstack.push(7.0); refreshNumView() }
        btn_8.setOnClickListener{ numstack.push(8.0); refreshNumView() }
        btn_9.setOnClickListener{ numstack.push(9.0); refreshNumView() }

        btn_clear.setOnClickListener{
            calc.clear()
            refreshResult(calc.recalculate())
        }

        btn_equals.setOnClickListener{
            equals()
        }
    }

    fun equals(){
        if(!numstack.empty()) {
            calc.pushOperand(getNumstackValue())
            numstack.clear()
            refreshResult(calc.recalculate())
            last = calc.recalculate()
            lastOperator = null
            refreshNumView()
        }
    }

    fun pushOperator(op: Char){
        if(lastOperator != null){
            equals()
            lastOperator = op
            refreshNumView()
        }
        if(!numstack.empty()) {
            val numstackVal = getNumstackValue()
            last = numstackVal
            lastOperator = op
            calc.pushOperand(numstackVal)
            numstack.clear()
            calc.pushOperator(op)
            refreshNumView()
        }
        else if(last != null){
            lastOperator = op
            calc.pushOperator(op)
            refreshNumView()
        }
    }

    fun getNumstackValue(): Double{
        var i = 1
        var ret = 0.0
        numstack.reversed().forEach {
            ret += it * i
            i *= 10
        }

        return ret
    }

    fun refreshNumView() {
        val curr = if (numstack.empty()) "" else getNumstackValue().toString()
        if(lastOperator != null) formula.text = """${last.toString()} $lastOperator $curr"""
        else if (last != null )formula.text = last.toString()
        else formula.text = curr
    }

    fun refreshResult(value: Double) {
        result.text = value.toString()
    }
}
