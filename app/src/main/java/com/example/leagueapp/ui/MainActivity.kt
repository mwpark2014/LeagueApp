package com.example.leagueapp.ui

import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.internal.BottomNavigationItemView
import android.support.design.internal.BottomNavigationMenuView
import android.util.Log

import com.example.leagueapp.R
import com.example.leagueapp.R.id.navigation
import java.lang.reflect.Field

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val addHomeTrans = fragmentManager.beginTransaction()
        addHomeTrans.replace(R.id.main_framelayout, HomeFragment.newInstance())
        addHomeTrans.commit()

        //Create BottomNavigationView, disable shift mode, then set item selected listener
        val bottomNavigationView = navigation as BottomNavigationView
        disableShiftMode(bottomNavigationView)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            val transaction = fragmentManager.beginTransaction()
            transaction.replace(R.id.main_framelayout,
                    when (item.itemId) {
                        R.id.action_home -> HomeFragment.newInstance()
                        R.id.action_champs -> ChampsFragment.newInstance()
                        R.id.action_items -> ItemsFragment.newInstance()
                        R.id.action_search -> SearchFragment.newInstance()
                        else -> run {Log.d(TAG,"Unidentified Action"); null}
                    }
            )
            transaction.commit()
            true
        }
    }

    private fun disableShiftMode(view: BottomNavigationView) {
        val menuView = view.getChildAt(0) as BottomNavigationMenuView
        try {
            val shiftingMode: Field = menuView.javaClass.getDeclaredField("mShiftingMode")
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);
            for (i in 0 until menuView.getChildCount()) {
                val item: BottomNavigationItemView = menuView.getChildAt(i) as BottomNavigationItemView
                //noinspection RestrictedApi
                item.setShiftingMode(false);
                // set once again checked value, so view will be updated
                //noinspection RestrictedApi
                item.setChecked(item.getItemData().isChecked());
            }
        } catch (e: NoSuchFieldException) {
            Log.e("BNVHelper", "Unable to get shift mode field", e);
        } catch (e: IllegalAccessException) {
            Log.e("BNVHelper", "Unable to change value of shift mode", e);
        }
    }
    companion object {
        val TAG = "MainActivity"
    }
}
