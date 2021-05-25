package com.jramirez.pruebazemoga.presentation.posts

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import com.jramirez.pruebazemoga.R
import com.jramirez.pruebazemoga.databinding.ActivityPostsBinding
import com.jramirez.pruebazemoga.domain.entity.UIPost
import com.jramirez.pruebazemoga.presentation.application.ZemogaApplication
import com.jramirez.pruebazemoga.presentation.posts.allposts.AllPostsAdapter
import com.jramirez.pruebazemoga.presentation.posts.allposts.AllPostsViewModel
import com.jramirez.pruebazemoga.presentation.posts.allposts.AllPostsViewModelFactory
import com.jramirez.pruebazemoga.presentation.util.CellClickListener
import com.jramirez.pruebazemoga.presentation.util.DeleteItemCallback

class PostsActivity : AppCompatActivity(), CellClickListener<UIPost> {

    private lateinit var binding: ActivityPostsBinding
    private val adapter: AllPostsAdapter = AllPostsAdapter(this)
    private val deleteItemCallback: DeleteItemCallback = DeleteItemCallback(adapter)
    private lateinit var allPostsViewModel: AllPostsViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        allPostsViewModel =
            ViewModelProvider(this, AllPostsViewModelFactory(ZemogaApplication.instance))
                .get(AllPostsViewModel::class.java)
        setUpBinding()
        setUpObserver()
        allPostsViewModel.getPosts()
    }

    private fun setUpBinding() {
        with(binding) {
            setSupportActionBar(toolbar)
            rvPosts.adapter = adapter
            binding.rvPosts.adapter = adapter
            val itemTouchHelper = ItemTouchHelper(deleteItemCallback)
            itemTouchHelper.attachToRecyclerView(binding.rvPosts)
            fab.setOnClickListener {
                adapter.removeAllItems()
            }
        }
    }

    private fun setUpObserver() {
        allPostsViewModel.livePosts.observe(this, {
            adapter.update(it)
        })
    }

    override fun onCellClickListener(item: UIPost) {
        Toast.makeText(this, item.id, Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_refresh -> allPostsViewModel.getPosts()
        }
        return super.onOptionsItemSelected(item)
    }
}