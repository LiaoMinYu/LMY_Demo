package com.lmy.gradle.elsa.buffer;

import lombok.SneakyThrows;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author LMinY
 * @since 2020/7/10
 */
public class DoubleBuffer {
    private final int maxSize;
    private final BlockingDeque<String> buffer1;
    private final BlockingDeque<String> buffer2;
    private final AtomicBoolean isBuffer1;
    private final FillBufferFunction f;
    private final AtomicBoolean fetching;

    public DoubleBuffer(int maxSize, FillBufferFunction f) {
        this.maxSize = maxSize;
        buffer1 = new LinkedBlockingDeque<>(maxSize);
        buffer2 = new LinkedBlockingDeque<>(maxSize);
        isBuffer1 = new AtomicBoolean(true);
        this.f = f;
        fetching = new AtomicBoolean(false);
        init();
    }

    /**
     * 初始化填满2个buffer
     */
    private void init() {
        fetching.compareAndSet(false, true);
        fillBuffer(buffer1);
        fetching.compareAndSet(false, true);
        fillBuffer(buffer2);
    }

    @SneakyThrows
    public String get() {
        maybeFillOtherBuffer();

        String id;
        if (isBuffer1.get()) {
            id = buffer1.poll();
            // switch to other buffer
            if (id == null) {
                isBuffer1.set(false);
                // TODO: retry
                id = buffer2.take();
            }
        } else {
            id = buffer2.poll();
            if (id == null) {
                isBuffer1.set(true);
                id = buffer1.take();
            }
        }
        return id;
    }

    private void maybeFillOtherBuffer() {
        if (isBuffer1.get()) {
            if (needFillBuffer(buffer1) && fetching.compareAndSet(false, true)) {
                fillBuffer(buffer2);
            }
        } else {
            if (needFillBuffer(buffer2) && fetching.compareAndSet(false, true)) {
                fillBuffer(buffer1);
            }
        }
    }

    private boolean needFillBuffer(BlockingDeque<String> buffer) {
        return buffer.size() < maxSize * 0.9;
    }

    private void fillBuffer(BlockingDeque<String> buffer) {
        if (buffer.remainingCapacity() > 0) {
            f.fill(buffer, fetching);
        } else {
            fetching.set(false);
        }
    }
}
