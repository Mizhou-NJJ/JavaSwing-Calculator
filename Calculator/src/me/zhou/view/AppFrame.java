package me.zhou.view;

import me.zhou.controller.EventHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AppFrame extends JFrame{

    public static JLabel resultLabel;
    public static AppFrame app;
    private String btnTexts[] = new String[]{
            "x^2","CE","C","Del",
            "1/x","(",")","/",
            "7","8","9","*",
            "4","5","6","-",
            "1","2","3","+",
            "PI","0","."};
    public AppFrame(int width,int height){
        Util.loadIndyFont();

        app = this;
        setSize(width,height);
       /*
       *
       * 取消默认窗口，下面选择自己实现
       * */
        setUndecorated(true);
        resultLabel = new JLabel("0",JLabel.RIGHT);
        init();
    }

    public void lunch(){
        setVisible(true);
    }

    //鼠标位置
    private int posX;
    private int posY;
    //记录鼠标点击时窗口的宽度和高度
    private int currentWidth;
    private int currentHeight;
    public static JLabel toMin;
    public static JLabel toClose;
    private Container container;
    private void init(){
        container = getContentPane();
        container.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        JFrame jf = this;
        jf.setLocationRelativeTo(null);
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.orange);

        JPanel appHead = new JPanel();
        BorderLayout appHeadLayout = new BorderLayout();
        appHeadLayout.setHgap(0);
        appHeadLayout.setVgap(0);
        appHead.setLayout(appHeadLayout);
        /*
        *
        *  当鼠标放在顶部菜单栏时，窗口位置随着鼠标位置改变
        * */
        appHead.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                jf.setLocation(e.getXOnScreen()-posX,e.getYOnScreen()-posY);
            }
        });
        appHead.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                posX = e.getX();
                posY = e.getY();
            }
        });

        /*
        *  App 图标
        *
        * */
        JLabel appIcon = new JLabel(" ");
        JPanel menuBar = new JPanel();
        FlowLayout menuBarLayout = new FlowLayout();
        menuBarLayout.setHgap(5);
        menuBarLayout.setVgap(5);
        menuBar.setLayout(menuBarLayout);
        menuBar.add(appIcon);
        appIcon.setIconTextGap(10);
        appIcon.setOpaque(true);
        appIcon.setIcon(new ImageIcon(new ImageIcon("res/app.png").getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH)));
        appHead.setPreferredSize(new Dimension(0,40));

        /*
         *  顶部菜单栏右边部分，最大化、最小化图标
         * */
        JPanel windowPart = new JPanel();
        toMin = new JLabel(" ");
        toMin.setOpaque(true);
        toClose = new JLabel("");
        /*
        *  添加最小化事件
        * */
        toMin.addMouseListener(new EventHandler.OnAppMin());
        toMin.setIcon(new ImageIcon(new ImageIcon("res/tomin.png").getImage().getScaledInstance(16,16,Image.SCALE_SMOOTH)));
        toClose.setIcon(new ImageIcon(new ImageIcon("res/doclose.png").getImage().getScaledInstance(16,16,Image.SCALE_SMOOTH)));
        /*
        * 当鼠标放在最小化图标是时，替换图标
        * */
        toMin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                toMin.setIcon(new ImageIcon(new ImageIcon("res/domin_hover.png").getImage().getScaledInstance(16,16, Image.SCALE_SMOOTH)));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                toMin.setIcon(new ImageIcon(new ImageIcon("res/tomin.png").getImage().getScaledInstance(16,16,Image.SCALE_SMOOTH)));
            }

        });
        // 添加关闭事件
        toClose.addMouseListener(new EventHandler.OnAppClose());
        // 和上面的一样，替换图标
        toClose.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                toClose.setIcon(new ImageIcon(new ImageIcon("res/doclose_hover.png").getImage().getScaledInstance(16,16, Image.SCALE_SMOOTH)));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                toClose.setIcon(new ImageIcon(new ImageIcon("res/doclose.png").getImage().getScaledInstance(16,16,Image.SCALE_SMOOTH)));
            }
        });

        windowPart.add(toMin);
        windowPart.add(toClose);
        appHead.add(windowPart,BorderLayout.EAST);

        /*
         *
         *
         *  App 中间部分，按钮界面、结果面板等
         *
         *
         * */
        JPanel appCenter = new JPanel();
        BorderLayout appCenterLayout = new BorderLayout();
        appCenterLayout.setVgap(10);
        appCenterLayout.setHgap(5);
        appCenter.setLayout(appCenterLayout);
        //按钮面板
        JPanel numPanel = new JPanel();
        numPanel.setLayout(new GridLayout(6,4,4,4));
        //添加按钮到按钮面板
        for(int i=0;i<btnTexts.length;i++){
            AppButton b = new AppButton(btnTexts[i]);
            b.addActionListener(new EventHandler.OnCalculatorButtonClick());
            numPanel.add(b);
        }
        //最后的一个按钮 =
        AppButton b = new AppButton("=");
        b.addActionListener(new EventHandler.OnCalculatorButtonClick());
        b.setBackground(new Color(0x1296db));
        b.setForeground(Color.WHITE);
        numPanel.add(b);

        //结果显示面板
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout(20,20));
        resultLabel.setFont(Util.sizeFont(20));
        resultLabel.setPreferredSize(new Dimension(0,60));
        inputPanel.add(new JLabel(),BorderLayout.EAST);
        inputPanel.add(resultLabel,BorderLayout.CENTER);

        appCenter.add(inputPanel,BorderLayout.NORTH);
        appCenter.add(numPanel,BorderLayout.CENTER);
        appCenter.add(new JLabel(),BorderLayout.WEST);
        appCenter.add(new JLabel(),BorderLayout.EAST);
        appCenter.add(new JLabel(),BorderLayout.SOUTH);

        container.add(appCenter,BorderLayout.CENTER);
//        container.add(appFooter,BorderLayout.SOUTH);
        container.add(appHead,BorderLayout.NORTH);
//        container.add(panel,BorderLayout.SOUTH);
        appHead.add(menuBar,BorderLayout.WEST);
        addMouseListener(new OnAppFrameMouse());
        addMouseMotionListener(new OnAppFrameMouseMotion());

    }

    /*
    * 下面实现窗口随鼠标的放大、缩小
    * */
    class OnAppFrameMouseMotion extends MouseMotionAdapter {
        @Override
        public void mouseDragged(MouseEvent e) {
            // 当鼠标位于 窗口右边缘部分时，窗口大小宽度随着鼠标位置改变
            if (e.getX()> AppFrame.this.getWidth()-50){
                int temp = e.getX();
                AppFrame.this.setSize(temp,currentHeight);
            }
            //位置随着高度改变
            if (e.getY()>AppFrame.this.getHeight()-50){
                AppFrame.this.setSize(currentWidth,e.getY());
            }
        }
    }
    class OnAppFrameMouse extends MouseAdapter{
        /**
         *  当 App Frame 发送鼠标事件时
         * @param e
         */
        @Override
        public void mouseClicked(MouseEvent e) {
            // 记录鼠标先对于App的坐标
            posX = e.getX();
            posY = e.getY();
        }

        @Override
        public void mousePressed(MouseEvent e) {
            currentWidth = AppFrame.this.getWidth();
            currentHeight = AppFrame.this.getHeight();
        }
    }

}
