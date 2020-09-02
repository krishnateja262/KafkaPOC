package com.teja.queue;

import com.teja.queue.QueueManager;

public class KafkaBaby {

    private static QueueManager queueManager = QueueManager.getQueueManager();

    public static QueueManager getQueueManager() {
        return queueManager;
    }
}
