package com.example.newsapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.example.newsapp.models.Article

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {


    //inner class means they have access to members of outer class.
    inner class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    lateinit var articleImage:ImageView
    lateinit var articleSource:TextView
    lateinit var articleTitle:TextView
    lateinit var articleDescription:TextView
    lateinit var articleDateTime:TextView


    //differentCallback is used to determine difference between two lists.
    private val differentCallback=object :DiffUtil.ItemCallback<Article>(){
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
           return oldItem.url==newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem==newItem
        }

    }
    //AsyncDiffer is user to determine difference between two lists in background thread.
    var differ=AsyncListDiffer(this,differentCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
      return ArticleViewHolder(
          LayoutInflater.from(parent.context).inflate(R.layout.item_news,parent,false)
      )
    }

    override fun getItemCount(): Int {
       return differ.currentList.size
    }

    //this function takes parameter as Article and returns unit means a function that does not return anything.
    private var onItemClickListener:((Article) -> Unit) ? = null

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        var article=differ.currentList[position]

        articleImage=holder.itemView.findViewById(R.id.articleImage)
        articleSource=holder.itemView.findViewById(R.id.articleSource)
        articleTitle=holder.itemView.findViewById(R.id.articleTitle)
        articleDescription=holder.itemView.findViewById(R.id.articleDescription)
        articleDateTime=holder.itemView.findViewById(R.id.articleDateTime)

        holder.itemView.apply {
            Glide.with(this)
                .load(article.urlToImage).into(articleImage)

            articleSource.text=article.source?.name
            articleTitle.text=article.title
            articleDescription.text=article.description
            articleDateTime.text=article.publishedAt

            setOnClickListener{
                onItemClickListener?.let {
                    it(article)
                }
            }

        }
    }
    fun setOnItemClickListener(listener:(Article)->Unit){
        onItemClickListener=listener
    }

}