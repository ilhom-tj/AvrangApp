package tj.colibri.avrang.data.ApiData.product

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.github.serpro69.kfaker.provider.Measurement
import java.lang.reflect.Type


object ProductTypeConverters {
    @TypeConverter
    fun imageListTo(json: String?): List<String> {
        val gson = Gson()
        val type: Type = object : TypeToken<List<String?>?>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun measurementsToString(list: List<String?>?): String {
        val gson = Gson()
        val type: Type = object : TypeToken<List<String?>?>() {}.type
        return gson.toJson(list, type)
    }
}
