package com.baub.customflashcards

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.baub.customflashcards.databinding.FragmentSettingsBinding
import com.baub.customflashcards.databinding.FragmentWFLevel1Binding

class SettingsFragment : Fragment() {
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!



    var score = 0
    var level = 1
    // This property is only valid between onCreateView and
    // onDestroyView.


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharedPref = requireActivity().getSharedPreferences("GlobalSettings", 0)
        level = sharedPref.getInt("setting_level", 1)
        val editor = sharedPref.edit()
        binding.levelText.text = level.toString()
        binding.buttonLevelUp.setOnClickListener {
            if(level<15) {
                level++
                binding.levelText.text = level.toString()
//                editor.putString("setting_level", "setting_value")
                editor.putInt("setting_level", level)
                editor.apply()
            }
            //findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

        binding.buttonLevelDown.setOnClickListener {
            if(level>1) {
                level--
                binding.levelText.text = level.toString()
//                editor.putString("setting_level", "setting_value")
                editor.putInt("setting_level", level)
                editor.apply()
            }
            //findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

    }



}