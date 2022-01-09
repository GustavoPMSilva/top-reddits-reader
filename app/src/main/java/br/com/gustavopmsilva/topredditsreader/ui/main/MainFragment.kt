package br.com.gustavopmsilva.topredditsreader.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.gustavopmsilva.topredditsreader.core.base.Resource
import br.com.gustavopmsilva.topredditsreader.databinding.MainFragmentBinding
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {

    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModel()

    private val postAdapter: PostAdapter by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        setupObservers()

        viewModel.fetchPosts("")
    }

    private fun setupViews() {
        with(binding) {
            srlRefresh.setOnRefreshListener {
                postAdapter.posts = emptyList()
                viewModel.fetchPosts("")
            }
            rcvPosts.adapter = postAdapter
        }
    }

    private fun setupObservers() {
        viewModel.posts.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> binding.srlRefresh.isRefreshing = true
                is Resource.Success -> {
                    binding.srlRefresh.isRefreshing = false

                    it.data?.data?.posts?.let { posts ->
                        val newList = postAdapter.posts.toMutableList().apply { addAll(posts) }
                        postAdapter.posts = newList
                    }
                }
                is Resource.Error -> binding.srlRefresh.isRefreshing = false
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}
