package com.grappim.spacexapp.model.twitter


import com.google.gson.annotations.SerializedName

data class UserMention(
  @SerializedName("id")
  val id: Int?,
  @SerializedName("id_str")
  val idStr: String?,
  @SerializedName("indices")
  val indices: List<Int?>?,
  @SerializedName("name")
  val name: String?,
  @SerializedName("screen_name")
  val screenName: String?
)