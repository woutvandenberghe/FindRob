package be.vives.findrobert.ui.adminscreen

import androidx.lifecycle.ViewModel
import be.vives.findrobert.data.MyConfiguration

class AdminScreenViewModel : ViewModel() {
    fun isAdmin(): Boolean {
        if(MyConfiguration.loggedInUser != null){
            return MyConfiguration.loggedInUser!!.userName == "admin" &&
                    MyConfiguration.loggedInUser!!.password == "admin"
        }
        else{
            return false;
        }
    }
}