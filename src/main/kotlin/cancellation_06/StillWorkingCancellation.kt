package cancellation_06

import kotlinx.coroutines.*

// runBlocking = The main difference is that the runBlocking method blocks the current thread for waiting.
// coroutineScope = while coroutineScope just suspends, releasing the underlying thread for other usages.
// launch = It launches a new coroutine concurrently with the rest of the code, which continues to work independently.

//They check for cancellation of coroutine and throw CancellationException when cancelled. However,
// if a coroutine is working in a computation and does not check for cancellation, then it cannot be cancelled.
//fun main(args: Array<String>) = runBlocking {
//    val startTime = System.currentTimeMillis()
//    val job = launch(Dispatchers.Default) {
//        var nextPrintTime = startTime
//        var i = 0
//        while (i < 5) {
//            if (System.currentTimeMillis() >= nextPrintTime) {
//                println("I'm working with ${i++} ${Thread.currentThread().name}")
//                nextPrintTime += 500L
//            }
//        }
//    }
//    delay(1300L)
//    println("I'm bored to wait with ${Thread.currentThread().name}")
//    job.cancelAndJoin()
//    println("Finally, I cancelled with ${Thread.currentThread().name}")
//}

// isActive check the coroutine is active and working as usual and if cancel signal triggered, value will be changed
// to false.
fun main(args: Array<String>) = runBlocking {
    val startTime = System.currentTimeMillis()
    val job = launch(Dispatchers.Default) {
        var nextPrintTime = startTime
        var i = 0
        while (i < 5 && isActive) {
            if (System.currentTimeMillis() >= nextPrintTime) {
                println("I'm working with ${i++} ${Thread.currentThread().name}")
                nextPrintTime += 500L
            }
        }
    }
    delay(1300L)
    println("I'm bored to wait with ${Thread.currentThread().name}")
    job.cancelAndJoin()
    println("Finally, I cancelled with ${Thread.currentThread().name}")
}