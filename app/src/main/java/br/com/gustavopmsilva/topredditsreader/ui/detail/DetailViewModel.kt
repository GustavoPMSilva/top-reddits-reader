package br.com.gustavopmsilva.topredditsreader.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.gustavopmsilva.topredditsreader.data.domain.Post

class DetailViewModel(post: Post) : ViewModel() {

    private val _postData = MutableLiveData(post)
    val postData: LiveData<Post>
        get() = _postData
}
