package com.grappim.spacexapp.repository

import com.grappim.spacexapp.model.capsule.CapsuleModel
import com.grappim.spacexapp.model.cores.CoreModel
import com.grappim.spacexapp.model.history.HistoryModel
import com.grappim.spacexapp.model.info.InfoModel
import com.grappim.spacexapp.model.launches.LaunchModel
import com.grappim.spacexapp.model.launchpads.LaunchPadModel
import com.grappim.spacexapp.model.payloads.PayloadModel
import com.grappim.spacexapp.model.rocket.RocketModel
import com.grappim.spacexapp.model.ships.ShipModel
import com.grappim.spacexapp.network.NetworkHandlerOld
import com.grappim.spacexapp.network.NetworkHelper
import com.grappim.spacexapp.network.services.SpaceXService
import com.grappim.spacexapp.util.Either
import com.grappim.spacexapp.util.Failure

//todo make one general function for every suspend

class SpaceXRepositoryImpl(
  private val networkHandler: NetworkHandlerOld,
  private val service: SpaceXService
) : SpaceXRepository, NetworkHelper {

  override suspend fun allCapsules(): Either<Failure, List<CapsuleModel>> {
    return when (networkHandler.isConnected) {
      true -> makeRequest(service.getAllCapsules(), emptyList())
      false, null -> Either.Left(Failure.NetworkConnection)
    }
  }

  override suspend fun upcomingCapsules(): Either<Failure, List<CapsuleModel>> {
    return when (networkHandler.isConnected) {
      true -> makeRequest(service.getUpcomingCapsules(), emptyList())
      false, null -> Either.Left(Failure.NetworkConnection)
    }
  }

  override suspend fun pastCapsules(): Either<Failure, List<CapsuleModel>> {
    return when (networkHandler.isConnected) {
      true -> makeRequest(service.getPastCapsules(), emptyList())
      false, null -> Either.Left(Failure.NetworkConnection)
    }
  }

  override suspend fun allRockets(): Either<Failure, List<RocketModel>> {
    return when (networkHandler.isConnected) {
      true -> makeRequest(service.getAllRockets(), emptyList())
      false, null -> Either.Left(Failure.NetworkConnection)
    }
  }

  override suspend fun allCores(): Either<Failure, List<CoreModel>> {
    return when (networkHandler.isConnected) {
      true -> makeRequest(service.getAllCores(), emptyList())
      false, null -> Either.Left(Failure.NetworkConnection)
    }
  }

  override suspend fun upcomingCores(): Either<Failure, List<CoreModel>> {
    return when (networkHandler.isConnected) {
      true -> makeRequest(service.getUpcomingCores(), emptyList())
      false, null -> Either.Left(Failure.NetworkConnection)
    }
  }

  override suspend fun pastCores(): Either<Failure, List<CoreModel>> {
    return when (networkHandler.isConnected) {
      true -> makeRequest(service.getPastCores(), emptyList())
      false, null -> Either.Left(Failure.NetworkConnection)
    }
  }

  override suspend fun allShips(): Either<Failure, List<ShipModel>> {
    return when (networkHandler.isConnected) {
      true -> makeRequest(service.getAllShips(), emptyList())
      false, null -> Either.Left(Failure.NetworkConnection)
    }
  }

  override suspend fun allLaunchPads(): Either<Failure, List<LaunchPadModel>> {
    return when (networkHandler.isConnected) {
      true -> makeRequest(service.getAllLaunchPads(), emptyList())
      false, null -> Either.Left(Failure.NetworkConnection)
    }
  }

  override suspend fun info(): Either<Failure, InfoModel> {
    return when (networkHandler.isConnected) {
      true -> makeRequest(service.getInfo(), InfoModel.empty())
      false, null -> Either.Left(Failure.NetworkConnection)
    }
  }

  override suspend fun history(): Either<Failure, List<HistoryModel>> {
    return when (networkHandler.isConnected) {
      true -> makeRequest(service.getHistory(), emptyList())
      false, null -> Either.Left(Failure.NetworkConnection)
    }
  }

  override suspend fun allPayloads(): Either<Failure, List<PayloadModel>> {
    return when (networkHandler.isConnected) {
      true -> makeRequest(service.getAllPayloads(), emptyList())
      false, null -> Either.Left(Failure.NetworkConnection)
    }
  }

  override suspend fun payloadById(payloadId: String?): Either<Failure, PayloadModel> {
    return when (networkHandler.isConnected) {
      true -> makeRequest(service.getPayloadById(payloadId), PayloadModel.empty())
      false, null -> Either.Left(Failure.NetworkConnection)
    }
  }

  override suspend fun allLaunches(): Either<Failure, List<LaunchModel>> {
    return when (networkHandler.isConnected) {
      true -> makeRequest(service.getAllLaunches(), emptyList())
      false, null -> Either.Left(Failure.NetworkConnection)
    }
  }

  override suspend fun pastLaunches(): Either<Failure, List<LaunchModel>> {
    return when (networkHandler.isConnected) {
      true -> makeRequest(service.getPastLaunches(), emptyList())
      false, null -> Either.Left(Failure.NetworkConnection)
    }
  }

  override suspend fun upcomingLaunches(): Either<Failure, List<LaunchModel>> {
    return when (networkHandler.isConnected) {
      true -> makeRequest(service.getUpcomingLaunches(), emptyList())
      false, null -> Either.Left(Failure.NetworkConnection)
    }
  }

  override suspend fun nextLaunch(): Either<Failure, LaunchModel> {
    return when (networkHandler.isConnected) {
      true -> makeRequest(service.getNextLaunch(), LaunchModel.empty())
      false, null -> Either.Left(Failure.NetworkConnection)
    }
  }

  override suspend fun latestLaunch(): Either<Failure, LaunchModel> {
    return when (networkHandler.isConnected) {
      true -> makeRequest(service.getLatestLaunch(), LaunchModel.empty())
      false, null -> Either.Left(Failure.NetworkConnection)
    }
  }

  override suspend fun oneLaunch(flightNumber: Int?): Either<Failure, LaunchModel> {
    return when (networkHandler.isConnected) {
      true -> makeRequest(service.getOneLaunch(flightNumber), LaunchModel.empty())
      false, null -> Either.Left(Failure.NetworkConnection)
    }
  }
}