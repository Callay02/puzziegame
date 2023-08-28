package top.callay.ui;

import top.callay.util.CodeUtil;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class LoginJFrame extends JFrame implements MouseListener {
    static ArrayList<User> userlist = new ArrayList<>();

    static {
        userlist.add(new User("admin", "admin123456"));
        userlist.add(new User("Callay02", "123456"));
    }

    String codeStr;

    JButton login = new JButton();
    JButton register = new JButton();
    JTextField username = new JTextField();
    JTextField password = new JTextField();
    JTextField code = new JTextField();

    public LoginJFrame() {
        initFrame();

        initView();

        this.setVisible(true);
    }

    private void initView() {

        this.getContentPane().removeAll();
        //1. 添加用户名文字
        JLabel usernameText = new JLabel(new ImageIcon("image/login/用户名.png"));
        usernameText.setBounds(116, 135, 47, 17);
        this.getContentPane().add(usernameText);

        //2.添加用户名输入框
        username.setBounds(195, 134, 200, 30);
        this.getContentPane().add(username);

        //3.添加密码文字
        JLabel passwordText = new JLabel(new ImageIcon("image/login/密码.png"));
        passwordText.setBounds(130, 195, 32, 16);
        this.getContentPane().add(passwordText);

        //4.密码输入框
        password.setBounds(195, 195, 200, 30);
        this.getContentPane().add(password);

        //验证码提示
        JLabel codeText = new JLabel(new ImageIcon("image/login/验证码.png"));
        codeText.setBounds(133, 256, 50, 30);
        this.getContentPane().add(codeText);

        //验证码的输入框
        code.setBounds(195, 256, 100, 30);
        this.getContentPane().add(code);

        codeStr = CodeUtil.getCode();
        JLabel rightCode = new JLabel();
        //设置内容
        rightCode.setText(codeStr);
        //位置和宽高
        rightCode.setBounds(300, 256, 50, 30);
        //添加到界面
        this.getContentPane().add(rightCode);

        //5.添加登录按钮
        login.setBounds(123, 310, 128, 47);
        login.setIcon(new ImageIcon("image/login/登录按钮.png"));
        //去除按钮的默认边框
        login.setBorderPainted(false);
        //去除按钮的默认背景
        login.setContentAreaFilled(false);
        login.addMouseListener(this);
        this.getContentPane().add(login);

        //6.添加注册按钮
        register.setBounds(256, 310, 128, 47);
        register.setIcon(new ImageIcon("image/login/注册按钮.png"));
        //去除按钮的默认边框
        register.setBorderPainted(false);
        //去除按钮的默认背景
        register.setContentAreaFilled(false);
        register.addMouseListener(this);
        this.getContentPane().add(register);

        //7.添加背景图片
        JLabel background = new JLabel(new ImageIcon("image/login/background.png"));
        background.setBounds(0, 0, 470, 390);
        this.getContentPane().add(background);

        this.getContentPane().repaint();
    }

    private void initFrame() {
        this.setSize(488, 430);
        //设置界面标题
        this.setTitle("登录");
        //设置界面置顶
        this.setAlwaysOnTop(true);
        //设置界面居中
        this.setLocationRelativeTo(null);
        //设置关闭模式是
        this.setDefaultCloseOperation(3);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == login) {
            System.out.println("登录按钮被点击");
            if (codeStr.equals(code.getText())) {
                for (int i = 0; i < userlist.size(); i++) {
                    if (userlist.get(i).getName().equals(username.getText()) && userlist.get(i).getPassword().equals(password.getText())) {
                        new GameJFrame();
                        this.setVisible(false);
                        return;
                    }
                }
                initView();
                showJDialog("用户名或密码错误");
            } else {
                showJDialog("验证码错误");
            }


        } else if (e.getSource()==register) {
            new RegsiterJFrame();
            this.setVisible(false);
        }
    }

    private void showJDialog(String Err) {
        JDialog messageDialog = new JDialog();
        messageDialog.setSize(200, 150);
        messageDialog.setLocationRelativeTo(null);
        messageDialog.setAlwaysOnTop(true);
        messageDialog.setLayout(null);
        messageDialog.setModal(true);

        JLabel msgLabel = new JLabel(Err);
        msgLabel.setBounds(10, 10, 150, 50);

        messageDialog.add(msgLabel);
        messageDialog.setVisible(true);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() == login) {
            login.setIcon(new ImageIcon("image/login/登录按下.png"));
        } else if (e.getSource()==register) {
            register.setIcon(new ImageIcon("image/login/注册按下.png"));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getSource() == login) {
            login.setIcon(new ImageIcon("image/login/登录按钮.png"));
        }else if (e.getSource()==register) {
            register.setIcon(new ImageIcon("image/login/注册按钮.png"));
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
