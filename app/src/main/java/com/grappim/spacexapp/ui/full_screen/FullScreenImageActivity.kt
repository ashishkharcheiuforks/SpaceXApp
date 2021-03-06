package com.grappim.spacexapp.ui.full_screen

import android.os.Bundle
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.grappim.spacexapp.R
import com.grappim.spacexapp.core.utils.GlideApp
import com.grappim.spacexapp.core.utils.PARCELABLE_TWITTER_IMAGES
import kotlinx.android.synthetic.main.activity_full_screen_image.*

class FullScreenImageActivity : FullScreenBaseActivity() {

  var args: String? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_full_screen_image)

    intent.apply {
      args = getStringExtra(PARCELABLE_TWITTER_IMAGES)
    }

    args?.apply {
      GlideApp.with(baseContext)
        .load(this)
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(ivFulScreen)
    }
  }

}
