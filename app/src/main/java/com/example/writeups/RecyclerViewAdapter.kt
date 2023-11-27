package com.example.writeups

import android.content.Context
import android.media.tv.TvView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter(val context: Context, val ids:ArrayList<Int>, val writups:ArrayList<String>, val authors:ArrayList<String>) : RecyclerView.Adapter<RecyclerViewAdapter.RVViewHolder>() {
    class RVViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val id_view = itemView.findViewById<TextView>(R.id.id_txt)
        val writup_view = itemView.findViewById<TextView>(R.id.writeup_txt)
        val author_view = itemView.findViewById<TextView>(R.id.author_txt)

        init {

        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RVViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_item, parent, false)
        return RVViewHolder(view)
    }


    override fun onBindViewHolder(holder: RecyclerViewAdapter.RVViewHolder, position: Int) {
        holder.id_view.text = ids[position].toString()
        holder.writup_view.text = writups[position]
        holder.author_view.text = authors[position]
    }

    override fun getItemCount(): Int {
        return ids.size
    }
}