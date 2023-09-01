package net.flow9.thisiskotlin.customview

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MyItem(
    //val aIcon:Int,
    val Image:Int,
    val Product:String,
    val Explain:String,
    val Seller:String,
    val Price:Int,
    val Address:String,
    var Good:Int,
    val Chat:String,
    var isLike: Boolean,
    var InterestCnt: Int,
   ) : Parcelable


