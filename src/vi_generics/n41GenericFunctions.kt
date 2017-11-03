package vi_generics

import util.TODO
import java.util.*
import kotlin.collections.HashSet

fun task41(): Nothing = TODO(
        """
        Task41.
        Add a 'partitionTo' function that splits a collection into two collections according to a predicate.
        Uncomment the commented invocations of 'partitionTo' below and make them compile.

        There is a 'partition()' function in the standard library that always returns two newly created lists.
        You should write a function that splits the collection into two collections given as arguments.
        The signature of the 'toCollection()' function from the standard library may help you.
    """,
        references = { l: List<Int> ->
            l.partition { it > 0 }
            l.toCollection(HashSet<Int>())
        }
)

// FIXME

//inline fun <T> List<T>.partitionTo(_key: List<T>, _val: List<T>, predicate: (T) -> Boolean): Pair<List<T>, List<T>> {
//    return partition(predicate)
//}
//
//inline fun <T> Set<T>.partitionTo(_key: Set<T>, _val: Set<T>, predicate: (T) -> Boolean): Pair<Set<T>, Set<T>> {
//    return with(partition(predicate)) {
//        Pair(first.toCollection(HashSet<T>()), second.toCollection(HashSet<T>()))
//    }
//}

inline fun <reified T, C : MutableCollection<T>> Collection<T>.partitionTo(first: C, second: C, predicate: (T) -> Boolean): Pair<C, C> {

}

fun List<String>.partitionWordsAndLines(): Pair<List<String>, List<String>> {
    return partitionTo(ArrayList<String>(), ArrayList()) { s -> !s.contains(" ") }
}

fun Set<Char>.partitionLettersAndOtherSymbols(): Pair<Set<Char>, Set<Char>> {
    return partitionTo(HashSet<Char>(), HashSet()) { c -> c in 'a'..'z' || c in 'A'..'Z' }
}