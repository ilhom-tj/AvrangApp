package tj.colibri.avrang.data.ApiData.product.room

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