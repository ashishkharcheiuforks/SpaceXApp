package com.grappim.spacexapp.ui.capsules

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.grappim.spacexapp.R
import com.grappim.spacexapp.util.FieldConstants
import kotlinx.android.synthetic.main.fragment_capsule.*

class CapsuleFragment : Fragment() {

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_capsule, container, false)
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    activity?.title = "Capsules"
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    btnGetAllCapsules.setOnClickListener {
      val args = Bundle()
      args.putInt(FieldConstants.CAPSULES_ARGS, 1)
      findNavController().navigate(R.id.nextFragment, args)
    }

    btnGetUpcomingCapsules.setOnClickListener {
      val args = Bundle()
      args.putInt(FieldConstants.CAPSULES_ARGS, 2)
      findNavController().navigate(R.id.nextFragment, args)
    }

    btnGetPastCapsules.setOnClickListener {
      val args = Bundle()
      args.putInt(FieldConstants.CAPSULES_ARGS, 3)
      findNavController().navigate(R.id.nextFragment, args)
    }
  }
}
