package com.example.register

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class AdapterTutores(var context:Context, items:ArrayList<Model>) :BaseAdapter() {
    var items : ArrayList<Model>? =null
    init{
        this.items=items
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var holder: viewHolder?=null
        var vista: View? = convertView
        if(vista==null){
            vista=LayoutInflater.from(context).inflate(R.layout.row,null)
            holder = viewHolder(vista)
            vista.tag=holder

        }
        else{
            holder=vista.tag as? viewHolder
        }
        val item = getItem(position) as Model
        holder?.tittle?.text=item.tittle
        holder?.description?.text=item.description
        holder?.img?.setImageResource(item.img)

        return vista!!
    }

    override fun getItem(position: Int): Any {
       return items?.get(position)!!
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
      return items?.count()!!
    }
    private class viewHolder(vista:View){
        var tittle: TextView? = null
        var description: TextView? = null
        var img:ImageView? = null
        init {
            tittle=vista.findViewById(R.id.textView1)
            description=vista.findViewById(R.id.textView2)
            img=vista.findViewById(R.id.image)
        }
    }

}