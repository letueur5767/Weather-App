package me.tangobee.weathernaut.services

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.content.pm.ServiceInfo.FOREGROUND_SERVICE_TYPE_MEDIA_PLAYBACK
import android.media.MediaPlayer
import android.os.IBinder
import android.os.Build
import androidx.core.app.NotificationCompat
import me.tangobee.weathernaut.R

class WeatherMusicService : Service() {

    companion object {
        private var mediaPlayer: MediaPlayer? = null
    }

    private val musicUrl = "https://weathernaut-backend.onrender.com/api/music"
    private val SERVICE_ID = 1

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        createNotificationChannel()

        // Foreground notification
        val notification: Notification = NotificationCompat.Builder(this, "MUSIC_SERVICE_CHANNEL")
            .setContentTitle("Jouer de la musique en fonction de la meteo")
            .setContentText("Profitez de la musique et de la météo de votre journée !")
            .setSmallIcon(R.drawable.icon_music)
            .build()

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
            startForeground(SERVICE_ID, notification)
        } else {
            startForeground(SERVICE_ID, notification,
                FOREGROUND_SERVICE_TYPE_MEDIA_PLAYBACK)
        }

        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer().apply {
                setDataSource(musicUrl)
                prepareAsync()
                setOnPreparedListener {
                    it.start()
                }
                setOnCompletionListener {
                    start()
                }
                setOnErrorListener { _, _, _ ->
                    stopSelf()
                    false
                }
            }
        } else if (!mediaPlayer!!.isPlaying) {
            mediaPlayer!!.start()
        }

        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mediaPlayer != null) {
            mediaPlayer?.stop()
            mediaPlayer?.release()
            mediaPlayer = null
        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null // We don't bind this service to an activity
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                "MUSIC_SERVICE_CHANNEL",
                "Canal de lecture de musique",
                NotificationManager.IMPORTANCE_LOW
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager?.createNotificationChannel(serviceChannel)
        }
    }
}
