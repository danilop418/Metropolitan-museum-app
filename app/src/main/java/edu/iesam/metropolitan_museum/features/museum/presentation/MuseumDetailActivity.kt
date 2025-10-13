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

class WorkOfArtDetailActivity : AppCompatActivity() {

    private lateinit var objectID: TextView
    private lateinit var title: TextView
    private lateinit var artistDisplayName: TextView
    private lateinit var artistNationality: TextView
    private lateinit var objectDate: TextView
    private lateinit var medium: TextView
    private lateinit var dimensions: TextView
    private lateinit var primaryImage: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_work_of_art_detail)

        objectID = findViewById(R.id.objectID)
        title = findViewById(R.id.title)
        artistDisplayName = findViewById(R.id.artistDisplayName)
        artistNationality = findViewById(R.id.artistNationality)
        objectDate = findViewById(R.id.objectDate)
        medium = findViewById(R.id.medium)
        dimensions = findViewById(R.id.dimensions)
        primaryImage = findViewById(R.id.primaryImage)

        MuseumObserver.selectedArt.observe(this, Observer { workOfArt ->
            if (workOfArt != null) {
                bindWorkOfArt(workOfArt)
            } else {
                Toast.makeText(this, "No hay obra de arte seleccionada", Toast.LENGTH_SHORT).show()
                finish()
            }
        })
    }

    private fun bindWorkOfArt(workOfArt: WorkOfArtUiModel) {
        objectID.text = workOfArt.objectID
        title.text = workOfArt.title
        artistDisplayName.text = workOfArt.artistDisplayName
        artistNationality.text = workOfArt.artistNationality
        objectDate.text = workOfArt.objectDate
        medium.text = workOfArt.medium
        dimensions.text = workOfArt.dimensions

        primaryImage.load(workOfArt.image) {
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