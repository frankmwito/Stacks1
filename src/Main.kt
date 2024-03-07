interface Stack<T : Any> {
    fun pop(): T?
    fun push(element: T)
    fun peek(): T?
    val count: Int
    val isEmpty: Boolean
        get() = count == 0
}

class StackImpl<T : Any> : Stack<T> {
    private val storage = arrayListOf<T>()

    override val count: Int
        get() = storage.size

    override fun push(element: T) {
        storage.add(element)
    }

    override fun peek(): T? {
        return storage.lastOrNull()
    }

    override fun pop(): T? {
        return storage.removeLastOrNull()
    }
}

fun String.evaluatePostfix(): Int {
    val stack = StackImpl<Int>()

    for (char in this) {
        when (char) {
            in '0'..'9' -> {
                stack.push(char.toString().toInt())
            }
            '+' -> {
                val operand2 = stack.pop() ?: return 0
                val operand1 = stack.pop() ?: return 0
                stack.push(operand1 + operand2)
            }
            '*' -> {
                val operand2 = stack.pop() ?: return 0
                val operand1 = stack.pop() ?: return 0
                stack.push(operand1 * operand2)
            }
        }
    }

    return stack.pop() ?: 0
}

fun main() {
    val expression1 = "23+5*"
    val expression2 = "52*3+"
    val expression3 = "82/3-"

    println(expression1.evaluatePostfix()) // Should print 25
    println(expression2.evaluatePostfix()) // Should print 16
    println(expression3.evaluatePostfix()) // Should print 2
}
