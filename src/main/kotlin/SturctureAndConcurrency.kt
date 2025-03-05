package org.example


import kotlinx.coroutines.*
import java.util.concurrent.Executors

fun main() {
    val threadPool = Executors.newSingleThreadExecutor()
    val coroutineContext = CoroutineName("My Own Coroutine") + threadPool.asCoroutineDispatcher()
    CoroutineScope(coroutineContext).launch {
        // 현재 코루틴 이름 출력
        println("Coroutine Name: ${coroutineContext[CoroutineName]?.name}")
        printWithThread("Hello")
    }

    Thread.sleep(1000)
    threadPool.shutdown()
}


suspend fun example13(){
     val job = CoroutineScope(Dispatchers.Default).launch {
         delay(1_00)
         printWithThread("job1")
     }
     
     job.join() //join 함수가 Suspend 함수이기 떄문에 메인 함수 자체를 Suspend로 만들어야됨
 }

class AsyncLogic {
    private val scope = CoroutineScope(Dispatchers.Default)

    fun doSomething(){
        scope.launch {

        }
    }

    fun destroy(){  //모든 코루틴에 취소 요청 보냄
        scope.cancel()
    }
}




fun example12() {
    val scope = CoroutineScope(Dispatchers.IO)

    fun fetchData() {
        scope.launch {
            delay(1000)
            println("데이터 가져오기 완료!")
        }
    }

    fun onClear() {
        scope.cancel() //ViewModel이 삭제되면 모든 코루틴도 취소됨
    }
}


//부모 코루틴이 취소되면 자식 코루틴도 자동 취소
fun example11() = runBlocking {
    val job = launch {
        repeat(5) { i ->
            println("자식 코루틴 실행 중... $i")
            delay(500)
        }
    }

    delay(1_000)
    println("부모 코루틴 취소!")
    job.cancel()
}
