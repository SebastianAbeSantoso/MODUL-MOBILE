package com.example.modul4xml.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.modul4xml.R
import com.example.modul4xml.databinding.ItemCarouselBinding
import com.example.modul4xml.model.Comic
import com.example.modul4xml.viewmodel.ComicViewModel

class CarouselAdapter(
    private val isLandscape: Boolean,
    private val viewModel: ComicViewModel,
    private val navController: NavController
) : ListAdapter<Comic, CarouselAdapter.ViewHolder>(DiffCallback()) {

    class ViewHolder(val binding: ItemCarouselBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCarouselBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val comic = getItem(position)
        val ctx = holder.itemView.context

        with(holder.binding) {
            backgroundImage.setImageResource(comic.backgroundImage)
            coverImage.setImageResource(comic.coverImage)

            titleText.apply {
                text = comic.title
                textSize = if (isLandscape) 32f else 20f
                maxLines = 1
            }
            descriptionText.apply {
                text = comic.description.joinToString("\n\n") { ctx.getString(it) }
                textSize = if (isLandscape) 24f else 15f
                maxLines = if (isLandscape) 2 else 5
            }
            authorText.apply {
                text = comic.author
                textSize = if (isLandscape) 24f else 20f
                maxLines = 1
            }
            genreText.apply {
                text = comic.genres.joinToString(", ") { ctx.getString(it) }
                textSize = if (isLandscape) 20f else 15f
                maxLines = 1
            }

            val navigate = {
                viewModel.selectComic(comic)
                navController.navigate(R.id.action_main_to_details)
            }
            coverImage.setOnClickListener { navigate() }
            infoColumn.setOnClickListener { navigate() }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Comic>() {
        override fun areItemsTheSame(oldItem: Comic, newItem: Comic) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Comic, newItem: Comic) = oldItem == newItem
    }
}
