package be.vives.findrobert.ui.adminscreen

import androidx.lifecycle.ViewModel
class AdminScreenViewModel : ViewModel() {
    fun isAdmin(): Boolean {
        /*if(MyConfiguration.loggedInUser != null){
            return MyConfiguration.loggedInUser == admin
        }
        else{
            return false;
        }*/
        return true
    }
}