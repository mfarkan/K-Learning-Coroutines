package structedSuspendedFunctions_13

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis


fun main(args: Array<String>) = runBlocking {
    val time = measureTimeMillis {
        println("The result is ${calculateMath()}")
    }
    println("Completed in $time ms")
}

suspend fun calculateMath(): Int = coroutineScope {
    val first = async {
        doSomethingUsefulOne()
    }
    val second = async {
        doSomethingUsefulTwo()
    }
    first.await() + second.await()
}

suspend fun doSomethingUsefulOne(): Int {
    println("I'm in first one with ${Thread.currentThread().name}")
    delay(5000L)
    println("I'm in first one with ${Thread.currentThread().name}")
    return 5
}

suspend fun doSomethingUsefulTwo(): Int {
    delay(1000L)
    println("I'm in second one with ${Thread.currentThread().name}")
    return 10
}
