package org.example

import kotlinx.coroutines.*
import kotlinx.coroutines.future.await
import java.util.concurrent.CompletableFuture

/**코루틴 중지 되었다가 재개 될수 있는 함수*/


fun main(): Unit = runBlocking {
    withTimeout(1_000L) { //주어진 시간내에 실행되지 않으면 자동으로 실행이 취소됨(=TimeOut 이랑 같음)
        delay(1_500L)
        10 + 20
    }
}




//실행 결과
//main : START
//main : 30
//main : END
fun example26(): Unit = runBlocking {
    printWithThread("START")
    printWithThread(calculateResult()) //suspend => 결과를 리턴 할때까지 기다림
    printWithThread("END")
}
suspend fun calculateResult(): Int = withContext(Dispatchers.Default) {
    val num1 = async {
        delay(1_000L)
        10
    }
    val num2 = async {
        delay(1_000L)
        20
    }
    num1.await() + num2.await()
}







fun example25(): Unit = runBlocking {
    val result1 = apiCall11()
    val result2 = apiCall12(result1)
    printWithThread(result2)
}
suspend fun apiCall11(): Int {
    return CoroutineScope(Dispatchers.Default).async {
        Thread.sleep(1_000L)
        100
    }.await()
}
//Suspend ==> 실행을 일시 중단할 수 있고 나중에 다시 재개할 수 있는 함수
suspend fun apiCall12(num: Int): Int {
    return CompletableFuture.supplyAsync {
        Thread.sleep(1_000L)
        num * 2
    }.await()
}

//결과값
//main : a function Call
//main : b function Call
//main : c function Call
fun example20(): Unit = runBlocking {
    launch {
        a()
        b()
    }
    launch {
        c()
    }
}

suspend fun a (){
    printWithThread("a function Call")
}
suspend  fun b (){
    printWithThread("b function Call")
}
suspend  fun c (){
    printWithThread("c function Call")
}