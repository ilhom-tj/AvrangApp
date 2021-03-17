package tj.colibri.avrang.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.view.Display
import androidx.lifecycle.Observer
import com.google.android.material.tabs.TabLayout
import tj.colibri.avrang.network.repositories.registrationRepo.RegistrationRepo

@SuppressLint("CommitPrefEdits")
class SessionManager(context: Context) {

    private var settings : SharedPreferences = context.getSharedPreferences("AppSettings",0)
    private var editor : SharedPreferences.Editor = settings.edit()
//    private val regRepo = RegistrationRepo(context)
    private val context = context
    //GET AND SET TOKEN
    fun getToken() : String? {
        return settings.getString("APP_TOKEN","error")
    }
    fun setToken(token : String) : Boolean{
        return editor.putString("APP_TOKEN",token).commit()
    }

    fun deleteToken(): Boolean{
        return editor.remove("APP_TOKEN").commit()
    }

    fun setPhone(phone : String) : Boolean{
        return editor.putString("phone",phone).commit()
    }
    fun getPhone() : String? {
        return settings.getString("phone","error")
    }

    fun setPassword(password : String) : Boolean?{
        return editor.putString("password",password).commit()
    }
    fun getPassword() : String? {
        return settings.getString("password","error")
    }

    fun deleteAll() {
        editor.remove("APP_TOKEN").commit()
        editor.remove("phone").commit()
        editor.remove("password").commit()
    }

    fun RefresToken(){
        if (getToken() != "error"){
            if (getPhone() != "error"){
                if (getPassword() != "error"){

                }
            }
        }
    }

}