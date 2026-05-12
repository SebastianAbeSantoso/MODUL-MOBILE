package com.example.modul3xml.fragment

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.modul3xml.databinding.FragmentDetailsBinding
import com.example.modul3xml.factory.ComicViewModelFactory
import com.example.modul3xml.repository.ComicRepository
import com.example.modul3xml.viewmodel.ComicViewModel
import kotlinx.coroutines.launch

class DetailsFragment : Fragment() {
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ComicViewModel by activityViewModels {
        ComicViewModelFactory(
            appName = "Modul 3 XML",
            repository = ComicRepository()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val isLandscape = resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
        val navController = findNavController()

        val cardWeight = if (isLandscape) 75f else 89f
        val buttonWeight = if (isLandscape) 25f else 11f

        val cardLp = binding.comicCardArea.layoutParams as android.widget.LinearLayout.LayoutParams
        cardLp.weight = cardWeight
        binding.comicCardArea.layoutParams = cardLp

        val btnLp = binding.btnReturn.layoutParams as android.widget.LinearLayout.LayoutParams
        btnLp.weight = buttonWeight
        binding.btnReturn.layoutParams = btnLp

        val titleFontSize = if (isLandscape) 32f else 40f
        val titleMaxLines = if (isLandscape) 1 else 10
        val descFontSize = if (isLandscape) 24f else 20f
        val descMaxLines = if (isLandscape) 3 else 15
        val authorFontSize = if (isLandscape) 24f else 20f
        val authorMaxLines = if (isLandscape) 1 else 3
        val genreFontSize = 20f
        val genreMaxLines = if (isLandscape) 1 else 3
        val buttonFontSize = if (isLandscape) 30f else 20f

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.selectedComic.collect { comic ->
                    comic ?: return@collect

                    val ctx = requireActivity()

                    binding.backgroundImage.setImageResource(comic.backgroundImage)
                    binding.coverImage.setImageResource(comic.coverImage)

                    binding.titleText.apply {
                        text = comic.title
                        textSize = titleFontSize
                        maxLines = titleMaxLines
                    }
                    binding.descriptionText.apply {
                        text = comic.description.joinToString("\n\n") { ctx.getString(it) }
                        textSize = descFontSize
                        maxLines = descMaxLines
                    }
                    binding.authorText.apply {
                        text = comic.author
                        textSize = authorFontSize
                        maxLines = authorMaxLines
                    }
                    binding.genreText.apply {
                        text = comic.genres.joinToString(", ") { ctx.getString(it) }
                        textSize = genreFontSize
                        maxLines = genreMaxLines
                    }
                }
            }
        }

        binding.btnReturn.apply {
            textSize = buttonFontSize
            setOnClickListener { navController.popBackStack() }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}