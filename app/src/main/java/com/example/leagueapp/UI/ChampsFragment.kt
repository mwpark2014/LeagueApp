package com.example.leagueapp.UI

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.leagueapp.R

/**
 * Created by Mason on 6/12/2017.
 */

class ChampsFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?
                              , savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(R.layout.champs_fragment, container, false)
        rootView.tag = TAG

        return rootView
    }
    companion object{
        private val TAG = "ChampsFragment"
        @JvmStatic fun newInstance(): ChampsFragment {
           return ChampsFragment()
        }
    }
}
