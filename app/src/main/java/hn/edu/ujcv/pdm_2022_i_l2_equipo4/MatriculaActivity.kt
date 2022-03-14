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
import kotlinx.android.synthetic.main.activity_matricula.*
import java.lang.IllegalArgumentException

class MatriculaActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_matricula)
        rellenarAlunmno()
        rellenarClases()

        btnMostrarMatricula.setOnClickListener {
            val intent: Intent = Intent(this, MostrarMatricula::class.java)
            startActivity(intent) }


        btnEnviarMatricula.setOnClickListener{MandarCorreo()}

    }

    fun rellenarAlunmno(){
        lateinit var opciones:Spinner
        lateinit var texto:EditText


        var listItem = ArrayList<String>()
        for (i in Singleton.listaAlumos) {
            listItem.add("Cuenta:"+i.NumeroCuenta+",  Nombre:"+i.Nombre)
        }


        opciones=findViewById(R.id.spnAlumnos)
        texto = findViewById(R.id.mostrar)
        val adaptador = ArrayAdapter(this,android.R.layout.simple_spinner_item,listItem)
        opciones.adapter=adaptador

        opciones.onItemSelectedListener= object:
            AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                p0: AdapterView<*>?,
                p1: View?,
                p2: Int,
                p3: Long
            ) {

                texto.setText(opciones.selectedItem.toString())
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
    }

    fun rellenarClases(){
        lateinit var opcionesClases:Spinner
        lateinit var textoClase:EditText

        var listItemm = ArrayList<String>()
        for (i in Singleton.listaClases) {
            listItemm.add("Cod:"+i.codigoClase+",  Clase:"+i.clase+ ",  Seccion:" + i.seccion+ ", " +
                    "Hora:" + i.hora + ",  Edificio:" + i.edificio+ ",  Aula:" + i.aula)
        }

        opcionesClases=findViewById(R.id.spnClases)
        textoClase = findViewById(R.id.txvClases)
        val adaptador = ArrayAdapter(this,android.R.layout.simple_spinner_item,listItemm)
        opcionesClases.adapter=adaptador

        opcionesClases.onItemSelectedListener= object:
            AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                p0: AdapterView<*>?,
                p1: View?,
                p2: Int,
                p3: Long
            ) {
                //textoClase.setText(opcionesClases.selectedItemPosition.toString())
                textoClase.setText(opcionesClases.selectedItem.toString())
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
    }

    fun guardarMatricula(view: View){
        var Cla = findViewById<EditText>(R.id.txvClases)
        var Alum = findViewById<EditText>(R.id.mostrar)

        if (verificarCamposEnBlanco()){

            try {
                var clase = Cla.text.toString().split(",")
                var alumn = Alum.text.toString().split(",")
                val matricula = Matricula(Cla.text.toString(),Alum.text.toString(),alumn[0]+" "+ clase[1])
                Singleton.listaMatricula.add(matricula) //guardar alumno
                Snackbar.make(view, "Matricula almacenado correctamente.", Snackbar.LENGTH_SHORT).show()
                reiniciar()
            } catch (ex: IllegalArgumentException) {
                when (Integer.parseInt(ex.message)) {
                    1 -> Snackbar.make(view, "Campo Alumno Vacio!.", Snackbar.LENGTH_INDEFINITE).show()
                    2 -> Snackbar.make(view, "Campo Clase Vacioo.", Snackbar.LENGTH_SHORT).show()

                }
            }
        }else{
            Snackbar.make(view, "Debes llenar todos los campos.", Snackbar.LENGTH_LONG).show()
        }
    }

    fun verificarCamposEnBlanco():Boolean{
        if (findViewById<TextView>(R.id.mostrar).text.isEmpty() || findViewById<TextView>(R.id.txvClases).text.isEmpty()){
            return false
        }
        return true
    }

    fun reiniciar(){
        var Cla = findViewById<EditText>(R.id.txvClases)

        Cla.text.clear()

    }

    private fun MandarCorreo() {
        var Correo = findViewById<EditText>(R.id.mostrar)
        val TO = arrayOf(getcorreo(Correo.text.toString().substring(7,17))) //Direcciones email  a enviar.

        val emailIntent = Intent(Intent.ACTION_SEND)

        emailIntent.data = Uri.parse("mailto:")
        emailIntent.type = "text/plain"
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO)
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Matricula")
        emailIntent.putExtra(Intent.EXTRA_TEXT, matricula(Correo.text.toString())) // * configurar email aquí!


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

    fun matricula(Alumno:String):String{

        var correo:String = "Las clase en las que usted ha sido matriculado son:"

        for (i in Singleton.listaMatricula){

            if(Alumno.equals(i.alumn)){
                correo += "\n\n"+i.clas
            }

        }

        return correo

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