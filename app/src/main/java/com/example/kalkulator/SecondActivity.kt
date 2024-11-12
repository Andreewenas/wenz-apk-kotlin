package com.example.kalkulator

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat

class SecondActivity : AppCompatActivity() {

    private lateinit var inputDisplay: EditText
    private var currentOperator: Char? = null
    private var firstOperand: Double? = null
    private var isOperatorPressed = false // To track if an operator was pressed

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        inputDisplay = findViewById(R.id.inputDisplay)

        // Function for Enter key calculation
        inputDisplay.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                calculateResult()
                true
            } else {
                false
            }
        }

        val buttonNumbers = listOf(
            findViewById<Button>(R.id.button0),
            findViewById<Button>(R.id.button1),
            findViewById<Button>(R.id.button2),
            findViewById<Button>(R.id.button3),
            findViewById<Button>(R.id.button4),
            findViewById<Button>(R.id.button5),
            findViewById<Button>(R.id.button6),
            findViewById<Button>(R.id.button7),
            findViewById<Button>(R.id.button8),
            findViewById<Button>(R.id.button9)
        )

        val buttonAdd: Button = findViewById(R.id.buttonAdd)
        val buttonSubtract: Button = findViewById(R.id.buttonSubtract)
        val buttonMultiply: Button = findViewById(R.id.buttonMultiply)
        val buttonDivide: Button = findViewById(R.id.buttonDivide)
        val buttonEquals: Button = findViewById(R.id.buttonEquals)
        val buttonClear: Button = findViewById(R.id.buttonClear)
        val buttonDecimal: Button = findViewById(R.id.buttonDecimal)
        val buttonBack: Button = findViewById(R.id.buttonBack)

        buttonNumbers.forEach { button ->
            button.setOnClickListener { appendToDisplay(button.text.toString()) }
        }

        buttonDecimal.setOnClickListener { appendToDisplay(".") }

        // Update operator buttons to display '×' and '÷'
        buttonAdd.setOnClickListener { setOperator('+') }
        buttonSubtract.setOnClickListener { setOperator('-') }
        buttonMultiply.setOnClickListener { setOperator('×') }
        buttonDivide.setOnClickListener { setOperator('÷') }

        buttonEquals.setOnClickListener { calculateResult() }
        buttonClear.setOnClickListener { clearDisplay() }

        buttonBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun appendToDisplay(text: String) {
        // If an operator was pressed, start entering the second operand
        if (isOperatorPressed) {
            inputDisplay.append(text)
        } else {
            if (inputDisplay.text.toString() == "0") {
                inputDisplay.setText(text)
            } else {
                inputDisplay.append(text)
            }
        }
    }

    private fun setOperator(operator: Char) {
        if (!isOperatorPressed) {
            firstOperand = inputDisplay.text.toString().toDoubleOrNull()
            currentOperator = operator
            inputDisplay.append(" $operator ")
            isOperatorPressed = true
        }
    }

    private fun calculateResult() {
        val expression = inputDisplay.text.toString()
        val tokens = expression.split(" ")

        if (tokens.size >= 3) {
            firstOperand = tokens[0].toDoubleOrNull()
            currentOperator = tokens[1].singleOrNull()
            val secondOperand = tokens[2].toDoubleOrNull()

            if (firstOperand != null && secondOperand != null && currentOperator != null) {
                val result: Double = when (currentOperator) {
                    '+' -> firstOperand!! + secondOperand
                    '-' -> firstOperand!! - secondOperand
                    '×' -> firstOperand!! * secondOperand  // Update to use '×'
                    '÷' -> if (secondOperand != 0.0) firstOperand!! / secondOperand else {
                        inputDisplay.setText("Error")
                        return
                    }
                    else -> return
                }
                inputDisplay.setText(DecimalFormat("#.##").format(result))
                currentOperator = null
                firstOperand = null
                isOperatorPressed = false
            }
        }
    }

    private fun clearDisplay() {
        inputDisplay.setText("0")
        currentOperator = null
        firstOperand = null
        isOperatorPressed=false
        }
}