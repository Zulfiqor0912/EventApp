package uz.gita.b5myeventapp

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import uz.gita.b5myeventapp.databinding.ActivityMainBinding
import uz.gita.b5myeventapp.presentation.adapter.MyAdapter
import uz.gita.b5myeventapp.presentation.ui.viewmodel.impl.MainViewModelImpl
import uz.gita.b5myeventapp.service.EventService
import com.karumi.dexter.BuildConfig


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var adapter = MyAdapter()
    private val viewModel by lazy {
        ViewModelProvider(this)[MainViewModelImpl::class.java]
    }
    private val localStorage = LocalStorage.getInstance()

    private var mediaSound: MediaPlayer? = null

    private val myPermissions =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted){
                createService()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mediaSound = MediaPlayer.create(this, R.raw.sound)

        val events = viewModel.getEvents()

        binding.list.adapter = adapter
        binding.list.layoutManager = LinearLayoutManager(this)

        adapter.submitList(events)

        adapter.setClickListener { isChecked, eventData ->
            if(isChecked) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                    if (ContextCompat.checkSelfPermission(this@MainActivity, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED){
                        createService()
                    }else{
                        myPermissions.launch(Manifest.permission.POST_NOTIFICATIONS)
                    }
                }else{
                    createService()
                }
                localStorage?.saveFirstLogic(eventData.id.toString(),true)
            }else{
                localStorage?.saveFirstLogic(eventData.id.toString(),false)
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            if (ContextCompat.checkSelfPermission(this@MainActivity, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED){
                createService()
            }else{
                myPermissions.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }else{
            createService()
        }

        binding.share.setOnClickListener {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, R.string.app_name)
            var shareMessage = "I recommend this app: Events Sounds".trim() + "\n"
            shareMessage = "${shareMessage}https://play.google.com/store/apps/details?id=${BuildConfig.APPLICATION_ID}".trimIndent()
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
            startActivity(Intent.createChooser(shareIntent, "Share This App"))
        }
    }

    private fun createService(){
        val intent = Intent(this@MainActivity, EventService::class.java)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(intent)
        } else startService(intent)
    }
}