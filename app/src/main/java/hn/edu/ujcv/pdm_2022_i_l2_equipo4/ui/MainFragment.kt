package hn.edu.ujcv.pdm_2022_i_l2_equipo4.ui

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import hn.edu.ujcv.pdm_2022_i_l2_equipo4.*
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: ClasesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ClasesViewModel::class.java)
        // TODO: Use the ViewModel
        btnClases.setOnClickListener {
            val intent:Intent = Intent(this.context, Clases::class.java)
            startActivity(intent)
        }
        btnAlumnos.setOnClickListener {
            val intent:Intent = Intent(this.context, AlumnoActivity::class.java)
            startActivity(intent)
        }
        btnMatricula.setOnClickListener {
            val intent:Intent = Intent(this.context, MatriculaActivity::class.java)
            startActivity(intent)
        }

        btnNotas.setOnClickListener{

            val intent:Intent = Intent(this.context,IngresarNota::class.java)
            startActivity(intent)
        }
    }




}