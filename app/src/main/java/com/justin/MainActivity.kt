package com.justin

import android.content.ClipData.Item
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import android.util.Log
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.navigation.NavigationView
import com.justin.newsapplication.R
import com.justin.ui.viewModel.NewsFeedViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private val viewModel: NewsFeedViewModel by viewModels {
        NewsFeedViewModel.Factory
    }

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment

        navController = navHostFragment.navController

        val drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)
        appBarConfiguration = AppBarConfiguration(setOf(R.id.newsFeedFragment4), drawerLayout)


        val navView = findViewById<NavigationView>(R.id.navigationView)

        // Set up navigation item selected listener
        navView.setNavigationItemSelectedListener { menuItem ->
            Log.d("debugging", menuItem.itemId.toString())
            Log.d("debugging", R.id.BusinessFragment.toString())
            when (menuItem.itemId) {
                R.id.GeneralFragment -> viewModel.setCategory("top")
                R.id.BusinessFragment -> viewModel.setCategory("business")
                R.id.EntertainmentFragment -> viewModel.setCategory("entertainment")
                R.id.SportsFragment -> viewModel.setCategory("sports")
                R.id.HealthFragment -> viewModel.setCategory("health")
                R.id.ScienceFragment -> viewModel.setCategory("science")
                R.id.TechnologyFragment -> viewModel.setCategory("technology")
                else -> {}
            }

            true
        }


        findViewById<Toolbar>(R.id.toolBar).setupWithNavController(
            navController,
            appBarConfiguration
        )




        if (!Environment.isExternalStorageManager()) {
            startActivity(
                Intent(
                    Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION,
                    Uri.fromParts("package", packageName, null)
                )
            )
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    fun hideToolbar() {
        findViewById<AppBarLayout>(R.id.appbar).isVisible = false
    }

    fun showToolbar() {
        findViewById<AppBarLayout>(R.id.appbar).isVisible = true
    }

}