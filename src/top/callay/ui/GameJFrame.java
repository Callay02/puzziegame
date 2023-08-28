package top.callay.ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GameJFrame extends JFrame implements KeyListener, ActionListener {

    //记录空白的位置
    int x = 0;
    int y = 0;

    //初始化图片索引
    int[][] data = initData();


    //图片路径
    int imageIndex=1;
    String path="image/animal/animal"+imageIndex+"/";

    //统计步数
    int step = 0;

    //创建选项下的条目对象
    JMenuItem animalItem = new JMenuItem("动物");
    JMenuItem girlItem = new JMenuItem("美女");
    JMenuItem sportItem = new JMenuItem("运动");

    JMenuItem replayItem = new JMenuItem("重新游戏");
    JMenuItem reLoginItem = new JMenuItem("重新登录(开发中)");
    JMenuItem closeItem = new JMenuItem("关闭游戏");

    JMenuItem accountItem = new JMenuItem("作者");

    public GameJFrame() {
        initJFrame();

        initJMenuBar();

        //初始化图片
        initImage();

        //显示界面
        this.setVisible(true);
    }

    private void initImage() {

        //与最下方repaint()方法配合，刷新界面
        this.getContentPane().removeAll();

        if(isWin()){
            JLabel winLabel = new JLabel(new ImageIcon("image/win.png"));
            winLabel.setBounds(203,283,197,73);
            this.getContentPane().add(winLabel);
        }

        //显示步数
        JLabel stepCount = new JLabel("步数："+step);
        stepCount.setBounds(50,30,100,20);
        this.getContentPane().add(stepCount);

        //显示白块位置
        JLabel moveBox = new JLabel("可移动方块位置：("+(x+1)+","+(y+1)+")");
        moveBox.setBounds(400,30,200,20);
        this.getContentPane().add(moveBox);

        //初始化数据
        for (int i = 0; i < 4; i++) {
            for (int i1 = 0; i1 < 4; i1++) {
                //创建ImageIcon对象,创建JLabel
                JLabel jLabel = new JLabel(new ImageIcon(path + data[i][i1] + ".jpg"));
                //指定图片位置
                jLabel.setBounds(105 * i1 + 83, 105 * i + 134, 105, 105);
                //设置边框
                jLabel.setBorder(new BevelBorder(1));
                //把JLabel添加到界面中
                this.getContentPane().add(jLabel);
            }
        }

        this.getContentPane().repaint();
    }

    private int[][] initData() {
        //打乱
        int[] tempArr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
        Random r = new Random();
        for (int i = 0; i < tempArr.length; i++) {
            int index = r.nextInt(tempArr.length);
            int temp = tempArr[i];
            tempArr[i] = tempArr[index];
            tempArr[index] = temp;
        }

        //添加到二维数组
        int[][] data = new int[4][4];

        for (int i = 0; i < tempArr.length; i++) {
            if (tempArr[i] == 16) {
                x = i / 4;
                y = i % 4;
                System.out.println("空白块坐标初始化：("+x+","+y+")");
            }
            data[i / 4][i % 4] = tempArr[i];
        }
        return data;
    }

    private void initJMenuBar() {
        //初始化菜单
        //创建菜单对象
        JMenuBar jMenuBar = new JMenuBar();

        //创建菜单上面的两个选项的对象
        JMenu changeImageMenu = new JMenu("更换图片");

        JMenu functionJMenu = new JMenu("功能");
        JMenu aboutJMenu = new JMenu("关于");

        //将选项条目添加到选项上
        changeImageMenu.add(animalItem);
        changeImageMenu.add(girlItem);
        changeImageMenu.add(sportItem);

        functionJMenu.add(changeImageMenu);
        functionJMenu.add(replayItem);
        functionJMenu.add(reLoginItem);
        functionJMenu.add(closeItem);

        aboutJMenu.add(accountItem);

        //绑定事件
        animalItem.addActionListener(this);
        girlItem.addActionListener(this);
        sportItem.addActionListener(this);

        replayItem.addActionListener(this);
        reLoginItem.addActionListener(this);
        closeItem.addActionListener(this);

        accountItem.addActionListener(this);

        //将选项添加到菜单上
        jMenuBar.add(functionJMenu);
        jMenuBar.add(aboutJMenu);
        //给整个界面设置菜单
        this.setJMenuBar(jMenuBar);
    }

    private void initJFrame() {
        //设置界面的宽高
        this.setSize(603, 680);
        //设置界面标题
        this.setTitle("拼图 V1.0");
        //设置界面置顶
        this.setAlwaysOnTop(true);
        //设置界面居中
        this.setLocationRelativeTo(null);
        //设置关闭面膜是
        this.setDefaultCloseOperation(3);
        //取消默认居中放置
        this.setLayout(null);
        this.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        //查看原图 a
        if(code == 65){
            this.getContentPane().removeAll();
            JLabel all = new JLabel(new ImageIcon(path+"all.jpg"));
            all.setBounds(83,134,420,420);
            this.add(all);
            this.getContentPane().repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //上下左右判断
        //左：37 上：38 右：39 下：40
        if(isWin()){
            return;
        }
        int code = e.getKeyCode();
        if (code==37){
            if (y!=3){
                System.out.println("向左移动");
                data[x][y]=data[x][y+1];
                data[x][y+1]=16;
                y++;
                //移动累计步数
                step++;
                initImage();
            }
        } else if (code == 38) {
            if(x!=3){
                System.out.println("向上移动");
                data[x][y]=data[x+1][y];
                data[x+1][y]=16;
                x++;
                //移动累计步数
                step++;
                initImage();
            }
        }else if (code == 39) {
            if(y!=0){
                System.out.println("向右移动");
                data[x][y]=data[x][y-1];
                data[x][y-1]=16;
                y--;
                //移动累计步数
                step++;
                initImage();
            }
        }else if (code == 40) {
            if (x!=0){
                System.out.println("向下移动");
                data[x][y]=data[x-1][y];
                data[x-1][y]=16;
                x--;
                //移动累计步数
                step++;
                initImage();
            }
        } else if (code == 65){
            initImage();
        } else if (code == 87) {    //作弊码 w
            data = new int[][]{
                    {1,2,3,4},
                    {5,6,7,8},
                    {9,10,11,12},
                    {13,14,15,16},
            };
            initImage();
        }
    }

    //判断是否胜利
    public boolean isWin(){
        int[][] win={
                {1,2,3,4},
                {5,6,7,8},
                {9,10,11,12},
                {13,14,15,16}
        };
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (data[i][j] != win[i][j]) {
                    return false;
                }
            }
        };
        return true;

    };

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==replayItem){
            System.out.println("重新游戏");
            step=0;
            data = initData();
            initImage();
        } else if (e.getSource()==reLoginItem) {
            System.out.println("重新登录");
            this.setVisible(false);
            new LoginJFrame();
        }else if (e.getSource()==closeItem) {
            System.out.println("退出游戏");
            System.exit(0);
        }else if (e.getSource()==accountItem) {
            System.out.println("关于作者");
            JDialog jdialog = new JDialog();
            JLabel myname = new JLabel("作者：Callay02");
            JLabel mygithub = new JLabel("Github：https://github.com/Callay02");

            myname.setBounds(20,40,400,40);
            mygithub.setBounds(20,80,400,40);

            jdialog.getContentPane().add(myname);
            jdialog.getContentPane().add(mygithub);

            jdialog.setSize(400,200);
            jdialog.setAlwaysOnTop(true);
            jdialog.setLayout(null);
            jdialog.setLocationRelativeTo(null);
            jdialog.setModal(true);
            jdialog.setVisible(true);
        } //更改图片
        else if (e.getSource()==animalItem) {
            System.out.println("更改为Animal");
            Random r = new Random();
            imageIndex = r.nextInt(8)+1;
            path="image/animal/animal"+imageIndex+"/";
            System.out.println("ImagePath:"+path);
            step=0;
            initImage();
        }
        else if (e.getSource()==girlItem) {
            System.out.println("更改为Girl");
            Random r = new Random();
            imageIndex = r.nextInt(13)+1;
            path="image/girl/girl"+imageIndex+"/";
            System.out.println("ImagePath:"+path);
            step=0;
            initImage();
        }
        else if (e.getSource()==sportItem) {
            System.out.println("更改为Sport");
            Random r = new Random();
            imageIndex = r.nextInt(10)+1;
            path="image/sport/sport"+imageIndex+"/";
            System.out.println("ImagePath:"+path);
            step=0;
            initImage();
        }
    }
}
