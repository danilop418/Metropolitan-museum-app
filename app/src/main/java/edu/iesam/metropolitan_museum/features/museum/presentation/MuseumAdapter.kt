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
        private val image: ImageView = view.findViewById(R.id.primaryImage)
        private val name: TextView = view.findViewById(R.id.)

        fun bind(workOfArt: WorkOfArtUiModel) {
            name.text = workOfArt.title
            image.load(workOfArt.image) {
                crossfade(true)
                placeholder(R.drawable.ic_launcher_background)
                error(R.drawable.ic_launcher_foreground)
            }

            itemView.setOnClickListener {
                val workOfArtDomain = WorkOfArt(
                    id = workOfArt.id,
                    title = workOfArt.title,
                    artist = workOfArt.artistDisplayName,
                    date = workOfArt.objectDate,
                    description = workOfArt.medium,
                    image = workOfArt.image
                )

                MuseumObserver.setWorkOfArt(workOfArtDomain)

                val context = itemView.context
                val intent = Intent(context, WorkOfArtDetailActivity::class.java)
                context.startActivity(intent)
            }
        }
    }
}