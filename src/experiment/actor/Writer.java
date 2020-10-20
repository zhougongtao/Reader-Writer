package experiment.actor;

import experiment.GUI.Controller;
import experiment.main.Global;
import java.util.Random;

public class Writer extends Actor implements Runnable {
    private String name;
    private String single_name;
    private int time;

    public Writer(int id){
        name="写者 "+ id + " 号 ";
        single_name="w"+id;
    }
    @Override
    public void run() {
        while(true){
            // 进入临界区 申请权限
            Controller.wait(name);
            Global.writerNum.getAndIncrement();    //写者等待人数
            Controller.addWaitQueue(single_name);
            // 获取权限
            P(Global.w);  // 无其它进程时进入  “写优先”
            P(Global.rw);
            // 写前操作
            Controller.removeWaitQueue();
            Global.writerNum.getAndDecrement();   //写者等待人数
            Controller.refresh();
            Controller.isWriting(name);          //图书内容
            Controller.refreshtext();
            // 写操作
            time = getRandom(Global.min, Global.max);
            long after = System.currentTimeMillis() + time;
            while (System.currentTimeMillis() < after);
            // 写后操作
            Controller.noWriting(name);
            Controller.refreshtext();
            Controller.outCriticalAreas(name,time);  //花费时间
            // 退出临界区 回收权限
            V(Global.rw);
            V(Global.w);
            // 休息时间
            time= getRandom(Global.relaxMin, Global.relaxMax);
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
