package com.kolyambus.lab2mobile

import android.media.Image
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var image: ImageView
    private lateinit var name: TextView
    private lateinit var info: TextView
    private lateinit var buttonPrev: Button
    private lateinit var buttonNext: Button
    private lateinit var imageList: List<Artwork>
    private var index = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        imageList = generateList()
        index = savedInstanceState?.getInt("index") ?: 0

        initializePointers()
        displayImage()

        buttonPrev.setOnClickListener { onTapPrev() }
        buttonNext.setOnClickListener { onTapNext() }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("index", index)
    }

    fun generateList(): List<Artwork> = listOf(
        Artwork(R.string.name1, R.string.author, R.string.year1, R.drawable.veduschaya),
        Artwork(R.string.name2, R.string.author, R.string.year2, R.drawable.solveig),
        Artwork(R.string.name3, R.string.author, R.string.year3, R.drawable.everest),
        Artwork(R.string.name4, R.string.author, R.string.year4, R.drawable.gosti),
        Artwork(R.string.name5, R.string.author, R.string.year5, R.drawable.vesna)
    )

    fun displayImage() {
        image.setImageResource(imageList[index].imageID)
        name.text = getString(imageList[index].name)
        info.text = getString(R.string.infoTemplate, getString(imageList[index].author), getString(imageList[index].year))
    }

    fun initializePointers() {
        image = findViewById<ImageView>(R.id.imageView)
        name = findViewById<TextView>(R.id.textName)
        info = findViewById<TextView>(R.id.textInfo)
        buttonPrev = findViewById<Button>(R.id.buttonPrev)
        buttonNext = findViewById<Button>(R.id.buttonNext)
    }

    fun onTapNext() {
        if (index == 0)
            buttonPrev.isEnabled = true
        if (index < imageList.lastIndex) {
            index++
            displayImage()
            if (index == imageList.lastIndex)
                buttonNext.isEnabled = false
        }
    }

    fun onTapPrev() {
        if (index  == imageList.lastIndex)
            buttonNext.isEnabled = true
        if (index > 0) {
            index--
            displayImage()
            if (index == 0)
                buttonPrev.isEnabled = false
        }
    }
}