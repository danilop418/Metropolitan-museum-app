package edu.iesam.metropolitan_museum.features.museum.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edu.iesam.metropolitan_museum.R
import edu.iesam.metropolitan_museum.features.museum.core.api.MuseumApiClient
import edu.iesam.metropolitan_museum.features.museum.data.MuseumDataRepository
import edu.iesam.metropolitan_museum.features.museum.data.remote.api.MuseumApiRemoteDataSource
import edu.iesam.metropolitan_museum.features.museum.domain.MuseumGetUseCase

class MuseumActivity : AppCompatActivity() {
    private lateinit var adapter: MuseumAdapter
    private lateinit var viewModel: MuseumListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_museum)

        setupRecyclerView()
        setupViewModel()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.museumRecyclerView)
        ViewCompat.setOnApplyWindowInsetsListener(recyclerView) { v, insets ->
            val innerPadding = insets.getInsets(
                WindowInsetsCompat.Type.systemBars()
                        or WindowInsetsCompat.Type.displayCutout()
            )
            v.setPadding(innerPadding.left, innerPadding.top, innerPadding.right, innerPadding.bottom)
            insets
        }

        adapter = MuseumAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    private fun setupViewModel() {
        val apiClient = MuseumApiClient()
        val remoteDataSource = MuseumApiRemoteDataSource(apiClient)
        val museumDataRepository = MuseumDataRepository(remoteDataSource)
        val museumGetUseCase = MuseumGetUseCase(museumDataRepository)
        viewModel = MuseumListViewModel(museumGetUseCase)
    }

    private fun observeViewModel() {
        viewModel.workOfArtList.observe(this) { artworks ->
            adapter.updateList(artworks)
        }

        viewModel.error.observe(this) { error ->
            error?.let {
                val message = when (it) {
                    is edu.iesam.metropolitan_museum.features.museum.domain.ErrorApp.InternetConexionError ->
                        "Sin conexión a Internet"
                    is edu.iesam.metropolitan_museum.features.museum.domain.ErrorApp.ServerErrorApp ->
                        "Error del servidor"
                    else -> "Error desconocido"
                }
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.loadWorkOfArt()
    }
}