package JSONTree

class MyString(var str: String) {
}

fun MyString.pop(index: Int): Char {
    val c = this.str[index]
    this.str = this.str.replaceRange(index, index+1, "")
    return c
}