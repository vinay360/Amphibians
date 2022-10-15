package com.example.amphibians

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.amphibians.network.Amphibian
import com.example.amphibians.ui.ListFragmentDirections
import com.example.amphibians.ui.NameAdapter
import com.example.amphibians.ui.Status

@BindingAdapter("amphibianName")
fun bindName(textView: TextView, amphibian: Amphibian?) {
    amphibian?.let {
        textView.text = it.name
    }
}

@BindingAdapter("listData")
fun bindList(recyclerView: RecyclerView, data: List<Amphibian>?) {
    val adapter = recyclerView.adapter as NameAdapter
    adapter.submitList(data)
}

@BindingAdapter("navigate")
fun bindNav(cardView: CardView, position: Int?) {
    position?.let {
        cardView.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToDetailFragment(position)
            cardView.findNavController().navigate(action)
        }
    }
}

@BindingAdapter("status")
fun setStatus(imageView: ImageView, status: Status) {
    when(status) {
        Status.LOADING -> {
            imageView.setImageResource(R.drawable.loading_animation)
            imageView.visibility = View.VISIBLE
        }
        Status.DONE -> {
            imageView.visibility = View.GONE
        }
        Status.ERROR -> {
            imageView.setImageResource(R.drawable.ic_error)
            imageView.visibility = View.VISIBLE
        }
    }
}