## Redis 학습 내용

### 1. 연결 확인

`spring-boot-starter-data-redis` 추가만으로 `LettuceConnectionFactory` /
`StringRedisTemplate`이 자동 설정됨. 별도 Bean 등록 없이 바로 주입받아 사용.

```java
@Autowired
private StringRedisTemplate redisTemplate;

redisTemplate.opsForValue().set("key", "value");
redisTemplate.opsForValue().get("key");
```

`@SpringBootTest` 기반 테스트로 연결 확인 → REST API로 만들어 Postman 요청 →
서버의 `redis-cli`(`KEYS *`, `GET`)로 실제 저장 값 직접 확인까지 완료.

### 2. TTL (유효 기간)

```java
redisTemplate.opsForValue().set(key, value, Duration.ofSeconds(seconds));
redisTemplate.getExpire(key, TimeUnit.SECONDS); // 남은 시간 조회
```

API로 TTL 걸어 저장 → 남은 시간이 실시간으로 줄어드는 것 확인 →
시간 만료 후 `redis-cli`에서 키가 자동 삭제된 것 확인.

### 3. Pub/Sub

- `RedisSubscriber` (`MessageListener` 구현) : 메시지 수신 시 실행될 로직
- `RedisPubSubConfig` (`@Configuration` + `@Bean`) : `RedisMessageListenerContainer`에
  구독자와 채널(`ChannelTopic`)을 등록
- Publisher는 별도 클래스 없이 컨트롤러에서 한 줄로 발행:

```java
redisTemplate.convertAndSend("my-channel", message);
```

발행 → 등록된 리스너가 채널을 구독하고 있다가 메시지 수신 → 콘솔 로그로 확인.



## 다음 단계

Kafka.