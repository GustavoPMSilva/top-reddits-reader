package br.com.gustavopmsilva.topredditsreader.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.gustavopmsilva.topredditsreader.data.domain.Post

class DetailViewModel(post: Post) : ViewModel() {

    private val _postData = MutableLiveData(post)
    val postData: LiveData<Post>
        get() = _postData

    private val _openUrl = MutableLiveData<String?>()
    val openUrl: LiveData<String?>
        get() = _openUrl

    fun openRedditInBrowser() {
        _postData.value?.permalink?.let {
            _openUrl.value = "$BASE_REDDIT_URL$it"
        }
    }

    fun onOpenRedditInBrowserCompleted() {
        _openUrl.value = null
    }

    companion object {
        private const val BASE_REDDIT_URL = "https://reddit.com"
    }
}
