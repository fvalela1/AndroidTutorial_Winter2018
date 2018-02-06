package com.example.francescovalela.androidtutorialwinter2018

import java.util.*

class Calculator {

    val formula: Stack<FormulaItem> = Stack()

    fun pushOperand(operand: Double): Unit {
        if (formula.isEmpty())
            return
        else if (formula.peek().isOperand)
            formula.pop()

        formula.push(FormulaItem(operand))
    }

    fun pushOperator(operator: Char): Unit {
        if (formula.isEmpty())
            return
        else if (formula.peek().isOperand)
            formula.pop()

        formula.push(FormulaItem(operator = operator))
    }

    fun recalculate(): Double {
        if (formula.empty())
            return 0.0
        else if (formula.peek().isOperand)
            formula.pop()

        var total = 0.0
        var mostRecentOperator: Char? = null

        //TODO fix this up
        for (item in formula) {
            if (mostRecentOperator == null)
                total += item.operand!!
            else if (!item.isOperand)
                mostRecentOperator = item.operator
            else {
                when (mostRecentOperator) {
                    '+' -> addition(total, item.operand!!)
                    'x' -> multiply(total, item.operand!!)
                    '/' -> divide(total, item.operand!!)
                    '-' -> subtract(total, item.operand!!)
                    '√' -> return 0.0 //TODO does not work
                    '%' -> mod(total, item.operand!!)
                }
                mostRecentOperator = null
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
        fun addition(num1: Double = 0.0, num2: Double): Double = num1 + num2

        fun subtract(num1: Double = 0.0, num2: Double): Double = num1 - num2

        fun multiply(num1: Double = 0.0, num2: Double): Double = num1 * num2

        fun divide(num1: Double = 0.0, num2: Double): Double = num1 / num2

        fun mod(num1: Double = 0.0, num2: Double): Double = num1 % num2

        fun sqrt(num1: Double = 0.0): Double = num1 * (1/num1)
    }
}
