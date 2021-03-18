package db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName= "events")
data class EventsEntity(

    @PrimaryKey
    var id: Int = 0,

    @ColumnInfo(name ="start")
    var start: String = "",

    @ColumnInfo(name ="finish")
    var finish: String = "",

    @ColumnInfo(name ="nname")
    var name: String = "",

    @ColumnInfo(name ="description")
    var description: String = "",

    @ColumnInfo(name ="day")
    var day: String = ""
)