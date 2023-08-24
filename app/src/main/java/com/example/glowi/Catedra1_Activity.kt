package com.example.glowi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Catedra1_Activity : AppCompatActivity() {
     val videoId = "BeK7XZMm-4Q"
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catedra1)
        //Rciclerview
        val recyclerView: RecyclerView = findViewById(R.id.indexRecyclerView)
        val indexList = listOf("Índice 1", "Índice 2", "Índice 3", /* ... */)

        val adapter = IndexAdapter(indexList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        //Añadimos el video desde youtube con el idkey del video
        val webView: WebView = findViewById(R.id.webView)
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = WebViewClient()


        val videoUrl = "https://www.youtube.com/embed/$videoId"
        webView.loadUrl(videoUrl)
    }
}
class IndexAdapter(private val indexList: List<String>) : RecyclerView.Adapter<IndexAdapter.IndexViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IndexViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_index, parent, false)
        return IndexViewHolder(view)
    }

    override fun onBindViewHolder(holder: IndexViewHolder, position: Int) {
        val index = indexList[position]
        holder.bind(index)
    }

    override fun getItemCount(): Int {
        return indexList.size
    }

    class IndexViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val indexTextView: TextView = itemView.findViewById(R.id.indexTextView)

        fun bind(index: String) {
            indexTextView.text = index
        }
    }
}
