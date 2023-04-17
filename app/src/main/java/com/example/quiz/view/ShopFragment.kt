package com.example.quiz.view

import android.app.AlertDialog
import android.app.WallpaperManager
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quiz.viewModel.QuizViewModel
import com.example.quiz.R
import com.example.quiz.databinding.FragmentShopBinding

class ShopFragment : Fragment() {
    private lateinit var viewModel: QuizViewModel
    lateinit var binding: FragmentShopBinding
    private lateinit var adapter: WallpapersAdapter
    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProvider(requireActivity())[QuizViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShopBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getWallpapers()
        setUpAdapter()
        viewModel.wallpapersLD.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    private fun setUpAdapter() {
        adapter = WallpapersAdapter()
        binding.wallpapersRecycler.adapter = adapter
        binding.wallpapersRecycler.layoutManager = LinearLayoutManager(requireContext())
        adapter.onWallpaperClickListener = {
            val dialog = AlertDialog.Builder(requireContext())
                .setMessage(R.string.are_you_sure)
                .setPositiveButton(R.string.yes) { _, _ ->
                    if ((requireContext() as MainActivity).buyWallpaper(it.price)) {
                        Toast.makeText(context, R.string.bought_successful, Toast.LENGTH_SHORT)
                            .show()
                        setWallpaper(it.image)
                    }
                }
                .setNegativeButton(R.string.no) { _, _ ->
                }
                .create()
            dialog.show()
        }
    }

    private fun setWallpaper(resId: Int) {
        val myWallpaperManager = WallpaperManager.getInstance(requireContext())
        myWallpaperManager.setResource(resId)
    }
}