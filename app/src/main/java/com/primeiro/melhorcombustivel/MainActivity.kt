package com.primeiro.melhorcombustivel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var campoPrecoGasolina: EditText
    lateinit var campoPrecoAlcool: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        defineCampos()
        defineBotao()
    }

    private fun defineCampos() {
        campoPrecoGasolina = findViewById<EditText>(R.id.campoValorGasolina)
        campoPrecoAlcool = findViewById<EditText>(R.id.campoValorAlcool)
    }

    private fun defineBotao() {
        val botaoCalcular = findViewById<Button>(R.id.botaoCalcular)
        botaoCalcular.setOnClickListener {
            val precoAlcool = campoPrecoAlcool.text.toString().toDouble()
            val precoGasolina = campoPrecoGasolina.text.toString().toDouble()
            val combustivel = calcularMelhorCombustivel(precoGasolina,precoAlcool)
            mostraResultado(combustivel)
        }
    }

    private fun mostraResultado(resultado: Combustivel) {
        val resultadoTextView = findViewById<TextView>(R.id.textCombustivel)
        resultadoTextView.text = "O Combustível indicado é ${resultado.toString()}"
    }

    private fun calcularMelhorCombustivel(precoGasolina: Double,
                                          precoAlcool: Double): Combustivel  {
        val fatorEscolha = 0.7
        val proporcao = precoAlcool / precoGasolina
        if (proporcao < fatorEscolha) {
            return Combustivel.ALCOOL
        } else {
            return Combustivel.GASOLINA
        }
    }

    enum class Combustivel(val nome: String) {
        GASOLINA("Gasolina"),
        ALCOOL("Álcool");

        override fun toString(): String {
            super.toString()
            return nome
        }
    }
}