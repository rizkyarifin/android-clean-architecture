package sample.base.app.utils.ext

import android.app.Activity
import android.widget.Toast
import androidx.fragment.app.Fragment

fun Activity.showToast(message : String, duration : Int = Toast.LENGTH_SHORT){
    Toast.makeText(this, message, duration).show()
}

fun Fragment.showToast(message : String, duration : Int = Toast.LENGTH_SHORT){
    Toast.makeText(activity, message, duration).show()
}