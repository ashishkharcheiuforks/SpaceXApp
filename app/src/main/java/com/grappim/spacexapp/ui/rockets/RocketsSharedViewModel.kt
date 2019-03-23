package com.grappim.spacexapp.ui.rockets

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.grappim.spacexapp.model.rocket.RocketModel
import com.grappim.spacexapp.network.API
import com.grappim.spacexapp.util.fetchData

class RocketsSharedViewModel(
  private val api: API
) : ViewModel(), LifecycleObserver {

  val allRockets = MutableLiveData<List<RocketModel>>()

  fun getAllRockets() {
    fetchData(api.getAllRockets(), allRockets)
  }

}