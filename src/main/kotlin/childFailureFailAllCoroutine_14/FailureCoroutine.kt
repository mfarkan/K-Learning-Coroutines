package childFailureFailAllCoroutine_14

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

// runBlocking = The main difference is that the runBlocking method blocks the current thread for waiting.
// coroutineScope = while coroutineScope just suspends, releasing the underlying thread for other usages.
// launch = It launches a new coroutine concurrently with the rest of the code, which continues to work independently
// does not carry any result.
// async = async is just like a launch except it might carry with in the future results with deferred result type.
// async is also a job , so you can cancel it.

// In this example we see the hierarchy of the coroutines, if one of them fail, all of them fail and all other
// coroutines get cancellation signal.
fun main(args: Array<String>) = runBlocking<Unit> {
    try {
        failedSuspendedSum()
    } catch (e: ArithmeticException) {
        println("Failed computition with ${Thread.currentThread().name}")
    }
}

suspend fun failedSuspendedSum(): Int = coroutineScope {
    val first = async {
        try {
            println("I'm first one with ${Thread.currentThread().name}")
            delay(15000L)
            15
        } finally {
            println("Finally, I had to stop with ${Thread.currentThread().name}")
        }
    }
    val second = async<Int> {
        println("I'm in second with ${Thread.currentThread().name}")
        10
    }
    val third = async<Int> {
        println("I'm in third with ${Thread.currentThread().name}")
        throw ArithmeticException()
    }
    first.await() + second.await() + third.await()
}