package com.beatrix.debug.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.beatrix.debug.R
import com.beatrix.debug.databinding.ActivityMainBinding
import com.beatrix.debug.views.fragments.DashboardFragment
import com.beatrix.debug.views.fragments.InfoFragment
import com.beatrix.debug.views.fragments.SettingsFragment
import kotlinx.android.synthetic.main.activity_main.*

/**
 * This activity is responsible only for holding the navigation bar
 * and switching between fragments
 */
class MainActivity : AppCompatActivity() {

    // declare binding
    private lateinit var binding: ActivityMainBinding

    // fragments used for switching between them in nav bar
    private val dashboardFragment = DashboardFragment()
    private val infoFragment = InfoFragment()
    private val settingsFragment = SettingsFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // set default fragment - dashboard
        replaceFragment(dashboardFragment)

        // depending on user click, the fragment will change
        bottomNavigationBar.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.ic_dashboard -> replaceFragment(dashboardFragment) // dashboard
                R.id.ic_settings -> replaceFragment(settingsFragment) // settings
                R.id.ic_info -> replaceFragment(infoFragment) // info
            }
            true
        }
    }

    // Function which allows to switch between fragments(replacing one with the other)
    private fun replaceFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, fragment)
            commit()
        }
}