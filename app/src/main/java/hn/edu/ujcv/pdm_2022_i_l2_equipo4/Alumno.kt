package hn.edu.ujcv.pdm_2022_i_l2_equipo4

import android.os.Parcel
import android.os.Parcelable
import java.util.regex.Pattern

class Alumno() : Parcelable {

    var NumeroCuenta : String =""
        set(value){
            val pattern = "20[0-9][0-9][1,2][3,2]0[0-9][0-9][1-9]"
            if(Pattern.matches(pattern, value)){
                field = value
            }else{
                throw IllegalArgumentException("1")
            }
        }
    var Nombre : String = ""
        set(value){
            val pattern = "^([A-Z][a-záéíóú]* )(([A-Z][a-záéíóú]* [A-Z][a-záéíóú]*)|([A-Z][a-záéíóú]*)|([A-Z][a-záéíóú]* [A-Z][a-záéíóú]* [A-Z][a-záéíóú]*))\$"
            if(Pattern.matches(pattern,value)){
                field = value
            }else{
                throw IllegalArgumentException("2")
            }
        }
    var Correo : String = ""
        set(value) {
            val pattern = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})\$"
            if(Pattern.matches(pattern,value)){
                field = value
            }else{
                throw IllegalArgumentException("3")
            }
        }

    constructor(NumeroCuenta:String, Nombre:String, Correo:String) : this(){
        this.NumeroCuenta = NumeroCuenta
        this.Nombre = Nombre
        this.Correo = Correo
    }


    constructor(parcel: Parcel) : this() {
        NumeroCuenta = parcel.readString().toString()
        Nombre = parcel.readString().toString()
        Correo = parcel.readString().toString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(NumeroCuenta)
        parcel.writeString(Nombre)
        parcel.writeString(Correo)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Alumno> {
        override fun createFromParcel(parcel: Parcel): Alumno {
            return Alumno(parcel)
        }

        override fun newArray(size: Int): Array<Alumno?> {
            return arrayOf(Alumno())
        }
    }

}