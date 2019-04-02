package com.grappim.spacexapp.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.ExpandableListView
import android.widget.TextView
import androidx.annotation.LayoutRes
import com.grappim.spacexapp.R

class CustomExpandableListAdapter(
  val context: Context,
  private val expandableListView: ExpandableListView,
  private val header: String,
  private val body: Any,
  @LayoutRes private val childLayout: Int,
  val listAdapterItemInit: (view: View) -> Unit
) : BaseExpandableListAdapter() {

  override fun isChildSelectable(
    groupPosition: Int, childPosition: Int
  ): Boolean = false

  override fun hasStableIds(): Boolean = false

  override fun getChildrenCount(groupPosition: Int): Int = 1

  override fun getChild(groupPosition: Int, childPosition: Int): Any =
    body

  override fun getGroupId(groupPosition: Int): Long = groupPosition.toLong()

  override fun getChildView(
    groupPosition: Int,
    childPosition: Int,
    isLastChild: Boolean,
    convertView: View?,
    parent: ViewGroup?
  ): View {
    var cv: View? = convertView
    if (cv == null) {
      cv = LayoutInflater.from(context).inflate(childLayout, null)
    }
    cv?.apply {
      listAdapterItemInit(this)
    }
    return cv!!
  }

  override fun getChildId(groupPosition: Int, childPosition: Int): Long = childPosition.toLong()

  override fun getGroupCount(): Int = 1

  override fun getGroup(groupPosition: Int): Any =
    header

  override fun getGroupView(
    groupPosition: Int,
    isExpanded: Boolean,
    convertView: View?,
    parent: ViewGroup?
  ): View {
    var cv: View? = convertView
    if (cv == null) {
      cv = LayoutInflater.from(context).inflate(R.layout.layout_elv_mission_group_item, null)
    }
    cv?.findViewById<TextView>(R.id.tvElvMissionGroupTitle)
      ?.apply {
        text = header
        setOnClickListener {
          if (expandableListView.isGroupExpanded(groupPosition)) {
            expandableListView.collapseGroup(groupPosition)
          } else {
            expandableListView.expandGroup(groupPosition)
          }
        }
      }
    return cv!!
  }
}