package com.example.francescovalela.androidtutorialwinter2018

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    // This adapter handles the digit stack so we can abstract it in this activity
    private val userInterfaceAdapter: UserInterfaceAdapter = UserInterfaceAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Define the layout of this activity (xml file)
        setContentView(R.layout.activity_main)

        // Bind functions to button presses
        initializeListeners()

        // Set the calculator to its initial cleared state
        userInterfaceAdapter.clear()
    }

    private fun initializeListeners() {
        setOperatorListeners()
        setDigitListeners()
        btn_clear.setOnClickListener { userInterfaceAdapter.clear() }
        btn_equals.setOnClickListener{ userInterfaceAdapter.equals() }
    }

    private fun setDigitListeners() {
        // add listeners to all number buttons (operands)
        btn_0.setOnClickListener{ userInterfaceAdapter.genericDigitListener(0.0) }
        btn_1.setOnClickListener{ userInterfaceAdapter.genericDigitListener(1.0) }
        btn_2.setOnClickListener{ userInterfaceAdapter.genericDigitListener(2.0) }
        btn_3.setOnClickListener{ userInterfaceAdapter.genericDigitListener(3.0) }
        btn_4.setOnClickListener{ userInterfaceAdapter.genericDigitListener(4.0) }
        btn_5.setOnClickListener{ userInterfaceAdapter.genericDigitListener(5.0) }
        btn_6.setOnClickListener{ userInterfaceAdapter.genericDigitListener(6.0) }
        btn_7.setOnClickListener{ userInterfaceAdapter.genericDigitListener(7.0) }
        btn_8.setOnClickListener{ userInterfaceAdapter.genericDigitListener(8.0) }
        btn_9.setOnClickListener{ userInterfaceAdapter.genericDigitListener(9.0) }
    }

    private fun setOperatorListeners() {
        // add listeners to all non number buttons (operators)
        btn_plus.setOnClickListener{ userInterfaceAdapter.pushOperator('+'); }
        btn_minus.setOnClickListener{ userInterfaceAdapter.pushOperator('-'); }
        btn_power.setOnClickListener{ userInterfaceAdapter.pushOperator('^'); }
        btn_multiply.setOnClickListener{ userInterfaceAdapter.pushOperator('*'); }
        btn_divide.setOnClickListener{ userInterfaceAdapter.pushOperator('/'); }
        btn_modulo.setOnClickListener{ userInterfaceAdapter.pushOperator('%'); }
        btn_undo.setOnClickListener{ userInterfaceAdapter.undo(); }
    }
}
