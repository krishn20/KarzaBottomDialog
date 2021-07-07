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


class ModalBottomSheetFragment private constructor(
    private val headingR: String?,
    private val subheadingR: String?,
    private val positiveTextR: String?,
    private val negativeTextR: String?,
    private val iconR: Int?,
    private val communicatorR: Communicator?
    ) : BottomSheetDialogFragment() {


    private lateinit var heading: TextView
    private lateinit var subHeading: TextView
    private lateinit var exitBtn: Button
    private lateinit var tryAgainBtn: Button
    private var icon: ImageView? = null
    private lateinit var viewLine: View
//    private var communicator: Communicator? = null


//    //-----------------------------------------------------------------------------------------------------//
//
//    fun setCommunicator(communicator: Communicator) {
//        this.communicator = communicator
//    }
//
//    //-----------------------------------------------------------------------------------------------------//

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme)
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val bottomSheetDialog = super.onCreateDialog(savedInstanceState)
        val view =
            LayoutInflater.from(context).inflate(R.layout.fragment_modal_bottom_sheet, null, false)

        this.isCancelable = false

        //-----------------------------------------------------------------------------------------------------//
        //----------------------------------------------------------------------------------------------------//

        heading = view.findViewById(R.id.tvErrorHeading)
        subHeading = view.findViewById(R.id.tvErrorSubheading)
        exitBtn = view.findViewById(R.id.exitBtn)
        tryAgainBtn = view.findViewById(R.id.tryAgainBtn)
        viewLine = view.findViewById(R.id.view2)
        icon = view.findViewById(R.id.icon_sheet)

        heading.text = headingR
        subHeading.text = subheadingR
        exitBtn.text = negativeTextR
        tryAgainBtn.text = positiveTextR
        if (iconR != null) {
            icon?.setImageResource(iconR)
        }


        //-----------------------------------------------------------------------------------------------------//
        //---------------------Setting up buttons according to the values received----------------------------//

        if (!negativeTextR.isNullOrBlank()) {
            exitBtn.visibility = View.VISIBLE
            tryAgainBtn.visibility = View.VISIBLE
            viewLine.visibility = View.VISIBLE
        } else {
            exitBtn.visibility = View.GONE
            viewLine.visibility = View.GONE
            tryAgainBtn.visibility = View.VISIBLE

            (tryAgainBtn.layoutParams as LinearLayout.LayoutParams).weight = 2.0f
        }

        //-----------------------------------------------------------------------------------------------------//
        //-------------------Passing button click value/response to the Interface function---------------------//


        exitBtn.setOnClickListener {
            communicatorR?.onPressNegative()
            this.dismiss()
        }

        tryAgainBtn.setOnClickListener {
            communicatorR?.onPressPositive()
            this.dismiss()
        }


        bottomSheetDialog.setContentView(view)
        return bottomSheetDialog
    }

    //-----------------------------------------------------------------------------------------------------//
    //-----------------------------------------------------------------------------------------------------//

    data class Builder(
        var headingR: String? = null,
        var subheadingR: String? = null,
        var positiveTextR: String? = null,
        var negativeTextR: String? = null,
        var iconR: Int? = null,
        var communicatorR: Communicator? = null
    ) {

        fun heading(heading: String) = apply { this.headingR = heading }
        fun subheading(subheading: String) = apply { this.subheadingR = subheading }
        fun positiveText(positiveText: String) = apply { this.positiveTextR = positiveText }
        fun negativeText(negativeText: String) = apply { this.negativeTextR = negativeText }
        fun icon(icon: Int) = apply { this.iconR = icon }
        fun communicator(communicator: Communicator) = apply { this.communicatorR = communicator }

        fun build() =
            ModalBottomSheetFragment(headingR, subheadingR, positiveTextR, negativeTextR, iconR, communicatorR)
    }


}
