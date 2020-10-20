package experiment.GUI.Listener;


import experiment.GUI.Controller;
import experiment.GUI.Work;
import experiment.main.Global;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Click2 implements MouseListener {
    JFrame old;
    public Click2(JFrame frame1){
        old=frame1;
    }
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Controller.getRWnumber();
        old.setVisible(false);                        //关闭当前窗口
        new Work().show();                               //打开新的窗口
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
