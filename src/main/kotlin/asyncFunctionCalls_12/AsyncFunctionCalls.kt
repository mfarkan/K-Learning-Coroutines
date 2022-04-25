package asyncFunctionCalls_12

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

// runBlocking = The main difference is that the runBlocking method blocks the current thread for waiting.
// coroutineScope = while coroutineScope just suspends, releasing the underlying thread for other usages.
// launch = It launches a new coroutine concurrently with the rest of the code, which continues to work independently
// does not carry any result.
// async = async is just like a launch except it might carry with in the future results with deferred result type.
// async is also a job , so you can cancel it.

// In this example we see that async keywords create new coroutine for everytime, and it may carry results in it.
// Default async behaivour is not lazy it will work eventually whether you call or not. When you set lazy it will
// work when you call function with start keyword.
fun main(args: Array<String>) = runBlocking {
    val time = measureTimeMillis {
        val one = async() { doSomethingUsefullOne() }
        val two = async(start = CoroutineStart.LAZY) { doSomethingUsefullTwo() }
        one.start()
        two.start() // without start the code will work sequential behavior.
        println("The answer is ${one.await() + two.await()}")
    }
    println("Completed in $time ms")
}

suspend fun doSomethingUsefullOne(): Int {
    delay(1000L)
    println("I'm first one and working in another coroutine with ${Thread.currentThread().name}")
    return 10
}

suspend fun doSomethingUsefullTwo(): Int {
    delay(1000L)
    println("I'm second one and working in another coroutine with ${Thread.currentThread().name}")
    return 5
}