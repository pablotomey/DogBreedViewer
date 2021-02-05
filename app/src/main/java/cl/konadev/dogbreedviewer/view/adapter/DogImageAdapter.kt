package cl.konadev.dogbreedviewer.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cl.konadev.dogbreedviewer.R
import cl.konadev.dogbreedviewer.base.BaseViewHolder
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.dog_breed_row.view.*
import kotlinx.android.synthetic.main.dog_image_grid.view.*

class DogImageAdapter(private val context: Context,
                      private val dogList:MutableList<String>): RecyclerView.Adapter<BaseViewHolder<*>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return MainViewHolder(LayoutInflater.from(context).inflate(R.layout.dog_image_grid,parent,false))
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
            Glide.with(context).load(item).into(itemView.dog_img)

        }

    }
}