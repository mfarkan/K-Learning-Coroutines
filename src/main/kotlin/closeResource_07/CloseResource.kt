package closeResource_07

import kotlinx.coroutines.*


// runBlocking = The main difference is that the runBlocking method blocks the current thread for waiting.
// coroutineScope = while coroutineScope just suspends, releasing the underlying thread for other usages.
// launch = It launches a new coroutine concurrently with the rest of the code, which continues to work independently.

// In the finally we might close our resources, such as I/O ops, network ops etc.
fun main(args: Array<String>) = runBlocking {
    val job = launch(Dispatchers.Default) {
        try {
            repeat(10) {
                println("I'm hanging out with ${Thread.currentThread().name}")
                delay(1000L)
            }
        } finally {
            println("I did what I can do with ${Thread.currentThread().name}")
        }
    }
    delay(2500L)
    job.cancelAndJoin()
    println("Finally, I returned in main with ${Thread.currentThread().name}")
}