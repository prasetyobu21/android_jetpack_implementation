package com.pras.submission1jetpack.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class FavoritePagerAdapter(activity: FavoriteActivity, data: Bundle): FragmentStateAdapter(activity) {
    private var fragmentBundle: Bundle = data

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when(position){
            0-> {
                fragment = FavMovieFragment()
            }
            1 ->{
                fragment = FavTvFragment()
            }
        }
        fragment?.arguments = this.fragmentBundle
        return fragment as Fragment
    }

}