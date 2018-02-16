package com.example.francescovalela.androidtutorialwinter2018;

import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class UserInterfaceAdapter(val activity : MainActivity) {

    private val calc = Calculator()
    private val digitStack: Stack<Double> = Stack()
    private var lastOperator: Char? = null
    private var lastOperand: Double? = null

    // Clear the calculator stack and the local digit stack.
    fun clear() {
        calc.clear()
        digitStack.clear()
        lastOperand = null
        lastOperator = null
        refreshFormulaView()
        refreshResultView(calc.recalculate())
    }

    // Push the digit stack to the calculator, recalculate the result and display it.
    fun equals() {
        if(!digitStack.empty()) {
            calc.pushOperand(getDigitValueFromStack())
            digitStack.clear()
            lastOperand = calc.recalculate()
            refreshResultView(lastOperand ?: 0.0)
            lastOperator = null
            refreshFormulaView()
        }
    }

    // Undo the last operation and refresh the views to reflect the change.
    fun undo() {
        if (digitStack.empty()) {
            calc.undo()
            lastOperator = null
            lastOperand = calc.recalculate()
            refreshFormulaView()
            refreshResultView(lastOperand ?: 0.0)
        } else {
            digitStack.clear()
            refreshFormulaView()
        }
    }

    fun pushOperator(op: Char) {
        if (!digitStack.empty()) {
            val completeDigitValue = getDigitValueFromStack()
            calc.pushOperand(completeDigitValue)
            lastOperand = calc.recalculate()
            lastOperator = op
            calc.pushOperator(op)
            digitStack.clear()
            refreshResultView(lastOperand ?: 0.0)
        } else if (lastOperand != null) {
            calc.pushOperator(op)
        }
        lastOperator = op
        refreshFormulaView()
    }

    // Create a double value from single digit elements on our local digit stack.
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
            lastOperator != null && lastOperand != null -> {
                val template = activity.resources.getString(R.string.formula)
                val tmp: Double = lastOperand ?: 0.0
                val previous = if (tmp - tmp.toLong() == 0.0)
                    tmp.toLong().toString() else tmp.toString()
                activity.formula.text = String.format(template, previous, lastOperator, curr)
            }
            lastOperand != null -> {
                val tmp: Double = lastOperand ?: 0.0
                val cleanLast: String = if (tmp - tmp.toLong() == 0.0)
                    tmp.toLong().toString() else tmp.toString()
                activity.formula.text = cleanLast
            }
            else -> activity.formula.text = curr
        }
    }

    private fun refreshResultView(value: Double) {
        activity.result.text = if (value - value.toLong() == 0.0)
            value.toLong().toString() else value.toString()
    }

    fun genericDigitListener(digit: Double){
        if(lastOperator == null) {
            calc.clear()
            lastOperand = null
        }
        digitStack.push(digit)
        refreshFormulaView()
    }

}
