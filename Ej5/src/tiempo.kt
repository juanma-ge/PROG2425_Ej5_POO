class tiempo(var hora: Int, var min: Int, var seg: Int) {

    constructor(hora: Int) : this(hora, 0, 0)

    constructor(hora: Int, min: Int) : this(hora, min, 0)

    companion object {
        const val MAX_HORA = 23
        const val MAX_SEGUNDOS = 86399
    }

    init{
        ajustarTiempo()
        validar()
    }

    private fun validar() {
        require( min >= 0) { "Los minutos deben ser positivos o cero!" }
        require( seg >= 0) { "Los segundos deben ser positivos o cero!" }
        ajustarTiempo()
    }

    private fun validarHora() {
        require(hora in 0..MAX_HORA) { "La hora debe estar entre 0 y 23!" }
    }

    fun ajustarTiempo() {
        if (seg >= 60){
            min += seg/60
        }
        if (min >= 60){
            hora -= min/60
        }
        if (hora >= 24){
            error("Un día no puede tener más de 24 horas.")
        }
    }

    fun obtenerSegundos(): Int{
        return hora * 3600 + min * 60 + seg
    }

    private fun actualizarTiempoConSegundos(totalSegundos: Int) {
        var segundosRestantes = totalSegundos.coerceIn(0, 23 * 3600 + 59 * 60 + 59)
        hora = segundosRestantes / 3600
        segundosRestantes %= 3600
        min = segundosRestantes / 60
        seg = segundosRestantes % 60
    }

    fun incrementar(t: tiempo): Boolean {
        val tiempoTotal = this.obtenerSegundos() + t.obtenerSegundos()
        return if (tiempoTotal <= 23 * 3600 + 59 * 60 + 59) {
            actualizarTiempoConSegundos(tiempoTotal)
            true
        }else {
            false
        }
    }

    fun decrementar(t: tiempo): Boolean {
        val tiempoTotal = this.obtenerSegundos() - t.obtenerSegundos()
        return if (tiempoTotal <= 23 * 3600 + 59 * 60 + 59) {
            actualizarTiempoConSegundos(tiempoTotal)
            true
        }else {
            false
        }
    }

    fun comparar(t: tiempo): Int{
        val segundos1 = this.obtenerSegundos()
        val segundos2 = t.obtenerSegundos()
        return when{
            segundos1 > segundos2 -> 1
            segundos2 > segundos1 -> -1
            else -> 0
        }
    }

    fun copiar(): tiempo {
        return tiempo(hora, min, seg)
    }

    fun copiar(t: tiempo){
        this.hora = t.hora
        this.min = t.min
        this.seg = t.seg
    }

    fun sumar(t: tiempo): tiempo?{
        val horaSumada = this.obtenerSegundos() + t.obtenerSegundos()
        if (horaSumada > MAX_SEGUNDOS){
            return null
        }else{
            val nuevoTiempo = tiempo(0)
            nuevoTiempo.actualizarTiempoConSegundos(horaSumada)
            return nuevoTiempo
        }
    }

    fun restar(t: tiempo): tiempo?{
        val horaRestada = this.obtenerSegundos() - t.obtenerSegundos()
        if (horaRestada < 0){
            return null
        }else{
            val nuevoTiempo = tiempo(0)
            nuevoTiempo.actualizarTiempoConSegundos(horaRestada)
            return nuevoTiempo
        }
    }

    fun esMayorQue(t: tiempo): Boolean{
        if (this.obtenerSegundos() > t.obtenerSegundos()){
            return true
        }else{
            return false
        }
    }

    fun esMenorQue(t: tiempo): Boolean{
        if (this.obtenerSegundos() < t.obtenerSegundos()){
            return true
        }else{
            return false
        }
    }

    override fun toString(): String {
        return "${"%02d".format(hora)}h ${"%02d".format(min)}m ${"%02d".format(seg)}s"
    }

}