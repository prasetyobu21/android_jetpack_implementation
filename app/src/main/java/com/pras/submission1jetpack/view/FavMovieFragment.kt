package com.pras.submission1jetpack.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.pras.submission1jetpack.R
import com.pras.submission1jetpack.databinding.FragmentFavMovieBinding
import com.pras.submission1jetpack.model.local.entity.MovieListEntity
import com.pras.submission1jetpack.viewmodel.FavoriteMovieViewModel
import com.pras.submission1jetpack.viewmodel.FavoriteMovieViewModelFactory
import com.pras.submission1jetpack.viewmodel.GetTvShowViewModelFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FavMovieFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FavMovieFragment : Fragment() {

    private lateinit var adapter: FavMovieAdapter
    private lateinit var viewModel: FavoriteMovieViewModel
    private var _binding: FragmentFavMovieBinding? = null
    private val binding get() = _binding!!

    // TODO: Rename and change types of parameters
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
        return inflater.inflate(R.layout.fragment_fav_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentFavMovieBinding.bind(view)

        adapter = FavMovieAdapter()

        val factory = FavoriteMovieViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(this, factory)[FavoriteMovieViewModel::class.java]

        binding.rvMovie.layoutManager = LinearLayoutManager(this@FavMovieFragment.context)
        binding.rvMovie.setHasFixedSize(false)
        binding.rvMovie.adapter = adapter
        showLoading(true)
        viewModel.getFavorieMovie().observe(viewLifecycleOwner, {
            adapter.submitList(it)
            Log.d("favMovie", it.toString())
            showLoading(false)

            adapter.setOnItemClickCallback(object : FavMovieAdapter.OnItemClickCallback {
                override fun onItemClickCallback(data: MovieListEntity) {
                    val intent =
                        Intent(this@FavMovieFragment.context, FilmDetailActivity::class.java)
                    val bundle = Bundle()
                    bundle.putParcelable(FilmDetailActivity.EXTRA_FILM, data)
                    intent.putExtra("Bundle", bundle)
                    intent.putExtra(FilmDetailActivity.EXTRA_TYPE, "movie")
//                intent.putExtra(FilmDetailActivity.EXTRA_TITLE, data.original_title)
                    intent.putExtra(FilmDetailActivity.EXTRA_ID, data.id.toString())
                    startActivity(intent)
                }
            })
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
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FavMovieFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FavMovieFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}