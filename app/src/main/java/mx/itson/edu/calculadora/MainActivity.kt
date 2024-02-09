package mx.itson.edu.calculadora

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

@Suppress("SpellCheckingInspection")
class MainActivity : AppCompatActivity() {
    private var operation: String? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        assignNumberClickListeners()
        clear()
        sumar()
        restar()
        dividir()
        multiplicar()
        calculate()
    }

    private fun assignNumberClickListener(number: Int) {
        val buttonId = resources.getIdentifier("btn$number", "id", packageName)
        val btn: Button = findViewById(buttonId)
        btn.setOnClickListener {
            agregarNumero(number)
        }
    }

    private fun assignNumberClickListeners() {
        for (i in 1..9) {
            assignNumberClickListener(i)
        }
    }


    private fun clear() {
        val btn: Button = findViewById(R.id.btnLimpiar)
        btn.setOnClickListener {
            val calcular1: TextView = findViewById(R.id.calcular1)
            val calcular2: TextView = findViewById(R.id.calcular2)
            operation = null
            calcular1.text = ""
            calcular2.text = ""
        }
    }


    private fun sumar() {
        val btn: Button = findViewById(R.id.btnSumar)
        btn.setOnClickListener {
            agregarOperacion("+", "SUMAR")
        }
    }


    private fun restar() {
        val btn: Button = findViewById(R.id.btnRestar)
        btn.setOnClickListener {
            agregarOperacion("-", "RESTAR")
        }
    }


    private fun dividir() {
        val btn: Button = findViewById(R.id.btnDividir)
        btn.setOnClickListener {
            agregarOperacion("/", "DIVIDIR")
        }
    }


    private fun multiplicar() {
        val btn: Button = findViewById(R.id.btnPor)
        btn.setOnClickListener {
            agregarOperacion("*", "MULTIPLICAR")
        }
    }


    private fun agregarNumero(value: Int) {
        val calcular2: TextView = findViewById(R.id.calcular2)
        calcular2.text = calcular2.text.toString().plus(value)
    }


    fun calculate() {
        val btn: Button = findViewById(R.id.btnIgual)
        btn.setOnClickListener {
            val calcular1: TextView = findViewById(R.id.calcular1)
            val calcular2: TextView = findViewById(R.id.calcular2)

            if (calcular2.text.isNotEmpty() && calcular1.text.length > 1) {
                val numeros = calcular1.text.toString().dropLast(1).filter { it.isDigit() }
                val numeros1: Double = numeros.toDouble()
                val numeros2: Double = calcular2.text.toString().toDouble()
                calcular1.text = ""
                calcular2.text = calcularOperacion(numeros1, numeros2).toString()
                this.operation = null
            }
        }
    }


    private fun agregarOperacion(simbolo: String, operacion: String) {
        val calcular1: TextView = findViewById(R.id.calcular1)
        val calcular2: TextView = findViewById(R.id.calcular2)

        if (calcular2.text != "" && this.operation == null) {
            this.operation = operacion
            calcular1.text = calcular2.text.toString().plus(simbolo)
            calcular2.text = ""
        }
    }

    // Calcular operacion basado en simbolo
    private fun calcularOperacion(numero1: Double, numero2: Double): Double {
        if (operation == "SUMA") {
            return (numero1 + numero2).toDouble()
        }

        if (operation == "RESTAR") {
            return (numero1 - numero2).toDouble()
        }

        if (operation == "DIVIDIR") {
            return (numero1 / numero2).toDouble()
        }

        return (numero1 * numero2).toDouble()
    }
}