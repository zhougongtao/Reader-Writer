package Example_Semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(5, false);   //no fair 并行最大为5,阻塞后来的
        ExecutorService service = Executors.newCachedThreadPool();     //创建一个线程池
        for(int i=0; i<10; i++){
            service.execute(new Worker(semaphore));
        }
        service.shutdown();
    }

}

class Worker implements Runnable{

    private Semaphore semaphore;
    Worker(Semaphore semaphore){
        this.semaphore = semaphore;
    }
    @Override
    public void run() {
        try {
            semaphore.acquire();//获取许可
            try {
                System.out.println(Thread.currentThread().getName()+" accessing...");
                Thread.sleep((long) (Math.random()*3000));
            } finally{
                semaphore.release();//释放许可
                System.out.println(Thread.currentThread().getName()+" leaving...");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
