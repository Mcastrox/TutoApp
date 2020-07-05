package com.example.tutoapp


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tutoapp.adapter.ListAdapter
import com.example.tutoapp.databinding.FragmentSearchBinding
import com.example.tutoapp.models.Disciplina
import com.example.tutoapp.models.Model
import com.example.tutoapp.models.RatingModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.protobuf.FloatValue


/**
 * A simple [Fragment] subclass.
 */
class SearchFragment : Fragment() {


    private lateinit var toolbar: Toolbar
    private var listaTutores = mutableListOf<Model>()
    private lateinit var adapter: ListAdapter
    private lateinit var list: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        adapter = ListAdapter(activity!!)
        adapter.setDataList(listaTutores)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentSearchBinding>(
            inflater,
            R.layout.fragment_search,
            container,
            false
        )
        search(binding)
        return binding.root
    }

    fun search(binding: FragmentSearchBinding) {
        toolbar = binding.toolbar
        list = binding.list
        list.layoutManager = LinearLayoutManager(activity!!)

        toolbar?.setTitle(R.string.ToolBarTitle)
        (activity as AppCompatActivity?)!!.setSupportActionBar(toolbar)
        var actionBar = activity?.actionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)


        var me = this // variable para guardar el contexto actual
        val ref = FirebaseDatabase.getInstance().getReference("Users") // referencia a la bd


        //Aqui se trae todos los valores
        ref.addValueEventListener(object : ValueEventListener {

            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                if (listaTutores.size > 0) {
                    listaTutores.clear()
                }
                for (e in p0.children) {
                    var name: String = ""
                    var lastName: String = ""
                    var email: String = ""
                    var nivel: String = ""
                    var ocupation: String = ""
                    var cellphone: String = ""
                    var direccion: String = ""
                    var rol: String = ""
                    var ruta: String = ""
                    var descripcion: String = ""
                    var listaDisciplina: ArrayList<Disciplina> = arrayListOf<Disciplina>()
                    var ratings: ArrayList<RatingModel>  = arrayListOf<RatingModel>()
                    var id: String = e.child("ID").value as String


                    if (e.child("Name").value != null) {
                        name = e.child("Name").value as String
                    }

                    if (e.child("lastName").value != null) {
                        lastName = e.child("lastName").value as String
                    }
                    if (e.child("direccion").value != null) {
                        direccion = e.child("direccion").value as String
                    }
                    if (e.child("correo").value != null) {
                        email = e.child("correo").value as String
                    }
                    if (e.child("telefono").value != null) {
                        cellphone = e.child("telefono").value as String
                    }
                    if (e.child("nivel").value != null) {
                        nivel = e.child("nivel").value as String
                    }
                    if (e.child("ocupacion").value != null) {
                        ocupation = e.child("ocupacion").value as String
                    }
                    if (e.child("Rol").value != null) {
                        rol = e.child("Rol").value as String
                    }
                    if (e.child("urlImage").value != null) {
                        ruta = e.child("urlImage").value as String
                    }
                    if (e.child("Descripcion").value != null) {
                        descripcion = e.child("Descripcion").value as String
                    }
                    if (e.child("disciplinas").exists()) {
                        for (item in 0..11) {
                            val name =
                                e.child("disciplinas").child("$item").child("name").value as String
                            val isSelected = e.child("disciplinas").child("$item")
                                .child("seleccionado").value as Boolean
                            if (isSelected) {
                                listaDisciplina.add(
                                    Disciplina(
                                        "$item",
                                        name,
                                        "",
                                        isSelected
                                    )
                                )
                            }
                        }
                    }

                    if (e.child("ratings").exists()) {
                        val count : Long = e.child("ratings").childrenCount -1
                        Log.d("ToyArto",count.toString())

                        for (item in 0..count ) {
                            val value = e.child("ratings").child("$item").child("value").value as String
                            ratings.add( RatingModel( value))
                        }
                    }

                    if (rol == "Tutor") {
                        listaTutores.add(
                            Model(
                                id,
                                name,
                                lastName,
                                email,
                                cellphone,
                                nivel,
                                ocupation,
                                direccion,
                                R.drawable.ic_art,
                                ruta,
                                descripcion,
                                listaDisciplina,
                                ratings
                            )
                        )
                    }

                }



                list.adapter = adapter
                adapter.notifyDataSetChanged()

                if (listaTutores.isNullOrEmpty()) {
                     binding.empty.visibility = View.VISIBLE
                     binding.list.visibility = View.GONE

                } else {
                    binding.empty.visibility = View.GONE
                    binding.list.visibility = View.VISIBLE
                }

            }

        })

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu);
        super.onCreateOptionsMenu(menu, inflater)
        val itemBusqueda = menu?.findItem(R.id.busqueda)
        var vistaBusqueda = itemBusqueda?.actionView as SearchView


        vistaBusqueda.queryHint = "Nombre..."

        vistaBusqueda.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                adapter.filter.filter(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return false
            }

        })
    }
}
