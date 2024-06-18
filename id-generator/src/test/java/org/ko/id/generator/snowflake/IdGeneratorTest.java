package org.ko.id.generator.snowflake;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class IdGeneratorTest {

    private IdGenerator idGenerator = new IdGenerator();

    @Test
    public void testBatchId() {
        for (int i = 0; i < 100; i++) {
            String batchId = idGenerator.batchId(1001, 100);
            System.out.println("批次号: " + batchId);
        }
    }

    @Test
    public void testSimpleUUID() {
        for (int i = 0; i < 100; i++) {
            String simpleUUID = idGenerator.simpleUUID();
            System.out.println("simpleUUID: " + simpleUUID);
        }
    }

    @Test
    public void testRandomUUID() {
        for (int i = 0; i < 100; i++) {
            String randomUUID = idGenerator.randomUUID();
            System.out.println("randomUUID: " + randomUUID);
        }
    }

    @Test
    public void testObjectID() {
        for (int i = 0; i < 100; i++) {
            String objectId = idGenerator.objectId();
            System.out.println("objectId: " + objectId);
        }
    }

    @Test
    public void testSnowflakeId() {
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        for (int i = 0; i < 20; i++) {
            executorService.execute(() -> {
                System.out.println("分布式 ID: " + idGenerator.snowflakeId());
            });
        }
        executorService.shutdown();
    }
}
