package com.client.player.ui.playlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.client.player.R


class MediaChooserAdapter(
    private val data: List<String>,
    private val clickListener: ItemClickListener
) : RecyclerView.Adapter<MediaChooserAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.view_uri_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.textView.text = data[position]
    }

    override fun getItemCount(): Int {
        return data.size
    }

    interface ItemClickListener {
        fun onItemClick(view: View, position: Int)
    }


    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        val textView: TextView = view.findViewById(R.id.media_item_description)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            clickListener.onItemClick(view, bindingAdapterPosition)
        }
    }
}
