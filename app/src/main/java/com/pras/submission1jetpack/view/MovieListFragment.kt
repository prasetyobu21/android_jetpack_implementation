package com.pras.submission1jetpack.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.pras.submission1jetpack.R
import com.pras.submission1jetpack.databinding.FragmentMovieListBinding
import com.pras.submission1jetpack.model.local.entity.MovieListEntity
import com.pras.submission1jetpack.viewmodel.GetMoviesViewModel
import com.pras.submission1jetpack.viewmodel.GetMoviesViewModelFactory
import com.pras.submission1jetpack.vo.Status

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class MovieListFragment : Fragment() {

    private lateinit var adapter: MovieAdapter
    private lateinit var viewModel: GetMoviesViewModel
    private var _binding: FragmentMovieListBinding? = null
    private val binding get() = _binding!!

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentMovieListBinding.bind(view)

        adapter = MovieAdapter()
//        adapter.notifyDataSetChanged()
        val factory = GetMoviesViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(this, factory)[GetMoviesViewModel::class.java]

        binding.rvMovie.layoutManager = LinearLayoutManager(this@MovieListFragment.context)
        binding.rvMovie.setHasFixedSize(false)
        binding.rvMovie.adapter = adapter

        viewModel.getMovieList().observe(viewLifecycleOwner, {
            if (it != null) {
                when(it.status){
                    Status.LOADING -> showLoading(true)
                    Status.SUCCESS -> {
                        showLoading(false)
                        Log.d("pagedListData: " , it.data.toString())
                        adapter.submitList(it.data)
                    }
                    Status.ERROR -> {
                        showLoading(false)
                        Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })

        adapter.setOnItemClickCallback(object : MovieAdapter.OnItemClickCallback {
            override fun onItemClickCallback(data: MovieListEntity) {
                val intent =
                    Intent(this@MovieListFragment.context, FilmDetailActivity::class.java)
                val bundle = Bundle()
                bundle.putParcelable(FilmDetailActivity.EXTRA_FILM, data)
                intent.putExtra("Bundle", bundle)
                intent.putExtra(FilmDetailActivity.EXTRA_TYPE, "movie")
                intent.putExtra(FilmDetailActivity.EXTRA_ID, data.id.toString())
                startActivity(intent)
            }
        })
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.loadingbar.visibility = View.VISIBLE
        } else {
            binding.loadingbar.visibility = View.GONE
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MovieListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }

        const val EXTRA_CATEGORIES = "extra_categories"
    }
}