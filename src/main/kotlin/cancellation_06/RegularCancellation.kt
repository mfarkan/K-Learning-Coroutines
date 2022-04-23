package cancellation_06

import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


// runBlocking = The main difference is that the runBlocking method blocks the current thread for waiting.
// coroutineScope = while coroutineScope just suspends, releasing the underlying thread for other usages.
// launch = It launches a new coroutine concurrently with the rest of the code, which continues to work independently.

// we've started to create a new coroutine in main method with launch and in that coroutine we wait 500ms for 10
// times but client will send cancellation signal.
fun main(args: Array<String>) = runBlocking {

    val job = launch {
        repeat(10) {
            println("I'm $it working with ${Thread.currentThread().name}")
            delay(500L)
        }
    }
    delay(2000L)
    println("I'm bored with ${Thread.currentThread().name}")
    job.cancelAndJoin()
    println("Finally, I cancelled with ${Thread.currentThread().name}")
}