package com.example.myagenda.dialogos

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.myagenda.R


// he copiado el código del diálogo de la página DEVELOPERS (DIÁLOGOS)

class BorrarDialogo(var borrarListener:BorrarListener) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            // Use the Builder class for convenient dialog construction
            val builder = AlertDialog.Builder(it)
            builder.setMessage(R.string.borrar_personas)
                .setPositiveButton(R.string.yes,
                    DialogInterface.OnClickListener { dialog, id ->
                        borrarListener.borrarPersonas()
                    })
                .setNegativeButton(R.string.cancel,
                    DialogInterface.OnClickListener { dialog, id ->
                      dialog.cancel()
                    })
            // Create the AlertDialog object and return it
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    // agregamos una interfaz

    interface BorrarListener{
        fun borrarPersonas()
    }
}