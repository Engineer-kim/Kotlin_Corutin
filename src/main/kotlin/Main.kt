package org.example

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.yield

fun main(): Unit = runBlocking{ //runBlocking 은 코루틴 시작부분(처음 고루틴 만듦)
    printWithThread("START")
    launch {  //launch 는 반환값이 없는 코루틴 (두번쨰 코루틴 만듦)
        newRoutine()
    }
    yield() // 지금 코루틴을 중단하고 다른 코루틴이 실행되도록 함(스레드를 양보함)
    printWithThread("END")
}

suspend fun newRoutine(){
    val num = 1
    val num2 = 2
    yield()
    printWithThread("${num + num2}")
}

fun printWithThread(str: Any){
    println("${Thread.currentThread().name} : $str")
}