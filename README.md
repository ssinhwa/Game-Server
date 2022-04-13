# Spring Game Server

Spring WebSocket + STOMP 이용하여 실시간 배틀로얄 게임 서버 만들기

## 계획

1. 채팅 서버 데모로 만들어 보면서 구조 이해(진행중)
2. Redis, Kafka 어떤거 쓸지 적용해보면서 고민중
3. 유저 JWT + 이메일 인증 구현 -> Spring Security는 Rest API로 적용하기 너무 어려울 것 같아서 제외하고, 자체적으로 인증후 토큰 발급해 주는 식으로 할 계획. 이메일 인증은 구현
   완료하였다.
4. 엔드포인트 정리하기