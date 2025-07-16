package com.example.finalexamgorgadze

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.finalexamgorgadze.Adapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finalexamgorgadze.databinding.FragmentHomeBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: Adapter
    private val games = mutableListOf<Games>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentHomeBinding.bind(view)
        adapter = Adapter(games) { savedGame ->
            if (!SavedGames.set.contains(savedGame)) {
                SavedGames.set.add(savedGame)
                Toast.makeText(requireContext(), "Saved!", Toast.LENGTH_SHORT).show()
            }
        }

        binding.recview.layoutManager = LinearLayoutManager(requireContext())
        binding.recview.adapter = adapter

        fetchGamesFromFirebase()
    }

    private fun fetchGamesFromFirebase() {
        val db = Firebase.firestore

        db.collection("Games")
            .get()
            .addOnSuccessListener { querySnapshot ->
                games.clear()
                for (document in querySnapshot.documents) {
                    try {
                        val game = document.toObject(Games::class.java)
                        game?.let {
                            games.add(it)
                            Log.d("Firestore", "Loaded game: ${it.Title}")
                        }
                    } catch (e: Exception) {
                        Log.e("Firestore", "Error parsing document ${document.id}", e)
                    }
                }
                adapter.notifyDataSetChanged()
            }
            .addOnFailureListener { exception ->
                Log.e("Firestore", "Error getting documents", exception)
                Toast.makeText(requireContext(), "Error loading games", Toast.LENGTH_SHORT).show()
            }
    }companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}







