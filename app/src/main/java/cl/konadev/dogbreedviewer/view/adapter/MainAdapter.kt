package cl.konadev.dogbreedviewer.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cl.konadev.dogbreedviewer.R
import cl.konadev.dogbreedviewer.base.BaseViewHolder
import kotlinx.android.synthetic.main.dog_breed_row.view.*

class MainAdapter(private val context: Context, private val dogList:MutableList<String>,
                  private val itemClickListener: OnDogBreedClickListener): RecyclerView.Adapter<BaseViewHolder<*>>() {

    interface OnDogBreedClickListener {
        fun onDogBreedClick(string: String, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return MainViewHolder(LayoutInflater.from(context).inflate(R.layout.dog_breed_row,parent,false))
    }

    override fun getItemCount(): Int {
        return dogList.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder) {
            is MainViewHolder -> holder.bind(dogList[position],position)
        }
    }

    inner class MainViewHolder(itemView: View): BaseViewHolder<String>(itemView){
        override fun bind(item: String, position: Int) {
            itemView.txt_dog_breed.text = item

            // item click
            itemView.dogbreed_row.setOnClickListener {itemClickListener.onDogBreedClick(item,position)}
        }

    }
}