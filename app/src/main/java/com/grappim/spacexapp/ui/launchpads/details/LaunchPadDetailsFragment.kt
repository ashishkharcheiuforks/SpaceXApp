package com.grappim.spacexapp.ui.launchpads.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.google.android.material.chip.Chip
import com.grappim.spacexapp.R
import com.grappim.spacexapp.util.CustomExpandableListAdapter
import kotlinx.android.synthetic.main.fragment_launch_pad_details.*
import timber.log.Timber

class LaunchPadDetailsFragment : Fragment() {

  val args: LaunchPadDetailsFragmentArgs by navArgs()

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_launch_pad_details, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    Timber.d("LaunchPadDetailsFragment - onViewCreated")
    super.onViewCreated(view, savedInstanceState)

    args.launchPadModel.let {
      tvLaunchPadDetailsDetails.text = it.details
      tvLaunchPadDetailsTitle.text = it.siteNameLong
      tvLaunchPadDetailsLaunches.text = getString(
        R.string.successful_attempted,
        it.successfulLaunches ?: 0,
        it.attemptedLaunches ?: 0
      )
      for (item in it.vehiclesLaunched.orEmpty()) {
        val chip = Chip(context)
        chip.text = item
        chip.isClickable = true
        chip.isCheckable = false
        cgLaunchPadDetails.addView(chip)
      }

      elvLaunchPadDetailsLocation.setAdapter(
        CustomExpandableListAdapter(
          context!!,
          elvLaunchPadDetailsLocation,
          "Location",
          it,
          R.layout.layout_elv_launch_pad_details_location,
          listAdapterItemInit = { view ->
            LocationListAdapterItem(
              view,
              it
            ).fillItemsWithData()
          }
        )
      )
    }
  }
}