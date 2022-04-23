package repetableCoroutine_05

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

// runBlocking = The main difference is that the runBlocking method blocks the current thread for waiting.
// coroutineScope = while coroutineScope just suspends, releasing the underlying thread for other usages.
// launch = It launches a new coroutine concurrently with the rest of the code, which continues to work independently.

// creating multiple coroutines are much more light-weight than creating threads. In this case loop just create 10
// coroutine and not wait until they finish immediately pass to println function.(20)
fun main(args: Array<String>) = runBlocking {
    repeat(10) {
        launch {
            delay(1500L)
            println("I'm increasing with ${Thread.currentThread().name}")
        }
    }
    println("I'm in main method with ${Thread.currentThread().name}")
}
