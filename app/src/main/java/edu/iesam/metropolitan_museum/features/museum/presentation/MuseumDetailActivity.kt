package edu.iesam.metropolitan_museum.features.museum.presentation

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import coil.load
import edu.iesam.metropolitan_museum.R
import edu.iesam.metropolitan_museum.features.museum.domain.WorkOfArt

class WorkOfArtDetailActivity : AppCompatActivity() {
    private lateinit var artworkImage: ImageView
    private lateinit var artworkTitle: TextView
    private lateinit var artworkArtist: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_work_of_art_detail)

        artworkImage = findViewById(R.id.artworkImage)
        artworkTitle = findViewById(R.id.artworkTitle)
        artworkArtist = findViewById(R.id.artworkArtist)

        MuseumObserver.selectedArt.observe(this, Observer { artwork ->
            if (artwork != null) {
                bindWorkOfArt(artwork)
            } else {
                Toast.makeText(this, "No hay obra seleccionada", Toast.LENGTH_SHORT).show()
                finish()
            }
        })
    }

    private fun bindWorkOfArt(artwork: WorkOfArt) {
        artworkTitle.text = artwork.title ?: "Sin título"
        artworkArtist.text = artwork.artistDisplayName ?: "Artista desconocido"
        artworkImage.load(artwork.primaryImage) {
            crossfade(true)
            placeholder(R.drawable.ic_launcher_background)
            error(R.drawable.ic_launcher_foreground)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        MuseumObserver.clear()
    }
}
