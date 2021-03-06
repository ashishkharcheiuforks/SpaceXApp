package com.grappim.spacexapp.recyclerview

import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.grappim.spacexapp.R
import com.grappim.spacexapp.core.extensions.getOffsetDateTime
import com.grappim.spacexapp.core.utils.DateTimeUtils
import com.grappim.spacexapp.model.launches.LaunchModel
import com.grappim.spacexapp.core.extensions.inflateLayout
import com.grappim.spacexapp.core.extensions.setMyImageResource
import com.grappim.spacexapp.core.extensions.setSafeOnClickListener
import com.grappim.spacexapp.core.extensions.show
import kotlinx.android.synthetic.main.layout_launches_item.view.*

class LaunchesAdapter(
  val onClick: (LaunchModel) -> Unit
) : RecyclerView.Adapter<LaunchesAdapter.LaunchesViewHolder>(), Filterable {

  private var items: List<LaunchModel> = emptyList()
  private var filteredList: List<LaunchModel> = items

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): LaunchesViewHolder =
    LaunchesViewHolder(
      parent
        .context
        .inflateLayout(R.layout.layout_launches_item, parent)
    )

  override fun getItemCount() = filteredList.size

  override fun onBindViewHolder(
    holder: LaunchesViewHolder,
    position: Int
  ) {
    holder.apply {
      model = filteredList[position]
      itemView.setSafeOnClickListener { onClick(filteredList[position]) }
    }
  }

  fun loadItems(newItems: List<LaunchModel>) {
    items = newItems
    filteredList = newItems
    notifyDataSetChanged()
  }

  override fun getFilter(): Filter =
    object : Filter() {
      override fun performFiltering(constraint: CharSequence?): FilterResults {
        val filteredResults: List<LaunchModel>? = if (constraint?.length == 0) {
          items
        } else {
          getFilteredResults(constraint = constraint.toString().toLowerCase())
        }
        val results = FilterResults()
        results.values = filteredResults
        return results
      }

      override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
        filteredList = results?.values as List<LaunchModel>
        notifyDataSetChanged()
      }
    }

  private fun getFilteredResults(constraint: String): List<LaunchModel> {
    val results = mutableListOf<LaunchModel>()
    for (i in items) {
      if (i.missionName != null && i.missionName.toLowerCase().contains(constraint)) {
        results.add(i)
      }
    }
    return results
  }

  class LaunchesViewHolder(
    private val view: View
  ) : RecyclerView.ViewHolder(view) {
    var model: LaunchModel? = null
      set(value) {
        field = value
        view.apply {
          tvLaunchesFlightNumber.text = value?.flightNumber.toString()
          tvLaunchesMissionName.text = value?.missionName
          tvLaunchesDate.text = value?.launchDateUtc?.let { date ->
            DateTimeUtils.getDateTimeFormatter4().format(date.getOffsetDateTime())
          } ?: let {
            "Unknown"
          }
          if (value?.launchSuccess != null) {
            groupLaunchesItem.show()
            ivLaunchesItemLaunchSuccess
              .setImageResource(
                setMyImageResource(
                  value.launchSuccess
                )
              )
          }
        }
      }
  }
}