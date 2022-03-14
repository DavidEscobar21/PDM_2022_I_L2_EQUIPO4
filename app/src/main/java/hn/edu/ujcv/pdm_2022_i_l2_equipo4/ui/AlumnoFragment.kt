package hn.edu.ujcv.pdm_2022_i_l2_equipo4.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import hn.edu.ujcv.pdm_2022_i_l2_equipo4.R

class AlumnoFragment : Fragment() {

    companion object {
        fun newInstance() = AlumnoFragment()
    }

    private lateinit var viewModel: AlumnoViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.alumno_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AlumnoViewModel::class.java)
        // TODO: Use the ViewModel
    }




}