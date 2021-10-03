package com.example.calculatorapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import net.objecthunter.exp4j.Expression
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception


class MainActivity : AppCompatActivity() {
    // numbers
    lateinit var one: Button
    lateinit var two: Button
    lateinit var three: Button
    lateinit var four: Button
    lateinit var five: Button
    lateinit var six: Button
    lateinit var seven: Button
    lateinit var eight: Button
    lateinit var nine: Button
    lateinit var zero: Button

    // operations
    lateinit var division: Button
    lateinit var multiplication: Button
    lateinit var subtraction: Button
    lateinit var addition: Button
    lateinit var dot: Button
    lateinit var sign: Button
    lateinit var del: Button
    lateinit var c: Button
    lateinit var equal: Button

    lateinit var res: TextView


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        one = findViewById(R.id.one)
        two = findViewById(R.id.two)
        three = findViewById(R.id.three)
        four = findViewById(R.id.four)
        five = findViewById(R.id.five)
        six = findViewById(R.id.six)
        seven = findViewById(R.id.seven)
        eight = findViewById(R.id.eight)
        nine = findViewById(R.id.nine)
        zero = findViewById(R.id.zero)

        // operations
        addition = findViewById(R.id.plus)
        division = findViewById(R.id.divide)
        multiplication = findViewById(R.id.multi)
        subtraction = findViewById(R.id.minus)
        sign = findViewById(R.id.sign)
        del = findViewById(R.id.del)
        c = findViewById(R.id.c)
        equal = findViewById(R.id.equal)
        dot = findViewById(R.id.dot)

        res = findViewById(R.id.result)

        one.setOnClickListener{
            res.text = "${res.text}1"
        }
        two.setOnClickListener{
            res.text = "${res.text}2"
        }
        three.setOnClickListener{
            res.text = "${res.text}3"
        }
        four.setOnClickListener{
            res.text = "${res.text}4"
        }
        five.setOnClickListener{
            res.text = "${res.text}5"
        }
        six.setOnClickListener{
            res.text = "${res.text}6"
        }
        seven.setOnClickListener{
            res.text = "${res.text}7"
        }
        eight.setOnClickListener{
            res.text = "${res.text}8"
        }
        nine.setOnClickListener{
            res.text = "${res.text}9"
        }
        zero.setOnClickListener{
            if(res.text.isNotEmpty())
                res.text = "${res.text}0"
        }
        dot.setOnClickListener{
            if(res.text.isNotEmpty())
                res.text = "${res.text}."
        }
        addition.setOnClickListener{
            res.text = "${res.text}+"
        }
        subtraction.setOnClickListener{
            res.text = "${res.text}-"
        }
        multiplication.setOnClickListener{
            if(res.text.isNotEmpty())
                res.text = "${res.text}*"
        }
        division.setOnClickListener{
            if(res.text.isNotEmpty())
                res.text = "${res.text}/"
        }
        c.setOnClickListener{
            res.text = ""
            res.hint = "0"
        }
        del.setOnClickListener{
            if(res.text.isNotEmpty())
                res.text = "${res.text.subSequence(0, res.text.length-1)}"
        }
        equal.setOnClickListener{
            val txt = res.text.toString()
            val expression = ExpressionBuilder(txt).build()

            try {
                val result = expression.evaluate()
                res.text = result.toString()
            }catch (e: Exception){
                res.text = ""
                res.hint = " Error"
            }
        }
        sign.setOnClickListener{
            val txt = res.text.toString()

            var len = txt.length
            if(len > 0){
                var lastNumber = ""
                for(i in len-1 downTo  0){
                    if(txt[i] != '*' && txt[i] != '/' ) {
                        lastNumber = txt[i] + lastNumber
                        if (txt[i] == '+' || txt[i] == '-')
                            break
                    }
                    else
                        break
                }

                if(lastNumber.length == len){
                    res.text = "${(lastNumber.toFloat() * -1)}"
                }
                else{
                    if((lastNumber.toFloat() * -1) > 0){
                        Log.d("ressssA", lastNumber)
                        Log.d("ressssB", txt.replace(lastNumber, "") as String)

                        res.text = txt.substring(0, len - lastNumber.length) + "+${(lastNumber.toFloat() * -1)}"
                    }else{
                        Log.d("ressssA", lastNumber)
                        Log.d("ressssB", txt.replace(lastNumber, "") as String)

                        res.text = txt.substring(0, len - lastNumber.length)+ "${(lastNumber.toFloat() * -1)}"
                    }
                }


            }
        }
    }
}