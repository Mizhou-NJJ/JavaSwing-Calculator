package me.zhou.controller;

import me.zhou.view.AppFrame;

import javax.swing.*;
import java.awt.event.*;

/**
 * 事件处理类
 */
public class EventHandler{
    /**
     * 当点击关闭按钮时
     */
    public static class OnAppClose extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            System.exit(0);
        }
    }

    /**
     * 点击最小化按钮时
     */
    public static class OnAppMin extends MouseAdapter{
        @Override
        public void mouseClicked(MouseEvent e) {
            System.out.println("hhh");
            AppFrame.app.setExtendedState(JFrame.ICONIFIED);
        }
    }

    /**
     * 点击按钮时
     */
    public static class OnCalculatorButtonClick implements ActionListener{
        //结果面板是否可以编辑，如当resultLabel 的内容为Invalid expression等时，应该拒绝输入
        private static boolean editAble = true;
        /**
         * 判断是否使正确的表达式
         * @param expression
         * @return
         */
        private boolean isValidExpression(String expression){
            if (Calculate.caculate(expression)==null){
                return false;
            }
            return true;
        }


        @Override
        public void actionPerformed(ActionEvent e) {
            String expValue = AppFrame.resultLabel.getText();
            String v = e.getActionCommand();
            switch (v){
                case "CE":
                case "C":
                    AppFrame.resultLabel.setText("0");

                    editAble = true;
                    break;
                case "Del":
                    if (!expValue.equals("0")){
                        expValue = expValue.substring(0,expValue.length()-1);
                        if (expValue.equals("")){
                            AppFrame.resultLabel.setText("0");
                            editAble = true;
                        }else{
                            AppFrame.resultLabel.setText(expValue);
                        }
                    }
                    break;
                case "PI":
                    AppFrame.resultLabel.setText(expValue+ Math.PI);
                    break;
                case "1/x":
                    String result = calculate(expValue);
                    if (result!=null){
                        try {
                            AppFrame.resultLabel.setText(String.valueOf(1/Double.parseDouble(result)));
                        }catch (Exception unknowError){
                            editAble = false;
                            AppFrame.resultLabel.setText(unknowError.getMessage());
                        }
                    }
                    break;
                case "x^2":
                    String result2 = calculate(expValue);
                    if (result2!=null){
                        try {
                            AppFrame.resultLabel.setText(String.valueOf(Math.pow(Double.parseDouble(result2),2)));
                        }catch (Exception unknowError){
                            editAble = false;
                            AppFrame.resultLabel.setText(unknowError.getMessage());
                        }
                    }
                    break;
                case "=":
                    //检查是否是有效的表达式
                    calculate(expValue);
                    break;
                default:
                    if (editAble){
                        if (expValue.equals("0")){
                            AppFrame.resultLabel.setText(e.getActionCommand());
                        }else{
                            AppFrame.resultLabel.setText(expValue+e.getActionCommand());
                        }
                        break;
                    }
            }


        }

        private String calculate(String expression){
            String result =null;
            if (isValidExpression(expression)){
                result =  Calculate.caculate(expression);
                if (result==null){
                    editAble = false;
                    AppFrame.resultLabel.setText("Invalid expression!");
                }else{
                    AppFrame.resultLabel.setText(result);
                    editAble = true;
                }
                return result;
            }else{
                editAble = false;
                AppFrame.resultLabel.setText("Invalid expression!");
                return null;
            }
        }
    }
}
