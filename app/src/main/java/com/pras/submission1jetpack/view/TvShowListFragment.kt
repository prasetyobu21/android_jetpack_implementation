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
import com.pras.submission1jetpack.databinding.FragmentTvShowListBinding
import com.pras.submission1jetpack.model.local.entity.TvListEntity
import com.pras.submission1jetpack.viewmodel.GetTvShowsViewModel
import com.pras.submission1jetpack.viewmodel.GetTvShowViewModelFactory
import com.pras.submission1jetpack.vo.Status

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class TvShowListFragment : Fragment() {

    private lateinit var adapter: TvShowsAdapter
    private lateinit var viewModel: GetTvShowsViewModel
    private var _binding: FragmentTvShowListBinding? = null
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
        return inflater.inflate(R.layout.fragment_tv_show_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentTvShowListBinding.bind(view)

        adapter = TvShowsAdapter()
//        adapter.notifyDataSetChanged()
        val factory = GetTvShowViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(this, factory)[GetTvShowsViewModel::class.java]

        binding.rvMovie.layoutManager = LinearLayoutManager(this@TvShowListFragment.context)
        binding.rvMovie.setHasFixedSize(false)
        binding.rvMovie.adapter = adapter
        viewModel.getTvList().observe(viewLifecycleOwner, {
            if (it != null) {
                when (it.status) {
                    Status.LOADING -> showLoading(true)
                    Status.SUCCESS -> {
                        showLoading(false)
                        Log.d("pagedListData: ", it.data.toString())
                        adapter.submitList(it.data)
                    }
                    Status.ERROR -> {
                        showLoading(false)
                        Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })

        adapter.setOnItemClickCallback(object : TvShowsAdapter.OnItemClickCallback {
            override fun onItemClickCallback(data: TvListEntity) {
                val intent = Intent(this@TvShowListFragment.context, FilmDetailActivity::class.java)
                val bundle = Bundle()
                bundle.putParcelable(FilmDetailActivity.EXTRA_FILM, data)
                intent.putExtra("Bundle", bundle)
                intent.putExtra(FilmDetailActivity.EXTRA_TYPE, "tvShow")
//                intent.putExtra(FilmDetailActivity.EXTRA_TITLE, data.title)
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
            TvShowListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}