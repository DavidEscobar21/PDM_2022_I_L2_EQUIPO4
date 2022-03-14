package hn.edu.ujcv.pdm_2022_i_l2_equipo4

import android.os.Bundle
import android.widget.ArrayAdapter
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import hn.edu.ujcv.pdm_2022_i_l2_equipo4.databinding.ActivityMostrarMatriculaBinding
import kotlinx.android.synthetic.main.fragment_first2.*

class MostrarMatricula : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMostrarMatriculaBinding
    var listItems = ArrayList<String>()
    var adapters: ArrayAdapter<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

     binding = ActivityMostrarMatriculaBinding.inflate(layoutInflater)
     setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_mostrar_matricula)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

      addListItem()
    }

    private fun addListItem(){
        for (i in Singleton.listaMatricula) {
            listItems.add(i.alumn+" "+i.clas)
        }
        adapters?.notifyDataSetChanged()
    }

    override fun onStart() {
        super.onStart()
        adapters = ArrayAdapter(this,android.R.layout.simple_list_item_1,listItems)
        listaMatricula.adapter = adapters
    }

    override fun onSupportNavigateUp(): Boolean {
    val navController = findNavController(R.id.nav_host_fragment_content_mostrar_matricula)
    return navController.navigateUp(appBarConfiguration)
            || super.onSupportNavigateUp()
    }


}