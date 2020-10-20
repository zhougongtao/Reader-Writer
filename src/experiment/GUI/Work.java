package experiment.GUI;

import experiment.GUI.Listener.Click1;
import experiment.actor.Reader;
import experiment.actor.Writer;
import experiment.main.Global;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.sql.ClientInfoStatus;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Work extends Basic {

    public String queue;                                    //加入队列的号码
    public static int pnumber;                                 //正在进行操作的人的号码
    public static int number;                                  //被操作的内容的编号
    JTextArea waiter;
    JLabel labnumber;
    JLabel rwnumber;
    JLabel labqueue;
    JLabel wait;
    JLabel labtext1;                              //三个字符串保存图书内容
    JLabel labtext2;
    JLabel labtext3;
    public static String a;
    public static String b;
    public static String c;
    public String YN="Y";            //是否有写者在等待读者
    public AtomicInteger readnumber=new AtomicInteger(0);                               //当前读者数量
    public JButton jb1=new JButton("返回");                //返回按钮
    public static List list;
    public void show() {

        super.init();

        JPanel panel1=new JPanel();               //标题
        panel1.add(title);
        panel1.setBounds(((Toolkit.getDefaultToolkit().getScreenSize().width)/2)-530, ((Toolkit.getDefaultToolkit().getScreenSize().height)/2)-400,450,50);
        frame1.add(panel1);


        waiter = new JTextArea();      //显示读写者操作
        JScrollPane scrollpane_1 = new JScrollPane(waiter);
        waiter.setCaretPosition(waiter.getText().length());
        waiter.setFont(font2);
        scrollpane_1.setBounds(50,150,450,200);
        frame1.add(scrollpane_1);

        JPanel panel4=new JPanel();
        JLabel lab = new JLabel("等待队列: ");
        lab.setFont(font2);
        panel4.add(lab);
        panel4.setBounds(475,130,400,30);
        frame1.add(panel4);

        JPanel panel5=new JPanel();
        wait = new JLabel(" ");
        wait.setFont(font2);
        panel5.add(wait);
        panel5.setBounds(475,180,400,50);
        frame1.add(panel5);

        JPanel panel6=new JPanel();
        labqueue = new JLabel("当前写者在队列的个数: 0");
        labqueue.setFont(font2);
        panel6.add(labqueue);
        panel6.setBounds(525,260,300,50);
        frame1.add(panel6);

        JPanel panel7=new JPanel();
        labnumber =new JLabel("当前在阅读的读者数量: 0");
        labnumber.setFont(font2);
        panel7.add(labnumber);
        panel7.setBounds(525,320,300,50);
        frame1.add(panel7);

        JPanel panel8=new JPanel();
        Message.init();
        list = Message.listB; //保存12个初始内容
        for(int i=0;i<list.size();i++) {
            if(i<4)
                a=a+(String) list.get(i)+"   ";         //字符串拼接方便显示
            else if(i>3&&i<8)
                b=b+(String) list.get(i)+"   ";
            else
                c=c+(String) list.get(i)+"   ";
        }
        labtext1= new JLabel(a);               //保存第一行内容
        labtext2= new JLabel(b);               //保存第二行内容
        labtext3= new JLabel(c);               //保存第三行内容
        //labtext1.setText(a);                         //把数组内容赋值到文本里
        labtext1.setFont(font3);
        //labtext2.setText(b);
        labtext2.setFont(font3);
        //labtext3.setText(c);
        labtext3.setFont(font3);
        panel8.add(labtext1);
        panel8.add(labtext2);
        panel8.add(labtext3);
        panel8.setBounds(300,380,520,350);
        frame1.add(panel8);


        JPanel panel9=new JPanel();
        jb1.setFont(font2);
        jb1.setPreferredSize(dim1);
        panel9.add(jb1);
        panel9.setBounds(350,500,120,50);
        frame1.add(panel9);

        JPanel panel10=new JPanel();
        JLabel labshow = new JLabel("<html><body><p>"+"图"+"<br>"+"书"+"<br>"+"内"+"<br>"+"容"+"</p></body></html>");    //图书内容标题
        labshow.setFont(font4);
        panel10.add(labshow);
        panel10.setBounds(140,380,20,100);
        frame1.add(panel10);

        jb1.addMouseListener(new Click1(frame1));
        frame1.setVisible(true);


        //线程开始执行
        run();
    }

    public void run(){
        Controller.waiter=waiter;
        Controller.number=labnumber;
        Controller.text1=labtext1;
        Controller.text2=labtext2;
        Controller.text3=labtext3;
        Controller.queue=labqueue;
        Controller.wait=wait;
        Controller.rwnumber=rwnumber;
        // 内容初始化
        Message.init();
        ExecutorService service = Executors.newCachedThreadPool();     //创建线程池

        //向线程池中导入进程
        for(int i=1;i<=Global.reader;i++){
            service.execute(new Reader(i));
        }
        for(int i=1;i<=Global.writer;i++){
            service.execute(new Writer(i));
        }


    }
}

