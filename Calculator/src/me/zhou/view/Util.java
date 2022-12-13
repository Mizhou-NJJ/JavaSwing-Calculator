package me.zhou.view;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Util {
    private static java.awt.Font font;
    public static Font jfOpenTTF(){
        if (font==null){

            File f=new File("res/font/jf-open.ttf");
            if (!f.exists()) f=new File("res/font/jf-open.ttf");
            try {
                font= java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT,f);
            } catch (FontFormatException | IOException e) {
                e.printStackTrace();
            }
        }
        return font;
    }
    public static Font sizeFont(int size){
        return new Font("jf-open.ttf",Font.BOLD,size);
    }

    /**
     * 全局控件字体
     */
    public static void loadIndyFont(){
        java.awt.Font font=jfOpenTTF().deriveFont(12f);
        UIManager.put("CheckBox.font",font);
        UIManager.put("Tree.font", font);
        UIManager.put("Viewport.font", font);
        UIManager.put("ProgressBar.font", font);
        UIManager.put("RadioButtonMenuItem.font", font);
        UIManager.put("FormattedTextField.font", font);
        UIManager.put("Panel.font",font);
        UIManager.put("TextArea.font",font);
        UIManager.put("Menu.font", font);
        UIManager.put("RadioButtonMenuItem.acceleratorFont",font);
        UIManager.put("Menu.acceleratorFont",font);
        UIManager.put("CheckBoxMenuItem.acceleratorFont",font);
        UIManager.put("TableHeader.font", font);
        UIManager.put("TextField.font", font);
        UIManager.put("OptionPane.font", font);
        UIManager.put("MenuBar.font", font);
        UIManager.put("Button.font", font);
        UIManager.put("Label.font", font);
        UIManager.put("PasswordField.font",font);
        UIManager.put("OptionPane.buttonFont", font);
        UIManager.put("ScrollPane.font", font);
        UIManager.put("MenuItem.font", font);
        UIManager.put("ToolTip.font", font);
        UIManager.put("List.font", font);
        UIManager.put("OptionPane.messageFont",font);
        UIManager.put("EditorPane.font", font);
        UIManager.put("Table.font", font);
        UIManager.put("TabbedPane.font",font);
        UIManager.put("RadioButton.font", font);
        UIManager.put("CheckBoxMenuItem.font",font);
        UIManager.put("TextPane.font", font);
        UIManager.put("PopupMenu.font", font);
        UIManager.put("TitledBorder.font", font);
        UIManager.put("ComboBox.font", font);
    }
    //控件默认背景
//    public static void loadIndyBgColor(){
//        UIManager.put("Panel.background",Color.WHITE);
//        UIManager.put("Label.background",Color.WHITE);
//
//    }
}
