package com.jramirez.pruebazemoga.presentation.main.allposts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import com.jramirez.pruebazemoga.databinding.FragmentMainBinding
import com.jramirez.pruebazemoga.domain.entity.UIPost
import com.jramirez.pruebazemoga.presentation.application.ZemogaApplication
import com.jramirez.pruebazemoga.presentation.util.CellClickListener
import com.jramirez.pruebazemoga.presentation.util.DeleteItemCallback

/**
 * A placeholder fragment containing a simple view.
 */
class AllPostsFragment : Fragment(), CellClickListener<UIPost> {

    private lateinit var allPostsViewModel: AllPostsViewModel
    private var _binding: FragmentMainBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val adapter: AllPostsAdapter = AllPostsAdapter(this)
    private val deleteItemCallback: DeleteItemCallback = DeleteItemCallback(adapter)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        allPostsViewModel =
            ViewModelProvider(this, AllPostsViewModelFactory(ZemogaApplication.instance))
                .get(AllPostsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val root = binding.root
        createObserver()
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        allPostsViewModel.getPosts()
        binding.rvPosts.adapter = adapter
        val itemTouchHelper = ItemTouchHelper(deleteItemCallback)
        itemTouchHelper.attachToRecyclerView(binding.rvPosts)
    }

    private fun createObserver() {
        allPostsViewModel.livePosts.observe(this, {
            adapter.update(it)
        })
    }

    fun removeAllPosts(){
        adapter.removeAllItems()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCellClickListener(item: UIPost) {
        Toast.makeText(activity, item.id, Toast.LENGTH_SHORT).show()
    }

    companion object {

        @JvmStatic
        fun newInstance(): AllPostsFragment {
            return AllPostsFragment()
        }
    }
}