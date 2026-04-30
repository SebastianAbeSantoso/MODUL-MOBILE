package com.example.modul3xml.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.modul3xml.databinding.FragmentSettingsBinding
import androidx.core.content.edit
import androidx.fragment.app.activityViewModels
import com.example.modul3xml.viewmodel.ComicViewModel

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ComicViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()

        binding.btnSwitchLanguage.setOnClickListener {
            val currentLocales = AppCompatDelegate.getApplicationLocales()
            val currentLang = if (!currentLocales.isEmpty) {
                currentLocales[0]?.language
            } else {
                java.util.Locale.getDefault().language
            }

            val newLang = if (currentLang == "id" || currentLang == "in") "en" else "id"

            AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(newLang))
        }

        binding.btnReturn.setOnClickListener {
            navController.popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}