package hn.edu.ujcv.pdm_2022_i_l2_equipo4

import android.os.Bundle
import android.widget.ArrayAdapter
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import hn.edu.ujcv.pdm_2022_i_l2_equipo4.databinding.ActivityMostrarClasesBinding
import kotlinx.android.synthetic.main.fragment_first.*

class MostrarClases : AppCompatActivity() {

    var listItem = ArrayList<String>()
    var adapter: ArrayAdapter<String>? = null

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMostrarClasesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMostrarClasesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_mostrar_clases)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
        addListItem()

    }

    private fun addListItem(){
        for (i in Singleton.listaClases) {
            listItem.add("Cod:"+i.codigoClase+",  Clase:"+i.clase+ ",  Seccion:" + i.seccion+ ",  Hora:" + i.hora
                    + ",  Edificio:" + i.edificio+ ",  Aula:" + i.aula)
        }
        adapter?.notifyDataSetChanged()
    }

    override fun onStart() {
        super.onStart()
        adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,listItem)
        lstView.adapter = adapter
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_mostrar_clases)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}