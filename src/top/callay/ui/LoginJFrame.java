package top.callay.ui;

import javax.swing.*;

public class LoginJFrame extends JFrame {

    public LoginJFrame() {
        this.setSize(488,430);
        //设置界面标题
        this.setTitle("登录");
        //设置界面置顶
        this.setAlwaysOnTop(true);
        //设置界面居中
        this.setLocationRelativeTo(null);
        //设置关闭面膜是
        this.setDefaultCloseOperation(3);
        this.setVisible(true);
    }
}
