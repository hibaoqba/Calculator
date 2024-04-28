package com.example.finalcalc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var screen: TextView
    private var operand1 = ""
    private var operand2 = ""
    private var operator = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views
        screen = findViewById(R.id.screen)

        // Set OnClickListener for buttons
        findViewById<Button>(R.id.ac).setOnClickListener(this)
        findViewById<Button>(R.id.one).setOnClickListener(this)
        findViewById<Button>(R.id.two).setOnClickListener(this)
        findViewById<Button>(R.id.three).setOnClickListener(this)
        findViewById<Button>(R.id.four).setOnClickListener(this)
        findViewById<Button>(R.id.five).setOnClickListener(this)
        findViewById<Button>(R.id.six).setOnClickListener(this)
        findViewById<Button>(R.id.seven).setOnClickListener(this)
        findViewById<Button>(R.id.eight).setOnClickListener(this)
        findViewById<Button>(R.id.nine).setOnClickListener(this)
        findViewById<Button>(R.id.zero).setOnClickListener(this)
        findViewById<Button>(R.id.point).setOnClickListener(this)
        findViewById<Button>(R.id.plus).setOnClickListener(this)
        findViewById<Button>(R.id.moins).setOnClickListener(this)
        findViewById<Button>(R.id.multiplication).setOnClickListener(this)
        findViewById<Button>(R.id.division).setOnClickListener(this)
        findViewById<Button>(R.id.equal).setOnClickListener(this)
        findViewById<Button>(R.id.delete).setOnClickListener(this)
        findViewById<Button>(R.id.on).setOnClickListener(this)
        findViewById<Button>(R.id.off).setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.ac -> {
                operand1 = ""
                operand2 = ""
                operator = ""
                screen.text = "0"
            }
            R.id.one, R.id.two, R.id.three, R.id.four, R.id.five,
            R.id.six, R.id.seven, R.id.eight, R.id.nine -> {
                // Remove leading zero if the current screen text is "0"
                if (screen.text.toString() == "0") {
                    screen.text = ""
                }
                screen.append((v as Button).text)
            }
            R.id.zero -> {
                if (screen.text.toString() == "0") {
                    return
                }
                screen.append((v as Button).text)
            }
            R.id.point -> {
                if (screen.text.toString() == "0") {
                    screen.text = "0."
                }
                if (!screen.text.contains(".")) {
                    screen.append((v as Button).text)
                }
            }
            R.id.plus, R.id.moins, R.id.multiplication, R.id.division -> {
                operator = (v as Button).text.toString()
                operand1 = screen.text.toString()
                screen.text = "0"
            }
            R.id.equal -> {
                operand2 = screen.text.toString()
                val result = calculateResult(operand1.toDouble(), operand2.toDouble(), operator)
                screen.text = result.toString()
            }
            R.id.delete -> {
                val currentText = screen.text.toString()
                if (currentText.isNotEmpty()) {
                    screen.text = currentText.dropLast(1)
                    if (screen.text.isEmpty()) {
                        screen.text = "0"
                    }
                }
            }
        }
    }

    private fun calculateResult(operand1: Double, operand2: Double, operator: String): Double {
        return when (operator) {
            "+" -> operand1 + operand2
            "-" -> operand1 - operand2
            "x" -> operand1 * operand2
            "/" -> operand1 / operand2
            else -> throw IllegalArgumentException("Invalid operator")
        }
    }
}
