package org.example

import kotlinx.coroutines.*
import kotlin.time.measureTime







fun example10(): Unit = runBlocking{
    val job = async(SupervisorJob()) { //SupervisorJob 자식 고루틴 실패시 원래는 부모취소되나 SupervisorJob사용으로 안됨
        throw IllegalArgumentException()
    }

    delay(1_000L)
}




fun example09(): Unit = runBlocking{
    val job1= CoroutineScope(Dispatchers.Default).async { //메인쓰레드가 아닌 다른쓰레드에서 새롭게 만들예정
        throw IllegalArgumentException()
    }
    delay(1_000L)
}


fun example08():Unit = runBlocking{
    val job = launch {
        try {
            delay(1_000L) //취소가 아니라 대기
        }catch (e: CancellationException){
            //Do nothing
            println("코루틴 취소됨!")
        }

        printWithThread("delay 트라이 캐치 지남")
    }

    delay(100L)
    printWithThread("취소 시작")
    job.cancel()

}




fun example07(): Unit = runBlocking {
    val job = launch(Dispatchers.Default) { //Dispatchers.Default 다른 쓰레드에 배정
        var i = 1
        var nextPrintTime = System.currentTimeMillis()
        while(i <= 5){
            if(nextPrintTime  <= System.currentTimeMillis()){
                printWithThread("${i++} 번쨰 호출")
                nextPrintTime += 1_000L
            }

            if(!isActive){ //isActive 는 현재 코루틴이 활성화 되어 있는지 , 취소 신호 받았는지 확인
                throw  CancellationException("job is canceled")
            }
        }

    }

    delay(100L)
    job.cancel()
}



fun example06(): Unit = runBlocking {
    val time = measureTime {
        val job1 = async { apiCall1() }
        val job2 = async { apiCall2(job1.await()) }

        printWithThread(job2.await())  //각각 1초씩 총 2초 대기지만 비동기이기 떄문에 1.2초 걸림
    }
    printWithThread("Completed in $time")
}


suspend fun apiCall1(): Int {
    delay(1_000L)
    return 1
}
suspend fun apiCall2(num: Int): Int {
    delay(1_000L)
    return num + 2
}


fun example05():Unit = runBlocking{
    val job = async {
        3 + 5
    }

    val eight = job.await() // async의 결과를 가져옴
    printWithThread(eight)
}


fun example04():Unit = runBlocking{
    val job1 = launch {
        delay(1_000L)
        printWithThread("job1")
    }
    job1.join() // 이전 코루틴 완전 종료까지 대기후 시작

    val job2 = launch {
        delay(1_000L)
        printWithThread("job2")
    }


}


fun exmaple03(): Unit = runBlocking{
    val job = launch(start = CoroutineStart.LAZY) {
        (1..5).forEach {
            printWithThread("Inner launch value $it")
            delay(500)
        }
    }

    delay(1_000L)
    job.cancel()
}


fun example02(): Unit = runBlocking{
    val job = launch(start = CoroutineStart.LAZY) {  //launch는 애초에 리턴이 없지만 ,
        // job 이라는 변수에는 launch를 대기 ,취소 , 시작 , 종료를 지정하기 위함
        printWithThread("Hello launch")
    }
    delay(1_000L)
    job.start()
    //printWithThread("END")
}



fun example01() {
  runBlocking {
      printWithThread("start")
      launch {
          delay(2_000L)
          printWithThread("Launch END")
      }
  }

    printWithThread("END")
}