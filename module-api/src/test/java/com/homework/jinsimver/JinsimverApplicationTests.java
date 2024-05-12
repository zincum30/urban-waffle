package com.homework.jinsimver;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@SpringBootTest
@ActiveProfiles("test")
class JinsimverApplicationTests {

	@Test
	void runAsync() throws ExecutionException, InterruptedException {
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
			System.out.println("Thread: " + Thread.currentThread().getName());
		});

		future.get();
		System.out.println("Thread: " + Thread.currentThread().getName());
	}

}
