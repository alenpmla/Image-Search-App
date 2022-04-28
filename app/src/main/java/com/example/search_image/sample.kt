package com.example.search_image

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

fun main() {
    runBlocking {
//        try {
//            val time = measureTimeMillis {
//                println("The answer is ${concurrentSum()}")
//            }
//            println("Execution completed in $time ms")
//        } catch (e: ArithmeticException) {
//            println("Computation failed with ArithmeticException")
//        }
        launch {
            for (k in 1..100) {
                println("I'm not blocked $k")
                delay(100)
            }
        }
        simple().collect() { value -> println(value) }


    }
    println("Hello world!")
}


fun simple(): Flow<Int> = flow {
    emit(1)
    delay(1000L)
    emit(2)
    delay(1000L)
    emit(3)
}


suspend fun concurrentSum(): Int = coroutineScope {
    val one = async { doSomethingUsefulOne() }
    val two = async { doSomethingUsefulOne() }
    one.await() + two.await()
}

suspend fun doSomethingUsefulOne(): Int {
    delay(1000L) // pretend we are doing something useful here
    return 13
}

suspend fun doSomethingUsefulTwo(): Int {
    delay(1000L) // pretend we are doing something useful here, too
    return 29
}