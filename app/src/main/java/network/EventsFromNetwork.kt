package network

import com.squareup.moshi.Json
import db.EventsEntity
import java.text.SimpleDateFormat

data class EventsFromNetwork(
    @Json(name = "id") val id: Int,
    @Json(name = "date_start") val start: String,
    @Json(name = "date_finish") val finish: String,
    @Json(name = "name") val name: String,
    @Json(name = "description") val description: String
)
{
    //formatting that's to get event day
    val simpleDateFormat = SimpleDateFormat("dd/MM/yy")
    var selectedDay = start.toLong()
    var formatedStartDate = simpleDateFormat.format(selectedDay)

    //dont forget to convert start date and finish date to "dd/MM/yy hh:mm a"
}


fun List<EventsFromNetwork>.asDatabaseModel() = map{
    EventsEntity(
        id = it.id,
        start = it.start,
        finish = it.finish,
        name = it.name,
        description = it.description,
        day = it.formatedStartDate
    )
}