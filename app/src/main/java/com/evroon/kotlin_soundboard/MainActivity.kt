package com.evroon.kotlin_soundboard

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import com.google.android.material.button.MaterialButton
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.appbar.*

class MainActivity : AppCompatActivity() {
    private fun getString(id: String): String {
        return resources.getString(resources.getIdentifier(id, "string", packageName))
    }

    private fun createButton(name: String): Button {
        val button = MaterialButton(this)
        button.text = getString(name)
        linear_layout!!.addView(button)
        return button
    }

    @SuppressLint("PrivateResource")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        super.setSupportActionBar(detail_toolbar)
        supportActionBar!!.title = getString(R.string.app_name)

        // Add textviews or sounds in this array to display them.
        // The strings are formatted as "{type}_{name},
        // Where type is either "textview" or "button".
        // The name must reference to a string resource in res/values/strings.xml.
        // The name must also reference to an audio resource/file in res/raw/.
        val layout = arrayOf(
            "textview_ik_gooi_blikje_in_de_water",
            "button_anderekeerzien",
            "button_dat_doe_je_toch_niet_zo",
            "button_eerstekeer",
            "button_geefniks",
            "button_hartstikkemooi",
            "button_houdoe",
            "button_ikhebhelemaalniks",
            "button_isniks",
            "button_kan_je_weer_bekeuring_krijgen",
            "button_komt_goed",
            "button_leegbilkindewater",
            "button_money",
            "button_mooieding",
            "button_nevergiveup",
            "button_niet_meer_doen",
            "button_niet_normaal",
            "button_ookboete",
            "button_perfect",
            "button_zoisnatuur",
            "button_waarom_niet",
            "button_honderd_euro",

            "textview_escalatie",
            "button_tijdomdagtezeggen",
            "button_prachtigis",
            "button_toeter",
            "button_sinterklaas_bestaat_niet",
            "button_groot_succes",

            "textview_overig",
            "button_wilgewoontoeteren",
            "button_aandacht",
            "button_alweer_een_winnaar",
            "button_wrong_answer",
            "button_doehetniet"
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
