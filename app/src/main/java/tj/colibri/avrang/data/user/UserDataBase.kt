package tj.colibri.avrang.data.user

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 4)
abstract class UserDataBase : RoomDatabase() {
    abstract val userDao: UserDao
    companion object {
        @Volatile
        private var INSTANCE: UserDataBase? = null
        fun getInstance(context: Context): UserDataBase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        UserDataBase::class.java,
                        "user_data_database"
                    ).build()
                }
                return instance
            }
        }
    }
}