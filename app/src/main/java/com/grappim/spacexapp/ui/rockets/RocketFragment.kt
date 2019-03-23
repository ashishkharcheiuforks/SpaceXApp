package com.grappim.spacexapp.ui.rockets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

import com.grappim.spacexapp.R
import kotlinx.android.synthetic.main.fragment_rocket.*

class RocketFragment : Fragment() {

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_rocket, container, false)
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    activity?.title = "Rockets"
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    btnGetAllRockets.setOnClickListener {
      val f = GetRocketsFragment()
      if (activity?.supportFragmentManager != null) {
        val ft: FragmentTransaction =
          (activity?.supportFragmentManager as FragmentManager).beginTransaction()
        ft.replace(R.id.contentFrame, f)
        ft.addToBackStack(null)
        ft.commit()
      }
    }
  }
}
