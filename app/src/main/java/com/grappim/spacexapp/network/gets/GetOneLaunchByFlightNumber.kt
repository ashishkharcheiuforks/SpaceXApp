package com.grappim.spacexapp.network.gets

import com.grappim.spacexapp.model.launches.LaunchModel
import com.grappim.spacexapp.repository.SpaceXRepository
import com.grappim.spacexapp.util.Either
import com.grappim.spacexapp.util.Failure
import com.grappim.spacexapp.util.UseCase

class GetOneLaunchByFlightNumber(
  private val spaceXRepository: SpaceXRepository
) : UseCase<LaunchModel, GetOneLaunchByFlightNumber.Params>() {
  override suspend fun run(params: Params): Either<Failure, LaunchModel> =
    spaceXRepository.oneLaunch(params.flightNumber)

  data class Params(
    val flightNumber: Int?
  )
}