package com.example.myagenda

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myagenda.adaptadores.PersonasAdapter
import com.example.myagenda.configuraciones.Constantes
import com.example.myagenda.databinding.ActivityMainBinding
import com.example.myagenda.ui.FormularioActivity
import com.example.myagenda.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //enlazamos el modelo con LiveData tras crear el modelo en activity_main


        viewModel = ViewModelProvider (this).get()
        binding.lifecycleOwner = this
        binding.modelo = viewModel
        viewModel.iniciar()


        binding.miRecycler.apply{
            layoutManager = LinearLayoutManager(applicationContext)
        }

        viewModel.personasList.observe(this, Observer {
            binding.miRecycler.adapter = PersonasAdapter(it)
        })

        binding.btnAbrirFormulario.setOnClickListener {
            val intent = Intent(this,FormularioActivity::class.java)
            intent.putExtra(Constantes.OPERACION_KEY, Constantes.OPERACION_INSERTAR)
            startActivity(intent)
        }

        //agrego un evento para busqueda

        binding.etBuscar.addTextChangedListener(object:TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                if(s.toString().isNotEmpty()){
                    viewModel.buscarPersonas()
                }
            }
            override fun beforeTextChanged(s : CharSequence?, start:Int, count:Int,after:Int) {
                TODO("Not yet implemented")
            }

            override fun onTextChanged(s: CharSequence?, start:Int, count:Int,after:Int) {
                TODO("Not yet implemented")
            }



        })
    }

}


