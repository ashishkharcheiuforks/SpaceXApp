package com.grappim.spacexapp.pagination.reddit

import androidx.lifecycle.MutableLiveData
import androidx.paging.ItemKeyedDataSource
import com.grappim.spacexapp.model.reddit.RedditModel
import com.grappim.spacexapp.network.NetworkHandler
import com.grappim.spacexapp.network.NetworkHelper
import com.grappim.spacexapp.network.services.RedditService
import com.grappim.spacexapp.pagination.NetworkState
import com.grappim.spacexapp.util.Failure
import org.koin.core.KoinComponent
import org.koin.core.inject

class RedditDataSource(
  private val subreddit: String? = null
) : ItemKeyedDataSource<String, RedditModel>(), KoinComponent, NetworkHelper {

  private val service: RedditService by inject()
  private val networkHandler: NetworkHandler by inject()

  val networkState = MutableLiveData<NetworkState>()
  val failure = MutableLiveData<Failure>()

  override fun loadInitial(
    params: LoadInitialParams<String>,
    callback: LoadInitialCallback<RedditModel>
  ) {
  }

  override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<RedditModel>) {
  }

  override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<RedditModel>) {
  }

  override fun getKey(item: RedditModel): String = item.name ?: ""
}