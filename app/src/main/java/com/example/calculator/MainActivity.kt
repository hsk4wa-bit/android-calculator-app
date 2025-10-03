package com.example.calculator

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.DecimalFormat
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    
    // UI Components
    private lateinit var display: TextView
    private lateinit var historyRecyclerView: RecyclerView
    private lateinit var historyAdapter: HistoryAdapter
    private lateinit var themeToggleButton: Button
    private lateinit var historyToggleButton: Button
    private lateinit var memoryIndicator: TextView
    
    // Calculator Logic Variables
    private var currentInput = ""
    private var operator = ""
    private var firstOperand = 0.0
    private var waitingForOperand = false
    private var shouldResetDisplay = false
    
    // Memory Function
    private var memoryValue = 0.0
    
    // History
    private val calculationHistory = mutableListOf<String>()
    private var isHistoryVisible = false
    
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
        setupHistoryRecyclerView()
        setupClickListeners()
        updateMemoryIndicator()
    }
    
    private fun initializeViews() {
        display = findViewById(R.id.display)
        historyRecyclerView = findViewById(R.id.historyRecyclerView)
        themeToggleButton = findViewById(R.id.btnTheme)
        historyToggleButton = findViewById(R.id.btnHistory)
        memoryIndicator = findViewById(R.id.memoryIndicator)
        
        display.text = "0"
    }
    
    private fun setupHistoryRecyclerView() {
        historyAdapter = HistoryAdapter(calculationHistory)
        historyRecyclerView.layoutManager = LinearLayoutManager(this)
        historyRecyclerView.adapter = historyAdapter
        historyRecyclerView.visibility = View.GONE
    }
    
    private fun setupClickListeners() {
        // Number buttons
        val numberButtons = listOf(
            R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
            R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9
        )
        
        numberButtons.forEachIndexed { index, buttonId ->
            findViewById<Button>(buttonId).setOnClickListener {
                onNumberClick(index.toString())
            }
        }
        
        // Operator buttons
        findViewById<Button>(R.id.btnAdd).setOnClickListener { onOperatorClick("+") }
        findViewById<Button>(R.id.btnSubtract).setOnClickListener { onOperatorClick("-") }
        findViewById<Button>(R.id.btnMultiply).setOnClickListener { onOperatorClick("×") }
        findViewById<Button>(R.id.btnDivide).setOnClickListener { onOperatorClick("÷") }
        
        // Function buttons
        findViewById<Button>(R.id.btnEquals).setOnClickListener { onEqualsClick() }
        findViewById<Button>(R.id.btnClear).setOnClickListener { onClearClick() }
        findViewById<Button>(R.id.btnDelete).setOnClickListener { onDeleteClick() }
        findViewById<Button>(R.id.btnDecimal).setOnClickListener { onDecimalClick() }
        
        // Memory buttons
        findViewById<Button>(R.id.btnMemoryAdd).setOnClickListener { onMemoryAdd() }
        findViewById<Button>(R.id.btnMemorySubtract).setOnClickListener { onMemorySubtract() }
        findViewById<Button>(R.id.btnMemoryRecall).setOnClickListener { onMemoryRecall() }
        findViewById<Button>(R.id.btnMemoryClear).setOnClickListener { onMemoryClear() }
        
        // Theme and History buttons
        themeToggleButton.setOnClickListener { toggleTheme() }
        historyToggleButton.setOnClickListener { toggleHistory() }
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
    
    private fun onDeleteClick() {
        if (currentInput.isNotEmpty()) {
            currentInput = currentInput.dropLast(1)
            updateDisplay(if (currentInput.isEmpty()) "0" else currentInput)
        }
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
        memoryIndicator.visibility = if (memoryValue != 0.0) View.VISIBLE else View.GONE
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
        historyAdapter.notifyDataSetChanged()
    }
    
    private fun toggleHistory() {
        isHistoryVisible = !isHistoryVisible
        historyRecyclerView.visibility = if (isHistoryVisible) View.VISIBLE else View.GONE
        historyToggleButton.text = if (isHistoryVisible) "Hide History" else "Show History"
    }
    
    private fun toggleTheme() {
        isDarkTheme = !isDarkTheme
        sharedPreferences.edit().putBoolean("dark_theme", isDarkTheme).apply()
        
        if (isDarkTheme) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
        
        // Recreate activity to apply theme
        recreate()
    }
}