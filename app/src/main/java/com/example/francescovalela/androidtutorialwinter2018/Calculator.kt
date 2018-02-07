package com.example.francescovalela.androidtutorialwinter2018

import java.util.Stack

class Calculator {

    val formula: Stack<FormulaItem> = Stack()

    fun pushOperand(operand: Double): Unit {
        formula.push(FormulaItem(operand = operand))
    }

    fun pushOperator(operator: Char): Unit {
        if (!formula.peek().isOperand) {
            formula.pop()
            formula.push(FormulaItem(operator = operator))
        }
    }

    fun recalculate(): Double {
        if (formula.empty())
            return 0.0
        else if (!formula.peek().isOperand)
            formula.pop()

        var total = 0.0
        var mostRecentOperator: Char? = null

        for (item in formula) {
            if (!item.isOperand)
                mostRecentOperator = item.operator
            else if (mostRecentOperator == null)
                total += item.operand!!
            else {
                when (mostRecentOperator) {
                    '+' -> return addition(total, item.operand!!)
                    '*' -> return multiply(total, item.operand!!)
                    '/' -> return divide(total, item.operand!!)
                    '-' -> return subtract(total, item.operand!!)
                    '√' -> return 0.0 //TODO does not work
                    '%' -> return mod(total, item.operand!!)
                    '^' -> return power(total, item.operand!!)
                }
            }
        }
        return total
    }


    fun undo(): Unit {
        if (formula.peek().isOperand)
            formula.pop()
        formula.pop()
    }

    fun clear(): Unit {
        formula.clear()
    }

    companion object Calculator {
        fun addition(num1: Double, num2: Double): Double = num1 + num2

        fun subtract(num1: Double, num2: Double): Double = num1 - num2

        fun multiply(num1: Double, num2: Double): Double = num1 * num2

        fun divide(num1: Double, num2: Double): Double {
            if (num2 == 0.0)
                return Double.NaN
            return num1 / num2
        }

        fun mod(num1: Double, num2: Double): Double {
            if (num2 == 0.0)
                return Double.NaN
            return num1 % num2
        }

        fun power(base: Double, exponent: Double): Double{
            if(exponent <= 0.0) return 1.0
            if(exponent == 1.0) return base
            if(exponent == 2.0) return (base * base)

            return if (base % 2.0 == 0.0)
                power(power(base, exponent/2),2.0)
            else
                base*power(base, exponent - 1)

        }
        fun sqrt(num1: Double): Double = num1 * (1/num1)
    }
}
