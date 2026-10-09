package com.example.madbutton

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        val green = android.content.res.ColorStateList.valueOf(android.graphics.Color.parseColor("#006400"))
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
        val dinein = findViewById<RadioButton>(R.id.dineIn)
        val delivery = findViewById<RadioButton>(R.id.delivery)
        val takeaway = findViewById<RadioButton>(R.id.takeaway)
        val distance = findViewById<EditText>(R.id.distance)

        dinein.buttonTintList = green
        takeaway.buttonTintList = green
        delivery.buttonTintList = green

        distance.backgroundTintList = android.content.res.ColorStateList.valueOf(android.graphics.Color.BLACK)

        delivery.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                distance.visibility = View.VISIBLE
                distance.requestFocus()
            } else {
                distance.visibility = View.GONE
            }
        }

        var showTotal = true

        complete.setOnClickListener {
            var total = 0.0
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
                val takeCharges = count * 5
                total += takeCharges
                result.append("\nTakeaway charges:$takeCharges")
            }

            else if (delivery.isChecked) {
                var delCharges = 0.0
                val km = distance.text.toString().toDoubleOrNull()
                if (km != null && km < 8.0 && km > 1.0) {
                    delCharges += 10 + (5 * km)
                    total += delCharges
                    result.append("\nDelivery charges: $delCharges")
                } else if (km != null && km < 1.0 && km > 0.0) {
                    delCharges += 10
                    total += delCharges
                    result.append("\nDelivery charges: $delCharges")
                } else {
                    res.setTextColor(android.graphics.Color.RED)
                    result.append("\nOutside delivery range")
                    showTotal = false
                }
            }

            if (showTotal) {
                result.append("\nTotal : " + total + "Rs")

                if (dinein.isChecked){
                    result.append("\nThank you!\nEnjoy your time with our cats and please visit again!")
                }
                else if (takeaway.isChecked){
                    result.append("\nThank you!\nPlease visit again and stay for the cats!")
                }

                else if (delivery.isChecked and showTotal){
                    result.append("\nThank you!\nVisit us at XYZ Nagar to have a pleasant time with cats!")
                }
                res.text = result.toString()
            }
        }

    }
}