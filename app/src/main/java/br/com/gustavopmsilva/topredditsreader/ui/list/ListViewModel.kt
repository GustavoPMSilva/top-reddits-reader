package br.com.gustavopmsilva.topredditsreader.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.gustavopmsilva.topredditsreader.core.base.Resource
import br.com.gustavopmsilva.topredditsreader.data.domain.Post
import br.com.gustavopmsilva.topredditsreader.data.domain.PostList
import br.com.gustavopmsilva.topredditsreader.data.repository.PostsRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ListViewModel(private val postsRepository: PostsRepository) : ViewModel() {

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean>
        get() = _loading

    private val _posts = MutableLiveData<PostList>()
    val posts: LiveData<PostList>
        get() = _posts

    private val _navigateToPostDetail = MutableLiveData<Post?>()
    val navigateToPostDetail: LiveData<Post?>
        get() = _navigateToPostDetail

    fun fetchPosts() {
        fetchPosts(null)
    }

    fun fetchNextPosts() {
        fetchPosts(_posts.value?.after)
    }

    private fun fetchPosts(after: String?) {
        viewModelScope.launch {
            postsRepository.fetchTop(after).collect {
                when (it) {
                    is Resource.Loading ->
                        _loading.value = true
                    is Resource.Success -> {
                        it.data?.let { data ->
                            data.after?.let { _loading.value = false }
                            after?.let {
                                val list = _posts.value?.posts?.toMutableList()
                                    ?.apply { addAll(data.posts) } ?: emptyList()
                                data.posts = list
                            }
                            _posts.value = data
                        }
                    }
                    is Resource.Error ->
                        _loading.value = false
                }
            }
        }
    }

    fun onPostClicked(post: Post) {
        _navigateToPostDetail.value = post.takeIf { it.isImagePost }
    }

    fun onNavigationCompleted() {
        _navigateToPostDetail.value = null
    }
}
