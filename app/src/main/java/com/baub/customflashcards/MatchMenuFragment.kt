package com.baub.customflashcards

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.baub.customflashcards.databinding.FragmentMatchMenuBinding
import androidx.navigation.fragment.findNavController


class MatchMenuFragment : Fragment() {

    private var _binding: FragmentMatchMenuBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = FragmentMatchMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.matchbuttonLevel1.setOnClickListener {
            println("TESTING!!!!!!!!!!!!!")
            findNavController().navigate(R.id.action_matchMenuFragment_to_memoryLevel1Fragment)
        }
        //remove these once we figure out what to do with level 2 word mixing
        binding.matchbuttonLevel2.visibility = View.INVISIBLE
        binding.matchbuttonLevel2.isClickable = false

//        binding.matchbuttonLevel2.setOnClickListener {
//            findNavController().navigate(R.id.action_thirdFragment_to_WFL2)
//        }
    }
}