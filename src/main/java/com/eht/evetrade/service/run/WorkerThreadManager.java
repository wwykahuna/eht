package com.eht.evetrade.service.run;

import com.eht.evetrade.utils.ThreadUtils;
import com.google.common.util.concurrent.Uninterruptibles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

@Component
public class WorkerThreadManager {

    private final Logger logger = LoggerFactory.getLogger(WorkerThreadManager.class);

    private ExecutorService workerExecService;

    private List<Future> futureList = new LinkedList<>();
    private List<WorkerThread> threadList = new LinkedList<>();

    private static AtomicBoolean signal = new AtomicBoolean(false);

    public void doStart() {
        if (!signal.get()) {
            signal.set(true);
            workerExecService = ThreadUtils.newDaemonFixedThreadExecutor("Worker-Execute-Thread", 20);
            for (int i = 0; i < 3; i++) {
                WorkerThread thread = new WorkerThread();
                Future future = workerExecService.submit(thread);
                threadList.add(thread);
                futureList.add(future);
            }
        }
    }

    public void doStop2() {
        Active.setStatus(false);
        for (Future f : futureList) {
            try {
                f.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        for (WorkerThread t : threadList) {
            System.out.println(t);
        }
    }

    public void doStop() {
        for (Future f : futureList) {
            System.out.println("1:isCancelled = " + f.isCancelled());
            System.out.println("1:isDone = " + f.isDone());
        }
        List<Runnable> runnableList = workerExecService.shutdownNow();
        Uninterruptibles.sleepUninterruptibly(10, TimeUnit.SECONDS);
        System.out.println("runnableList = " + runnableList.size());
        for (Runnable r : runnableList) {
            System.out.println(r);
        }
        for (Future f : futureList) {
            System.out.println("2:isCancelled = " + f.isCancelled());
            System.out.println("2:isDone = " + f.isDone());
        }
    }
}