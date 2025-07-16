package com.example.finalexamgorgadze

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.finalexamgorgadze.databinding.FragmentMainBinding


class MainFragment : Fragment() {

    lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=FragmentMainBinding.inflate(inflater,container,false)
        return binding.root
    }
    private fun loadFragment(f : Fragment){
        parentFragmentManager.beginTransaction().replace(R.id.placeHolder,f).commit()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)= with(binding) {
        super.onViewCreated(view, savedInstanceState)
        bottomNavMenu.selectedItemId = R.id.home
        loadFragment(HomeFragment.newInstance())
        bottomNavMenu.setOnItemSelectedListener{
            when(it.itemId){
                R.id.home ->{
                    loadFragment(HomeFragment.newInstance())
                    true
                }
                R.id.info ->{
                    loadFragment(InfoFragment.newInstance())
                    true
                }
                R.id.user ->{
                    loadFragment(UserFragment.newInstance())
                    true
                }
                else -> false
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }
}