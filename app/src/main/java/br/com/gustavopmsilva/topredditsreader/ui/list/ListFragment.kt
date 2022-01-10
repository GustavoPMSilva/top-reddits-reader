package br.com.gustavopmsilva.topredditsreader.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.gustavopmsilva.topredditsreader.R
import br.com.gustavopmsilva.topredditsreader.databinding.ListFragmentBinding
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ListFragment : Fragment() {

    private var _binding: ListFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ListViewModel by viewModel()

    private val postListAdapter: PostListAdapter by inject {
        parametersOf(PostListAdapter.OnClickListener { viewModel.onPostClicked(it) })
    }
    private lateinit var layoutManager: GridLayoutManager

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
        requireActivity().title = getString(R.string.top_reddit_posts)

        layoutManager = GridLayoutManager(context, 3)

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

                    val visibleItemCount: Int = layoutManager.childCount
                    val totalItemCount: Int = layoutManager.itemCount
                    val pastVisibleItems: Int = layoutManager.findFirstVisibleItemPosition()

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
            postListAdapter.submitList(it.posts)
        }

        viewModel.navigateToPostDetail.observe(viewLifecycleOwner) {
            it?.let {
                findNavController().navigate(
                    ListFragmentDirections.actionListFragmentToDetailFragment(it)
                )
                viewModel.onNavigationCompleted()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
