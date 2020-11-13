package com.rafa.calc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.view.Display
import android.view.Surface
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.widget.Button
import android.widget.TableLayout
import android.widget.TextView
import android.widget.Toast
import kotlin.math.round

class MainActivity : AppCompatActivity() {

    var limpio = true

    var oldNumber = ""

    var op = "+"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var text: TextView = findViewById(R.id.editText) as TextView
        text.keyListener = null
        var buIgual = findViewById(R.id.Igual) as Button
        buIgual.setEnabled(false)

        cleanEvent(this)

    }

    // CONVERSIONES

    fun Hexadecimal_Decimal(caracter: Char): Int {
        return when (caracter) {
            'a' -> 10
            'b' -> 11
            'c' -> 12
            'd' -> 13
            'e' -> 14
            'f' -> 15
            else -> caracter.toString().toInt()
        }
    }

    fun convertirDeHexadecimal_Decimal(hexadecimal: String): Int {
        var decimal: Int = 0
        var potencia = 0
        for (x in hexadecimal.length - 1 downTo 0) {
            val valor = Hexadecimal_Decimal(hexadecimal[x])
            val elevado = Math.pow(16.0, potencia.toDouble()).toInt() * valor
            decimal += elevado
            potencia++
        }
        return decimal
    }

    fun convertirDeBinario_Decimal(num: Int): Int {

        var num = num
        var decimalNumber = 0
        var i = 0
        var remainder: Int

        while (num != 0) {
            remainder = num % 10
            num /= 10
            decimalNumber += (remainder * Math.pow(2.0, i.toDouble())).toInt()
            ++i
        }
        return decimalNumber
    }

    // CAMBIAR DE LAYOUT

    fun deciHexaLayout(view: View) {

        var text = findViewById(R.id.editText) as TextView

        var decimal = findViewById(R.id.decimalLayout) as TableLayout

        var hexadecimal = findViewById(R.id.hexaLayout) as TableLayout

        decimal.setVisibility(View.INVISIBLE)

        hexadecimal.setVisibility(View.VISIBLE)

        var numero = text.text.toString()

        var numeroInt:Int = round(numero.toDouble()).toInt()

        text.setText(Integer.toHexString(numeroInt))

        var buIgual = findViewById(R.id.HexIgual) as Button

        buIgual.setEnabled(false)

    }

    fun deciBinLayout(view: View) {

        var text = findViewById(R.id.editText) as TextView

        var decimal = findViewById(R.id.decimalLayout) as TableLayout

        var binario = findViewById(R.id.binLayout) as TableLayout

        decimal.setVisibility(View.INVISIBLE)

        binario.setVisibility(View.VISIBLE)

        var numero = text.text.toString()

        var numeroInt:Int = round(numero.toDouble()).toInt()

        text.setText(Integer.toBinaryString(numeroInt))

        var buIgual = findViewById(R.id.BinEqual) as Button

        buIgual.setEnabled(false)

    }

    fun hexaDeciLayout(view: View) {

        var text = findViewById(R.id.editText) as TextView

        var hexadecimal = findViewById(R.id.hexaLayout) as TableLayout

        var decimal = findViewById(R.id.decimalLayout) as TableLayout

        hexadecimal.setVisibility(View.INVISIBLE)

        decimal.setVisibility(View.VISIBLE)

        try {
            var numero = text.text.toString()

            text.setText(convertirDeHexadecimal_Decimal(numero).toString())
        } catch (e: Exception) {
            Toast.makeText(this@MainActivity, "Se ha intentado convertir un número demasiado largo, pulse el botón AC.", Toast.LENGTH_SHORT).show()
        }

        var buIgual = findViewById(R.id.Igual) as Button

        buIgual.setEnabled(false)
    }

    fun hexaBinLayout(view: View) {

        var text = findViewById(R.id.editText) as TextView

        var hexadecimal = findViewById(R.id.hexaLayout) as TableLayout

        var binario = findViewById(R.id.binLayout) as TableLayout

        hexadecimal.setVisibility(View.INVISIBLE)

        binario.setVisibility(View.VISIBLE)

        var buIgual = findViewById(R.id.BinEqual) as Button

        buIgual.setEnabled(false)

        try {

            var numero = text.text.toString()

            numero = convertirDeHexadecimal_Decimal(numero).toString()

            var numeroInt:Int = round(numero.toDouble()).toInt()

            text.setText(Integer.toBinaryString(numeroInt))

        } catch (e: Exception) {
            Toast.makeText(this@MainActivity, "Se ha intentado convertir un número demasiado largo, pulsa el botón AC.", Toast.LENGTH_SHORT).show()

        }

    }

    fun binDecimalLayout(view: View) {

        var text = findViewById(R.id.editText) as TextView

        var binario = findViewById(R.id.binLayout) as TableLayout

        var decimal = findViewById(R.id.decimalLayout) as TableLayout

        binario.setVisibility(View.INVISIBLE)

        decimal.setVisibility(View.VISIBLE)

        var numero = text.text.toString()

        var buIgual = findViewById(R.id.Igual) as Button

        buIgual.setEnabled(false)

        try {

            var numeroInt = numero.toInt()

            text.setText(convertirDeBinario_Decimal(numeroInt).toString())

        } catch (e: Exception) {
            Toast.makeText(this@MainActivity, "Se ha intentado convertir un número demasiado largo.", Toast.LENGTH_SHORT).show()
        }
    }

    fun binHexaLayout(view: View) {

        var text = findViewById(R.id.editText) as TextView

        var binario = findViewById(R.id.binLayout) as TableLayout

        var hexadecimal = findViewById(R.id.hexaLayout) as TableLayout

        binario.setVisibility(View.INVISIBLE)

        hexadecimal.setVisibility(View.VISIBLE)

        var numero = text.text.toString()

        try {

            var numeroInt:Int = round(numero.toDouble()).toInt()

            numero = convertirDeBinario_Decimal(numeroInt).toString()

            var numeroInt2:Int = round(numero.toDouble()).toInt()

            text.setText(Integer.toHexString(numeroInt2))

            var buIgual = findViewById(R.id.HexIgual) as Button

            buIgual.setEnabled(false)


        } catch (e: Exception) {
            Toast.makeText(this@MainActivity, "Se ha intentado convertir un número demasiado largo.", Toast.LENGTH_SHORT).show()
        }

    }

    // CONTROL DE TECLAS NUMÉRICAS

    fun decimalNumberEvent(view: View) {

        var text: TextView = findViewById(R.id.editText) as TextView
        var bu1 = findViewById(R.id.but1) as Button
        var bu2 = findViewById(R.id.but2) as Button
        var bu3 = findViewById(R.id.but3) as Button
        var bu4 = findViewById(R.id.but4) as Button
        var bu5 = findViewById(R.id.but5) as Button
        var bu6 = findViewById(R.id.but6) as Button
        var bu7 = findViewById(R.id.but7) as Button
        var bu8 = findViewById(R.id.but8) as Button
        var bu9 = findViewById(R.id.but9) as Button
        var bu0 = findViewById(R.id.but0) as Button
        var buPunto = findViewById(R.id.punto) as Button

        if (limpio){
            text.setText("")
            limpio = false
        }

        var butclick = text.text.toString()



        var butsel = view as Button

        when(butsel.id){

            bu1.id -> {butclick += "1"}
            bu2.id -> {butclick += "2"}
            bu3.id -> {butclick += "3"}
            bu4.id -> {butclick += "4"}
            bu5.id -> {butclick += "5"}
            bu6.id -> {butclick += "6"}
            bu7.id -> {butclick += "7"}
            bu8.id -> {butclick += "8"}
            bu9.id -> {butclick += "9"}
            bu0.id -> {butclick += "0"}
            buPunto.id -> {butclick += "."}

        }

        text.setText(butclick)

    }

    fun binNumberEvent(view: View) {

        var text: TextView = findViewById(R.id.editText) as TextView
        var bu1 = findViewById(R.id.Binbut1) as Button
        var bu0 = findViewById(R.id.Binbut0) as Button

        if (limpio){
            text.setText("")
            limpio = false
        }

        var butclick = text.text.toString()



        var butsel = view as Button

        when(butsel.id){

            bu1.id -> {butclick += "1"}
            bu0.id -> {butclick += "0"}

        }

        text.setText(butclick)

    }

    fun hexaNumberEvent(view:View){

        var text: TextView = findViewById(R.id.editText) as TextView

        var bu0 = findViewById(R.id.Hexbut0) as Button
        var bu1 = findViewById(R.id.Hexbut1) as Button
        var bu2 = findViewById(R.id.Hexbut2) as Button
        var bu3 = findViewById(R.id.Hexbut3) as Button
        var bu4 = findViewById(R.id.Hexbut4) as Button
        var bu5 = findViewById(R.id.Hexbut5) as Button
        var bu6 = findViewById(R.id.Hexbut6) as Button
        var bu7 = findViewById(R.id.Hexbut7) as Button
        var bu8 = findViewById(R.id.Hexbut8) as Button
        var bu9 = findViewById(R.id.Hexbut9) as Button
        var buA = findViewById(R.id.HexA) as Button
        var buB = findViewById(R.id.HexB) as Button
        var buC = findViewById(R.id.HexC) as Button
        var buD = findViewById(R.id.HexD) as Button
        var buE = findViewById(R.id.HexE) as Button
        var buF = findViewById(R.id.HexF) as Button


        if (limpio){
            text.setText("")
            limpio = false
        }

        var butclick = text.text.toString()

        var butsel = view as Button

        when(butsel.id){

            bu0.id -> {butclick += "0"}
            bu1.id -> {butclick += "1"}
            bu2.id -> {butclick += "2"}
            bu3.id -> {butclick += "3"}
            bu4.id -> {butclick += "4"}
            bu5.id -> {butclick += "5"}
            bu6.id -> {butclick += "6"}
            bu7.id -> {butclick += "7"}
            bu8.id -> {butclick += "8"}
            bu9.id -> {butclick += "9"}

            buA.id -> {butclick += "a"}
            buB.id -> {butclick += "b"}
            buC.id -> {butclick += "c"}
            buD.id -> {butclick += "d"}
            buE.id -> {butclick += "e"}
            buF.id -> {butclick += "f"}

        }

        text.setText(butclick)

    }

    // CONTROL DE TECLAS DE OPERACIONES

        // LIMPIAR

        fun cleanEvent(view: View) {

        var text: TextView = findViewById(R.id.editText) as TextView

        text.setText("0")

        limpio = true

        var buIgual = findViewById(R.id.Igual) as Button
        buIgual.setEnabled(false)

    }

        // OPERACIONES

        fun operatorEvent(view: View) {

        var text: TextView = findViewById(R.id.editText) as TextView
        var suma = findViewById(R.id.Sumar) as Button
        var resta = findViewById(R.id.Restar) as Button
        var multi = findViewById(R.id.Multi) as Button
        var div = findViewById(R.id.Divide) as Button
        var buIgual = findViewById(R.id.Igual) as Button

        oldNumber = text.text.toString()

        var butsel = view as Button

        when(butsel.id){

            multi.id -> {
                    op = "*"
                    limpio = true
                    text.setText("0")
            }

            suma.id -> {
                    op = "+"
                    limpio = true
                    text.setText("0")
            }

            resta.id -> {
                    op = "-"
                    limpio = true
                    text.setText("0")
            }

            div.id -> {
                    op = "/"
                    limpio = true
                    text.setText("0")
            }
        }
        buIgual.setEnabled(true)
    }

        fun binOperatorEvent(view: View) {

        var text: TextView = findViewById(R.id.editText) as TextView
        var suma = findViewById(R.id.BinSumar) as Button
        var resta = findViewById(R.id.BinRestar) as Button
        var multi = findViewById(R.id.BinMulti) as Button
        var div = findViewById(R.id.BinDivide) as Button
        var buIgual = findViewById(R.id.BinEqual) as Button

        var numero = text.text.toString()

        var numeroInt = numero.toInt()

        oldNumber = convertirDeBinario_Decimal(numeroInt).toString()

        var butsel = view as Button

        when(butsel.id){

            multi.id -> {
                op = "*"
                limpio = true
                text.setText("0")
            }

            suma.id -> {
                op = "+"
                limpio = true
                text.setText("0")
            }

            resta.id -> {
                op = "-"
                limpio = true
                text.setText("0")
            }

            div.id -> {
                op = "/"
                limpio = true
                text.setText("0")
            }
        }

        buIgual.setEnabled(true)

    }

        fun hexOperatorEvent(view: View) {

            var text: TextView = findViewById(R.id.editText) as TextView
            var suma = findViewById(R.id.HexSumar) as Button
            var resta = findViewById(R.id.HexRestar) as Button
            var multi = findViewById(R.id.HexMulti) as Button
            var div = findViewById(R.id.HexDivide) as Button
            var buIgual = findViewById(R.id.HexIgual) as Button

            var numero = text.text.toString()

            oldNumber = convertirDeHexadecimal_Decimal(numero).toString()

            var butsel = view as Button

            when(butsel.id){

                multi.id -> {
                    op = "*"
                    limpio = true
                    text.setText("0")
                }

                suma.id -> {
                    op = "+"
                    limpio = true
                    text.setText("0")
                }

                resta.id -> {
                    op = "-"
                    limpio = true
                    text.setText("0")
                }

                div.id -> {
                    op = "/"
                    limpio = true
                    text.setText("0")
                }
            }

            buIgual.setEnabled(true)

        }

        // RESULTADOS

        fun resolvEvent(view: View) {

        var text: TextView = findViewById(R.id.editText) as TextView

        var buIgual = findViewById(R.id.Igual) as Button

        var newNumber = text.text.toString()

        var result = 0.0

        var butsel = view as Button

        try {

            when(op){

                "*" -> {result = oldNumber.toDouble() * newNumber.toDouble()}
                "+" -> {result = oldNumber.toDouble() + newNumber.toDouble()}
                "-" -> {result = oldNumber.toDouble() - newNumber.toDouble()}
                "/" -> {result = oldNumber.toDouble() / newNumber.toDouble()}

            }


        if (oldNumber.equals("")){
            text.setText(0)
            limpio = true
        } else {
            if (newNumber.equals("0") && op.equals("/")) {
                Toast.makeText(this@MainActivity, "No se puede dividir entre 0", Toast.LENGTH_SHORT).show()
                text.setText("0")
                limpio = true
            } else {
                text.setText(result.toString())
                limpio = true

                if (limpio) {
                    buIgual.setEnabled(false)
                }
            }
        }

        } catch (e: Exception) {
            Toast.makeText(this@MainActivity, "Se ha producido un error.", Toast.LENGTH_SHORT).show()
        }

    }

        fun BinResolvEvent(view: View) {

        var text: TextView = findViewById(R.id.editText) as TextView

        var buIgual = findViewById(R.id.BinEqual) as Button

        var newNumber = text.text.toString()

        var newNumberInt = newNumber.toInt()

        newNumber = convertirDeBinario_Decimal(newNumberInt).toString()

        var result = 0.0

        try {

            when(op){

                "*" -> {result = oldNumber.toDouble() * newNumber.toDouble()}
                "+" -> {result = oldNumber.toDouble() + newNumber.toDouble()}
                "-" -> {result = oldNumber.toDouble() - newNumber.toDouble()}
                "/" -> {result = oldNumber.toDouble() / newNumber.toDouble()}

            }


            if (oldNumber.equals("")){
                text.setText(0)
                limpio = true
            } else {
                if (newNumber.equals("0") && op.equals("/")) {
                    Toast.makeText(this@MainActivity, "No se puede dividir entre 0", Toast.LENGTH_SHORT).show()
                    text.setText("0")
                    limpio = true
                } else {
                    var numeroInt:Int = round(result).toInt()
                    text.setText(Integer.toBinaryString(numeroInt))
                    limpio = true

                    if (limpio) {
                        buIgual.setEnabled(false)
                    }
                }
            }

        } catch (e: Exception) {
            Toast.makeText(this@MainActivity, "Se ha producido un error.", Toast.LENGTH_SHORT).show()
        }

    }

        fun hexResolvEvent(view: View) {

            var text: TextView = findViewById(R.id.editText) as TextView

            var buIgual = findViewById(R.id.HexIgual) as Button

            var newNumber = text.text.toString()

            newNumber = convertirDeHexadecimal_Decimal(newNumber).toString()

            var result = 0.0

            try {

                when(op){

                    "*" -> {result = oldNumber.toDouble() * newNumber.toDouble()}
                    "+" -> {result = oldNumber.toDouble() + newNumber.toDouble()}
                    "-" -> {result = oldNumber.toDouble() - newNumber.toDouble()}
                    "/" -> {result = oldNumber.toDouble() / newNumber.toDouble()}

                }


                if (oldNumber.equals("")){
                    text.setText(0)
                    limpio = true
                } else {
                    if (newNumber.equals("0") && op.equals("/")) {
                        Toast.makeText(this@MainActivity, "No se puede dividir entre 0", Toast.LENGTH_SHORT).show()
                        text.setText("0")
                        limpio = true
                    } else {
                        var numeroInt:Int = round(result).toInt()
                        text.setText(Integer.toHexString(numeroInt))
                        limpio = true

                        if (limpio) {
                            buIgual.setEnabled(false)
                        }
                    }
                }

            } catch (e: Exception) {
                Toast.makeText(this@MainActivity, "Se ha producido un error.", Toast.LENGTH_SHORT).show()
            }

        }

}