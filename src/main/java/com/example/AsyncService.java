package com.example;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

/**
 * @author daicham
 */
@Service
public class AsyncService {
    @Async
    public Future<String> async() {
        try {
            Thread.currentThread().sleep(10 * 1000);
            System.out.println("woke up!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new AsyncResult<String>("Completed.");
    }
}
