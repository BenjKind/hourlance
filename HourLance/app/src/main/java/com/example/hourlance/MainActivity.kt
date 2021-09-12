package com.example.hourlance

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.example.hourlance.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    // MY VAR
    lateinit var toggle: ActionBarDrawerToggle
    //

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //
        val drawerLayout: DrawerLayout = findViewById(R.id.drawerLayout)
        val navView: NavigationView = findViewById(R.id.nav_view)

        toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_dashboard -> Toast.makeText(
                    applicationContext,
                    "Clicked Dashboard",
                    Toast.LENGTH_SHORT
                ).show()
                R.id.nav_clients -> Toast.makeText(
                    applicationContext,
                    "Clicked Clients",
                    Toast.LENGTH_SHORT
                ).show()
                R.id.nav_invoices -> Toast.makeText(
                    applicationContext,
                    "Clicked Invoices",
                    Toast.LENGTH_SHORT
                ).show()
                R.id.nav_calendar -> Toast.makeText(
                    applicationContext,
                    "Clicked Calendar",
                    Toast.LENGTH_SHORT
                ).show()
                R.id.nav_settings -> Toast.makeText(
                    applicationContext,
                    "Clicked Settings",
                    Toast.LENGTH_SHORT
                ).show()
            }
            true
        }
        //

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.mainNewButton.setOnClickListener {

            if (binding.newTimeEntryButton.visibility == View.INVISIBLE) {
                binding.newTimeEntryButton.visibility = View.VISIBLE
                binding.newClientEntryButton.visibility = View.VISIBLE
            } else {
                binding.newTimeEntryButton.visibility = View.INVISIBLE
                binding.newClientEntryButton.visibility = View.INVISIBLE
            }
        }

        fun onOptionsItemSelected(item: MenuItem): Boolean {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            // MY CODE
            if (toggle.onOptionsItemSelected(item)) {
                return true
            }
            //
            return super.onOptionsItemSelected(item)
        }

        fun onSupportNavigateUp(): Boolean {
            val navController = findNavController(R.id.nav_host_fragment_content_main)
            return navController.navigateUp(appBarConfiguration)
                    || super.onSupportNavigateUp()
        }
    }
}