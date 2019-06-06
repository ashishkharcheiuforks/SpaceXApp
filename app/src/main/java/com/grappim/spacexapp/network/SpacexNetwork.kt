package com.grappim.spacexapp.network

import com.grappim.spacexapp.model.capsule.CapsuleModel
import com.grappim.spacexapp.model.cores.CoreModel
import com.grappim.spacexapp.model.rocket.RocketModel
import com.grappim.spacexapp.repository.NewRepository
import com.grappim.spacexapp.util.Either
import com.grappim.spacexapp.util.Failure
import retrofit2.Response
import timber.log.Timber

class SpacexNetwork(
  private val networkHandler: NetworkHandler,
  private val service: SpacexService
) : NewRepository {

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

  private fun <T> makeRequest(
    callResponse: Response<T>,
    default: T
  ): Either<Failure, T> {
    Timber.d("NewRepository - makeRequest")
    return try {
      when (callResponse.isSuccessful) {
        true -> Either.Right(callResponse.body() ?: default)
        false -> Either.Left(Failure.ServerError)
      }
    } catch (exception: Throwable) {
      Either.Left(Failure.ServerError)
    }
  }
}