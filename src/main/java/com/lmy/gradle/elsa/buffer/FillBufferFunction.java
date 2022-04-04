package com.lmy.gradle.elsa.buffer;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 一个异步函数, 指定如何获取id并填充buffer.
 * 主要用于测试, 模拟不同id获取速率对DoubleBuffer的影响.
 *
 * @author LMinY
 * @since 2020/7/10
 */
public interface FillBufferFunction {
    /**
     * 一个异步函数, 指定如何获取id并填充buffer.
     *
     * @param buffer   需要填充的buffer
     * @param fetching 当填充完成时, 需要讲fetching设为false
     */
    void fill(BlockingDeque<String> buffer, AtomicBoolean fetching);
}
