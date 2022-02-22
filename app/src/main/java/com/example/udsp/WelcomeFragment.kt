package com.example.udsp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigator
import androidx.navigation.findNavController
import androidx.transition.TransitionInflater
import com.example.udsp.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment() {
    private var _binding: FragmentWelcomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.startButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_welcomeFragment_to_questionsFragment)
        }

        binding.aboutButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_welcomeFragment_to_aboutFragment)
        }

        binding.instructionsButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_welcomeFragment_to_instructionsFragment)
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}