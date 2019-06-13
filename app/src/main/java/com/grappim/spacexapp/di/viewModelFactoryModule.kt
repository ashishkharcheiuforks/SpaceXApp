package com.grappim.spacexapp.di

import com.grappim.spacexapp.ui.capsules.CapsuleViewModelFactory
import com.grappim.spacexapp.ui.cores.CoreViewModelFactory
import com.grappim.spacexapp.ui.history.HistoryViewModelFactory
import com.grappim.spacexapp.ui.info.InfoViewModelFactory
import com.grappim.spacexapp.ui.launches.completed.CompletedViewModelFactory
import com.grappim.spacexapp.ui.launches.upcoming.UpcomingViewModelFactory
import com.grappim.spacexapp.ui.launchpads.LaunchPadViewModelFactory
import com.grappim.spacexapp.ui.missions_payloads.MissionViewModelFactory
import com.grappim.spacexapp.ui.rockets.RocketsViewModelFactory
import com.grappim.spacexapp.ui.ships.ShipsViewModelFactory
import com.grappim.spacexapp.ui.social_media.twitter.TwitterViewModelFactory
import org.koin.dsl.module

val viewModuleFactoryModule = module {
  factory { CapsuleViewModelFactory(get(), get(), get()) }
  factory { CoreViewModelFactory(get(), get(), get()) }
  factory { RocketsViewModelFactory(get()) }
  factory { ShipsViewModelFactory(get()) }
  factory { MissionViewModelFactory(get(), get()) }
  factory { InfoViewModelFactory(get()) }
  factory { HistoryViewModelFactory(get()) }
  factory { LaunchPadViewModelFactory(get()) }
  factory { TwitterViewModelFactory(get()) }
  factory { CompletedViewModelFactory(get()) }
  factory { UpcomingViewModelFactory(get()) }
}