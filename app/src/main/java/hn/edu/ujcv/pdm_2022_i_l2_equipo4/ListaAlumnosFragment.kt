package hn.edu.ujcv.pdm_2022_i_l2_equipo4

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.ListView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


/**
 * A simple [Fragment] subclass.
 * Use the [BlankFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListaAlumnosFragment : Fragment() {

    private lateinit var listView : ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        var view = inflater.inflate(R.layout.alumnos_list, container, false)
        listView = view.findViewById(R.id.alumnosListView)

        val itemsLista = arrayOfNulls<String>(Singleton.listaAlumos.size)

        for (i in 0 until Singleton.listaAlumos.size) {
            val item = Singleton.listaAlumos[i]
            itemsLista[i] = item.NumeroCuenta + "\t\t" + item.Nombre
        }

        val adapter = ArrayAdapter(this.requireContext(), android.R.layout.simple_list_item_1, itemsLista)

        listView.adapter = adapter

        val botonRegreso = view.findViewById<ImageButton>(R.id.regresar)
        botonRegreso.setOnClickListener {
            val intent = Intent(this.context,AlumnoActivity::class.java)
            startActivity(intent)

        }

        return view
    }


    companion object {
        fun newInstance() = ListaAlumnosFragment()
    }




}