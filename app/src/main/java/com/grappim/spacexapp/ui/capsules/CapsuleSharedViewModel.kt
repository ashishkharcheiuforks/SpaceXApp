package com.grappim.spacexapp.ui.capsules

import androidx.lifecycle.*
import com.grappim.spacexapp.model.capsule.CapsuleModel
import com.grappim.spacexapp.network.API
import com.grappim.spacexapp.util.fetchData

class CapsuleSharedViewModel(
  private val api: API
) : ViewModel(), LifecycleObserver {

  val allCapsules = MutableLiveData<List<CapsuleModel>>()

  val upcomingCapsules = MutableLiveData<List<CapsuleModel>>()

  val pastCapsules = MutableLiveData<List<CapsuleModel>>()

  @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
  fun getPastCapsules() {
    fetchData(api.getPastCapsules(), pastCapsules)
  }

  @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
  fun getUpcomingCapsules() {
    fetchData(api.getUpcomingCapsules(), upcomingCapsules)
  }

  @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
  fun getAllCapsules() {
    fetchData(api.getCapsules(), allCapsules)
  }

}