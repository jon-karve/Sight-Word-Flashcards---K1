package com.baub.customflashcards

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.baub.customflashcards.databinding.FragmentThirdBinding
import androidx.navigation.fragment.findNavController


class ThirdFragment : Fragment() {

    private var _binding: FragmentThirdBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = FragmentThirdBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonLevel1.setOnClickListener {
            findNavController().navigate(R.id.action_thirdFragment_to_level1)
        }
        //remove these once we figure out what to do with level 2 word mixing
        binding.buttonLevel2.visibility = View.INVISIBLE
        binding.buttonLevel2.isClickable = false

        binding.buttonLevel2.setOnClickListener {
            findNavController().navigate(R.id.action_thirdFragment_to_WFL2)
        }
    }
}