package com.project.nagad.bazaar.features

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.project.nagad.bazaar.R
import com.project.nagad.bazaar.base.BaseActivity
import com.project.nagad.bazaar.features.homeProduct.ui.HomeProductFragment
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        replaceFragment(HomeProductFragment.newInstance())

        bottomNavigation.setOnNavigationItemSelectedListener { menuItem ->

            when (menuItem.itemId) {
                R.id.navigation_home -> {
                    val fragment =
                        HomeProductFragment.newInstance()
                    replaceFragment(fragment)
                    return@setOnNavigationItemSelectedListener true
                }
            }

            false
        }

        bottomNavigation.setOnNavigationItemReselectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    Timber.d("Clicked reselected")
                }
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.fragmentContainer,
                fragment,
                fragment.javaClass.simpleName
            )
            .commit()
    }

}
