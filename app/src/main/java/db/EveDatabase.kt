package db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [EventsEntity::class], version = 1, exportSchema = false)
abstract class EveDatabase: RoomDatabase() {

    abstract val eventsDao: EventsDao

    companion object{

        @Volatile
        private var INSTANCE: EveDatabase? = null

        fun getInstance (context: Context): EveDatabase{
            synchronized(this){
                var instance = INSTANCE

                if(instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        EveDatabase::class.java,
                        "events_db"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}