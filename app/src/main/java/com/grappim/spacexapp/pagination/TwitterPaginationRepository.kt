package com.grappim.spacexapp.pagination

import androidx.lifecycle.Transformations
import androidx.paging.Config
import androidx.paging.toLiveData
import com.grappim.spacexapp.model.twitter.UserTimelineModel
import timber.log.Timber

class TwitterPaginationRepository {

  fun getTweets(screenName: String): Listing<UserTimelineModel> {
    Timber.d("TwitterPaginationRepository - getTweets - $screenName")
    val sourceFactory = TwitterDataSourceFactory(screenName)
    val livePagedList = sourceFactory.toLiveData(
      config = Config(
        pageSize = 30,
        enablePlaceholders = false,
        initialLoadSizeHint = 60,
        prefetchDistance = 20
      )
    )

    return Listing(
      pagedList = livePagedList,
      networkState = Transformations.switchMap(sourceFactory.sourceLiveData) {
        it.networkState
      },
      refresh = { sourceFactory.sourceLiveData.value?.invalidate() },
      failure = Transformations.switchMap(sourceFactory.sourceLiveData) {
        it.failure
      }
    )
  }
}