package hn.edu.ujcv.pdm_2022_i_l2_equipo4

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_ingresar_nota.*
import java.lang.IllegalArgumentException

class IngresarNota : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingresar_nota)
        spinnerAlumno()


        btnEnviarN.setOnClickListener { MandarCorreo() }

    }

    fun GuardarNota(view : View) {
        var notas = findViewById<EditText>(R.id.seleccionMatricula)
        var PParcial = findViewById<EditText>(R.id.txtPrimerP)
        var SParcial = findViewById<EditText>(R.id.txtSegundoP)
        var TParcial = findViewById<EditText>(R.id.txtTercerP)


        if (verificarCamposEnBlanco()){
            try {
                val notass = DatosNota(notas.text.toString(),PParcial.text.toString().toInt(),SParcial.text.toString().toInt(),TParcial.text.toString().toInt())
                Singleton.listaNotas.add(notass) //guardar alumno
                Snackbar.make(view, "notas almacenado correctamente.", Snackbar.LENGTH_SHORT).show()
                reiniciar()
            } catch (ex: IllegalArgumentException) {
                when (Integer.parseInt(ex.message)) {
                    1 -> Snackbar.make(view, "Numero de cuenta inválido.", Snackbar.LENGTH_INDEFINITE).show()
                    2 -> Snackbar.make(view, "Nombre inválido.", Snackbar.LENGTH_SHORT).show()
                    3 -> Snackbar.make(view, "Correo inválido.", Snackbar.LENGTH_SHORT).show()
                    4 -> Snackbar.make(view, "Correo inválido.", Snackbar.LENGTH_SHORT).show()
                }
            }
        }else{
            Snackbar.make(view, "Debes llenar todos los campos.", Snackbar.LENGTH_LONG).show()
        }
    }

    fun verificarCamposEnBlanco():Boolean{
        if (findViewById<TextView>(R.id.seleccionMatricula).text.isEmpty() || findViewById<TextView>(R.id.txtPrimerP).text.isEmpty() || findViewById<TextView>(R.id.txtSegundoP).text.isEmpty() || findViewById<TextView>(R.id.txtTercerP).text.isEmpty()){
            return false
        }
        return true
    }

    fun reiniciar(){
        var primerP = findViewById<EditText>(R.id.txtPrimerP)
        var segundoP = findViewById<EditText>(R.id.txtSegundoP)
        var tercerP = findViewById<EditText>(R.id.txtTercerP)
        primerP.text.clear()
        segundoP.text.clear()
        tercerP.text.clear()
    }







private fun spinnerAlumno() {

    lateinit var infoMatricula: Spinner
    lateinit var textoNota:EditText


    var listItemm = ArrayList<String>()
    for (i in Singleton.listaMatricula) {
        listItemm.add(i.matricula)
    }


    infoMatricula=findViewById(R.id.spnNota)
    textoNota=findViewById(R.id.seleccionMatricula)


    val adaptador = ArrayAdapter(this,android.R.layout.simple_spinner_item,listItemm)
    infoMatricula.adapter=adaptador

    infoMatricula.onItemSelectedListener= object:
        AdapterView.OnItemSelectedListener{
        override fun onItemSelected(
            p0: AdapterView<*>?,
            p1: View?,
            p2: Int,
            p3: Long
        ) {

            textoNota.setText(infoMatricula.selectedItem.toString())

        }
        override fun onNothingSelected(p0: AdapterView<*>?) {
            TODO("Not yet implemented")
        }

    }

}

    private fun MandarCorreo() {
        var notas = findViewById<EditText>(R.id.seleccionMatricula)
        val TO = arrayOf(getcorreo(notas.text.toString().substring(7,17))) //Direcciones email  a enviar.

        val emailIntent = Intent(Intent.ACTION_SEND)

        emailIntent.data = Uri.parse("mailto:")
        emailIntent.type = "text/plain"
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO)
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Notas")
        emailIntent.putExtra(Intent.EXTRA_TEXT, notas()) // * configurar email aquí!


        try {
            startActivity(Intent.createChooser(emailIntent, "Enviar email."))
            Log.i("EMAIL", "Enviando email...")
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(
                this,
                "NO existe ningún cliente de email instalado!.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    fun notas():String{

        var notas = findViewById<EditText>(R.id.seleccionMatricula)
        var clase = notas.text.toString().split(":")
        var Mensaje = "No se han ingresado las notas de la Clase: ${clase[2]}"
        for (i in Singleton.listaNotas){

            if (i.not.equals(notas.text.toString())){

                Mensaje="Sus notas de la clase: ${clase[2]}\n" +
                        "Primer Parcial: ${i.primerP} \n" +
                        "Segundo Parcial: ${i.segundoP}\n" +
                        "Tercer Parcial: ${i.tercerP}\n" +
                        "Total: ${(i.primerP+i.segundoP+i.tercerP)/3}"
            }

        }


        return Mensaje

    }

    fun getcorreo(numeroCuenta:String):String{

        var correo:String = "Davidandrew.de@gmail.com"

        for (i in Singleton.listaAlumos){

            if(numeroCuenta.equals(i.NumeroCuenta)){
                correo = i.Correo
            }

        }

        return correo

    }

}
