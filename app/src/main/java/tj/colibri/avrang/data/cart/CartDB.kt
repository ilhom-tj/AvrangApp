package tj.colibri.avrang.data.cart

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import tj.colibri.avrang.data.favorite.Favorite
import tj.colibri.avrang.data.favorite.FavoriteDao

@Database(entities = [CartItem::class], version = 3)
abstract class CartDB : RoomDatabase() {
    abstract val cartDao : CartDao

    companion object {
        @Volatile
        private var INSTANCE: CartDB? = null
        fun getInstance(context: Context): CartDB {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        CartDB::class.java,
                        "cart_data_database",
                    ).fallbackToDestructiveMigration().build()
                }
                return instance
            }
        }
    }
}