package com.ln.kotlin.kotlinlibrary.ui.fragment

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.support.design.widget.BottomSheetDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alibaba.android.arouter.launcher.ARouter
import com.ln.kotlin.kotlinlibrary.R
import com.ln.kotlin.mylibrary.BaseFragment
import com.ln.kotlin.mylibrary.https.AlmightyDialog
import kotlinx.android.synthetic.main.frament_personal.*




/**
 * description: 个人中心页面
 * Created by liNan on 2017/6/19 15:41

 */
class PersonalFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater?.inflate(R.layout.frament_personal, null) as View
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
    }

    private fun init() {
        popupDialog()
        val gd = GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP, intArrayOf(Color.parseColor("#e0e0e0"), Color.BLUE))
        btn.background = gd
        // 1. 应用内简单的跳转(通过URL跳转在'进阶用法'中)

    }

    /**
     * bottomSheetDialog
     */
    private fun popupDialog() {

        val mBottomSheetDialog = BottomSheetDialog(this.activity)
        val view = LayoutInflater.from(this.activity).inflate(R.layout.dialog_bottom_sheet, null, false)
        mBottomSheetDialog.setContentView(view)
        headBackImg.setOnClickListener {
            if (mBottomSheetDialog.isShowing) {
                mBottomSheetDialog.dismiss()
            } else {
                mBottomSheetDialog.show()
            }
        }

        info.setOnClickListener {
            val almightDialog = AlmightyDialog()
            almightDialog.show(childFragmentManager, "dialog")
//            ARouter.getInstance().build("/ui/LoginActivity")
//                    .withLong("key1", 666L)
//                    .withString("key3", "888")
//                    .navigation()
//            val testUriMix = Uri.parse("arouter://m.aliyun.com/ui/LoginActivity")
//            ARouter.getInstance().build(testUriMix)
//                    .withString("key3", "value1")
//                    .navigation()

            ARouter.getInstance()
                    .build("/ui/LoginActivity")
                    .withString("url", "file:///android_asset/tes")
                    .withString("key3", "value1")
                    .navigation()

        }
//        val behavior = BottomSheetBehavior.from(scroll)
//        if (behavior.state == BottomSheetBehavior.STATE_EXPANDED) {
//            behavior.setState(BottomSheetBehavior.STATE_COLLAPSED)
//        } else {
//            behavior.setState(BottomSheetBehavior.STATE_EXPANDED)
//        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}