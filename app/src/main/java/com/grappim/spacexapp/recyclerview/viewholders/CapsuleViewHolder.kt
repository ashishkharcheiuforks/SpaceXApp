package com.grappim.spacexapp.recyclerview.viewholders

import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.grappim.spacexapp.core.extensions.getOffsetDateTime
import com.grappim.spacexapp.core.utils.DateTimeUtils
import com.grappim.spacexapp.model.capsule.CapsuleModel
import kotlinx.android.synthetic.main.layout_capsule_item.view.*

class CapsuleViewHolder(
  private val view: View
) : RecyclerView.ViewHolder(view) {
  val btnCapsuleSpecs: Button = view.btnCapsuleSpecs
  var capsule: CapsuleModel? = null
    set(value) {
      field = value
      view.apply {
        tvCapsuleSerial.text = value?.capsuleSerial
        tvCapsuleStatus.text = value?.status?.capitalize()
        tvCapsuleOriginalLaunch.text =
          if (value?.originalLaunch == null) {
            "Unknown"
          } else {
            DateTimeUtils.getDateTimeFormatter3().format(value.originalLaunch.getOffsetDateTime())
          }
        tvCapsuleType.text = value?.type
        tvCapsuleNumberOfMissions.text = value?.missions?.size.toString()
      }
    }
}