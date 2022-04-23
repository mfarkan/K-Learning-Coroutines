package coroutine_job_04

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

// runBlocking = The main difference is that the runBlocking method blocks the current thread for waiting.
// coroutineScope = while coroutineScope just suspends, releasing the underlying thread for other usages.
// launch = It launches a new coroutine concurrently with the rest of the code, which continues to work independently.

// a Job object that is a handle to the launched coroutine and can be used to explicitly wait for its completion.

fun main(args: Array<String>) = runBlocking {
    val firstJob = launch {
        delay(2000L)
        println("I'm a job with ${Thread.currentThread().name}")
    }
    firstJob.join()
    println("I'm main method with ${Thread.currentThread().name}")
}

