package experiment.GUI;

import experiment.main.Global;

import javax.swing.*;
import javax.swing.JLabel;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Controller {

    //根据id找自己操作的编号
    public static LinkedList<String> name_que = new LinkedList<String>();
    public static HashMap<String , Integer> hmReader= new HashMap<String , Integer>();
    public static HashMap<String , Integer> hmWriter= new HashMap<String , Integer>();
    public static JTextArea waiter;
    public static JLabel number;
    public static JLabel rwnumber;
    public static JLabel queue;
    public static JLabel wait;
    public static JLabel text1;
    public static JLabel text2;
    public static JLabel text3;
    /**
     *  用户输入部分
     */

    // 将用户数输入的读者写者数量添加到全局变量 Global 中
    public static void getRWnumber(){
        int r=Integer.parseInt(Index.textreader.getText());
        int w=Integer.parseInt(Index.textwriter.getText());
        Global.reader=r;
        Global.writer=w;
        System.out.println(Global.reader+ "  "+Global.writer);
    }

    /**
     *  通用输出部分
     */
    public static void wait(String name){
        waiter.append(name+" 开始等待\n");
        waiter.setCaretPosition(waiter.getText().length());
    }

    public static void inCriticalAreas(String name){
        waiter.append(name+" 进入临界区\n");
        waiter.setCaretPosition(waiter.getText().length());
    }

    public static void outCriticalAreas(String name,int time){
        waiter.append(name+" 离开临界区 , 共计耗时：" + time + " 毫秒\n");
        waiter.setCaretPosition(waiter.getText().length());
    }

    /**
     *  读者输出部分
     */
    public static void  isReading(String name){
        int pitem=Message.isreading();
        waiter.append(name+" 开始阅读  第 "+(pitem+1)+" 内容  " + Message.getNode(pitem)+"\n");
        waiter.setCaretPosition(waiter.getText().length());
        hmReader.put(name,pitem);
    }
    public static void noReading(String name){
        int item= hmReader.get(name);
        hmReader.remove(name);
        Message.noreading(item);
    }

    /**
     *  写者输出部分
     */
    public static void isWriting(String name){
        int pitem=Message.randLen();
        String str1=Message.getNode(pitem);
        String str2=Message.modify(pitem);
        waiter.append(name+" 开始写作  第 "+ (pitem+1)+ " 内容由 "+str1+" 改写为 " +str2+"\n");
        waiter.setCaretPosition(waiter.getText().length());
        hmWriter.put(name,pitem);
    }
    public static void noWriting(String name){
        int item =  hmWriter.get(name);
        hmWriter.remove(name);
        Message.nowriting(item);
    }
    /**
     *  刷新界面
     */
    public static void addWaitQueue(String single_name){
        Controller.name_que.addLast(single_name);  //加入等待队列
        wait.setText(name_que.toString());
    }
    public static void removeWaitQueue(){
        Controller.name_que.removeFirst();
        wait.setText(name_que.toString());
    }
    public static void refresh() {
        queue.setText("当前写者在队列的个数: " + Global.writerNum.get());
        number.setText("当前在阅读的读者数量: "+Global.count.get());
    }
    public static void refreshtext() {
        String a1,b1,c1,t;
        t=Message.tostring();
        a1=t.substring(0,32);
        b1=t.substring(32,64);
        c1=t.substring(64,96);
        text1.setText(a1);
        text2.setText(b1);
        text3.setText(c1);
    }

}
