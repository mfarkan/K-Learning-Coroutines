package coroutineScope_03

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

// runBlocking = The main difference is that the runBlocking method blocks the current thread for waiting.
// coroutineScope = while coroutineScope just suspends, releasing the underlying thread for other usages.
// launch = It launches a new coroutine concurrently with the rest of the code, which continues to work independently.

fun main(args: Array<String>) = runBlocking {
    doWorldAgain()
    println("I'm in the main method with ${Thread.currentThread().name}")
}

// coroutine scope do not block the current coroutine and fire another one.
suspend fun doWorld() = coroutineScope {
    launch {
        delay(1000L)
        println("I'm in launch scope with ${Thread.currentThread().name}")
    }
    println("I'm out launch scope with ${Thread.currentThread().name}")
}

// coroutine scope do not block the current coroutine and fire another one.
suspend fun doWorldAgain() = coroutineScope {
    launch {
        delay(2000L)
        println("I waited 2 sec with ${Thread.currentThread().name}")
    }
    launch {
        delay(1000L)
        println("I waited 1 sec with ${Thread.currentThread().name}")
    }
    println("I didn't waited with ${Thread.currentThread().name}")
}