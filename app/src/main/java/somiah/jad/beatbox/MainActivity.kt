package somiah.jad.beatbox

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.GridLayout
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import somiah.jad.beatbox.databinding.ActivityMainBinding
import somiah.jad.beatbox.databinding.ItemSoundBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        val listBinding : ActivityMainBinding
                = DataBindingUtil.setContentView(this,R.layout.activity_main)
        listBinding.recyclerView.apply {
            layoutManager = GridLayoutManager(context,3)
            adapter = SoundAdapter()
        }
    }

    private inner class SoundHolder(private val itemBinding: ItemSoundBinding):
                    RecyclerView.ViewHolder(itemBinding.root){
    }

    private inner class SoundAdapter():
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

        override fun getItemCount(): Int {
            return 0
        }

        override fun onBindViewHolder(holder: SoundHolder, position: Int) {
            TODO("Not yet implemented")
        }

    }
}