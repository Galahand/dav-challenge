package com.example.platzi_challenge.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.platzi_challenge.data.Beer
import com.example.platzi_challenge.databinding.BeerOverviewItemBinding

class BeerAdapter : RecyclerView.Adapter<BeerAdapter.BeerViewHolder>() {

    private var beers: List<Beer> = listOf()
    private var onTap: ((beer: Beer) -> Unit)? = null
    private var onLastVisible: (() -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerViewHolder {
        val binding = BeerOverviewItemBinding.inflate(LayoutInflater.from(parent.context))
        return BeerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BeerViewHolder, position: Int) {
        holder.bind(beers[position])
        if (position + 1 == beers.size) {
            println("DAV: should fetch more")
            onLastVisible?.invoke()
        }
    }

    override fun getItemCount() = beers.size

    fun updateList(beers: List<Beer>) {
        this.beers = beers
        notifyDataSetChanged()
    }

    fun addItems(beers: List<Beer>) {
        val prevSize = this.beers.size
        this.beers = beers
        notifyItemRangeInserted(prevSize, beers.size - prevSize)
    }

    fun setOnTap(onTap: (beer: Beer) -> Unit) {
        this.onTap = onTap
    }

    fun setOnLastVisible(onLastVisible: () -> Unit) {
        this.onLastVisible = onLastVisible
    }

    inner class BeerViewHolder(
        private val binding: BeerOverviewItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(beer: Beer) {
            binding.beer = beer
            binding.root.setOnClickListener { onTap?.invoke(beer) }
        }
    }
}