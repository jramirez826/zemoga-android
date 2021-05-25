package com.jramirez.pruebazemoga.presentation.posts.allposts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jramirez.pruebazemoga.databinding.ItemAllPostBinding
import com.jramirez.pruebazemoga.domain.entity.UIPost
import com.jramirez.pruebazemoga.presentation.util.CellClickListener
import com.jramirez.pruebazemoga.presentation.util.CellRemovedListener

class AllPostsAdapter(private val cellClickListener: CellClickListener<UIPost>? = null) :
    RecyclerView.Adapter<AllPostsViewHolder>(), CellRemovedListener {

    private val list: MutableList<UIPost> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllPostsViewHolder {
        val binding = ItemAllPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AllPostsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AllPostsViewHolder, position: Int) {
        val item = list[position]
        with(holder.binding) {
            readMark.visibility = if (!item.isRead && position < 20) View.VISIBLE else View.GONE
            favoriteMark.visibility = if (item.isFavorite) View.VISIBLE else View.GONE
            labDescription.text = item.body
        }
    }

    override fun getItemCount(): Int = list.size

    override fun onRemoveItem(position: Int) {
        notifyItemRemoved(position)
        list.removeAt(position)
    }

    fun update(items: List<UIPost>) {
        list.clear()
        list.addAll(items)
        notifyDataSetChanged()
    }

    fun removeAllItems() {
        notifyItemRangeRemoved(0, itemCount)
        list.clear()
    }
}