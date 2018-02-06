package com.example.francescovalela.androidtutorialwinter2018

class FormulaItem(inOperand: Double?, inOperator: Char? = null) {

    var isOperand: Boolean
    val operand: Double = inOperand ?: 0.0
    val operator: Char = inOperator ?: ' '

    init {
        isOperand = inOperator == null
    }

}