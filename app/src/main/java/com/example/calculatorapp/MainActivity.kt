package com.example.calculatorapp

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.text.isDigitsOnly

class MainActivity : AppCompatActivity() {

    lateinit var Button9: Button
    lateinit var Button8: Button
    lateinit var Button7: Button
    lateinit var Button6: Button
    lateinit var Button5: Button
    lateinit var Button4: Button
    lateinit var Button3: Button
    lateinit var Button2: Button
    lateinit var Button1: Button
    lateinit var Button0: Button
    lateinit var ButtonDiv: Button
    lateinit var ButtonMultiply: Button
    lateinit var ButtonSub: Button
    lateinit var ButtonNegativeNum: Button
    lateinit var ButtonDot: Button
    lateinit var ButtonAdd: Button
    lateinit var ButtonDEL: Button
    lateinit var ButtonClear: Button
    lateinit var ButtonEqual: Button
    lateinit var ResulttextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ResulttextView = findViewById(R.id.textViewResult)

        Button9 = findViewById(R.id.BTN9)
        Button8 = findViewById(R.id.BTN8)
        Button7 = findViewById(R.id.BTN7)
        Button6 = findViewById(R.id.BTN6)
        Button5 = findViewById(R.id.BTN5)
        Button4 = findViewById(R.id.BTN4)
        Button2 = findViewById(R.id.BTN2)
        Button3 = findViewById(R.id.BTN3)
        Button1 = findViewById(R.id.BTN1)
        Button0 = findViewById(R.id.BTN0)
        ButtonDiv = findViewById(R.id.BTNbivision)
        ButtonMultiply = findViewById(R.id.BTNmultiply)
        ButtonSub = findViewById(R.id.BTNminus)
        ButtonNegativeNum = findViewById(R.id.BTNnegative)
        ButtonDot = findViewById(R.id.BTNdot)
        ButtonAdd = findViewById(R.id.BTNplus)
        ButtonDEL = findViewById(R.id.BTNDEL)
        ButtonClear = findViewById(R.id.BTNclear)
        ButtonEqual = findViewById(R.id.BTNequal)

        Button9.setOnClickListener { NumberButton(Button9) }
        Button8.setOnClickListener { NumberButton(Button8) }
        Button7.setOnClickListener { NumberButton(Button7) }
        Button6.setOnClickListener { NumberButton(Button6) }
        Button5.setOnClickListener { NumberButton(Button5) }
        Button4.setOnClickListener { NumberButton(Button4) }
        Button3.setOnClickListener { NumberButton(Button3) }
        Button2.setOnClickListener { NumberButton(Button2) }
        Button1.setOnClickListener { NumberButton(Button1) }
        Button0.setOnClickListener { NumberButton(Button0) }
        ButtonDiv.setOnClickListener { NumberButton(ButtonDiv) }
        ButtonMultiply.setOnClickListener { NumberButton(ButtonMultiply) }
        ButtonSub.setOnClickListener { NumberButton(ButtonSub) }
        ButtonNegativeNum.setOnClickListener { NumberButton(ButtonSub) }
        ButtonDot.setOnClickListener { NumberButton(ButtonDot) }
        ButtonAdd.setOnClickListener { NumberButton(ButtonAdd) }

        ButtonDEL.setOnClickListener{
            if (ResulttextView.text.toString().length > 0)
                ResulttextView.text = ResulttextView.text.substring(0, ResulttextView.text.toString().length - 1)
        }

        ButtonClear.setOnClickListener { ResulttextView.setText("") }
        ButtonEqual.setOnClickListener {
            var firstnum = ""
            var Theoperation = ""
            var isDotNum1 = false
            var isDotNum2 = false
            var secondnum = ""
            var result = 0f
            val uerinput = ResulttextView.text.toString()


            for (i in uerinput) { //15+3.5*2
                when {

                    i.isDigit() -> {
                        if (Theoperation.isEmpty()) {
                            firstnum += i
                        } else {
                            secondnum += i
                        }
                    }

                    i == '.' -> {
                        if (Theoperation.isEmpty() && isDotNum1 == false) {
                            firstnum += i
                            isDotNum1 = true
                        } else if (isDotNum2 == false) {
                            secondnum += i
                            isDotNum2 = true
                        }
                    }
                    else -> {
                        if (Theoperation.isEmpty() == false &&firstnum.isEmpty()==false && secondnum.isEmpty()==false) {//if I have multiple operations
                            firstnum = TheCalculate(firstnum, Theoperation, secondnum).toString()
                            Theoperation=""
                            secondnum=""
                        }

                            if (i == '+' || i == '*') {
                                Theoperation = i.toString()
                            }

                            if (i == '-') {
                                if (firstnum.isEmpty() && Theoperation.isEmpty()) {
                                    firstnum += i
                                } else if (Theoperation.isEmpty()) {
                                    Theoperation += i
                                } else {
                                    secondnum += i
                                }
                            }

                            if (i == '/') {
                                if (firstnum.isEmpty()) {
                                    firstnum = "0"
                                }
                                Theoperation += '/'

                            }
                        }

                }

            }
            if(Theoperation.isEmpty()==false&& firstnum.isEmpty()==false && secondnum.isEmpty()==false)
            { result = TheCalculate(firstnum, Theoperation, secondnum)
                ResulttextView.text = result.toString()}

        }

    }

    fun NumberButton(bt: Button) {
        ResulttextView.text = ResulttextView.text.toString() + bt.text.toString()
    }
    fun TheCalculate(firstnum: String, Theoperation: String, secondnum: String): Float {
        var result = 0f
        when {
            Theoperation == "+" -> {
                result = firstnum.toFloat() + secondnum.toFloat()
            }
            Theoperation == "*" -> {
                result = firstnum.toFloat() * secondnum.toFloat()
            }
            Theoperation == "-" -> {
                result = firstnum.toFloat() - secondnum.toFloat()
            }
            Theoperation == "/" -> {
                if (secondnum != "0") {
                    result = firstnum.toFloat() / secondnum.toFloat()
                } else {
                    result = 0f
                }

            }
        }
        return result
    }

}