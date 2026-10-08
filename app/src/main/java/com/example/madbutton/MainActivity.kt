package com.example.madbutton

import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.TextView
import android.widget.EditText
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
        val dine = findViewById<RadioButton>(R.id.dineIn)
        val delivery = findViewById<RadioButton>(R.id.delivery)
        val takeaway = findViewById<RadioButton>(R.id.takeaway)
        val distance = findViewById<EditText>(R.id.distance)

        delivery.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                distance.visibility = View.VISIBLE
                distance.requestFocus()
            } else {
                distance.visibility = View.GONE
            }
        }

        complete.setOnClickListener {
            var total = 0
            var count = 0
            val result = StringBuilder()
            result.append("Selected Items: ")
            if (pizza.isChecked){
                count += 1
                result.append("\nPizza: 100Rs")
                total += 100
            }
            if (coffee.isChecked){
                count += 1
                result.append("\nCoffee: 50Rs")
                total += 50
            }
            if (burger.isChecked){
                count += 1
                result.append("\nBurger: 120Rs")
                total += 120
            }

            if(takeaway.isChecked) {
                var takeCharges = count * 5
                total += takeCharges
                result.append("\nTakeaway charges:$takeCharges")
            }

            if (delivery.isChecked) {
                var delCharges = 0
                val km = distance.text.toString().toIntOrNull()
                if (km != null) {
                    delCharges += 10 + (5 * km)
                }
                total += delCharges
                result.append("\nDelivery charges: $delCharges")
            }

            result.append("\nTotal : " + total + "Rs")
            res.text=result.toString()
        }

    }
}