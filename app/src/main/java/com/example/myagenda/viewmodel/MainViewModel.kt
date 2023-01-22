package com.example.myagenda.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myagenda.configuraciones.PersonasApp.Companion.db
import com.example.myagenda.models.Personas
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel: ViewModel() {
    val personasList = MutableLiveData<List<Personas>?>()
    var parametroBusqueda = MutableLiveData<String?>()

    fun iniciar() {
        viewModelScope.launch {
            personasList.value = withContext(Dispatchers.IO) {

                db.personasDao().getAll()
            }

        }
    }

    fun buscarPersonas() {
        viewModelScope.launch {
            personasList.value = withContext(Dispatchers.IO) {

                db.personasDao().getByName(parametroBusqueda.value!!)
            }

        }
    }
}
