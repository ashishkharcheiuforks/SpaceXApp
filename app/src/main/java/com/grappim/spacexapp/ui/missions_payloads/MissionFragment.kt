package com.grappim.spacexapp.ui.missions_payloads

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.grappim.spacexapp.R
import com.grappim.spacexapp.core.extensions.*
import com.grappim.spacexapp.elv.CustomExpandableListAdapter
import com.grappim.spacexapp.model.payloads.PayloadModel
import com.grappim.spacexapp.ui.base.BaseFragment
import com.grappim.spacexapp.util.*
import kotlinx.android.synthetic.main.fragment_mission.*
import org.koin.core.KoinComponent
import org.koin.core.inject
import timber.log.Timber

//todo mission or payload?

class MissionFragment : BaseFragment(), KoinComponent {

  private val args: MissionFragmentArgs by navArgs()
  private val viewModelFactory:MissionViewModelFactory by inject()
  private val viewModel by viewModels<MissionViewModel> { viewModelFactory }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_mission, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    Timber.d("MissionFragment - onViewCreated")

    clMissionFragment.gone()
    pbMission.show()

    viewModel.apply {
      onObserve(onePayload, ::renderPayload)
      onFailure(failure, ::handleFailure)
    }

    getData()
  }

  private fun getData() {
    val missionName = args.missionArgs.name
    viewModel.loadPayloadById(
      payloads[missionName]
        ?: missionName
    )
  }

  private fun renderPayload(payload: PayloadModel?) {
    pbMission.gone()
    clMissionFragment.show()

    payload?.let {
      tvMissionPayloadId.text = it.payloadId
      tvMissionManufacturer.text = it.manufacturer
      tvMissionNationality.text = it.nationality ?: "N/A"
      ivMissionReused.setImageResource(
        setMyImageResource(
          it.reused
        )
      )

      elvMission.setAdapter(
        CustomExpandableListAdapter(
          requireContext(),
          elvMission,
          "Orbit Params",
          it,
          R.layout.layout_elv_mission_item,
          listAdapterItemInit = { view ->
            MissionsPayloadsListAdapterItem(
              view,
              it
            ).fillItemWithData()
          }
        )
      )
    }
  }

  override fun renderFailure(failureText: String) {
    tvMissionPayloadId.showSnackbar(failureText)
    clMissionFragment.gone()
    pbMission.gone()
  }
}
