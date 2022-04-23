package timeOutCoroutines_09

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull

// runBlocking = The main difference is that the runBlocking method blocks the current thread for waiting.
// coroutineScope = while coroutineScope just suspends, releasing the underlying thread for other usages.
// launch = It launches a new coroutine concurrently with the rest of the code, which continues to work independently.

// sometimes a coroutine works what we expected, and we need to handle these anomalies with time limit but this
// implementation will throw exception with stack trace.
//fun main(args: Array<String>) = runBlocking {
//    val job = withTimeout(1500L) {
//        repeat(10) {
//            println("I'm waiting to timeout with ${Thread.currentThread().name}")
//            delay(500L)
//        }
//    }
//}

//with this implementation, we will get NULL when we get timeout exception.
fun main(args: Array<String>) = runBlocking {
    val result = withTimeoutOrNull(1500L) {
        repeat(10) {
            println("I'm waiting to timeout with ${Thread.currentThread().name}")
            delay(500L)
        }
        "Done"
    }
    println("Hello my dear $result with ${Thread.currentThread().name}")
}