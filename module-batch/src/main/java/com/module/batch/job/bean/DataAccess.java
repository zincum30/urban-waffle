package com.module.batch.job.bean;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class DataAccess {
    public final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    public final Lock readLock = readWriteLock.readLock();
    public final Lock writeLock = readWriteLock.writeLock();
}
