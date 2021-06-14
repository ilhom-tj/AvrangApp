package tj.colibri.avrang.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences

@SuppressLint("CommitPrefEdits")
class SessionManager(context: Context) {

    private var settings : SharedPreferences = context.getSharedPreferences("AppSettings",0)
    private var editor : SharedPreferences.Editor = settings.edit()

    //GET AND SET TOKEN
    fun getToken() : String? {
        return settings.getString("APP_TOKEN","error")
    }
    fun setToken(token : String) : Boolean{
        return editor.putString("APP_TOKEN",token).commit()
    }



    fun setPhone(phone : String) : Boolean{
        return editor.putString("phone",phone).commit()
    }
    fun getPhone() : String? {
        return settings.getString("phone","error")
    }

    fun setPassword(password : String) : Boolean{
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



}