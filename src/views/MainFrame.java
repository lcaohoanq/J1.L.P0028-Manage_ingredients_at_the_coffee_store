package views;

//import controllers.Controller;

import constants.Message;
import controllers.MainFrameController;
import enums.MainMenu;
import styles.BorderHandler;
import styles.ColorHandler;
import styles.FontHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainFrame extends MyFrame{
    public MainFrame() {
        super();
        super.setSize(600,500);
        includeScrollPane = false;
    }
    @Override
    protected void initTitleZone(){
        super.initTitleZone();
        jLabel.setText(Message.COFFEE_SHOP_MANAGEMENT_PROGRAM);
    }
    @Override
    protected void initButtonZone() {
        jPanel_RightPanel.setBackground(ColorHandler.PRIMARY_COLOR);
        jPanel_RightPanel.setForeground(ColorHandler.TEXT_COLOR);
        jPanel_Button.setBackground(ColorHandler.PRIMARY_COLOR);
        jPanel_Button.setLayout(new GridLayout(6, 1, 0, 5));
        for (MainMenu button : MainMenu.values()) {
            btn = new JButton(button.getValue());
            btn.setBackground(ColorHandler.TEXT_COLOR);
            btn.setForeground(ColorHandler.PRIMARY_COLOR);
            btn.setFont(FontHandler.BUTTON_FONT);
            btn.addActionListener(new MainFrameController(this));
            btn.setActionCommand(Integer.toString(button.getKey()));
            jPanel_Button.add(btn);
        }
        jPanel_RightPanel.setBorder(BorderHandler.BORDER);
        jPanel_RightPanel.add(jPanel_JLabel, BorderLayout.NORTH);
        jPanel_RightPanel.add(jPanel_Button, BorderLayout.CENTER);
    }
    @Override
    protected void initContainer() {
        super.initContainer();
        panel.add(jPanel_RightPanel, BorderLayout.CENTER);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Manage ingredients")){
            IngredientFrame ingredientFrame = new IngredientFrame();
            ingredientFrame.setVisible(true);
            this.dispose();
        }else if(e.getActionCommand().equals("Manage beverages")) {
            DrinkFrame drinkFrame = new DrinkFrame();
            drinkFrame.setVisible(true);
            this.dispose();
        }else if(e.getActionCommand().equals("Manage orders")) {
            OrderFrame orderFrame = new OrderFrame();
            orderFrame.setVisible(true);
            this.dispose();
        }else if(e.getActionCommand().equals("Manage reports")) {
            ReportFrame reportFrame = new ReportFrame();
            reportFrame.setVisible(true);
            this.dispose();
        }else if(e.getActionCommand().equals("Store data to file")) {
            //Controller.storeDataToFile();
        }else if(e.getActionCommand().equals("Exit")) {
            System.exit(0);
        }
    }
    @Override
    protected void initApp(){
        super.initApp();
    }
}
