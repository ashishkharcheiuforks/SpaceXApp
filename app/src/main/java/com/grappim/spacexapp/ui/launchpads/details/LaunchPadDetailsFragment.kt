package com.grappim.spacexapp.ui.launchpads.details

import android.os.Bundle
import android.view.*
import androidx.navigation.fragment.navArgs
import com.google.android.material.chip.Chip
import com.grappim.spacexapp.R
import com.grappim.spacexapp.elv.CustomExpandableListAdapter
import com.grappim.spacexapp.elv.LocationListAdapterItem
import com.grappim.spacexapp.ui.base.BaseFragment
import com.grappim.spacexapp.core.extensions.startBrowser
import kotlinx.android.synthetic.main.fragment_launch_pad_details.*
import timber.log.Timber

class LaunchPadDetailsFragment : BaseFragment() {

  private val args: LaunchPadDetailsFragmentArgs by navArgs()

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? =
    inflater.inflate(R.layout.fragment_launch_pad_details, container, false)

  override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
    super.onCreateOptionsMenu(menu, inflater)
    menu.clear()
    activity?.menuInflater?.inflate(R.menu.toolbar_menu_wiki, menu)
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      R.id.wiki -> {
        startBrowser(args.launchPadModel.wikipedia)
      }
    }
    return super.onOptionsItemSelected(item)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    Timber.d("LaunchPadDetailsFragment - onViewCreated")

    setHasOptionsMenu(true)

    args.launchPadModel.let {
      tvLaunchPadDetailsDetails.text = it.details
      tvLaunchPadDetailsTitle.text = it.siteNameLong
      tvLaunchPadDetailsLaunches.text = getString(
        R.string.successful_attempted,
        it.successfulLaunches ?: 0,
        it.attemptedLaunches ?: 0
      )
      for (item in it.vehiclesLaunched.orEmpty()) {
        val chip = Chip(requireContext())
        chip.text = item
        chip.isClickable = true
        chip.isCheckable = false
        cgLaunchPadDetails.addView(chip)
      }

      elvLaunchPadDetailsLocation.setAdapter(
        CustomExpandableListAdapter(
          requireContext(),
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
