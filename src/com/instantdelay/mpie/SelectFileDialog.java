package com.instantdelay.mpie;

import java.awt.Container;
import java.awt.Dimension;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JRadioButton;
import javax.swing.LayoutStyle;

/**
 * @author Spencer Van Hoose
 */
public class SelectFileDialog {
   
   private JFrame uxWindow;
   
   private JLabel uxLocationLabel;
   private JRadioButton uxUserDataRadioBtn;
   private JRadioButton uxCustomLocRadioBtn;
   private JLabel uxCustomLocLabel;
   private JButton uxBrowseBtn;
   
   private JList uxList;
   private JButton uxOpenBtn;
   private JButton uxCancelBtn;
   
   public SelectFileDialog() {
      initComponents();
   }
   
   public void show() {
      uxWindow.setVisible(true);
   }
   
   private void initComponents() {
      uxWindow = new JFrame("Select Saved Game");
      uxWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      Container content = uxWindow.getContentPane();
      
      GroupLayout layout = new GroupLayout(content);
      uxWindow.setLayout(layout);
      
      uxLocationLabel = new JLabel("Select saved game location:");
      uxUserDataRadioBtn = new JRadioButton("Minecraft User Data");
      uxCustomLocRadioBtn = new JRadioButton();
      uxCustomLocLabel = new JLabel();
      uxBrowseBtn = new JButton("Browse...");
      
      uxList = new JList(new String[] {"hello", "item2"});
      uxCancelBtn = new JButton("Cancel");
      uxOpenBtn = new JButton("Open");
      
      layout.setAutoCreateGaps(true);
      layout.setAutoCreateContainerGaps(true);
      
      layout.setVerticalGroup(
            layout.createSequentialGroup()
               .addComponent(uxLocationLabel)
               .addComponent(uxUserDataRadioBtn)
               .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                     .addComponent(uxCustomLocRadioBtn)
                     .addComponent(uxCustomLocLabel)
                     .addComponent(uxBrowseBtn))
               .addComponent(uxList, 50, GroupLayout.DEFAULT_SIZE, Integer.MAX_VALUE)
               .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                     .addComponent(uxCancelBtn, 25, 25, 25)
                     .addComponent(uxOpenBtn))
            );
      
      layout.setHorizontalGroup(
            layout.createSequentialGroup()
               .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                     .addComponent(uxLocationLabel)
                     .addComponent(uxUserDataRadioBtn)
                     .addGroup(layout.createSequentialGroup()
                           .addComponent(uxCustomLocRadioBtn)
                           .addComponent(uxCustomLocLabel)
                           .addComponent(uxBrowseBtn))
                     .addComponent(uxList, 120, 120, Integer.MAX_VALUE)
                     .addGroup(layout.createSequentialGroup()
                           .addComponent(uxCancelBtn, 100, 100, 100)
                           .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, GroupLayout.DEFAULT_SIZE, Integer.MAX_VALUE)
                           .addComponent(uxOpenBtn))
                     )
            );
      
      layout.linkSize(uxCancelBtn, uxOpenBtn);
      
      uxWindow.pack();
   }
}
