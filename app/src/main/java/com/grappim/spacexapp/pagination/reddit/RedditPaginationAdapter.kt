package com.grappim.spacexapp.pagination.reddit

import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.grappim.spacexapp.R
import com.grappim.spacexapp.model.reddit.RedditModel
import com.grappim.spacexapp.core.extensions.inflateLayout
import com.grappim.spacexapp.core.extensions.setSafeOnClickListener
import com.grappim.spacexapp.core.utils.GlideApp
import kotlinx.android.synthetic.main.layout_reddit_item.view.*

class RedditPaginationAdapter(
  val onClick: (RedditModel?) -> Unit
) : PagedListAdapter<RedditModel, RedditPaginationViewHolder>(MY_DIFF_UTIL) {

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): RedditPaginationViewHolder =
    RedditPaginationViewHolder(
      parent
        .context
        .inflateLayout(R.layout.layout_reddit_item, parent)
    )

  override fun onBindViewHolder(holder: RedditPaginationViewHolder, position: Int) {
    holder.apply {
      model = getItem(position)
      itemView.setSafeOnClickListener { onClick(getItem(position)) }

      GlideApp.with(imagePreview.context)
        .load(getItem(adapterPosition)?.preview?.images?.get(0)?.source?.url ?: "")
        .transition(DrawableTransitionOptions.withCrossFade())
        .centerCrop()
        .apply(RequestOptions().placeholder(R.drawable.glide_placeholder).centerCrop())
        .into(imagePreview)
    }
  }

  companion object {
    val MY_DIFF_UTIL = object : DiffUtil.ItemCallback<RedditModel>() {
      override fun areItemsTheSame(oldItem: RedditModel, newItem: RedditModel): Boolean =
        oldItem == newItem

      override fun areContentsTheSame(oldItem: RedditModel, newItem: RedditModel): Boolean =
        oldItem.name == newItem.name
    }
  }
}

class RedditPaginationViewHolder(
  private val view: View
) : RecyclerView.ViewHolder(view) {
  val imagePreview: AppCompatImageView = view.imagePreview
  var model: RedditModel? = null
    set(value) {
      field = value
      view.apply {
        tvRedditAuthor.text = value?.author
        tvRedditCreatedUTC.text = value?.createdUtc.toString()
        tvRedditScore.text = value?.score.toString()
        tvRedditSelftext.text = value?.selftext
        tvRedditTitle.text = value?.title
      }
    }
}