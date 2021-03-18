package overview

import android.example.com.diary.databinding.FragmentOverviewBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import db.EveDatabase
import java.text.SimpleDateFormat


class OverviewFragment : Fragment() {


    private lateinit var viewModel:OverviewViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val binding = FragmentOverviewBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner

        //creating viewModel class
        val application = requireNotNull(this.activity).application
        val dataSource = EveDatabase.getInstance(application).eventsDao
        val viewModelFactory = OverviewViewModelFactory(application, dataSource)
        viewModel =ViewModelProvider(this, viewModelFactory).get(OverviewViewModel::class.java)

        //bind viewModel to xml
        binding.viewModel = viewModel


        //listen clicking and show events from db - this block under developing at current moment
        binding.calendarView.setOnDayClickListener { day->
            var simpleDateFormat = SimpleDateFormat("dd/MM/yy")
            var selectedDay = day.calendar.time
            var mydate = simpleDateFormat.format(selectedDay)
            Toast.makeText(context,mydate,Toast.LENGTH_LONG)
        }

        return binding.root
    }

}