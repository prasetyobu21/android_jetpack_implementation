package com.pras.submission1jetpack.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.pras.submission1jetpack.R
import com.pras.submission1jetpack.databinding.FragmentFavTvBinding
import com.pras.submission1jetpack.model.local.entity.TvListEntity
import com.pras.submission1jetpack.viewmodel.FavoriteTvViewModel
import com.pras.submission1jetpack.viewmodel.FavoriteTvViewModelFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FavTvFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FavTvFragment : Fragment() {

    private lateinit var adapter: FavTvAdapter
    private lateinit var viewModel: FavoriteTvViewModel
    private var _binding: FragmentFavTvBinding? = null
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
        return inflater.inflate(R.layout.fragment_fav_tv, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentFavTvBinding.bind(view)

        adapter = FavTvAdapter()

        val factory = FavoriteTvViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(this, factory)[FavoriteTvViewModel::class.java]

        binding.rvMovie.layoutManager = LinearLayoutManager(this@FavTvFragment.context)
        binding.rvMovie.setHasFixedSize(false)
        binding.rvMovie.adapter = adapter
        showLoading(true)
        viewModel.getFavoriteTv().observe(viewLifecycleOwner, {
            adapter.submitList(it)
            showLoading(false)

            adapter.setOnItemClickCallback(object : FavTvAdapter.OnItemClickCallback{
                override fun onItemClickCallback(data: TvListEntity) {
                    val intent =
                        Intent(this@FavTvFragment.context, FilmDetailActivity::class.java)
                    val bundle = Bundle()
                    bundle.putParcelable(FilmDetailActivity.EXTRA_FILM, data)
                    intent.putExtra("Bundle", bundle)
                    intent.putExtra(FilmDetailActivity.EXTRA_TYPE, "tvShow")
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
         * @return A new instance of fragment FavTvFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FavTvFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}