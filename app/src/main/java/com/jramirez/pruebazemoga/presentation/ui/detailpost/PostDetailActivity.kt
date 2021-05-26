package com.jramirez.pruebazemoga.presentation.ui.detailpost

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.jramirez.pruebazemoga.databinding.ActivityPostDetailBinding
import com.jramirez.pruebazemoga.domain.entity.UIPost
import com.jramirez.pruebazemoga.presentation.util.IntentConstants
import kotlinx.android.synthetic.main.activity_post_detail.*

class PostDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPostDetailBinding
    private lateinit var postDetailViewModel: PostDetailViewModel
    private val adapter: CommentAdapter by lazy { CommentAdapter() }
    private var isFavorite: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var post: UIPost? = null
        intent.extras?.let {
            post = it.getParcelable(IntentConstants.UI_POST)
        }
        post?.let {
            postDetailViewModel =
                ViewModelProvider(
                    this, PostDetailViewModelFactory(it)
                ).get(PostDetailViewModel::class.java)
            setUpObserver()
            setUpBinding(it)
            postDetailViewModel.getUser()
            postDetailViewModel.getComments()
            this.isFavorite = false
        }
    }

    private fun setUpObserver() {
        with(postDetailViewModel) {
            liveComments.observe(this@PostDetailActivity, {
                adapter.update(it)
            })
            liveUser.observe(this@PostDetailActivity, {
                with(binding) {
                    labUserName.text = it.name
                    labUserEmail.text = it.email
                    labUserPhone.text = it.phone
                    labUserWebsite.text = it.website
                }
            })
            liveIntent.observe(this@PostDetailActivity, {
                setResult(Activity.RESULT_OK, it)
                finish()
            })
        }
    }

    private fun setUpBinding(post: UIPost) {
        with(binding) {
            setSupportActionBar(toolbar)
            labPostTitle.text = post.title
            labPostDescription.text = post.body
            rvComments.adapter = adapter
            menuFavorite.isSelected = post.isFavorite
            menuFavorite.setOnClickListener {
                menuFavorite.isSelected = !menuFavorite.isSelected
                postDetailViewModel.onFavorite(menuFavorite.isSelected)
            }
        }
    }

    override fun onBackPressed() {
        postDetailViewModel.onBackPressed()
    }
}