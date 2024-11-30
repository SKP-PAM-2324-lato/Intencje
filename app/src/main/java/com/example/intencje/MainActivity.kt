package com.example.intencje

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        result ->
        if(result.resultCode == RESULT_OK){
            val data = result.data?.getBooleanExtra("message", false)
            Toast.makeText(this, "Regulmin zakaceptowano $data", Toast.LENGTH_SHORT).show()

        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val button = findViewById<Button>(R.id.activity2)
        button.setOnClickListener {
            val intent = Intent(this, Activity2::class.java)
            intent.putExtra("Message", "Witaj w drugiej aktuwności")
            startActivity(intent)

        }

        val button2 = findViewById<Button>(R.id.activity3)
        button2.setOnClickListener {
            val intent = Intent(this, Activity3::class.java)
            resultLauncher.launch(intent)

        }
    }
}