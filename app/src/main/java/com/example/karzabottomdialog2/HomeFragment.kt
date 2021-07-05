package com.example.karzabottomdialog2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast


class HomeFragment : Fragment(), Communicator {

    private val heading = "Error"
    private val subHeading = "Capture a clear image of front of your Voter ID to proceed"
    private val negativeBtnText = "Exit"
    private val positiveBtnText = "Try Again"
    private val icon = R.drawable.error_with_bg

    private lateinit var b1: Button
    private lateinit var b2: Button


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_home, container, false)

        //----------------------------------------------------------------------------------------------------//
        //----------------------------------------------------------------------------------------------------//

        b1 = view.findViewById(R.id.button1)
        b2 = view.findViewById(R.id.button2)

        b1.setOnClickListener {
            val mbsf = ModalBottomSheetFragment.instance(heading, subHeading, positiveBtnText, negativeBtnText, icon)
            mbsf.setCommunicator(this)
            mbsf.show(parentFragmentManager, "TAG")
        }

        b2.setOnClickListener {
            val mbsf = ModalBottomSheetFragment.instance(heading, subHeading, positiveBtnText, "", icon)
            mbsf.setCommunicator(this)
            mbsf.show(parentFragmentManager, "TAG")
        }


        return view
    }

    //-----------------------------------------------------------------------------------------------------------//
    //------------------Interface methods defined in the HomeFragment--------------------------------------------//


    override fun onPressPositive() {
        Toast.makeText(context, "Positive Click", Toast.LENGTH_SHORT).show()
    }

    override fun onPressNegative() {
        Toast.makeText(context, "Negative Click", Toast.LENGTH_SHORT).show()
    }

}