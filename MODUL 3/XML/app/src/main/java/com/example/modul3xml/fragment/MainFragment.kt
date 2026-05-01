package com.example.modul3xml.fragment

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.example.modul3xml.R
import com.example.modul3xml.adapter.CarouselAdapter
import com.example.modul3xml.adapter.ComicListAdapter
import com.example.modul3xml.databinding.FragmentMainBinding
import com.example.modul3xml.viewmodel.ComicViewModel

class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ComicViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val isLandscape = resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
        val navController = findNavController()

        binding.toolbar.setOnMenuItemClickListener { menuItem ->
            if (menuItem.itemId == R.id.action_settings) {
                navController.navigate(R.id.action_main_to_settings)
                true
            } else false
        }

        val carouselAdapter = CarouselAdapter(isLandscape, viewModel, navController)
        binding.carouselRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = carouselAdapter
            PagerSnapHelper().attachToRecyclerView(this)
        }

        if (isLandscape) {
            val lp = binding.carouselRecyclerView.layoutParams
            lp.height = resources.getDimensionPixelSize(R.dimen.carousel_height_landscape)
            binding.carouselRecyclerView.layoutParams = lp
        }

        val listAdapter = ComicListAdapter(isLandscape, viewModel, navController)
        binding.listRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = listAdapter
            isNestedScrollingEnabled = false
        }

        viewModel.carouselComics.observe(viewLifecycleOwner) { comics ->
            carouselAdapter.submitList(comics)
        }

        viewModel.listComics.observe(viewLifecycleOwner) { comics ->
            listAdapter.submitList(comics)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
