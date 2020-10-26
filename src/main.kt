import java.io.File
import javax.swing.*

class App(val observer: WeatherObserver) : JFrame() {
    init {
        initUI()
    }

    private fun initUI() {
        layout = null
        isResizable = false
        title = "Simple example"
        setSize(500, 800)
        setLocationRelativeTo(null)
        defaultCloseOperation = EXIT_ON_CLOSE
        val cityField = JTextField("location name")
        val weatherField = JTextArea()
        weatherField.isEditable = false
        weatherField.setBounds(20, 100, 460, 400)
        add(weatherField)
        cityField.setBounds(175, 20, 150, 30)
        add(cityField)
        val showButton = JButton("Show Weather")
        showButton.setBounds(190, 60, 120, 20)
        showButton.addActionListener {
            observer.location = cityField.text
            observer.update()
            var print = ""
            val current = observer["current"].getNode()
            for (i in current.keys()) {
                print += (i + ": " + current[i].value + "\n")
                println(i + ": " + current[i].value)
            }
            weatherField.text = print
        }
        add(showButton)

    }
}

fun main() {
    val test = App(WeatherObserver(File("configs")))
    test.isVisible = true
}
