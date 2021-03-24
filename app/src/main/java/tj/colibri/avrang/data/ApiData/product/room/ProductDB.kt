package tj.colibri.avrang.data.ApiData.product.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import tj.colibri.avrang.data.mock.ProductCard2

//@Database(entities = [ProductCache::class], version = 2)
//abstract class ProductDB : RoomDatabase() {
//    abstract val productDao : ProductDao
//
//    companion object {
//        @Volatile
//        private var INSTANCE: ProductDB? = null
//        fun getInstance(context: Context): ProductDB {
//            synchronized(this) {
//                var instance = INSTANCE
//                if (instance == null) {
//                    instance = Room.databaseBuilder(
//                        context.applicationContext,
//                        ProductDB::class.java,
//                        "product_database",
//                    ).build()
//                }
//                return instance
//            }
//        }
//    }
//}