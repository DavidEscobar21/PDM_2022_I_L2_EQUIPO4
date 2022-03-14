package hn.edu.ujcv.pdm_2022_i_l2_equipo4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import hn.edu.ujcv.pdm_2022_i_l2_equipo4.ui.AlumnoFragment
import java.lang.IllegalArgumentException

class AlumnoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.alumno_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, AlumnoFragment.newInstance())
                    .commitNow()
        }
    }

    fun guardarAlumno(view : View) {
        var numeroCuenta = findViewById<EditText>(R.id.editTextNumber)
        var nombre = findViewById<EditText>(R.id.textNombre)
        var correo = findViewById<EditText>(R.id.textCorreo)
        if (verificarCamposEnBlanco()){
            try {
                val alumno = Alumno(numeroCuenta.text.toString(),nombre.text.toString(),correo.text.toString())
                Singleton.listaAlumos.add(alumno) //guardar alumno
                Snackbar.make(view, "Alumno almacenado correctamente.", Snackbar.LENGTH_SHORT).show()
                reiniciar()
            } catch (ex: IllegalArgumentException) {
                when (Integer.parseInt(ex.message)) {
                    1 -> Snackbar.make(view, "Numero de cuenta inválido.", Snackbar.LENGTH_INDEFINITE).show()
                    2 -> Snackbar.make(view, "Nombre inválido.", Snackbar.LENGTH_SHORT).show()
                    3 -> Snackbar.make(view, "Correo inválido.", Snackbar.LENGTH_SHORT).show()
                }
            }
        }else{
            Snackbar.make(view, "Debes llenar todos los campos.", Snackbar.LENGTH_LONG).show()
        }
    }

        fun verificarCamposEnBlanco():Boolean{
            if (findViewById<TextView>(R.id.editTextNumber).text.isEmpty() || findViewById<TextView>(R.id.textNombre).text.isEmpty() || findViewById<TextView>(R.id.textCorreo).text.isEmpty()){
                return false
            }
            return true
        }

        fun verLista(view :View){
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, ListaAlumnosFragment.newInstance())
            .commitNow()

    }

    fun reiniciar(){
        var numeroCuenta = findViewById<EditText>(R.id.editTextNumber)
        var nombre = findViewById<EditText>(R.id.textNombre)
        var correo = findViewById<EditText>(R.id.textCorreo)
        numeroCuenta.text.clear()
        nombre.text.clear()
        correo.text.clear()
    }




}