package com.jramirez.pruebazemoga.presentation.ui.detailpost

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jramirez.pruebazemoga.databinding.ItemCommentBinding
import com.jramirez.pruebazemoga.domain.entity.UIComment

class CommentAdapter :
    RecyclerView.Adapter<CommentViewHolder>() {

    private val list: MutableList<UIComment> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val binding = ItemCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CommentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val item = list[position]
        holder.binding.labComment.text = item.body
    }

    override fun getItemCount(): Int = list.size

    fun update(items: List<UIComment>) {
        list.clear()
        list.addAll(items)
        notifyDataSetChanged()
    }
}