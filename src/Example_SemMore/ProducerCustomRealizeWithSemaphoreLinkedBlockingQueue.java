package Example_SemMore;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class ProducerCustomRealizeWithSemaphoreLinkedBlockingQueue {

    static AtomicInteger c = new AtomicInteger(1);     //原子数字 具有原子性的增减算法

    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();     //创建线程池
        CakeStand stand = new CakeStand();

        //向线程池中导入进程
        service.execute(new CakeProducer(stand, "producer1", c));
        service.execute(new CakeProducer(stand, "producer2", c));
        service.execute(new CakeProducer(stand, "producer3", c));
        service.execute(new CakeCustomer(stand, "customer1"));
        service.execute(new CakeCustomer(stand, "customer2"));
    }
}

// 实体类 蛋糕 Cake
class Cake {
    private String name;
    Cake(String name) { this.name = name; }
    public String toString() {
        return name;
    }
}

// 控制类 包含各类的信号量
class CakeStand {
    BlockingQueue<Cake> queue = new LinkedBlockingQueue<Cake>(15);
    Semaphore notFull = new Semaphore(10);//生产信号量
    Semaphore notEmpty = new Semaphore(0);//消费信号量

    public void put(Cake cake) {
        try {
            notFull.acquire();
            try {
                queue.put(cake);
            } finally {
                notEmpty.release();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public Cake take() {
        try {
            notEmpty.acquire();
            try {
                Cake cake = queue.take();
                return cake;
            } finally {
                notFull.release();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}

class CakeProducer implements Runnable {

    private CakeStand stand;
    private String name;
    private AtomicInteger c;

    public CakeProducer(CakeStand stand, String name, AtomicInteger c) {
        this.stand = stand;
        this.name = name;
        this.c = c;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            String str = "cake-" + c.getAndIncrement();
            System.out.println("生产:" + name + "-" + str);
            stand.put(new Cake(str));
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}

class CakeCustomer implements Runnable {

    private CakeStand stand;
    private String name;

    public CakeCustomer(CakeStand stand, String name) {
        this.stand = stand;
        this.name = name;

    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            Cake cake = stand.take();
            System.err.println("消费:" + name + "-" + cake.toString());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}