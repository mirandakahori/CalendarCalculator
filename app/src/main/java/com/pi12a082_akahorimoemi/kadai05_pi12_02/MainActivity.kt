package com.pi12a082_akahorimoemi.kadai05_pi12_02

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import net.objecthunter.exp4j.ExpressionBuilder
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var display: TextView
    private lateinit var modeIndicator: TextView
    private var isDateMode = false
    private val dateFormat = SimpleDateFormat("yyyyMMdd", Locale.JAPAN)
    private val generalHistory = mutableListOf<String>()
    private val dateHistory = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        display = findViewById(R.id.display)
        modeIndicator = findViewById(R.id.modeIndicator)
        setupButtons()
        updateModeIndicator() // 初期状態のモードを表示
    }

    private fun setupButtons() {
        val buttons = listOf(
            R.id.btn0 to "0", R.id.btn1 to "1", R.id.btn2 to "2",
            R.id.btn3 to "3", R.id.btn4 to "4", R.id.btn5 to "5",
            R.id.btn6 to "6", R.id.btn7 to "7", R.id.btn8 to "8",
            R.id.btn9 to "9", R.id.btnPlus to "+", R.id.btnMinus to "-",
            R.id.btnMultiply to "*", R.id.btnDivide to "/", R.id.btnDot to "."
        )
        buttons.forEach { (id, value) ->
            findViewById<Button>(id).setOnClickListener { onInput(value) }
        }
        findViewById<Button>(R.id.btnEquals).setOnClickListener { calculateResult() }
        findViewById<Button>(R.id.btnAC).setOnClickListener { clearAll() }
        findViewById<Button>(R.id.btnC).setOnClickListener { deleteLast() }
        findViewById<Button>(R.id.btnAt).setOnClickListener { toggleDateMode() }
        findViewById<Button>(R.id.btnShowGeneralHistory).setOnClickListener { showGeneralHistory() }
        findViewById<Button>(R.id.btnShowDateHistory).setOnClickListener { showDateHistory() }
    }

    private fun onInput(value: String) {
        if (isDateMode && value == ".") {
            // 日付モードでは小数点を無効化
            return
        }

        // オペレーター後に小数点が入力可能にするロジック
        if (value == ".") {
            // 直前のオペレーター位置を探す
            val lastOperatorIndex = currentInput.lastIndexOfAny(charArrayOf('+', '-', '*', '/'))
            val lastInputPart = if (lastOperatorIndex != -1) {
                currentInput.substring(lastOperatorIndex + 1)
            } else {
                currentInput
            }

            // 直前のオペレーター後にすでに小数点が含まれている場合は無視
            if (lastInputPart.contains(".")) {
                return
            }
        }

        // 入力に新しい値を追加
        currentInput += value
        updateDisplay()
    }



    private fun updateDisplay() {
        val displayValue = currentInput
            .replace("+", " + ")
            .replace("-", " - ")
            .replace("*", " × ")
            .replace("/", " ÷ ")

        display.text = if (isDateMode) {
            "@$displayValue"
        } else {
            formatNumber(displayValue)
        }
    }


    private fun clearAll() {
        currentInput = ""
        display.text = "0"
    }

    private fun deleteLast() {
        if (currentInput.isNotEmpty()) {
            currentInput = currentInput.dropLast(1)
        }

        // 入力が空になったら "0" を表示
        if (currentInput.isEmpty()) {
            display.text = "0"
        } else {
            updateDisplay()
        }
    }


    // モードを切り替える関数
    private fun toggleDateMode() {
        isDateMode = !isDateMode
        currentInput = ""
        updateDisplay()

        if (!isDateMode){
            display.text = "0"
        }

        updateModeIndicator() // モード表示を更新

    }

    // モード表示を更新する関数
    private fun updateModeIndicator() {
        modeIndicator.text = if (isDateMode) {
            getString(R.string.mode_date) // 日付モードに切り替え
        } else {
            getString(R.string.mode_general) // 一般モードに戻す
        }
    }


    // 曜日を文字列に変換するメソッド
    private fun Date.dayOfWeek(): String {
        val calendar = Calendar.getInstance().apply { time = this@dayOfWeek }
        return when (calendar.get(Calendar.DAY_OF_WEEK)) {
            Calendar.SUNDAY -> "日"
            Calendar.MONDAY -> "月"
            Calendar.TUESDAY -> "火"
            Calendar.WEDNESDAY -> "水"
            Calendar.THURSDAY -> "木"
            Calendar.FRIDAY -> "金"
            Calendar.SATURDAY -> "土"
            else -> ""
        }
    }

    private fun calculateDateResult(input: String): String {
        val today = Date()
        val todayFormatted = dateFormat.format(today)
        val todayDayOfWeek = today.dayOfWeek() // 今日の曜日を取得
        return if (input.startsWith("-") || input.startsWith("+")) {
            val days = input.toInt()
            val targetDate = Calendar.getInstance().apply { add(Calendar.DATE, days) }.time
            val targetFormatted = dateFormat.format(targetDate)
            val targetDayOfWeek = targetDate.dayOfWeek() // 計算結果の曜日を取得
            "$todayFormatted.$todayDayOfWeek/$targetFormatted.$targetDayOfWeek"
        } else {
            val inputDate = dateFormat.parse(input) ?: return "Invalid date"
            val diffInMillis = inputDate.time - today.time
            val diffInDays = (diffInMillis / (1000 * 60 * 60 * 24)).toInt()
            if (diffInDays < 0) {
                val daysAgo = -diffInDays
                val inputDayOfWeek = inputDate.dayOfWeek() // 入力日付の曜日を取得
                "$todayFormatted.$todayDayOfWeek/－${daysAgo}.$inputDayOfWeek"
            } else {
                val daysLater = diffInDays
                val inputDayOfWeek = inputDate.dayOfWeek() // 入力日付の曜日を取得
                "$todayFormatted.$todayDayOfWeek/＋${daysLater}.$inputDayOfWeek"
            }
        }
    }


    private fun addToGeneralHistory(input: String, result: String) {
        val formattedInput = input
            .replace("+", " + ")
            .replace("-", " - ")
            .replace("*", " × ")
            .replace("/", " ÷ ")

        // 小数点の結果でもカンマをつけない
        val formattedResult = result

        generalHistory.add("・$formattedInput = $formattedResult") // 履歴に追加
    }



    private fun addToDateHistory(input: String, result: String) {
        // 改行を入れる
        dateHistory.add("・$input\n  = $result")
    }

    private var currentInput = "" // ユーザーが入力した値を保持
    private var currentResult: Double = 0.0 // 計算結果を数値で保持

    private fun calculateResult() {
        try {
            if (currentInput.isEmpty()) {
                display.text = "Error: Empty input"
                return
            }

            // 小数点が2つ以上入っているか、正しく計算できる形式か確認
            if (currentInput.contains(Regex("\\.{2,}"))) {
                display.text = "Error: Invalid input"
                return
            }

            val result = if (isDateMode) {
                calculateDateResult(currentInput)
            } else {
                calculateMathResult(currentInput)
            }

            displayResult(result)
            currentResult = result.toDoubleOrNull() ?: 0.0
            val previousInput = currentInput
            currentInput = currentResult.toString()

            // 計算履歴に追加
            if (!isDateMode) {
                addToGeneralHistory(previousInput, result)
            } else {
                addToDateHistory(previousInput, result)
            }

        } catch (e: Exception) {
            val errorMessage = e.message ?: "Unknown error"
            display.text = if (errorMessage.contains("For input string")) {
                currentInput // エラーが発生した入力を表示
            } else {
                "Error: $errorMessage"
            }
        }
    }

    private fun calculateMathResult(input: String): String {
        return try {
            // 表示用の記号（×、÷）を演算用の記号（*、/）に戻す
            val formattedInput = input
                .replace("×", "*")
                .replace("÷", "/")

            // ExpressionBuilderで計算
            val expression = ExpressionBuilder(formattedInput).build()
            val result = expression.evaluate()

            // 整数か小数かを確認してフォーマット
            if (result == result.toLong().toDouble()) {
                result.toLong().toString()
            } else {
                String.format("%.3f", result).trimEnd('0').trimEnd('.')
            }
        } catch (e: Exception) {
            throw Exception("Invalid expression: ${e.message}")
        }
    }

    private fun displayResult(result: String) {
        display.text = result // 計算結果を表示
    }



    private fun formatNumber(input: String): String {
        // フォーマット処理が必要ならここで行う。不要ならそのまま入力を返す
        return input
    }

    // 一般計算履歴を表示するモーダル
    private fun showGeneralHistory() {
        val dialog = Dialog(this)
        val dialogView = LayoutInflater.from(this).inflate(R.layout.general_history_modal, null)
        // 閉じるボタンの設定
        val btnCloseGeneralModal = dialogView.findViewById<ImageButton>(R.id.btnCloseGeneralModal)
        btnCloseGeneralModal.setOnClickListener { dialog.dismiss() }
        // 履歴表示部分の設定
        val generalHistoryContainer = dialogView.findViewById<LinearLayout>(R.id.generalHistoryContainer)
        generalHistory.forEach { entry ->
            val historyItem = TextView(this)
            historyItem.text = entry
            historyItem.textSize = 18f
            generalHistoryContainer.addView(historyItem)
        }
        // 履歴クリアボタンの設定
        val btnClearGeneralHistory = dialogView.findViewById<Button>(R.id.btnClearGeneralHistory)
        btnClearGeneralHistory.setOnClickListener {
            generalHistory.clear()
            generalHistoryContainer.removeAllViews()
        }
        dialog.setContentView(dialogView)
        // サイズを固定
        val window = dialog.window
        window?.setLayout(
            (resources.displayMetrics.widthPixels * 0.9).toInt(),
            (resources.displayMetrics.heightPixels * 0.65).toInt()
        )
        dialog.show()
    }

    // 日付計算履歴を表示するモーダル
    private fun showDateHistory() {
        val dialog = Dialog(this)
        val dialogView = LayoutInflater.from(this).inflate(R.layout.date_history_modal, null)
        // 閉じるボタンの設定
        val btnCloseDateModal = dialogView.findViewById<ImageButton>(R.id.btnCloseDateModal)
        btnCloseDateModal.setOnClickListener { dialog.dismiss() }
        // 履歴表示部分の設定
        val dateHistoryContainer = dialogView.findViewById<LinearLayout>(R.id.dateHistoryContainer)
        dateHistory.forEach{ entry ->
            val historyItem = TextView(this)
            historyItem.text = entry
            historyItem.textSize = 18f
            dateHistoryContainer.addView(historyItem)
        }
        // 履歴クリアボタンの設定
        val btnClearDateHistory = dialogView.findViewById<Button>(R.id.btnClearDateHistory)
        btnClearDateHistory.setOnClickListener {
            dateHistory.clear()
            dateHistoryContainer.removeAllViews()
        }
        dialog.setContentView(dialogView)
        // サイズを固定
        val window = dialog.window
        window?.setLayout(
            (resources.displayMetrics.widthPixels * 0.9).toInt(),
            (resources.displayMetrics.heightPixels * 0.65).toInt()
        )
        dialog.show()
    }
}


