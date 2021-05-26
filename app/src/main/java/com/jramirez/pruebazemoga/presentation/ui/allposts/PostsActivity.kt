package com.jramirez.pruebazemoga.presentation.ui.allposts

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import com.google.android.material.tabs.TabLayout
import com.jramirez.pruebazemoga.R
import com.jramirez.pruebazemoga.databinding.ActivityPostsBinding
import com.jramirez.pruebazemoga.domain.entity.UIPost
import com.jramirez.pruebazemoga.presentation.application.ZemogaApplication
import com.jramirez.pruebazemoga.presentation.util.DeleteItemCallback
import com.jramirez.pruebazemoga.presentation.util.IntentConstants

class PostsActivity : AppCompatActivity(),
    TabLayout.OnTabSelectedListener {

    private lateinit var binding: ActivityPostsBinding
    private val adapter: AllPostsAdapter by lazy { AllPostsAdapter(allPostsViewModel) }
    private val deleteItemCallback: DeleteItemCallback by lazy { DeleteItemCallback(adapter) }
    private lateinit var allPostsViewModel: AllPostsViewModel
    private lateinit var tabAll: TabLayout.Tab
    private lateinit var tabFavorite: TabLayout.Tab


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        allPostsViewModel =
            ViewModelProvider(this)
                .get(AllPostsViewModel::class.java)
        setUpBinding()
        setUpObserver()
        allPostsViewModel.getPosts()
    }

    private fun setUpBinding() {
        with(binding) {
            setSupportActionBar(toolbar)
            tabAll = tabs.newTab().setId(1).setText(R.string.tab_all_post)
            tabFavorite = tabs.newTab().setId(2).setText(R.string.tab_favorite_post)
            tabs.addTab(tabAll)
            tabs.addTab(tabFavorite)
            tabs.addOnTabSelectedListener(this@PostsActivity)
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
        with(allPostsViewModel) {
            livePosts.observe(this@PostsActivity, {
                adapter.update(it)
            })
            liveIntent.observe(this@PostsActivity, {
                startActivityForResult(it, IntentConstants.REQUEST_CODE)
            })
        }
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

    override fun onTabSelected(tab: TabLayout.Tab?) {
        when (tab?.id) {
            tabAll.id -> allPostsViewModel.allPostsTab()
            tabFavorite.id -> allPostsViewModel.favoritePostsTab()
        }
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {
    }

    override fun onTabReselected(tab: TabLayout.Tab?) {
        onTabSelected(tab)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IntentConstants.REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val post = data?.getParcelableExtra<UIPost>(IntentConstants.UI_POST)
            post?.let {
                adapter.updateItem(it)
            }
        }
    }
}