package edu.iesam.metropolitan_museum.features.museum.presentation

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import edu.iesam.metropolitan_museum.R
import edu.iesam.metropolitan_museum.features.museum.domain.WorkOfArt

class MuseumAdapter : RecyclerView.Adapter<MuseumAdapter.WorkOfArtViewHolder>() {
    private var lista: List<WorkOfArtUiModel> = emptyList()

    fun updateList(newList: List<WorkOfArtUiModel>) {
        lista = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkOfArtViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_work_of_art, parent, false)
        return WorkOfArtViewHolder(view)
    }

    override fun getItemCount(): Int = lista.size

    override fun onBindViewHolder(holder: WorkOfArtViewHolder, position: Int) {
        holder.bind(lista[position])
    }

    inner class WorkOfArtViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val title: TextView = view.findViewById(R.id.title)
        private val artistDisplayName: TextView = view.findViewById(R.id.artistDisplayName)
        private val artistNationality: TextView = view.findViewById(R.id.artistNationality)
        private val objectDate: TextView = view.findViewById(R.id.objectDate)
        private val medium: TextView = view.findViewById(R.id.medium)
        private val dimensions: TextView = view.findViewById(R.id.dimensions)
        private val primaryImage: ImageView = view.findViewById(R.id.primaryImage)

        fun bind(workOfArt: WorkOfArtUiModel) {
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

            itemView.setOnClickListener {
                val workOfArtDomain = WorkOfArt(
                    objectID = workOfArt.objectID,
                    title = workOfArt.title,
                    artistDisplayName = workOfArt.artistDisplayName,
                    artistNationality = workOfArt.artistNationality,
                    objectDate = workOfArt.objectDate,
                    medium = workOfArt.medium,
                    dimensions = workOfArt.dimensions,
                    primaryImage = workOfArt.image
                )

                MuseumObserver.setWorkOfArt(workOfArtDomain)

                val context = itemView.context
                val intent = Intent(context, WorkOfArtDetailActivity::class.java)
                context.startActivity(intent)
            }
        }
    }
}