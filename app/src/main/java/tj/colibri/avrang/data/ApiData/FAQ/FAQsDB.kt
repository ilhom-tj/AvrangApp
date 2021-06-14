package tj.colibri.avrang.data.ApiData.FAQ

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FAQs::class], version = 1)
abstract class FAQsDB : RoomDatabase() {
    abstract val faqDao : FAQDao
    companion object {
        @Volatile
        private var INSTANCE: FAQsDB? = null
        fun getInstance(context: Context): FAQsDB {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        FAQsDB::class.java,
                        "faq_database",
                    ).fallbackToDestructiveMigration().build()
                }
                return instance
            }
        }
    }
}