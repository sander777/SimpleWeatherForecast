package JSONTree

import java.lang.Exception

class Node(value: String?, node: JSONTree?) {
    var value: String? = value
    var node: JSONTree? = node

    @JvmName("getNode1")
    fun getNode(): JSONTree {
        if (this.node != null) {
            return this.node!!
        } else {
            throw Exception("E")
        }
    }

    @JvmName("getValue1")
    fun getValue(): String {
        if (this.value != null) {
            return this.value!!
        } else {
            throw Exception("E")
        }
    }
}

class JSONTree() {
    var childs: HashMap<String, Node> = HashMap()
    operator fun get(key: String): Node {
        return this.childs[key]!!
    }

    fun keys(): MutableSet<String> {
        return childs!!.keys
    }
}

fun MyString.getTree(): JSONTree {
    val result = JSONTree()
    if (this.pop(0) != '{') {

    }
    while (str.isNotEmpty()) {
        var c = this.str[0]
        if (c == '}') break
        if (c.toString().matches(Regex("[:, \n]+"))) {
            this.pop(0)
            continue
        }
        if (c == '\"') {
            val word = this.readFromTill("\"", "\"")
            if (this.str.matches(Regex(":[ ]*\".*\".*"))) {
                result.childs[word] = Node(this.readFromTill("\"", "\""), null)
                c = this.pop(0)
            } else if (this.str.matches(Regex(":[ ]*\\{.*"))) {
                var i: Int = 0
                while (str[i] != '{') i++
                str = str.substring(i, str.length)
                result.childs[word] = Node(null, this.getTree())
                c = this.pop(0)
            } else if (this.str.matches(Regex(":[ ]*.*"))) {
                result.childs[word] = Node(this.readFromTill(":", ","), null)
            }
        }
        if (c == '}') break
    }
    return result
}

fun MyString.readFromTill(from: String, end: String): String {
    var c = ""
    var result = ""
    c = this.pop(0).toString()

    while (c != from) {
        c = this.pop(0).toString()
    }
    c = this.pop(0).toString()
    while (c != end) {
        result += c
        c = this.pop(0).toString()
    }
    return result
}



