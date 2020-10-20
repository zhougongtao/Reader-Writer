package experiment.actor;

import experiment.GUI.Controller;
import experiment.GUI.Message;
import experiment.main.Global;
import java.util.Random;

public class Reader extends Actor implements Runnable{
    private String name;
    private String single_name;
    private int time;

    public Reader(int id){
        name="读者 "+ id + " 号 ";
        single_name="r"+id;
    }
    @Override
    public void run() {
        while(true){
            // 进入临界区 申请权限
            Controller.wait(name);
            Controller.addWaitQueue(single_name);
            // 获取权限
            P(Global.w);  // 无其它进程时进入  “写优先”
            if(Global.count.get()==0){
                P(Global.rw);
            }
            //获得一定权限后
            Controller.removeWaitQueue();      //等待队列
            Global.count.getAndIncrement();    //在读人数
            Controller.refresh();
            V(Global.w);
            // 读前操作
            Controller.isReading(name);        // 图书内容
            Controller.refreshtext();
            // 读操作花费时间
            time = getRandom(Global.min, Global.max);
            long after = System.currentTimeMillis() + time;
            while (System.currentTimeMillis() < after);
            // 读后操作
            Controller.noReading(name);             // 图书内容
            Controller.refreshtext();
            Controller.outCriticalAreas(name,time); //花费时间
            Global.count.getAndDecrement();         //在读人数
            Controller.refresh();
            // 退出临界区 回收权限
            if(Global.count.get()==0) {
                V(Global.rw);
            }
            // 休息时间
            time = getRandom(Global.relaxMin, Global.relaxMax);
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
