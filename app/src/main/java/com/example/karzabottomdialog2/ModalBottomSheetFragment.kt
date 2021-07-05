package com.example.karzabottomdialog2

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class ModalBottomSheetFragment : BottomSheetDialogFragment() {

    private val PARAM1 = "param1"
    private val PARAM2 = "param2"
    private val PARAM3 = "param3"
    private val PARAM4 = "param4"
    private val PARAM5 = "param5"

    private lateinit var headingText: String
    private lateinit var subheadingText: String
    private lateinit var positiveButtonText: String
    private lateinit var negativeButtonText: String
    private var iconDraw = 0

    private lateinit var heading: TextView
    private lateinit var subHeading: TextView
    private lateinit var exitBtn: Button
    private lateinit var tryAgainBtn: Button
    private var icon: ImageView? = null

    private lateinit var viewLine: View
    private var communicator: Communicator? = null


    //-----------------------------------------------------------------------------------------------------//

    fun setCommunicator(communicator: Communicator)
    {
        this.communicator = communicator
    }

    //-----------------------------------------------------------------------------------------------------//

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme)

        if (arguments != null)
        {
            headingText = arguments?.getString(PARAM1).toString()
            subheadingText = arguments?.getString(PARAM2).toString()
            positiveButtonText = arguments?.getString(PARAM3).toString()
            negativeButtonText = arguments?.getString(PARAM4).toString()
            iconDraw = arguments?.getInt(PARAM5, R.drawable.error_with_bg)!!.toInt()
        }

    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val bottomSheetDialog = super.onCreateDialog(savedInstanceState)
        val view =  LayoutInflater.from(context).inflate(R.layout.fragment_modal_bottom_sheet, null, false)

        this.isCancelable = false

        //-----------------------------------------------------------------------------------------------------//
        //----------------------------------------------------------------------------------------------------//

        heading = view.findViewById(R.id.tvErrorHeading)
        subHeading = view.findViewById(R.id.tvErrorSubheading)
        exitBtn = view.findViewById(R.id.exitBtn)
        tryAgainBtn = view.findViewById(R.id.tryAgainBtn)
        viewLine = view.findViewById(R.id.view2)
        icon = view.findViewById(R.id.icon_sheet)

        heading.text = headingText
        subHeading.text = subheadingText
        exitBtn.text = negativeButtonText
        tryAgainBtn.text = positiveButtonText
        icon?.setImageResource(iconDraw)


        //-----------------------------------------------------------------------------------------------------//
        //---------------------Setting up buttons according to the values received----------------------------//

        if (negativeButtonText.isNotEmpty())
        {
            exitBtn.visibility = View.VISIBLE
            tryAgainBtn.visibility = View.VISIBLE
            viewLine.visibility = View.VISIBLE
        }
        else
        {
            exitBtn.visibility = View.GONE
            viewLine.visibility = View.GONE
            tryAgainBtn.visibility = View.VISIBLE

            (tryAgainBtn.layoutParams as LinearLayout.LayoutParams).weight = 2.0f
        }

        //-----------------------------------------------------------------------------------------------------//
        //-------------------Passing button click value/response to the Interface function---------------------//


        exitBtn.setOnClickListener {
            communicator?.onPressNegative()
            this.dismiss()
        }

        tryAgainBtn.setOnClickListener {
            communicator?.onPressPositive()
            this.dismiss()
        }


        bottomSheetDialog.setContentView(view)
        return bottomSheetDialog
    }

    //-----------------------------------------------------------------------------------------------------//
    //-----------------------------------------------------------------------------------------------------//

    companion object
    {
        @JvmStatic
        fun instance(heading: String, subheading: String, positiveText: String, negativeText: String, icon: Int): ModalBottomSheetFragment
        {
            return ModalBottomSheetFragment().apply {
                val bundle = Bundle()
                bundle.putString(PARAM1, heading)
                bundle.putString(PARAM2, subheading)
                bundle.putString(PARAM3, positiveText)
                bundle.putString(PARAM4, negativeText)
                bundle.putInt(PARAM5, icon)
                arguments = bundle
            }
        }
    }

}
