package com.project.nagad.bazaar.features.homeProduct.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.nagad.bazaar.R
import com.project.nagad.presentation.model.StoriesBanner
import kotlinx.android.synthetic.main.layout_item_success_stories_vp.view.*

class StoriesViewPagerAdapter(
    private val items: List<StoriesBanner>,
    private val context: Context
) :
    RecyclerView.Adapter<StoriesViewPagerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.layout_item_success_stories_vp,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context)
            .load(items[position].image)
            .placeholder(R.color.brand_green)
            .into(holder.storyImageView)

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val storyImageView: ImageView = itemView.storiesImageView
    }
}