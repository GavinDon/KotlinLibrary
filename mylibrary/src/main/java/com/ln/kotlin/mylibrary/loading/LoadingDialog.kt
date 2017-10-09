package com.ln.kotlin.mylibrary.loading

/**
 * description:
 * Created by liNan on 2017/7/20 15:53

 */
class LoadingDialog private constructor(aa:String,bb:String,cc:String) {

    constructor(builder:Builder) : this(builder.aa,builder.bb,builder.cc ){
        var bb = "bb"

    }
    fun setAa(){
    }

     class Builder {
        var aa = ""
        var bb = ""
        var cc = ""

         fun addAA(a: String):Builder{
            aa = a
            println(aa)
            return this
        }

         fun addBB(b: String):Builder {
            bb = b
            println(bb)

            return this
        }

         fun addCC(c: String):Builder {
            cc = c
            println(cc)
            return this
        }
        fun build():Builder{
            LoadingDialog(this@Builder)
            return this
        }
    }
}