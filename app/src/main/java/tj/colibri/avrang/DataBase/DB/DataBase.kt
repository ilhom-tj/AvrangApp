package tj.colibri.avrang.DataBase.DB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import tj.colibri.avrang.models.Cart.CartIndex
import tj.colibri.avrang.models.FAQ.FAQs
import tj.colibri.avrang.models.Chekout.CheckOutItem
import tj.colibri.avrang.data.Cart.CartItem
import tj.colibri.avrang.data.Favorite.FavoriteCard
import tj.colibri.avrang.data.User.User

@Database(
    entities = [
        CartIndex::class,
        CartItem::class,
        CheckOutItem::class,
        FAQs::class,
        FavoriteCard::class,
        User::class
    ], version = 1
)
abstract class DataBase : RoomDatabase() {
    abstract val cartIndexDao: CartIndexDao
    abstract val checkOutDaoDao: CheckOutDao
    abstract val faqDao: FAQDao
    abstract val cartDao: CartDao
    abstract val favorite: FavoriteDao
    abstract val userDao: UserDao

    companion object {
        @Volatile
        private var INSTANCE: DataBase? = null
        fun getInstance(context: Context): DataBase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        DataBase::class.java,
                        "app_data_base",
                    ).allowMainThreadQueries().fallbackToDestructiveMigration().build()
                }
                return instance
            }
        }
    }
}