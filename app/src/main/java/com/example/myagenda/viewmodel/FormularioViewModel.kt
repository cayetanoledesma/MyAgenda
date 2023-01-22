package com.example.myagenda.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myagenda.configuraciones.Constantes
import com.example.myagenda.configuraciones.PersonasApp.Companion.db
import com.example.myagenda.models.Personas
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FormularioViewModel: ViewModel() {

    var id = MutableLiveData<Long>()
    var nombre = MutableLiveData<String>()
    var apellidos = MutableLiveData<String>()
    var telefono = MutableLiveData<String>()
    var email = MutableLiveData<String>()
    var edad = MutableLiveData<Int>()
    var operacion = Constantes.OPERACION_INSERTAR
    var operacionExitosa = MutableLiveData<Boolean>()

    // aplicamos init que es un constructor que es lo que antes se ejecuta

    init {
        edad.value = 40
    }

    //si es insertar?, lógica para insertar en la BBDD, cuando le de a guardar va a aparecer un LOG
    //y se inserte en la BBDD
    fun guardarUsuario() {
        if(validarInformacion()){
            var mPersonas = Personas(0,nombre.value!!,apellidos.value!!,telefono.value!!,email.value!!,"")
            when (operacion) {
                Constantes.OPERACION_INSERTAR -> {
                    viewModelScope.launch {
                        val result = withContext(Dispatchers.IO) {
                            db.personasDao().insert(
                                arrayListOf<Personas>(
                                    mPersonas
                                )
                            )
                        }
                        operacionExitosa.value = result.isNotEmpty()
                    }


                }
                Constantes.OPERACION_EDITAR -> {
                    mPersonas.idPersona = id.value!!
                    viewModelScope.launch {
                        val result = withContext(Dispatchers.IO){
                            db.personasDao().update(mPersonas)
                        }

                        operacionExitosa.value = (result>0)
                    }
                }
            }
        }else{
            operacionExitosa.value = false
        }

    }

    fun cargarDatos() {
       viewModelScope.launch {
           var persona:Personas = withContext(Dispatchers.IO){
               db.personasDao().getById(id.value!!)
           }

           nombre.value = persona.nombre
           apellidos.value = persona.apellidos
           telefono.value = persona.telefono
           email.value = persona.email





       }

    }

    // devuelve true si la información no es nula ni vacia
    private fun validarInformacion():Boolean{
        return !(nombre.value.isNullOrEmpty() ||
                apellidos.value.isNullOrEmpty() ||
                email.value.isNullOrEmpty() ||
                telefono.value.isNullOrEmpty() ||
                edad.value!! <= 0 || edad.value!! >= 100




                )
    }

    fun eliminarPersonas() {
        var mPersonas = Personas(id.value!!,"","","","","")
        viewModelScope.launch {
            var result = withContext(Dispatchers.IO){
                db.personasDao().delete(mPersonas)
            }
            operacionExitosa.value = (result>0)
        }
    }
}






