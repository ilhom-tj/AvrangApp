package tj.colibri.avrang.data.favorite

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import tj.colibri.avrang.data.mock.ProductCard2

@Database(entities = [FavoriteCard::class], version = 4)
abstract class FavoriteDB : RoomDatabase() {
    abstract val favorite : FavoriteDao

    companion object {
        @Volatile
        private var INSTANCE: FavoriteDB? = null
        fun getInstance(context: Context): FavoriteDB {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        FavoriteDB::class.java,
                        "favorite_data_database",
                        ).fallbackToDestructiveMigration().build()
                }
                return instance
            }
        }
    }
}