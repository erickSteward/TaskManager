package com.taskmanager;

import javax.swing.*;
import java.awt.*;


public class TaskManagerFrame extends JFrame{
    private TaskPanel taskPanel;
    public TaskManagerFrame (){
        setTitle("Task Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        initComponents();
    }

    public void initComponents(){
        //Create a menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem exitItem = new JMenuItem("Exit");

        exitItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        //Create the main Content
        taskPanel = new TaskPanel(this);
        add(taskPanel, BorderLayout.CENTER);

        // Create a toolBar
        JToolBar toolBar = new  JToolBar();
        JButton addButton = new  JButton("Add Task");
        addButton.addActionListener(e -> showAddTaskDialog());
        toolBar.add(addButton);

        add(toolBar, BorderLayout.NORTH);

    }

    public void showAddTaskDialog(){
        AddTaskDialog dialog = new AddTaskDialog(this);
        dialog.setVisible(true);

        if (dialog.getTask() != null){
            taskPanel.addTask(dialog.getTask());
        }
    }
}