package br.com.gustavopmsilva.topredditsreader.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.gustavopmsilva.topredditsreader.core.base.Resource
import br.com.gustavopmsilva.topredditsreader.data.model.TopPostsResponse
import br.com.gustavopmsilva.topredditsreader.data.repository.PostsRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel(private val postsRepository: PostsRepository) : ViewModel() {

    private val _posts = MutableLiveData<Resource<TopPostsResponse>>(Resource.Loading())
    val posts: LiveData<Resource<TopPostsResponse>>
        get() = _posts

    fun fetchPosts() {
        fetchPosts("")
    }

    fun fetchNextPosts() {
        fetchPosts(_posts.value?.data?.data?.after ?: "")
    }

    private fun fetchPosts(after: String) {
        viewModelScope.launch {
            postsRepository.fetchTop(after).collect { _posts.value = it }
        }
    }
}
