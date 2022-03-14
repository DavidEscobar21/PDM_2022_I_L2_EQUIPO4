package hn.edu.ujcv.pdm_2022_i_l2_equipo4

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_clases.*
import java.util.*

class Clases : AppCompatActivity() {
    var dt = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clases)
        btnMostrarClases.setOnClickListener {
            val intent: Intent = Intent(this, MostrarClases::class.java)
            startActivity(intent) }
        btnRegistrarClase.setOnClickListener { GuardarClase() }
    }


    private fun GuardarClase() {

        if (txtCodigoClases.text.trim().isEmpty() || txtCodigoClases.text.length != 8){
            txtCodigoClases.error ="El código debe de contener 8 dígitos"
        }else if(txtClases.text.trim().isEmpty() || txtClases.text.length < 3){
            txtClases.error ="El nombre de la clase no puede contener menos de 3 letras"
        }else if(txtSeccion.text.trim().isEmpty()){
            txtSeccion.error ="La sección no puede estar vacía"
        }else if(txvHora.text.isEmpty()){
            txvHora.error ="Debe de seleccionar una fecha"
        }else if(txtEdificio.text.trim().isEmpty()){
            txtEdificio.error ="El edificio no debe de estar vacío"
        }else if(txtAula.text.trim().isEmpty()){
            txtAula.error ="El aula no debe de estar vacía"
        }else{
        val clase = DatosClases(txtCodigoClases.text.toString(),txtClases.text.toString(),
            txtSeccion.text.toString(),txvHora.text.toString(),txtEdificio.text.toString(),
            txtAula.text.toString().toInt())
        Singleton.listaClases.add(clase)
        Toast.makeText(this, "Se guardó correctamente", Toast.LENGTH_SHORT).show()
        limpiarClase()
        }
    }



    fun openDateTimePicker(view: android.view.View) {
        var c: Calendar = Calendar.getInstance()

        TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { timePicker, hh, mi ->
            if(mi<=9 && hh<=9){
            dt = "0$hh:0$mi"
            }else if(mi<=9 && hh>9){
            dt = "$hh:0$mi"
            }else if(mi>9 && hh<=9){
                dt = "0$hh:$mi"
            }else{
                dt = "$hh:$mi"
            }

            txvHora.setText(dt)
        },c.get(Calendar.HOUR),c.get(Calendar.MINUTE),true).show()
    }


    private fun limpiarClase() {
        txtAula.setText("")
        txtClases.setText("")
        txtCodigoClases.setText("")
        txtEdificio.setText("")
        txtSeccion.setText("")
        txvHora.setText("")
    }

}