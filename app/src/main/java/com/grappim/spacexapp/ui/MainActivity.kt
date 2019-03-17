package com.grappim.spacexapp.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import com.grappim.spacexapp.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity(),  NavigationView.OnNavigationItemSelectedListener {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    setSupportActionBar(toolbar)

    val toggle = ActionBarDrawerToggle(
      this,
      drawerLayout,
      R.string.navigation_drawer_open,
      R.string.navigation_drawer_close
    )
    toggle.isDrawerIndicatorEnabled = true
    drawerLayout.addDrawerListener(toggle)
    toggle.syncState()
    navigationView.setNavigationItemSelectedListener(this)
    displaySelectedScreen(R.id.nav_capsules)
  }

  override fun onNavigationItemSelected(p0: MenuItem): Boolean {
    displaySelectedScreen(p0.itemId)
    return true
  }

  private fun displaySelectedScreen(itemId: Int) {
    var fragment: Fragment? = null
    when(itemId){
      R.id.nav_ships->{
      fragment = ShipsFragment()
      }

      R.id.nav_capsules->{
      fragment = CapsuleFragment()
      }
    }
    if (fragment != null) {
      val ft = supportFragmentManager.beginTransaction()
      ft.replace(R.id.contentFrame, fragment)
      ft.commit()
    }
    drawerLayout.closeDrawer(GravityCompat.START)
  }

  override fun onBackPressed() {
    if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
      drawerLayout.closeDrawer(GravityCompat.START)
    } else {
      super.onBackPressed()
    }
  }
}
