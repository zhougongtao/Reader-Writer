package Example_SemSingle;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class ProducerCustomRealizeWithSemaphore {

    public static void main(String[] args) {
        SemaphoreBuffer semaphoreBuffer = new SemaphoreBuffer();
        Producer producer = new Producer(semaphoreBuffer);
        Customer customer = new Customer(semaphoreBuffer);
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(customer);
        service.execute(producer);
        service.shutdown();
    }

}

class SemaphoreBuffer{
    List<Integer> list = new ArrayList<Integer>();
    Semaphore producerSemaphore = new Semaphore(1);// 允许并行的线程数为1
    Semaphore customerSemaphore = new Semaphore(0);// 0即state的初始值 则一开始消费就阻塞了 见上例说明中remaining<0

    public void put(int num){
        try {
            producerSemaphore.acquire();
            try {
                list.add(num);
            } finally{
                customerSemaphore.release();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int get(){
        try {
            customerSemaphore.acquire();
            try{
                return list.remove(0);
            }finally{
                producerSemaphore.release();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 0;
    }

}

class Customer implements Runnable{
    private SemaphoreBuffer buffer;
    Customer(SemaphoreBuffer buffer){
        this.buffer = buffer;
    }
    @Override
    public void run() {
        while(!Thread.interrupted()){
            int num = buffer.get();
            System.out.println("Customer get the num "+num);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}

class Producer implements Runnable{
    private SemaphoreBuffer buffer;
    Producer(SemaphoreBuffer buffer){
        this.buffer = buffer;
    }
    int c =0;
    @Override
    public void run() {

        while(!Thread.interrupted()){
            buffer.put(c);
            System.out.println("Producer put the num "+c);
            c++;
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
