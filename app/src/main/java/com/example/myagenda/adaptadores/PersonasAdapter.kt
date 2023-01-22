package com.example.myagenda.adaptadores

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myagenda.R
import com.example.myagenda.configuraciones.Constantes
import com.example.myagenda.databinding.ItemListBinding
import com.example.myagenda.models.Personas
import com.example.myagenda.ui.FormularioActivity

class PersonasAdapter (private val dataSet: List<Personas>?) :
        RecyclerView.Adapter<PersonasAdapter.ViewHolder>() {


        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
                val view = LayoutInflater.from(viewGroup.context)
                        .inflate(R.layout.item_list, viewGroup, false)
                return ViewHolder(view)
        }

        override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

                val item = dataSet?.get(position)
                viewHolder.enlazarItem(item!!)

        }

        override fun getItemCount() = dataSet!!.size

        class ViewHolder(view:View) : RecyclerView.ViewHolder(view){
        var binding = ItemListBinding.bind(view)
                var contexto = view.context
        fun enlazarItem(p: Personas)
        {
                binding.tvNombre.text = "${p.nombre} ${p.apellidos}"
                binding.tvEmail.text = p.email
                binding.tvTelefono.text = p.telefono
                binding.tvEdad.text = p.edad

                binding.root.setOnClickListener {
                        val intent = Intent(contexto,FormularioActivity::class.java)
                        intent.putExtra(Constantes.OPERACION_KEY,Constantes.OPERACION_EDITAR)
                        intent.putExtra(Constantes.ID_PERSONAS_KEY,p.idPersona)
                        contexto.startActivity(intent)
                }
                }

        }

}








