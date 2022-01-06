package com.sadio.Miniproject.apis.view

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sadio.Miniproject.R
import com.sadio.Miniproject.apis.jokes.model.JokesUi
import com.sadio.Miniproject.apis.jokes.viewmodel.JokesViewModel
import com.sadio.Miniproject.databinding.ActivityApisBinding
import com.sadio.Miniproject.databinding.ItemsJokesBinding

class ApisActivity : AppCompatActivity() {

    private lateinit var viewModel: JokesViewModel
    private lateinit var binding : ActivityApisBinding
    private lateinit var binding2 : ItemsJokesBinding

    private val adapter = JokesAdapter { item, view, clickId ->
        onJokeClick(item, view, clickId);
    }

    private val observer = Observer<List<JokesUi>> {
        adapter.submitList(it)
    }

    private val channelId = "notifications_channel"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityApisBinding.inflate(layoutInflater)
        binding2 = ItemsJokesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        createNotificationChannel()

        viewModel = ViewModelProvider(this)[JokesViewModel::class.java]

        binding.recyclerViewApis.layoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.recyclerViewApis.adapter = adapter

        binding.buttonAddJoke.setOnClickListener {
            viewModel.fetchNewJoke()
        }

        binding.buttonDeleteAll.setOnClickListener {
            viewModel.deleteAll()
        }
    }

    private fun onJokeClick(jokesUi: JokesUi, view : View, clickId: Int) {
        if (clickId == 0) {
            onJokeClickShowNotification(jokesUi, view)
        } else if (clickId == 1) {
            onJokeClickDelete(jokesUi, view)
        } else {
            throw RuntimeException("Wrong click id received $clickId")
        }
    }

    private fun onJokeClickShowNotification(jokesUi: JokesUi, view : View) {
        val builder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle(jokesUi.api)
            .setContentText(jokesUi.setup)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        with(NotificationManagerCompat.from(this)) {
            notify(0, builder.build())
        }
    }

    private fun createNotificationChannel() {
        val name = "notifications"
        val descriptionText = "channel_notifications"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(channelId, name, importance).apply {
            description = descriptionText
        }
        val notificationManager: NotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

    private fun onJokeClickDelete(jokesUi: JokesUi, view: View) {
        viewModel.deleteJokeWithTimestamp(jokesUi.timestamp)
    }

    override fun onStart() {
        super.onStart()
        viewModel.jokeLiveData.observe(this, observer)
    }

    override fun onStop() {
        viewModel.jokeLiveData.removeObserver(observer)
        super.onStop()
    }
}