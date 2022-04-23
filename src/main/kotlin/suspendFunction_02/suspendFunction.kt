package suspendFunction_02

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

// runBlocking = The main difference is that the runBlocking method blocks the current thread for waiting.
// coroutineScope = while coroutineScope just suspends, releasing the underlying thread for other usages.
// launch = It launches a new coroutine concurrently with the rest of the code, which continues to work independently.

fun main(args: Array<String>) = runBlocking {
    launch {
        suspendMethod()
    }
    println("I'm in the main method.")
}

suspend fun suspendMethod(): Unit {
    delay(1000L)
    println("I'm suspended method.")
}