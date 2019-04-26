package com.id.zul.news.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.id.zul.news.R
import com.id.zul.news.model.Article
import com.id.zul.news.utils.DateConverter
import com.squareup.picasso.Picasso

class DataAdapter(private val results: MutableList<Article>, private var context: Context) :
    RecyclerView.Adapter<DataAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        context = parent.context

        val view = LayoutInflater.from(context)
            .inflate(R.layout.template_recycler, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return results.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(results[position])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val textViewTitle = itemView.findViewById<TextView>(R.id.text_title)
        private val textViewDate = itemView.findViewById<TextView>(R.id.text_date)
        private val textViewDescription = itemView.findViewById<TextView>(R.id.text_description)
        private val textViewAuthors = itemView.findViewById<TextView>(R.id.text_authors)
        private val imageView = itemView.findViewById<ImageView>(R.id.image_template)

        fun bindItem(results: Article) {
            textViewTitle.text = results.title
            textViewDate.text = DateConverter.formatDate(results.publishedAt)
            textViewAuthors.text = results.author
            textViewDescription.text = results.description

            Picasso.get()
                .load(results.urlToImage)
                .into(imageView)
        }
    }

}