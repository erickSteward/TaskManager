package com.taskmanager;

import javax.swing.*;
import java.awt.*;

public class TaskPanel extends JPanel {
    private JList<Task> taskList;
    private DefaultListModel<Task> listModel;
    private JTextArea descriptionArea;

    public TaskPanel(JFrame parent){
        setLayout(new BorderLayout());

        listModel = new DefaultListModel<>();
        taskList = new JList<>(listModel);
        taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        taskList.addListSelectionListener(e -> updatDescriptionArea());

        JScrollPane listScrollPane = new JScrollPane(taskList);
        listScrollPane.setPreferredSize(new Dimension(200, 0));

        descriptionArea = new JTextArea();
        descriptionArea.setEditable(false);
        JScrollPane descScrollPane = new JScrollPane(descriptionArea);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, listScrollPane, descScrollPane);
        splitPane.setResizeWeight(0.3);

        add(splitPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton completeButton = new JButton("Mark Completed");
        completeButton.addActionListener(e -> markSelectedTaskCompleted());
        buttonPanel.add(completeButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    public void addTask(Task task){
        listModel.addElement(task);
    }

    public void updateDescriptionArea(){
        Task selectedTask = taskList.getSelectedValue();
        if (selectedTask != null){
            descriptionArea.setText("Title: " + selectedTask.getTitle() + "\n\n" +
                    "Description: " + selectedTask.getDescription() + "\n\n" +
                    "Due Date: " + selectedTask.getDueDate() + "\n\n" +
                    "Status: " + (selectedTask.isCompleted() ? "Completed" : "Pending"));
        } else {
            descriptionArea.setText("");
        }
    }

    public void markSelectedTaskCompleted(){
        int selectedIndex = taskList.getSelectedIndex();

        if(selectedIndex != -1){
            Task task = listModel.get(selectedIndex);
            task.setCompleted(true);
            updateDescriptionArea();
            taskList.repaint();
        }
    }



}
