package com.jramirez.pruebazemoga.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jramirez.pruebazemoga.databinding.ActivityMainBinding
import com.jramirez.pruebazemoga.presentation.main.allposts.AllPostsFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val allPostsFragment: AllPostsFragment = AllPostsFragment.newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    private fun setUpBinding() {
        with(binding) {
            val sectionsPagerAdapter =
                SectionsPagerAdapter(this@MainActivity, supportFragmentManager)
            viewPager.adapter = sectionsPagerAdapter
            tabs.setupWithViewPager(viewPager)
            fab.setOnClickListener {
                allPostsFragment.removeAllPosts()
            }
        }
    }
}