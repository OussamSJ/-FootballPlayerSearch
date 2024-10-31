package com.iscod.footballplayersearch

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val playerNameEditText = findViewById<EditText>(R.id.playerNameEditText)
        val searchButton = findViewById<Button>(R.id.searchButton)
        val playerInfoTextView = findViewById<TextView>(R.id.playerInfoTextView)

        searchButton.setOnClickListener {
            val playerName = playerNameEditText.text.toString()

            if (playerName.isNotEmpty()) {
                getPlayerInfo(playerName, playerInfoTextView)
            } else {
                playerInfoTextView.text = "Veuillez entrer un nom de joueur"
            }
        }
    }

    private fun getPlayerInfo(playerName: String, playerInfoTextView: TextView) {
        val call = RetrofitClient.instance.getPlayerProfile(
            "88809a16cb66db351fc458b2301d5c2c",
            "88809a16cb66db351fc458b2301d5c2c",
            playerName
        )

        call.enqueue(object : Callback<PlayerResponse> {
            override fun onResponse(call: Call<PlayerResponse>, response: Response<PlayerResponse>) {
                if (response.isSuccessful) {
                    val player = response.body()?.response?.get(0)?.player
                    if (player != null) {
                        playerInfoTextView.text = """
                            Nom : ${player.name}
                            Position : ${player.position}
                            Nationalité : ${player.nationality}
                        """.trimIndent()
                    } else {
                        playerInfoTextView.text = "Aucun joueur trouvé."
                    }
                } else {
                    playerInfoTextView.text = "Erreur lors de la récupération des données."
                }
            }

            override fun onFailure(call: Call<PlayerResponse>, t: Throwable) {
                playerInfoTextView.text = "Erreur de réseau : ${t.message}"
            }
        })
    }
}
