package com.sadio.Miniproject.apis.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sadio.Miniproject.apis.jokes.model.JokesUi
import com.sadio.Miniproject.databinding.ItemsJokesBinding


val jokesDiffUtils = object : DiffUtil.ItemCallback<JokesUi>() {

    override fun areItemsTheSame(oldItem: JokesUi, newItem: JokesUi): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: JokesUi, newItem: JokesUi): Boolean {
        return oldItem == newItem
    }

}

class JokeViewHolder(
    val binding: ItemsJokesBinding,
    onJokeClickListener: (jokesUi: JokesUi, view: View, clickId: Int) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    private lateinit var ui: JokesUi

    init {
        binding.root.setOnClickListener {
            onJokeClickListener(ui, itemView, 0)
        }
        binding.buttonDeleteJoke.setOnClickListener {
            onJokeClickListener(ui, itemView, 1)
        }
    }

    fun bind(jokesUi: JokesUi) {

        ui = jokesUi

        binding.itemJokesCategory.text = "category :" + jokesUi.category
        binding.itemJokesSetup.text = "Setup: "+ " \n " + jokesUi.setup
        binding.itemJokesDelivery.text = "Delivery:"+ " \n " + jokesUi.delivery

    }
}



class JokesAdapter(
    private val onJokeClickListener: (jokesUi: JokesUi, view: View, clickId: Int) -> Unit,
) : ListAdapter<JokesUi, JokeViewHolder>(jokesDiffUtils) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        when (viewType) {
            MyItemType.ROW.type -> {
                JokeViewHolder(
                    ItemsJokesBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    ), onJokeClickListener
                )
            }
            else -> throw RuntimeException("Wrong view type received $viewType")
        }

    override fun onBindViewHolder(holder: JokeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

enum class MyItemType(val type: Int) {
    ROW(0)
}