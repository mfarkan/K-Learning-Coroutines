package composingSuspendedFunctions_11

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis


// runBlocking = The main difference is that the runBlocking method blocks the current thread for waiting.
// coroutineScope = while coroutineScope just suspends, releasing the underlying thread for other usages.
// launch = It launches a new coroutine concurrently with the rest of the code, which continues to work independently.

//We use a normal sequential invocation, because the code in the coroutine, just like in the regular code, is sequential by default.
// for this example, we did not do anything special to make these functions to run separately it should work as a
// regular code
fun main(args: Array<String>) = runBlocking {
    val elapsedTime = measureTimeMillis {
        val one = doSomethingUsefull1()
        val two = doSomethingUsefull2()
        println("The answer is ${one + two}")
    }
    println("Completed in $elapsedTime ms")
}

suspend fun doSomethingUsefull1(): Int {
    delay(1000L)
    println("I'm done with first one with ${Thread.currentThread().name}")
    return 5
}

suspend fun doSomethingUsefull2(): Int {
    delay(1000L)
    println("I'm done with second one with ${Thread.currentThread().name}")
    return 10
}