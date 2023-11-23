package com.example.myapplication

import android.content.ClipData
import android.content.ClipboardManager
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import com.example.myapplication.databinding.ActivityMainBinding
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val edits = mutableListOf<EditText>()

    private var low = 0
    private var up = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val aliases = resources.getStringArray(R.array.text_view_captions)
        val root = LinearLayout(this)
        root.orientation = LinearLayout.VERTICAL
        for (i in low..up) {
            val config = Configuration(resources.configuration)
            config.setLocale(Locale("en"))
            val localizedContext = createConfigurationContext(config)
            val strings = localizedContext.resources.getStringArray(R.array.text_view_captions)
            val tv = TextView(this)
            tv.text = aliases[i]
            val et = EditText(this)
            et.tag = genTagAttribute(strings[i].lowercase().replace(" ", "_"))
            et.hint = strings[i]
            et.setText(convertFromMeters(1.0,strings[i]).toString())
            et.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {}
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            })
            tv.setOnClickListener {
                val manager = this.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
                val clip = ClipData.newPlainText("", et.text.toString())
                manager.setPrimaryClip(clip)
            }
            val row = LinearLayout(this)
            row.orientation = LinearLayout.HORIZONTAL
            row.addView(tv)
            row.addView(et)
            root.addView(row)
            edits.add(et)
        }
        binding.rootLinear.addView(root)
    }
    private fun genTagAttribute(text: String) = when(text) {
        "feet" -> "et_foot"
        "inches" -> "et_inch"
        else -> "et_"+text.substring(0, text.length - 1)
    }
    private fun toMeters(v: Double, t: String) = when (t) {
        "Inches","Дюйм" -> v * 0.0254
        "Yards","Ярд" -> v * 0.9144
        "Feet","Фут" -> v * 0.3048
        "Miles","Миля" -> v * 1609.34
        "Yottametres","Иоттаметр" -> v * 1e10
        "Zettametres","Зеттаметр" -> v * 1e9
        "Exametres","Эксаметр"-> v * 1e8
        "Petametres","Петаметр" -> v * 1e7
        "Terametres","Тераметр" -> v * 1e6
        "Gigametres","Гигаметр" -> v * 1e5
        "Megametres","Мегаметр" -> v * 1e4
        "Kilometres","Километр" -> v * 1e3
        "Hectometres","Гектометр" -> v * 1e2
        "Decametres","Декаметр" -> v * 1e1
        "Metres","Метр" -> v
        "Decimetres","Дециметр" -> v * 1e-1
        "Centimetres","Сантиметр" -> v * 1e-2
        "Millimetres","Миллиметр" -> v * 1e-3
        "Micrometres","Микрометр" -> v * 1e-4
        "Nanometres","Нанометр" -> v * 1e-5
        "Picometres","Пикометр" -> v * 1e-6
        "Femtometres","Фемтометр" -> v * 1e-7
        "Attometres","Аттометр" -> v * 1e-8
        "Zeptometres","Земтометр" -> v * 1e-9
        "Yoctometres","Иоктометр" -> v * 1e-10
        else -> v
    }

    private fun convertFromMeters(v: Double, t: String) = when (t) {
        "Inches","Дюйм" -> v / 0.0254
        "Yards","Ярд" -> v / 0.9144
        "Feet","Фут" -> v / 0.3048
        "Miles","Миля" -> v / 1609.3445
        "Yottametres","Иоттаметр" -> v / 1e10
        "Zettametres","Зеттаметр" -> v / 1e9
        "Exametres","Эксаметр" -> v / 1e8
        "Petametres","Петаметр" -> v / 1e7
        "Terametres","Тераметр" -> v / 1e6
        "Gigametres","Гигаметр" -> v / 1e5
        "Megametres","Мегаметр" -> v / 1e4
        "Kilometres","Километр" -> v / 1e3
        "Hectometres","Гектометр" -> v / 1e2
        "Decametres","Декаметр" -> v / 1e1
        "Metres","Метр" -> v
        "Decimetres","Дециметр" -> v / 1e-1
        "Centimetres","Сантиметр" -> v / 1e-2
        "Millimetres","Миллиметр" -> v / 1e-3
        "Micrometres","Микрометр" -> v / 1e-4
        "Nanometres","Нанометр" -> v / 1e-5
        "Picometres","Пикометр" -> v / 1e-6
        "Femtometres","Фемтометр" -> v / 1e-7
        "Attometres","Аттометр"-> v / 1e-8
        "Zeptometres","Земтометр" -> v / 1e-9
        "Yoctometres","Иоктометр" -> v / 1e-10
        else -> v
    }
}