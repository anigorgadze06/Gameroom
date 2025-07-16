package com.example.finalexamgorgadze

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.finalexamgorgadze.databinding.FragmentUserBinding


class UserFragment : Fragment(R.layout.fragment_user) {

    private lateinit var binding: FragmentUserBinding
    private lateinit var adapter: SavedPostersAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentUserBinding.bind(view)
        adapter = SavedPostersAdapter(SavedGames.set.toMutableList())

        binding.recyclerview.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.recyclerview.adapter = adapter
    }
    companion object {
        @JvmStatic
        fun newInstance() = UserFragment()
    }
}




