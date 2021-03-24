package tj.colibri.avrang.data.ApiData.Cart

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CartIndex::class], version = 3)
abstract class CartIndexDB : RoomDatabase() {
    abstract val cartIndexDao : CartIndexDao
    companion object {
        @Volatile
        private var INSTANCE: CartIndexDB? = null
        fun getInstance(context: Context): CartIndexDB {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        CartIndexDB::class.java,
                        "cart_index_data_database",
                    ).fallbackToDestructiveMigration().build()
                }
                return instance
            }
        }
    }
}