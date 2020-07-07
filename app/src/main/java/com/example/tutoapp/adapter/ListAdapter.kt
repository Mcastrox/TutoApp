package com.example.tutoapp.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.tutoapp.PseleccionadoActivity
import com.example.tutoapp.R
import com.example.tutoapp.models.Model
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row.view.*
import kotlinx.android.synthetic.main.rvrow.view.*
import kotlinx.android.synthetic.main.rvrow.view.cuota
import kotlinx.android.synthetic.main.rvrow.view.image
import kotlinx.android.synthetic.main.rvrow.view.textView1
import kotlinx.android.synthetic.main.rvrow.view.textView2
import kotlinx.android.synthetic.main.rvrow.view.textView3
import kotlinx.android.synthetic.main.rvrow.view.ubicacion_solicitud

class ListAdapter(private val context: Context) : RecyclerView.Adapter<ListAdapter.ListViewHolder>(), Filterable {

    private var tutorList = mutableListOf<Model>()
    private var tutorListFilter = mutableListOf<Model>()

    fun setDataList(data : MutableList<Model>){
        tutorList = data
        tutorListFilter = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rvrow, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return if(tutorListFilter.size > 0){
            tutorListFilter.size
        }else{
            0
        }
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val tutor = tutorListFilter[position]
        holder.bindView(tutor)
        holder.itemView.setOnClickListener {
            val intent = Intent(context, PseleccionadoActivity::class.java)
            intent.putExtra("tutor", tutor)
            context.startActivity(intent)
        }
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bindView(tutor : Model){
            if(tutor.ruta == "0"){
                itemView.image.setImageResource(R.drawable.ic_usuario)
            }
            else{
                Picasso.get().load(tutor.ruta).into(itemView.image)
            }
            itemView.apply {
                textView1.text=tutor.name
                textView2.text=tutor.lastname
                ubicacion_solicitud.text = tutor.location
                textView3.text=tutor.ocupacion
                cuota.text = "$" + "%.2f".format(tutor.cuota.toDouble())
            }
        }
    }

    override fun getFilter(): Filter {
        return object : Filter(){
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val searchString = constraint.toString()
                if(searchString.isEmpty()){
                    tutorListFilter = tutorList
                }else{
                    val resultList = mutableListOf<Model>()
                    for(item in tutorList){
                        val tutorName = item.name+" "+item.lastname
                        Log.d("ListenerFocus",tutorName)
                        if(tutorName.toLowerCase().contains(searchString.toLowerCase())){
                            resultList.add(item)
                        }
                    }
                    tutorListFilter = resultList
                }
                val filterResult = FilterResults()
                filterResult.values = tutorListFilter
                return filterResult
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                tutorListFilter = results!!.values as MutableList<Model>
                notifyDataSetChanged()
            }

        }
    }


}