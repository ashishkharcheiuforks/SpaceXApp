package com.grappim.spacexapp.network.gets

import com.grappim.spacexapp.model.rocket.RocketModel
import com.grappim.spacexapp.repository.SpaceXRepository
import com.grappim.spacexapp.util.Either
import com.grappim.spacexapp.util.Failure
import com.grappim.spacexapp.util.UseCase

class GetRockets(
  private val spaceXRepository: SpaceXRepository
) : UseCase<List<RocketModel>, UseCase.None>() {
  override suspend fun run(params: None): Either<Failure, List<RocketModel>> =
    spaceXRepository.allRockets()
}