fun pedirTiempo(msj: String, aceptaVacio: Boolean = false): Int{
    var num: Int? = null
    do{
        println(msj)
        val entrada = readln().trim()
        if(aceptaVacio && entrada.isEmpty()){
            num = 0
        }else{
            try {
                num = entrada.toInt()
            }catch (e: NumberFormatException){
                println("Error, no has introducido un número correcto o vacío.")
            }
        }
    }while (num == null)
    return num
}


fun main(){
    val hora = pedirTiempo("Dime la hora")
    val minuto = pedirTiempo("Dime el minuto", aceptaVacio = true)
    val segundo = pedirTiempo("Dime los segundos", aceptaVacio = true)

    var tiempo1 = tiempo(hora,minuto,segundo)
    var tiempo2 = tiempo(hora,minuto)
    println(tiempo1.sumar(tiempo2))

}