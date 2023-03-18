package com.example.sd_raw

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStreamReader

class FrontPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_front_page)
        supportActionBar?.hide()
        val name : EditText = findViewById(R.id.name)
        val cgpa : EditText = findViewById(R.id.cgpa)
        val save: Button = findViewById(R.id.save_btn)
        val load: Button = findViewById(R.id.load_btn)
        save.setOnClickListener {
            val n1=name.text.toString()
            val grade=cgpa.text.toString()
            if(grade.toFloat()>10.0){
                Toast.makeText(this, "CGPA must be  <= 10.0", Toast.LENGTH_LONG).show()
            }
            else{
                val file= File(getExternalFilesDir(null),"student.txt")
                val fos= FileOutputStream(file,false)
                fos.write("$n1,$grade".toByteArray())
                fos.close()
                name.setText("")
                cgpa.setText("")
            }
        }
        load.setOnClickListener {
            val file= File(getExternalFilesDir(null),"student.txt")
            if (file.length() == 0L) {
                Toast.makeText(this, "Error: File is empty", Toast.LENGTH_SHORT).show()
            }
            else{
                val fis= FileInputStream(file)
                val isr= InputStreamReader(fis)
                val br= BufferedReader(isr)
                val line:String = br.readLine()
                val part=line.split(",")
                name.setText(part[0])
                cgpa.setText(part[1])
                fis.close()
            }
        }
    }
}