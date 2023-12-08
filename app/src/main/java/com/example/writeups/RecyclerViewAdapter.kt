package com.example.writeups

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.media.tv.TvView
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter(val activity: Activity, val context: Context, val ids:ArrayList<Int>, val writups:ArrayList<String>, val authors:ArrayList<String>, val titles:ArrayList<String>) : RecyclerView.Adapter<RecyclerViewAdapter.RVViewHolder>() {
    class RVViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val id_view = itemView.findViewById<TextView>(R.id.id_txt)
        val writup_view = itemView.findViewById<TextView>(R.id.writeup_txt)
        val author_view = itemView.findViewById<TextView>(R.id.author_txt)
        val title_view = itemView.findViewById<TextView>(R.id.title_txt)
        val mainLayout = itemView.findViewById<LinearLayout>(R.id.mainLayout)
        val anim = AnimationUtils.loadAnimation(itemView.context, R.anim.translate_ani)



        init {
            mainLayout.startAnimation(anim)
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
//        holder.id_view.text = ids[position].toString()
        holder.writup_view.text = writups[position]
        holder.author_view.text = authors[position]
        holder.title_view.text = titles[position]

        holder.mainLayout.setOnClickListener {
            val intent = Intent(context, UpdateWriteupsActivity::class.java)
            intent.putExtra("id",ids[position].toString())
            intent.putExtra("title",titles[position])
            intent.putExtra("writeup",writups[position])
            intent.putExtra("author",authors[position])
            activity.startActivityForResult(intent, 1)
        }
    }

    override fun getItemCount(): Int {
        return ids.size
    }
}