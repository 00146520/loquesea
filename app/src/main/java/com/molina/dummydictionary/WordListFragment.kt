package com.molina.dummydictionary

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.molina.dummydictionary.databinding.FragmentWordListBinding
import com.molina.dummydictionary.repository.DictionaryRepository
import com.molina.dummydictionary.ui.WordViewModel
import com.molina.dummydictionary.ui.WordViewModelFactory

class WordListFragment : Fragment() {
    private val viewModelFactory by lazy {
        val repository = DictionaryRepository()
        WordViewModelFactory(repository)
    }
    private val viewModel: WordViewModel by viewModels {
        viewModelFactory
    }
    private lateinit var binding: FragmentWordListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_word_list, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val wordListRecyclerView = binding.wordListRecyclerView
        val wordAdapter = WordAdapter()
        wordListRecyclerView.apply {
            adapter = wordAdapter
        }
        viewModel.words.observe(viewLifecycleOwner) { data ->
            wordAdapter.setData(data)
        }
    }

}