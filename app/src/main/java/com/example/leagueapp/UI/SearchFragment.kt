package com.example.leagueapp.UI

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by Mason on 6/12/2017.
 */

class SearchFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?
                              , savedInstanceState: Bundle): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }
    companion object{
        private val TAG = "SearchFragment"
        @JvmStatic fun newInstance(): SearchFragment {
           return SearchFragment()
        }
    }
}
