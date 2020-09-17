import java.io.File

fun main() {
    val weather = Weather(File("configs"), "Kyyiv")
    weather.update();
    val current = weather["current"].getNode()
    for(i in current.keys()) {
        println(i + ": " + current[i].value)
    }
}
