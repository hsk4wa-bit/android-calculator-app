package com.example.calculator

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import java.text.DecimalFormat
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    
    // UI Components
    private lateinit var display: TextView // maps to displayBig
    
    // Calculator Logic Variables
    private var currentInput = ""
    private var operator = ""
    private var firstOperand = 0.0
    private var waitingForOperand = false
    private var shouldResetDisplay = false
    
    // Memory Function
    private var memoryValue = 0.0
    
    // History (no list UI; kept for future CHECK navigation)
    private val calculationHistory = mutableListOf<String>()
    
    // Theme
    private lateinit var sharedPreferences: SharedPreferences
    private var isDarkTheme = false
    
    override fun onCreate(savedInstanceState: Bundle?) {
        // Initialize theme before super.onCreate()
        sharedPreferences = getSharedPreferences("calculator_prefs", Context.MODE_PRIVATE)
        isDarkTheme = sharedPreferences.getBoolean("dark_theme", false)
        
        if (isDarkTheme) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
        
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeViews()
        setupClickListeners()
        updateMemoryIndicator()
    }
    
    private fun initializeViews() {
        display = findViewById(R.id.displayBig)
        display.text = "0"
        // displaySmall exists but not used by logic yet
    }
    
    private fun setupClickListeners() {
        // Number buttons (including 00 and dot)
        val digitIds = listOf(
            R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
            R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9
        )
        digitIds.forEach { id ->
            findViewById<Button>(id).setOnClickListener { v ->
                onNumberClick((v as Button).text.toString())
            }
        }
        findViewById<Button>(R.id.btn00).setOnClickListener { onNumberClick("00") }
        findViewById<Button>(R.id.btnDot).setOnClickListener { onDecimalClick() }
        
        // Operator buttons
        findViewById<Button>(R.id.btnPlus).setOnClickListener { onOperatorClick("+") }
        findViewById<Button>(R.id.btnMultiply).setOnClickListener { onOperatorClick("×") }
        findViewById<Button>(R.id.btnDivide).setOnClickListener { onOperatorClick("÷") }
        
        // Function buttons
        findViewById<Button>(R.id.btnEquals).setOnClickListener { onEqualsClick() }
        findViewById<Button>(R.id.btnAC).setOnClickListener { onClearClick() }
        
        // Utility / scientific buttons
        findViewById<Button>(R.id.btnPlusMinus).setOnClickListener { onToggleSign() }
        findViewById<Button>(R.id.btnSqrt).setOnClickListener { onSqrt() }
        findViewById<Button>(R.id.btnPercent).setOnClickListener { onPercent() }
        findViewById<Button>(R.id.btnGstPlus).setOnClickListener { onGst(+18) } // TODO: configurable rate
        findViewById<Button>(R.id.btnGstMinus).setOnClickListener { onGst(-18) }
        findViewById<Button>(R.id.btnMenu).setOnClickListener { onMenu() }

        // History navigation / utils
        findViewById<Button>(R.id.btnCheckPrev).setOnClickListener { onHistoryPrev() }
        findViewById<Button>(R.id.btnCheckNext).setOnClickListener { onHistoryNext() }
        findViewById<Button>(R.id.btnCorrect).setOnClickListener { onCorrectDoubleZero() }
        findViewById<Button>(R.id.btnAutoReplay).setOnClickListener { onAutoReplay() }

        // Memory buttons mapped to existing logic when possible
        findViewById<Button>(R.id.btnMPlus).setOnClickListener { onMemoryAdd() }
        findViewById<Button>(R.id.btnMMinus).setOnClickListener { onMemorySubtract() }
        findViewById<Button>(R.id.btnMRC).setOnClickListener { onMRC() }
        findViewById<Button>(R.id.btnGT).setOnClickListener { onGT() }
        findViewById<Button>(R.id.btnMU).setOnClickListener { onMU() }
    }
    
    private fun onNumberClick(number: String) {
        if (shouldResetDisplay) {
            currentInput = ""
            shouldResetDisplay = false
        }
        
        if (waitingForOperand) {
            currentInput = ""
            waitingForOperand = false
        }
        
        currentInput += number
        updateDisplay(currentInput)
    }
    
    private fun onOperatorClick(newOperator: String) {
        val inputValue = currentInput.toDoubleOrNull() ?: 0.0
        
        if (!waitingForOperand && operator.isNotEmpty()) {
            val result = performCalculation()
            updateDisplay(formatNumber(result))
            firstOperand = result
        } else {
            firstOperand = inputValue
        }
        
        operator = newOperator
        waitingForOperand = true
    }
    
    private fun onEqualsClick() {
        if (operator.isNotEmpty() && !waitingForOperand) {
            val result = performCalculation()
            val calculation = "$firstOperand $operator ${currentInput.toDoubleOrNull() ?: 0.0} = $result"
            addToHistory(calculation)
            
            updateDisplay(formatNumber(result))
            firstOperand = result
            operator = ""
            shouldResetDisplay = true
        }
    }
    
    private fun performCalculation(): Double {
        val secondOperand = currentInput.toDoubleOrNull() ?: 0.0
        
        return when (operator) {
            "+" -> firstOperand + secondOperand
            "-" -> firstOperand - secondOperand
            "×" -> firstOperand * secondOperand
            "÷" -> {
                if (secondOperand != 0.0) {
                    firstOperand / secondOperand
                } else {
                    Double.POSITIVE_INFINITY // Handle division by zero
                }
            }
            else -> secondOperand
        }
    }
    
    private fun onClearClick() {
        currentInput = ""
        operator = ""
        firstOperand = 0.0
        waitingForOperand = false
        shouldResetDisplay = false
        updateDisplay("0")
    }
    
    private fun onDecimalClick() {
        if (shouldResetDisplay) {
            currentInput = "0"
            shouldResetDisplay = false
        }
        
        if (waitingForOperand) {
            currentInput = "0"
            waitingForOperand = false
        }
        
        if (!currentInput.contains(".")) {
            currentInput += if (currentInput.isEmpty()) "0." else "."
            updateDisplay(currentInput)
        }
    }
    
    // Memory Functions
    private fun onMemoryAdd() {
        val currentValue = display.text.toString().toDoubleOrNull() ?: 0.0
        memoryValue += currentValue
        updateMemoryIndicator()
    }
    
    private fun onMemorySubtract() {
        val currentValue = display.text.toString().toDoubleOrNull() ?: 0.0
        memoryValue -= currentValue
        updateMemoryIndicator()
    }
    
    private fun onMemoryRecall() {
        updateDisplay(formatNumber(memoryValue))
        currentInput = memoryValue.toString()
        shouldResetDisplay = true
    }
    
    private fun onMemoryClear() {
        memoryValue = 0.0
        updateMemoryIndicator()
    }

    private fun updateMemoryIndicator() {
        // No memory UI badge in this layout; keep for future
    }
    
    private fun updateDisplay(value: String) {
        display.text = value
    }
    
    private fun formatNumber(number: Double): String {
        return if (number == number.toLong().toDouble()) {
            number.toLong().toString()
        } else {
            DecimalFormat("#.##########").format(number)
        }
    }
    
    private fun addToHistory(calculation: String) {
        calculationHistory.add(0, calculation)
        if (calculationHistory.size > 50) { // Limit history to 50 items
            calculationHistory.removeAt(calculationHistory.size - 1)
        }
    }

    // --- New UI action stubs ---
    private fun onToggleSign() {
        // TODO: toggle sign of current input
        currentInput = if (currentInput.startsWith("-")) currentInput.removePrefix("-")
        else if (currentInput.isNotEmpty()) "-$currentInput" else currentInput
        updateDisplay(if (currentInput.isEmpty()) "0" else currentInput)
    }

    private fun onSqrt() {
        // TODO: improve with proper error handling
        val value = currentInput.toDoubleOrNull() ?: 0.0
        val result = kotlin.math.sqrt(value)
        updateDisplay(formatNumber(result))
        currentInput = result.toString()
        shouldResetDisplay = true
    }

    private fun onPercent() {
        // TODO: percentage relative to firstOperand/operator if needed
        val value = currentInput.toDoubleOrNull() ?: 0.0
        val result = value / 100.0
        updateDisplay(formatNumber(result))
        currentInput = result.toString()
        shouldResetDisplay = true
    }

    private fun onGst(ratePercent: Int) {
        // Simple placeholder: apply +/- percentage to current input
        val value = currentInput.toDoubleOrNull() ?: 0.0
        val result = value * (1 + ratePercent / 100.0)
        updateDisplay(formatNumber(result))
        currentInput = result.toString()
        shouldResetDisplay = true
    }

    private fun onHistoryPrev() {
        // TODO: navigate history backward
    }

    private fun onHistoryNext() {
        // TODO: navigate history forward
    }

    private fun onCorrectDoubleZero() {
        val s = currentInput.ifEmpty { display.text.toString() }
        val newS = if (s.endsWith("00")) s.dropLast(1) else s
        currentInput = newS
        updateDisplay(if (newS.isEmpty()) "0" else newS)
    }

    private fun onAutoReplay() {
        // TODO: repeat last operation
    }

    private fun onMU() {
        // TODO: implement Mark Up logic
    }

    private fun onGT() {
        // TODO: implement Grand Total logic
    }

    private fun onMRC() {
        // TODO: typical behavior: first press recall, second press clear
        onMemoryRecall()
    }

    private fun onMenu() {
        // TODO: open settings/menu
    }
}