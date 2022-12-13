package me.zhou.view;

import javax.swing.*;
import java.awt.*;

/**
 * 对按钮UI进行一些初始化设置，
 */
public class AppButton extends JButton {
    public AppButton(String text){
        super(text);
        // 设置按钮界面
        setBackground(Color.white);
        setFocusPainted(false);
        setBorderPainted(false);
    }


}
