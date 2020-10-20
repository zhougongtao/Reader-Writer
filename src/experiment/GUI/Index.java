package experiment.GUI;

import experiment.GUI.Basic;
import experiment.GUI.Listener.Click2;
import experiment.GUI.Work;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Index extends Basic {
    protected static final MouseListener MouseListener = null;


    public	static JTextField textreader;                                  //读者编辑框
    public	static JTextField textwriter;                                  //写者编辑框
    public	static int r;                                                  //输入的读者数量
    public	static int w;                                                  //输入的写者数量


    public  void indexshow() {

        super.init();                  //字体格式初始化


        JFrame frame = new JFrame();           //初始表示窗口
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭窗口时退出程序
        frame.setTitle("读写者问题");          //设置标题
        frame.setBounds(((Toolkit.getDefaultToolkit().getScreenSize().width)/2)-400, ((Toolkit.getDefaultToolkit().getScreenSize().height)/2)-250,800,500);//设置窗口位置和大小
        frame.setLayout(null);

        title=new JLabel("读者/写者问题");
        JPanel panel1=new JPanel();               //标题面板
        title.setFont(font1);                     //标题字体
        panel1.add(title);
        panel1.setBounds(((Toolkit.getDefaultToolkit().getScreenSize().width)/2)-580, ((Toolkit.getDefaultToolkit().getScreenSize().height)/2)-400,400,50);
        frame.add(panel1);



        JPanel panel2=new JPanel();               //读者面板
        JLabel labreader = new JLabel("读者数量");         //读者标签
        labreader.setFont(font2);                   //标签字体
        panel2.add(labreader);                    //插入读者标签
        textreader = new JTextField();
        textreader.setPreferredSize(dim);          //编辑框初始化
        panel2.add(textreader);                    //插入读者数量编辑框
        panel2.setBounds(250,150,300,50);
        frame.add(panel2);



        JPanel panel3=new JPanel();                //写者面板
        JLabel labwriter = new JLabel("写者数量");         //写者标签
        labwriter.setFont(font2);                  //标签字体
        panel3.add(labwriter);
        textwriter = new JTextField();
        textwriter.setPreferredSize(dim);
        panel3.add(textwriter);
        panel3.setBounds(250,200,300,50);
        frame.add(panel3);



        JButton jb=new JButton("创建进程");                 //按钮
        JPanel panel4=new JPanel();                       //按钮面板
        jb.setFont(font2);                                //按钮字体
        jb.setPreferredSize(dim1);                        //设置按钮大小
        panel4.add(jb);
        panel4.setBounds(350,250,120,50);
        frame.add(panel4);


        jb.addMouseListener(new Click2(frame));
        frame.setVisible(true);
    }
}