package experiment.GUI.Listener;

import experiment.GUI.Index;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Click1 implements MouseListener {
    JFrame old;
    public Click1(JFrame frame1){
        old=frame1;
    }
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        old.dispose();                                //销毁当前窗口
        new Index().indexshow();                         //打开新的界面
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}
