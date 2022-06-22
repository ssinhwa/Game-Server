package com.ssinhwa.gameserver.auth.config;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AsyncConfig extends AsyncConfigurerSupport {
    /* 기본 실행하는 Thread 수 */
    private static final int CORE_POOL_SIZE = 2;

    /* 동시 동작하는 최대 Thread 수 */
    private static final int MAX_POOL_SIZE = 5;

    /* MAX_POOL_SIZE 를 초과하는 요청 시, 큐에 저장하는 데 최대 수용 가능한 큐의 수  */
    private static final int QUEUE_CAPACITY = 0;

    /* 샘플 Thread Bean Name */
    private static final String THREAD_NAME = "EmailExecutor";


    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(CORE_POOL_SIZE);
        executor.setMaxPoolSize(MAX_POOL_SIZE);
        executor.setQueueCapacity(QUEUE_CAPACITY);
        executor.setThreadNamePrefix(THREAD_NAME);
        executor.initialize();
        return executor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return null;
    }

}
