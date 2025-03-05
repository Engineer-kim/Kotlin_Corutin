- 코루틴의 기본적인 흐름도
\
  ![image](https://github.com/user-attachments/assets/e2890485-9d8a-4b83-8f29-28f454ff6fc9)




- 코루틴의 Yield
\
![image](https://github.com/user-attachments/assets/a00ff405-a0a7-4813-b1e8-1da516232402)
    \
yield() 라는 함수는 지금 코루틴의 실행을 잠시 멈추고 다른 코루틴이 실행
되도록 양보
\
Suspend : 스레드를 강제로 중단 , Yield : 다른 스레드에게 양보, 스레드는 계속 준비 상태



- 코루틴 VS 스레드 차이 비교
\
 ![image](https://github.com/user-attachments/assets/f96171c1-ecec-4b80-940a-d0432001694c)
\
- 코루틴에서의 Aysnc , Cancel , Join
\
![image](https://github.com/user-attachments/assets/e41e63b1-49cb-410e-ace3-458a8fbcb3db)


-  코루틴 상태 정리(취소만)

![image](https://github.com/user-attachments/assets/7aec37fb-8b30-4788-8d71-e6a3c68abf1f)
\
- 코루틴 상태 정리(완료 포함)
 \
![image](https://github.com/user-attachments/assets/73327a1b-5fb8-44fb-9493-089ae859255b)
\

작업이 완료된 경우 바로 COMPLETED 안되는 이유 -> 
\
자식 코루틴이 있을 경우, 자식 코루틴들이 모두 완료될 때까지 기다릴 수 있고,
자식 코루틴들 중 하나에서 예외가 발생하면 다른 자식 코루틴들에게도 취소 요청을 보내기때문
