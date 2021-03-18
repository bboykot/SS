package overview

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import db.EventsDao
import db.EventsEntity
import kotlinx.coroutines.launch
import network.EventApi
import network.EventsFromNetwork
import network.asDatabaseModel


class OverviewViewModel(application: Application,val dataSource: EventsDao): AndroidViewModel(application) {

    private val _events = MutableLiveData<List<EventsFromNetwork>>()
    val events : LiveData<List<EventsFromNetwork>>
    get() = _events

    
    var eventsFromDB = showEvents("19/03/21")


    var eventsString: String = "12"
    init {
        getEventsFromNet()
    }

    //loading data from net and inserting to DB
     fun getEventsFromNet(){
        viewModelScope.launch {
             try { _events.value = EventApi.retro.getEvents()
                 val eventsToDB = EventApi.retro.getEvents()
                    dataSource.insertAll(eventsToDB.asDatabaseModel())}
             catch (e: Exception) { eventsString = "Failure: ${e.message}" }
        }


    }

    //Select events by day, at  the end this function will be called from fragment at onclickListener block
    fun showEvents(day: String):LiveData<List<EventsEntity>>{
        val eventsDB: LiveData<List<EventsEntity>>  = dataSource.getEventsByDay(day)
        return eventsDB
    }

}