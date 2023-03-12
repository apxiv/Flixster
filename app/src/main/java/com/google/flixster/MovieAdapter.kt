package com.google.flixster

import android.content.res.Configuration
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MovieAdapter(
    private val movies: List<Movie>,
    private val itemListener: OnListFragmentInteractionListener
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.itemView.apply {
            findViewById<TextView>(R.id.movie_title).text = movies[position].title
            findViewById<TextView>(R.id.movie_overview).text = movies[position].overview
        }

        val orientation = holder.itemView.resources.configuration.orientation

        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Glide.with(holder.itemView)
                .load("https://image.tmdb.org/t/p/w500/${movies[position].posterUrl}")
                .centerCrop()
                .placeholder(R.drawable.loading)
                .error(R.drawable.popcorn)
                .into(holder.itemView.findViewById(R.id.movie_poster))
        } else {
            // portrait orientation
            Glide.with(holder.itemView)
                .load("https://image.tmdb.org/t/p/w500/${movies[position].posterUrl}")
                .centerInside()
                .placeholder(R.drawable.loading)
                .error(R.drawable.popcorn)
                .into(holder.itemView.findViewById(R.id.movie_poster))
        }

    }

}