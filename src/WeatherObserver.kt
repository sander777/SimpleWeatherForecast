import JSONTree.JSONTree
import JSONTree.MyString
import JSONTree.Node
import JSONTree.getTree
import java.io.File
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.util.*

class WeatherObserver {
    private var token: String = ""
    var location: String = ""
    var weather: JSONTree = JSONTree()

    constructor(configFile: File) {
        var content = ""
        val read = Scanner(configFile)
        while (read.hasNext()) {
            content += read.next()
        }
        this.token = MyString(content).getTree()["weather-token"].getValue()
    }

    constructor(configFile: File, location: String) {
        var content = ""
        this.location = location
        val read = Scanner(configFile)
        while (read.hasNext()) {
            content += read.next()
        }
        this.token = MyString(content).getTree()["weather-token"].getValue()
    }

    fun update() {
        val url = URL("http://api.weatherstack.com/current?access_key=${this.token}&query=${this.location}")
//        val url = URL("http://192.168.1.56/weatherAPI/weatherforecast?city=${this.location}")
        val con: HttpURLConnection = url.openConnection() as HttpURLConnection
        con.requestMethod = "GET"
        val inStream = InputStreamReader(con.inputStream)
        var buff = ""
        for (i in inStream.readLines()) {
            buff += i
        }
        println(buff)
        val weatherMyStr = MyString(buff)
        this.weather = weatherMyStr.getTree()
        println(weatherMyStr.str)
    }

    operator fun get(index: String): Node {
        return this.weather[index]
    }
}