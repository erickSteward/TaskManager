package com.taskmanager;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

    public class AddTaskDialog extends JDialog {

        private JTextField titleField;
        private JTextArea descriptionArea;
        private JSpinner dateSpinner;
        private Task task;

        public AddTaskDialog(JFrame parent){
            super(parent, "Add Ne Task", true);
            setSize(300, 250);
            setLocationRelativeTo(parent);

            initComponents();
        }

        public void initComponents(){
            setLayout(new BorderLayout());
            JPanel formPanel = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();

            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.anchor = GridBagConstraints.WEST;
            gbc.insets = new Insets(5, 5, 5, 5);

            formPanel.add(new JLabel("Title: "));
            gbc.gridy++;

            formPanel.add(new JLabel("Description: "), gbc);
            gbc.gridy++;

            formPanel.add(new JLabel("Due Date: "), gbc);

            gbc.gridx = 1;
            gbc.gridy = 0;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.weightx = 1.0;

            titleField = new JTextField(20);
            formPanel.add(titleField, gbc);
            gbc.gridy++;

            descriptionArea = new JTextArea(10,20);
            JScrollPane scrollPane = new JScrollPane(descriptionArea);
            formPanel.add(scrollPane,gbc);
            gbc.gridy++;

            SpinnerDateModel dateModel = new SpinnerDateModel();
            dateSpinner = new JSpinner(dateModel);
            gbc.gridy++;

            JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateSpinner, "yyyy-MM-dd");
            dateSpinner.setEditor(dateEditor);
            formPanel.add(dateSpinner);

            add(formPanel, BorderLayout.CENTER);

            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

            JButton saveButton = new JButton("Save");
            saveButton.addActionListener(e -> saveTask());

            JButton cancelButton = new JButton("Cancel");
            cancelButton.addActionListener(e -> dispose());

            buttonPanel.add(saveButton);
            buttonPanel.add(cancelButton);

            add(buttonPanel, BorderLayout.SOUTH);



        }

        public void saveTask(){
            String title = titleField.getText().trim();
            String description = descriptionArea.getText().trim();
            LocalDate dueDate = LocalDate.parse(((JSpinner.DefaultEditor) dateSpinner.getEditor()).getTextField().getText());

            if (title.isEmpty()){
                JOptionPane.showMessageDialog(this, "Title cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            task = new Task(title, description, dueDate);
            dispose();
        }

    public Task getTask(){
            return task;
    }
}

