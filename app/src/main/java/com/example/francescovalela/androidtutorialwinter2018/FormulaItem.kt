package com.example.francescovalela.androidtutorialwinter2018

/*
    The formula item is a custom data-structure that resides in the calculators
    formula stack. This data type can store both operands and operators which makes it
    useful for the calculators single stack.
 */

data class FormulaItem(
        val operand: Double? = 0.0,
        val operator: Char? = ' ',
        val isOperand: Boolean = operator == ' '
)