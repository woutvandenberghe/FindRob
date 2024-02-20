package be.vives.findrobert.ui.adminscreen

import androidx.lifecycle.ViewModel
import be.vives.findrobert.data.MyConfiguration

class AdminScreenViewModel : ViewModel() {
    fun isAdmin(): Boolean {
        return if(MyConfiguration.loggedInUser != null){
            MyConfiguration.loggedInUser!!.userName == "admin" &&
                    MyConfiguration.loggedInUser!!.password == "admin"
        } else{
            false;
        }
    }
}