package somiah.jad.beatbox

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.SeekBar
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import somiah.jad.beatbox.databinding.ActivityMainBinding
import somiah.jad.beatbox.databinding.ItemSoundBinding

class MainActivity : AppCompatActivity() {

    private lateinit var beatBox: BeatBox
    //private lateinit var speedControlBar: SeekBar
    private var speed: Float = 1.0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        beatBox = BeatBox(assets)


        val listBinding : ActivityMainBinding
                = DataBindingUtil.setContentView(this,R.layout.activity_main)
        listBinding.recyclerView.apply {
            layoutManager = GridLayoutManager(context,3)
            adapter = SoundAdapter(beatBox.sounds)
        }
        //speedControlBar = findViewById(R.id.seekbar)
        //speedControlBar.setProgress(50)
        listBinding.seekbar.setProgress(50)
        listBinding.seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                speed = (p1/50).toFloat()
                Log.d("MAIN", speed.toString())
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        }

        )

//        speedControlBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
//            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
//                speed = (p1/50).toFloat()
//                Log.d("MAIN", speed.toString())
//            }
//
//            override fun onStartTrackingTouch(p0: SeekBar?) {
//
//            }
//
//            override fun onStopTrackingTouch(p0: SeekBar?) {
//            }
//        })
    }


    override fun onDestroy() {
        super.onDestroy()
        beatBox.release()
    }

    private inner class SoundHolder(private val itemBinding: ItemSoundBinding):
                    RecyclerView.ViewHolder(itemBinding.root){
        init {
            itemBinding.viewModel = SoundViewModel(beatBox)
        }
        fun bind(sound: Sound) {
            itemBinding.apply {
                viewModel?.sound = sound
                executePendingBindings()
            }
        }

    }

    private inner class SoundAdapter(private val sounds: List<Sound>):
                    RecyclerView.Adapter<SoundHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SoundHolder {
            val binding = DataBindingUtil.inflate<ItemSoundBinding>(
                layoutInflater,
                R.layout.item_sound,
                parent,
                false
            )
            return SoundHolder(binding)

        }

        override fun getItemCount() =  sounds.size

        override fun onBindViewHolder(holder: SoundHolder, position: Int) {

            val sound = sounds[position]
            holder.bind(sound)

        }

    }
}