package org.ko.zookeeper.zkclient;

import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

public class ZkClientWatcherTests {

    @Test
    public void testWatchParent() throws InterruptedException {
        ZkClientWatcher zkClientWatcher = new ZkClientWatcher();
        zkClientWatcher.watchParent("/root/servers/server1");
        TimeUnit.SECONDS.sleep(30000);
    }

    @Test
    public void testWatchChild() throws InterruptedException {
        ZkClientWatcher zkClientWatcher = new ZkClientWatcher();
        zkClientWatcher.watchChild("/root/servers");
        TimeUnit.SECONDS.sleep(30000);
    }

    @Test
    public void testWatchClientState() throws InterruptedException {
        ZkClientWatcher zkClientWatcher = new ZkClientWatcher();
        zkClientWatcher.watchClientState("/root/servers");
        TimeUnit.SECONDS.sleep(30000);
    }

}
