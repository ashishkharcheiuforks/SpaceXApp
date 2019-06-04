package com.grappim.spacexapp.recyclerview

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.grappim.spacexapp.R
import com.grappim.spacexapp.util.*
import kotlinx.android.synthetic.main.layout_twitter_item_image.view.*
import timber.log.Timber

class TwitterItemImageAdapter(
  val onImageClick: () -> Unit
) : RecyclerView.Adapter<TwitterItemImageViewHolder>() {

  private var items: List<String> = emptyList()

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): TwitterItemImageViewHolder =
    TwitterItemImageViewHolder(
      parent
        .context
        .inflateLayout(R.layout.layout_twitter_item_image)
    )

  override fun onBindViewHolder(holder: TwitterItemImageViewHolder, position: Int) {
    holder.apply {
      val lp = twitterImage.layoutParams
      when (items.size) {
        1, 2 -> {
          lp.height = 200.px
        }
        else -> {
          lp.height = 100.px
        }
      }
      twitterImage.layoutParams = lp
      GlideApp.with(cl.context)
        .load(items[position])
        .transition(DrawableTransitionOptions.withCrossFade())
        .centerCrop()
        .roundCorners(16)
        .into(twitterImage)
      twitterImage.setOnClickListener {
        onImageClick()
      }
    }
  }

  override fun getItemCount(): Int {
    Timber.d("TwitterItemImageAdapter - getItemCount() = ${items.size}")
    return items.size
  }

  fun loadItems(newItems: List<String>) {
    items = newItems
    notifyDataSetChanged()
  }
}

class TwitterItemImageViewHolder(
  private val view: View
) : RecyclerView.ViewHolder(view) {
  val cl: ConstraintLayout = view.clTwitterItemImage
  val twitterImage: ImageView = view.ivTwitterItemImage

}