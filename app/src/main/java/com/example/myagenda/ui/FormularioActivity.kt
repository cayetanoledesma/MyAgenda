package com.example.myagenda.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.myagenda.MainActivity
import com.example.myagenda.R
import com.example.myagenda.configuraciones.Constantes
import com.example.myagenda.databinding.ActivityFormularioBinding
import com.example.myagenda.dialogos.BorrarDialogo
import com.example.myagenda.viewmodel.FormularioViewModel

class FormularioActivity : AppCompatActivity(),BorrarDialogo.BorrarListener {

    lateinit var binding:ActivityFormularioBinding
    lateinit var viewModel:FormularioViewModel
    lateinit var dialogoBorrar:BorrarDialogo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormularioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dialogoBorrar = BorrarDialogo(this)

        viewModel = ViewModelProvider(this).get()
        viewModel.operacion = intent.getStringExtra(Constantes.OPERACION_KEY)!!
        binding.modelo = viewModel
        binding.lifecycleOwner = this

        // este observador va a decirnos si la funcion es exitosa
        viewModel.operacionExitosa.observe(this, Observer {
            if(it){
                mostrarMensaje("operación Exitosa")
                irAlInicio()

            }else{
                mostrarMensaje("Se produjo un error")
            }
        })
        if(viewModel.operacion.equals(Constantes.OPERACION_EDITAR)){
            viewModel.id.value = intent.getLongExtra(Constantes.ID_PERSONAS_KEY,0)

            viewModel.cargarDatos()
            binding.linearEditar.visibility = View.VISIBLE
            binding.btnGuardar.visibility =View.GONE

        }else{
            binding.linearEditar.visibility = View.GONE
            binding.btnGuardar.visibility = View.VISIBLE
        }

        binding.btnBorrar.setOnClickListener(){
            mostrarDialogo()
        }
    }

    private fun mostrarDialogo(){
        dialogoBorrar.show(supportFragmentManager, "Dialogo Borrar")
    }

    private fun mostrarMensaje(s:String){
        Toast.makeText(applicationContext,s, Toast.LENGTH_LONG).show()

    }
    // cuando cambies de actividad  lo que estaba anterior se va a destruir
    private fun irAlInicio(){
        val intent = Intent(applicationContext,MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

    override fun borrarPersonas() {
       viewModel.eliminarPersonas()
    }
}