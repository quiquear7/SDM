package com.uc3m.dresser.ui.listadapter

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chauthai.swipereveallayout.ViewBinderHelper
import com.uc3m.dresser.database.Prenda
import com.uc3m.dresser.databinding.RecyclerViewItemBinding
import com.uc3m.dresser.ui.SendData


class ListaAdapter(listener: SendPrenda): RecyclerView.Adapter<ListaAdapter.MyViewHolder>() {
    private var prendaList = emptyList<Prenda>()
    private val viewBinderHelper = ViewBinderHelper()
    private  var listener: SendPrenda = listener

    class MyViewHolder(val binding: RecyclerViewItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = RecyclerViewItemBinding.inflate(
            LayoutInflater.from(parent.context), parent,
            false
        )
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int){
        val item = prendaList[position]

        with(holder){
            viewBinderHelper.setOpenOnlyOne(true)
            viewBinderHelper.bind(holder.binding.swipeLayout, prendaList[position].id.toString())
            viewBinderHelper.closeLayout(prendaList[position].id.toString())
            if(item.ruta!="*"){
                val imgBitmap: Bitmap =  BitmapFactory.decodeFile(item.ruta)
                binding.iButton.setImageBitmap(imgBitmap)
            }
            binding.tCategoria.text = item.categoria
            binding.tColor.text = item.color
            binding.tNombre.text = item.nombre
            binding.tOcasion.text = item.ocasion
            binding.editButton.setOnClickListener{
                listener.sendInfo(item, 0)
            }
            binding.deleteButton.setOnClickListener{
                listener.sendInfo(item, 1)
            }
        }

    }


    override fun getItemCount(): Int {
        return prendaList.size
    }

    fun setData(prendaList: List<Prenda>){
        this.prendaList = prendaList
        notifyDataSetChanged()
    }

    fun saveStates(outState: Bundle?) {
        viewBinderHelper.saveStates(outState)
    }

    fun restoreStates(inState: Bundle?) {
        viewBinderHelper.restoreStates(inState)
    }
}