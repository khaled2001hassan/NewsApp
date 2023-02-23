package com.example.newsapp2.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp2.R
import com.example.newsapp2.model.ArticlesItem

class ArticleAdapter(var itemList :List<ArticlesItem?>):RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_article,parent,false)
        return ArticleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val item =itemList.get(position)
        holder.authorTextView.text=item?.author
        holder.titleTextView.text=item?.title
        holder.dateTextView.text=item?.publishedAt
        Glide.with(holder.itemView).load(item?.urlToImage).into(holder.articleImage)



    }


    override fun getItemCount(): Int {
        return itemList.size
    }
    fun changeData( newItem :List<ArticlesItem?>){
        itemList = newItem
        notifyDataSetChanged()
    }
    class ArticleViewHolder(var item:View):RecyclerView.ViewHolder(item){
        val titleTextView = item.findViewById<TextView>(R.id.ArticleTitle)
        val authorTextView = item.findViewById<TextView>(R.id.AuthorName)
        val dateTextView = item.findViewById<TextView>(R.id.DateTextView)
        val articleImage = item.findViewById<ImageView>(R.id.ItemImage)
    }
}