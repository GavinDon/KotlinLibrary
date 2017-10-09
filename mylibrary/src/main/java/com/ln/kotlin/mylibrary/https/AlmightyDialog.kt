package com.ln.kotlin.mylibrary.https

import android.app.AlertDialog
import android.app.Dialog
import android.app.DialogFragment
import android.os.Bundle
import android.view.LayoutInflater
import com.ln.kotlin.mylibrary.R

/**
 * description:
 * Created by liNan on 2017/7/20 10:40

 */
class AlmightyDialog : DialogFragment() {



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val alertDialog=AlertDialog.Builder(this.activity)
        alertDialog.setView(LayoutInflater.from(this.activity).inflate(R.layout.dialog, null, false))
        return alertDialog.create()
    }

    private fun initView() {

    }

}