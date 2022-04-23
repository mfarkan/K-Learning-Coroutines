package nonCancellableBlock_08

import kotlinx.coroutines.*

// runBlocking = The main difference is that the runBlocking method blocks the current thread for waiting.
// coroutineScope = while coroutineScope just suspends, releasing the underlying thread for other usages.
// launch = It launches a new coroutine concurrently with the rest of the code, which continues to work independently.

// in finally block , we might close all resources without getting any intercept or cancel signal.
fun main(args: Array<String>) = runBlocking {
    val job = launch {
        try {
            repeat(100) {
                println("I'm repeating with ${Thread.currentThread().name}")
                delay(500L)
            }
        } finally {
            withContext(NonCancellable) {
                println("I'm running in finally with ${Thread.currentThread().name}")
                delay(1000L)
                println("I waited 1 sec and no one will be able to cancel me with ${Thread.currentThread().name}")
            }
        }
    }

    delay(2500L)
    println("I'm tired with ${Thread.currentThread().name}")
    job.cancelAndJoin()
    println("I quit with ${Thread.currentThread().name}")
}