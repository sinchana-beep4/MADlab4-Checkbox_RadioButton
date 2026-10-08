package com.example.madbutton

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val pizza = findViewById<CheckBox>(R.id.pizza)
        val coffee = findViewById<CheckBox>(R.id.coffee)
        val burger = findViewById<CheckBox>(R.id.burger)
        val complete = findViewById<Button>(R.id.submit)
        val res = findViewById<TextView>(R.id.display)

        complete.setOnClickListener {
            var total = 0
            val result = StringBuilder()
            result.append("Selected Items: ")
            if (pizza.isChecked){
                result.append("\nPizza 100Rs")
                total += 100
            }
            if (coffee.isChecked){
                result.append("\nCoffee 50Rs")
                total += 50
            }
            if (burger.isChecked){
                result.append("\nBurger 120Rs")
                total += 120
            }

            result.append("\nTotal : " + total + "Rs")
            res.text=result.toString()
        }

    }
}