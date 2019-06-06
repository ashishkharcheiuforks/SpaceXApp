package com.grappim.spacexapp.repository

import com.grappim.spacexapp.model.capsule.CapsuleModel
import com.grappim.spacexapp.model.rocket.RocketModel
import com.grappim.spacexapp.util.Either
import com.grappim.spacexapp.util.Failure

interface NewRepository {

  suspend fun allRockets(): Either<Failure, List<RocketModel>>

  suspend fun allCapsules(): Either<Failure, List<CapsuleModel>>

  suspend fun upcomingCapsules(): Either<Failure, List<CapsuleModel>>

  suspend fun pastCapsules(): Either<Failure, List<CapsuleModel>>

}


