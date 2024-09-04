package chapter6.jiwon

sealed class Tree<out T>
object Nil: Tree<Nothing>()
data class Node<out T>(val el: T, val left: Tree<T>, val right: Tree<T>): Tree<T>()

fun Tree<Int>.insert(elem: Int): Tree<Int> = when(this)  {
    Nil -> Node(elem, Nil, Nil)
    is Node -> when {
        elem <= el -> Node(el, left.insert(elem), right)
        else -> Node(el, left, right.insert(el))
    }
}
