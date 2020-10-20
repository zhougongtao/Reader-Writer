package experiment.actor;

import java.util.concurrent.Semaphore;
import java.util.Random;

public class Actor {
    // P操作
    protected synchronized void P(Semaphore sem) {
        try {
            sem.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    // V操作
    protected synchronized void V(Semaphore sem) {
        sem.release();
    }
    // 获取一个范围为[min,max]的随机数
    public int getRandom(int min, int max) {
        Random random=new Random();
        int time = random.nextInt();
        if (time < 0)
            time *= -1;
        time = time % (max - min + 1) + min;
        return time;
    }
}
