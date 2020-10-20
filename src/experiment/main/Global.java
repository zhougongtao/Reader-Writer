package experiment.main;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class Global{
    public static Semaphore rw=new Semaphore(1,true);         //控制读者写者互斥
    public static Semaphore w=new Semaphore(1,true);          //控制读者优先
    public static AtomicInteger count = new AtomicInteger(0);    //表示当前读者读者个数
    public static AtomicInteger writerNum = new AtomicInteger(0);//写者进入等待队列的个数
    public static int reader=2;    //读者数量
    public static int writer=2;    //写者数量

    //工作时间
    public static int min=2000;
    public static int max=5000;
    //休息时间
    public static int relaxMin=5000;
    public static int relaxMax=10000;
}