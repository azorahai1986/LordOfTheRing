package com.example.lordofthering.dialog

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import com.bumptech.glide.Glide
import com.example.lordofthering.MainActivity
import com.example.lordofthering.R
import com.example.lordofthering.databinding.DielogInfoBinding
import com.example.lordofthering.home.model.datamodel.Doc
import com.example.lordofthering.home.model.datamodel.Moview

class StartDialog {

    fun inflateDialog(context: Context, activity: Activity, list:Doc, image: String) {
        val dialogBinding = DielogInfoBinding.inflate(activity.layoutInflater, null, false)
        val dialogConstructor = AlertDialog.Builder(context).setView(dialogBinding.root)

        val alertDialog = dialogConstructor.show()
        alertDialog.setCancelable(false)
       /* dialogBinding.constraintContainer.setOnClickListener {
            alertDialog.dismiss()

        }*/

        dialogBinding.apply {
            textExit.setOnClickListener {
                alertDialog.dismiss()
            }
            Glide.with(activity).load(image).into(imageDialog)

        }

            "Nominada a ${list.academyAwardNominations} premios de la academia.".also { dialogBinding.textNomination.text = it }

            if (list.academyAwardWins == 0) "No obtuvo premios".also { dialogBinding.textWins.text = it } else "Ganadora de ${list.academyAwardWins} premios en todo el mundo.".also { dialogBinding.textWins.text = it }
            ("Su recaudación fué de $${list.boxOfficeRevenueInMillions} Millones de dolares en todo el mundo, con un presupuesto de" +
                    " $${list.budgetInMillions} Millones de dolares.").also { dialogBinding.textrecaudacion.text = it }

            ("Tiene una duración de ${list.runtimeInMinutes} minutos y recibió una calificación total de ${list.rottenTomatoesScore}% " +
                    "de la plataforma Rotten Tomatoes.").also { dialogBinding.textRotenTomatos.text = it }


        Log.e("PREMIOS", list.academyAwardWins.toString())



    }





}