import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

// runBlocking = The main difference is that the runBlocking method blocks the current thread for waiting.
// coroutineScope = while coroutineScope just suspends, releasing the underlying thread for other usages.
// launch = It launches a new coroutine concurrently with the rest of the code, which continues to work independently.

fun main() = runBlocking {
    //doWorldAgain()
    println("Current Thread : ${Thread.currentThread().name}")
//    val newCorouritine = launch {
//        delay(1000L)
//        println("Job Couritine Thread : ${Thread.currentThread().name}")
//    }
//    newCorouritine.join()

    repeat(5) {
        launch {
            delay(1000L)
            println("Repeat Current Thread : ${Thread.currentThread().name}")
        }
    }
}


suspend fun doWorldAgain() = coroutineScope {  // this: CoroutineScope
    launch {
        println("Launch1 Current Thread : ${Thread.currentThread().name}")
        delay(2000L)
        println("World1!")
    }
    launch {
        println("Launch2 Current Thread : ${Thread.currentThread().name}")
        delay(1000L)
        println("World2!")
    }
    println("Current Thread : ${Thread.currentThread().name}")
    println("Hello")
}