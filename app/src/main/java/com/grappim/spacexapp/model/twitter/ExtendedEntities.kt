package com.grappim.spacexapp.model.twitter


import com.google.gson.annotations.SerializedName

data class ExtendedEntities(
  @SerializedName("media")
  val media: List<Media?>?
)