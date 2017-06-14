package com.example.leagueapp.UI

import android.app.Fragment
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.internal.BottomNavigationItemView
import android.support.design.internal.BottomNavigationMenu
import android.support.design.internal.BottomNavigationMenuView
import android.util.Log

import com.example.leagueapp.R
import java.lang.reflect.Field
import kotlin.reflect.KClass

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Create BottomNavigationView, disable shift mode, then set item selected listener
        val bottomNavigationView = findViewById(R.id.navigation) as BottomNavigationView
        disableShiftMode(bottomNavigationView)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            val transaction = fragmentManager.beginTransaction()
            transaction.replace(R.id.main_framelayout,
                    when (item.itemId) {
                        R.id.action_home -> HomeFragment.newInstance()
                        R.id.action_champs -> HomeFragment.newInstance()
                        R.id.action_items -> HomeFragment.newInstance()
                        R.id.action_search -> HomeFragment.newInstance()
                        else -> run {Log.d(TAG,"what"); null}
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
