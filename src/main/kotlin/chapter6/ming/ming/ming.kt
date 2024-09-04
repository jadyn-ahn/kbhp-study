package chapter6.ming

sealed class Tree<out T> {
    object EmptyTree : Tree<Nothing>()

    data class Node<out T>(
        val value: T,
        val left: Tree<T> = EmptyTree,
        val right: Tree<T> = EmptyTree,
    ) : Tree<T>()
}

fun Tree<Int>.insert(elem: Int): Tree<Int> =
    when (this) {
        Tree.EmptyTree -> Tree.Node(elem)
        is Tree.Node -> {
            if (elem < value) {
                Tree.Node(value, left.insert(elem), right)
            } else {
                Tree.Node(value, left, right.insert(elem))
            }
        }
        else -> throw IllegalArgumentException() // 패턴 매칭이 전부 될텐데 왜 컴파일러가 else를 요구할까요? 컴파일러 버전이?
    }

tailrec fun Tree<Int>.insertTailrec(elem: Int): Tree<Int> =
    when (this) {
        Tree.EmptyTree -> Tree.Node(elem)
        is Tree.Node -> {
            if (elem < value) {
                left.insertTailrec(elem)
            } else {
                right.insertTailrec(elem)
            }
        }
        else -> throw IllegalArgumentException()
    }

fun Tree<Int>.contains(elem: Int): Boolean =
    when (this) {
        Tree.EmptyTree -> false
        is Tree.Node -> {
            if (elem < value) {
                left.contains(elem)
            } else if (elem > value) {
                right.contains(elem)
            } else {
                true
            }
        }
        else -> throw IllegalArgumentException()
    }
