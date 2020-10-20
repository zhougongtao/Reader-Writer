package experiment.GUI;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Basic {
    public static JLabel title;
    public static Font font1;
    public static Font font2;
    public static Font font3;
    public static Font font4;
    public static Dimension dim ;
    public static Dimension dim1;
    public static JFrame frame;
    public static JFrame frame1;

    public void init() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭窗口时退出程序
        frame.setTitle("读写者问题");          //设置标题
        frame.setBounds(((Toolkit.getDefaultToolkit().getScreenSize().width)/2)-400, ((Toolkit.getDefaultToolkit().getScreenSize().height)/2)-250,800,500);//设置窗口位置和大小
        frame.setLayout(null);
        frame1 = new JFrame();//表示窗口
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭窗口时退出程序
        frame1.setTitle("动态过程");
        frame1.setBounds(((Toolkit.getDefaultToolkit().getScreenSize().width)/2)-500, ((Toolkit.getDefaultToolkit().getScreenSize().height)/2)-300,900,600);//设置窗口位置和大小
        frame1.setLayout(null);
        font1=new Font("宋体",Font.BOLD+ Font.ITALIC,40);
        font2= new Font("宋体",Font.BOLD,16);
        font3= new Font("宋体",Font.BOLD,30);
        font4= new Font("宋体",Font.BOLD,20);
        dim = new Dimension(100,30);
        dim1=new Dimension(120,30);
    }
}
