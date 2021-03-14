package com.evroon.kotlin_soundboard

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private fun getString(id: String): String {
        return resources.getString(resources.getIdentifier(id, "string", packageName))
    }

    private fun createButton(name: String): Button {
        val button = Button(this)
        button.text = getString(name)
        linear_layout!!.addView(button)
        return button
    }

    @SuppressLint("PrivateResource")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Add textviews or sounds in this array to display them.
        // The strings are formatted as "{type}_{name},
        // Where type is either "textview" or "button".
        // The name must reference to a string resource in res/values/strings.xml.
        // The name must also reference to an audio resource/file in res/raw/.
        val layout = arrayOf(
            "textview_splash_sounds",
            "button_splash"
        )

        layout.forEach {
            if (it.startsWith("textview_")) {
                val textview = TextView(this)
                textview.text = getString(it.replaceFirst("textview_", ""))
                textview.setTextAppearance(R.style.Base_TextAppearance_AppCompat_Large)
                textview.setPadding(8, 0, 0, 0)
                linear_layout!!.addView(textview)
            } else {
                val mp3 = resources.getIdentifier(it.replaceFirst("button_", ""), "raw", packageName)

                createButton(it.replaceFirst("button_", "")).setOnClickListener {
                    val player = MediaPlayer.create(this, mp3)
                    player.setOnCompletionListener { player.release() }
                    player.start()
                }
            }
        }
    }
}
