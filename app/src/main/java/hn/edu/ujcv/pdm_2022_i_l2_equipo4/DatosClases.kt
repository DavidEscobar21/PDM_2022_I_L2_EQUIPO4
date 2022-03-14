package hn.edu.ujcv.pdm_2022_i_l2_equipo4

class DatosClases {

    var codigoClase:String = ""
    var clase:String = ""
    var seccion:String = ""
    var hora:String = ""
    var edificio:String = ""
    var aula:Int = 0

     constructor()
     constructor(codigoClase: String,clase: String,seccion: String,hora: String,edificio: String,aula: Int){
        this.codigoClase = codigoClase
        this.clase = clase
        this.seccion = seccion
        this.hora = hora
        this.edificio = edificio
        this.aula = aula
    }
}