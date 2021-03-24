package tj.colibri.avrang.data.ApiData.chekout

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CheckOutItem::class], version = 4)
abstract class CheckOutDB : RoomDatabase() {
    abstract val checkOutDaoDao : CheckOutDao
    companion object {
        @Volatile
        private var INSTANCE: CheckOutDB? = null
        fun getInstance(context: Context): CheckOutDB {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        CheckOutDB::class.java,
                        "check_out_data_database",
                    ).fallbackToDestructiveMigration().build()
                }
                return instance
            }
        }
    }
}