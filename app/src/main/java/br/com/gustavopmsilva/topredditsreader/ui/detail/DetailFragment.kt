package br.com.gustavopmsilva.topredditsreader.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import br.com.gustavopmsilva.topredditsreader.databinding.DetailFragmentBinding
import coil.load
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class DetailFragment : Fragment() {

    private var _binding: DetailFragmentBinding? = null
    private val binding get() = _binding!!

    private val args: DetailFragmentArgs by navArgs()

    private val viewModel: DetailViewModel by viewModel { parametersOf(args.post) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
    }

    private fun setupObservers() {
        viewModel.postData.observe(viewLifecycleOwner) { post ->
            with(binding) {
                requireActivity().title = post.subredditName
                tvTitle.text = post.title
                post.image?.let { url ->
                    ivImage.load(
                        HtmlCompat.fromHtml(url, HtmlCompat.FROM_HTML_MODE_LEGACY).toString()
                    )
                }
                tvVotes.text = post.ups.toString()
                tvNumComments.text = post.numComments.toString()
                tvAuthor.text = post.author
                btnOpen.setOnClickListener {
                    viewModel.openRedditInBrowser()
                }
            }
        }

        viewModel.openUrl.observe(viewLifecycleOwner) {
            it?.let { url ->
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(browserIntent)
                viewModel.onOpenRedditInBrowserCompleted()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
