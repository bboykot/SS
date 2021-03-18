package db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface EventsDao {

    @Update
    suspend fun update (eventsEntity: EventsEntity)

    @Query("select * from events")
    fun getAll(): LiveData<List<EventsEntity>>

    @Query("Select * from events where day = :day ")
    fun getEventsByDay(day :String):LiveData<List<EventsEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(events: List<EventsEntity>)

}