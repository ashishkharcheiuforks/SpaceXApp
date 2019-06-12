package com.grappim.spacexapp.di

import com.grappim.spacexapp.network.createOkHttpClient
import com.grappim.spacexapp.network.createRetrofit
import com.grappim.spacexapp.network.createTwitterOauthInterceptor
import com.grappim.spacexapp.network.services.TwitterService
import com.grappim.spacexapp.pagination.TwitterPaginationRepository
import com.grappim.spacexapp.util.KODEIN_TWITTER_INTERCEPTOR
import com.grappim.spacexapp.util.KODEIN_TWITTER_OK_HTTP_CLIENT
import com.grappim.spacexapp.util.KODEIN_TWITTER_RETROFIT
import com.grappim.spacexapp.util.TWITTER_API_BASE_URL
import org.koin.core.qualifier.named
import org.koin.dsl.module

val twitterModule = module {
  single(named(KODEIN_TWITTER_INTERCEPTOR)) { createTwitterOauthInterceptor() }
  single(named(KODEIN_TWITTER_OK_HTTP_CLIENT)) {
    createOkHttpClient(
      get(
        named(
          KODEIN_TWITTER_INTERCEPTOR
        )
      )
    )
  }
  single(named(KODEIN_TWITTER_RETROFIT)) {
    createRetrofit(
      TWITTER_API_BASE_URL,
      get(named(KODEIN_TWITTER_OK_HTTP_CLIENT))
    )
  }
  single { TwitterService(get(named(KODEIN_TWITTER_RETROFIT))) }
  single { TwitterPaginationRepository() }
}