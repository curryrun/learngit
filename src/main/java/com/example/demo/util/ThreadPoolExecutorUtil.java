package com.example.demo.util;

import javax.annotation.PreDestroy;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 多线程工具类
 *
 * @author zengsongbin
 */
public class ThreadPoolExecutorUtil {
    // public static final int NTHREADS =
    // Runtime.getRuntime().availableProcessors() * 8;// 默认线程池个数
    public static final int NTHREADS = 200;// 默认线程池个数

    private ThreadPoolExecutorUtil() {
    }

    private volatile static ThreadPoolExecutorUtil instance;
    // private static final LinkedBlockingQueue<Runnable> QUEUE = new
    // LinkedBlockingQueue<>(100);// 后续应加入队列长度监听机制
    private static final LinkedBlockingQueue<Runnable> QUEUE = new LinkedBlockingQueue<>(5000);// 后续应加入队列长度监听机制
    private ExecutorService executorService = new ThreadPoolExecutor(NTHREADS, NTHREADS, 0L, TimeUnit.MILLISECONDS,
            QUEUE);

    public static ThreadPoolExecutorUtil getInstance() {
        if (null == instance) {
            synchronized (ThreadPoolExecutorUtil.class) {
                if (null == instance) {
                    instance = new ThreadPoolExecutorUtil();
                }
            }
        }
        return instance;
    }

    public ExecutorService getThreadPoolExecutorService() {
        if (null == executorService) {
            ThreadPoolExecutor executor = new ThreadPoolExecutor(NTHREADS, NTHREADS, 0L, TimeUnit.MILLISECONDS, QUEUE);
            /**
             * 重写Rejected策略，缺省抛出TaskRejectedException异常，然后不执行
             * 当pool已经达到maxsize的时候 不在新线程中执行任务，而是有调用者所在的线程来执行
             */
            executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
            return executor;
        }
        return executorService;
    }

    @PreDestroy
    public void destroy() {
        executorService.shutdown();
    }
}

