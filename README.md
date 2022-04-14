# Spring Game Server

Spring WebSocket + STOMP 이용하여 실시간 배틀로얄 게임 서버 만들기

## 계획

1. 채팅 서버 데모로 만들어 보면서 구조 이해 -> (완료)
2. Redis, Kafka 어떤거 쓸지 적용해보면서 고민중 -> 일단 둘 다 적용은 함
3. 유저 JWT + 이메일 인증 구현
    - Spring Security는 Rest API로 적용하기 너무 어려울 것 같아서 제외
    - 자체적으로 인증후 토큰 발급해 주는 식으로 할 계획.
    - 이메일 인증은 구현 완료하였다.
    - 이메일 전송 시간 동안 Rest API 렉 발생 -> Async로 해결
    - 이메일 인증 링크 좀 더 난수화 하기
    -
4. 엔드포인트 정리하기