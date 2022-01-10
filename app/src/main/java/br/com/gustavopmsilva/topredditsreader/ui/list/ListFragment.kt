package br.com.gustavopmsilva.topredditsreader.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.gustavopmsilva.topredditsreader.databinding.ListFragmentBinding
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListFragment : Fragment() {

    private var _binding: ListFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ListViewModel by viewModel()

    private val postListAdapter: PostListAdapter by inject()
    private lateinit var layoutManager: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        setupObservers()

        viewModel.fetchPosts()
    }

    private fun setupViews() {
        layoutManager = LinearLayoutManager(context)

        with(binding) {
            srlRefresh.setOnRefreshListener {
                viewModel.fetchPosts()
            }
            rcvPosts.layoutManager = layoutManager
            rcvPosts.adapter = postListAdapter
            rcvPosts.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (srlRefresh.isRefreshing) {
                        return
                    }

                    val visibleItemCount = layoutManager.childCount
                    val totalItemCount = layoutManager.itemCount
                    val pastVisibleItems = layoutManager.findFirstVisibleItemPosition()
                    if (pastVisibleItems + visibleItemCount >= totalItemCount) {
                        viewModel.fetchNextPosts()
                    }
                }
            })
        }
    }

    private fun setupObservers() {
        viewModel.loading.observe(viewLifecycleOwner) {
            binding.srlRefresh.isRefreshing = it
        }

        viewModel.posts.observe(viewLifecycleOwner) {
            postListAdapter.posts = it.posts
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = ListFragment()
    }
}