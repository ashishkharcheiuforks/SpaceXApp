package com.grappim.spacexapp.ui.launchpads.details

import android.view.View
import com.grappim.spacexapp.ElvAdapterItem
import com.grappim.spacexapp.model.launchpads.LaunchPadModel
import kotlinx.android.synthetic.main.layout_elv_launch_pad_details_location.view.*

class LocationListAdapterItem(
  val view: View,
  private val launchPad: LaunchPadModel
) : ElvAdapterItem {
  override fun fillItemsWithData() {
    view.apply {
      tvElvLaunchPadLocation.text = launchPad.location?.name
      tvElvLaunchPadRegion.text = launchPad.location?.region
      tvElvLaunchPadLatitude.text = launchPad.location?.latitude.toString()
      tvElvLaunchPadLongitude.text = launchPad.location?.longitude.toString()
    }
  }
}