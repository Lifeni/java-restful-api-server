package lfn.java;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;

public class GUI {
    private JPanel rootPanel;
    private JPanel inputPanel;
    private JTextField url;
    private JButton send;
    private JPanel valuePanel;
    private JPanel outputPanel;
    private JLabel responseLabel;
    private JLabel request;
    private JComboBox method;
    private JTextArea headers;
    private JTextArea body;
    private JTextArea response;
    private JScrollPane responsePanel;
    private JTextArea logs;
    private JScrollPane logsPanel;
    private JScrollPane headersPanel;
    private JScrollPane bodyPanel;
    private JButton save;
    private JButton clean;
    private JButton resetHeaders;
    private JButton resetBody;
    private JPanel statusPanel;
    private JLabel status;
    private JLabel idTips;

    public GUI() {
        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                if (!send.isEnabled()) {
                    System.out.println(233);
                    return;
                }

                response.setText("");
                response.setFont(new Font("Consolas", Font.PLAIN, 14));

                statusPanel.setBackground(new Color(60, 142, 255));
                status.setText("Waiting");
                send.setEnabled(false);

                if (method.getSelectedItem() == "GET" || method.getSelectedItem() == "DELETE") {
                    try {
                        String res;
                        res = Connection.send(method.getSelectedItem(), url.getText());
                        response.setText(res);
                        statusPanel.setBackground(new Color(42, 178, 148));
                        String t = String.valueOf(Connection.getStatus());
                        if (t.equals("200")) {
                            statusPanel.setBackground(new Color(42, 178, 148));
                        } else {
                            statusPanel.setBackground(new Color(255, 86, 53));
                        }
                        status.setText(t);
                    } catch (IOException e) {
                        statusPanel.setBackground(new Color(255, 86, 53));
                        status.setText("Error");
                        e.printStackTrace();
                    }
                } else {
                    try {
                        String res;
                        res = Connection.send(method.getSelectedItem(), url.getText(), headers.getText(), body.getText());
                        response.setText(res);
                        String t = String.valueOf(Connection.getStatus());
                        if (t.equals("200")) {
                            statusPanel.setBackground(new Color(42, 178, 148));
                        } else {
                            statusPanel.setBackground(new Color(255, 86, 53));
                        }
                        status.setText(t);
                    } catch (IOException e) {
                        statusPanel.setBackground(new Color(255, 86, 53));
                        status.setText("Error");
                        e.printStackTrace();
                    }
                }
                logs.setText(Log.all().toString());
                send.setEnabled(true);
            }
        });
        method.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    Object item = e.getItem();
                    switch (item.toString()) {
                        case "GET": {
                            url.setText("http://localhost:8080/server/api/get/");
                            headers.setEnabled(false);
                            body.setEnabled(false);
                            idTips.setVisible(false);
                            break;
                        }
                        case "POST": {
                            url.setText("http://localhost:8080/server/api/post/");
                            headers.setEnabled(true);
                            body.setEnabled(true);
                            idTips.setVisible(false);
                            break;
                        }
                        case "PUT": {
                            url.setText("http://localhost:8080/server/api/put/{id}");
                            headers.setEnabled(true);
                            body.setEnabled(true);
                            idTips.setVisible(true);
                            break;
                        }
                        case "DELETE": {
                            url.setText("http://localhost:8080/server/api/delete/{id}");
                            headers.setEnabled(false);
                            body.setEnabled(false);
                            idTips.setVisible(true);
                            break;
                        }
                    }
                }
            }
        });
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = Log.save();
                logs.append(s + "\n\n");
            }
        });
        clean.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Log.clean();
                logs.setText("");
            }
        });
        resetHeaders.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                headers.setText("// Works in POST and PUT methods\n// Defaults:\nContent-Type: application/json");
            }
        });
        resetBody.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                body.setText("// Works in POST and PUT methods\n// Only JSON format\n" +
                        "// Defaults:\n{\n    \"user\":\"client\",\n    \"message\":\"hello!\"\n}\n");
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("RESTful API Test Tool");
        frame.setContentPane(new GUI().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        rootPanel = new JPanel();
        rootPanel.setLayout(new GridBagLayout());
        rootPanel.setEnabled(false);
        Font rootPanelFont = this.$$$getFont$$$("Courier New", -1, -1, rootPanel.getFont());
        if (rootPanelFont != null) rootPanel.setFont(rootPanelFont);
        inputPanel = new JPanel();
        inputPanel.setLayout(new GridBagLayout());
        inputPanel.setBackground(new Color(-1));
        Font inputPanelFont = this.$$$getFont$$$("Courier New", -1, -1, inputPanel.getFont());
        if (inputPanelFont != null) inputPanel.setFont(inputPanelFont);
        inputPanel.setPreferredSize(new Dimension(650, 90));
        inputPanel.setVerifyInputWhenFocusTarget(true);
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.ipadx = 10;
        gbc.ipady = 10;
        gbc.insets = new Insets(10, 10, 0, 10);
        rootPanel.add(inputPanel, gbc);
        inputPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, this.$$$getFont$$$(null, -1, -1, inputPanel.getFont())));
        method = new JComboBox();
        method.setAutoscrolls(false);
        method.setBackground(new Color(-1));
        method.setEditable(false);
        method.setFocusable(false);
        Font methodFont = this.$$$getFont$$$("Consolas", Font.BOLD, 16, method.getFont());
        if (methodFont != null) method.setFont(methodFont);
        method.setForeground(new Color(-16777216));
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        defaultComboBoxModel1.addElement("GET");
        defaultComboBoxModel1.addElement("POST");
        defaultComboBoxModel1.addElement("PUT");
        defaultComboBoxModel1.addElement("DELETE");
        method.setModel(defaultComboBoxModel1);
        method.setOpaque(true);
        method.setPopupVisible(false);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.2;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady = 15;
        gbc.insets = new Insets(0, 5, 0, 5);
        inputPanel.add(method, gbc);
        send = new JButton();
        send.setBackground(new Color(-12808449));
        Font sendFont = this.$$$getFont$$$("Consolas", Font.BOLD, 16, send.getFont());
        if (sendFont != null) send.setFont(sendFont);
        send.setForeground(new Color(-1));
        send.setHideActionText(true);
        send.setText("SEND ->");
        send.setToolTipText("发送请求");
        send.setVerticalAlignment(0);
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.weightx = 0.2;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady = 13;
        gbc.insets = new Insets(0, 5, 0, 5);
        inputPanel.add(send, gbc);
        url = new JTextField();
        url.setBackground(new Color(-1));
        Font urlFont = this.$$$getFont$$$("Consolas", -1, 16, url.getFont());
        if (urlFont != null) url.setFont(urlFont);
        url.setForeground(new Color(-16777216));
        url.setHorizontalAlignment(0);
        url.setText("http://localhost:8080/server/api/get");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 100;
        gbc.ipady = 20;
        gbc.insets = new Insets(0, 5, 0, 5);
        inputPanel.add(url, gbc);
        request = new JLabel();
        Font requestFont = this.$$$getFont$$$(null, Font.BOLD, 16, request.getFont());
        if (requestFont != null) request.setFont(requestFont);
        request.setForeground(new Color(-16777216));
        request.setText("Request");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 10, 10);
        inputPanel.add(request, gbc);
        idTips = new JLabel();
        idTips.setAutoscrolls(false);
        idTips.setBackground(new Color(-43467));
        idTips.setDoubleBuffered(false);
        idTips.setEnabled(true);
        idTips.setFocusCycleRoot(false);
        Font idTipsFont = this.$$$getFont$$$("Courier New", Font.BOLD, 16, idTips.getFont());
        if (idTipsFont != null) idTips.setFont(idTipsFont);
        idTips.setForeground(new Color(-1));
        idTips.setHorizontalAlignment(0);
        idTips.setHorizontalTextPosition(0);
        idTips.setOpaque(true);
        idTips.setText("{id} needs to be given");
        idTips.setToolTipText("必须输入 ID");
        idTips.setVisible(false);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.ipadx = 20;
        gbc.ipady = 8;
        gbc.insets = new Insets(0, 10, 0, 10);
        inputPanel.add(idTips, gbc);
        valuePanel = new JPanel();
        valuePanel.setLayout(new GridBagLayout());
        valuePanel.setAutoscrolls(false);
        valuePanel.setBackground(new Color(-1));
        valuePanel.setEnabled(true);
        Font valuePanelFont = this.$$$getFont$$$("Courier New", -1, -1, valuePanel.getFont());
        if (valuePanelFont != null) valuePanel.setFont(valuePanelFont);
        valuePanel.setPreferredSize(new Dimension(700, 202));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 0, 10);
        rootPanel.add(valuePanel, gbc);
        valuePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5), null));
        headersPanel = new JScrollPane();
        headersPanel.setAutoscrolls(true);
        headersPanel.setVerticalScrollBarPolicy(20);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        valuePanel.add(headersPanel, gbc);
        headersPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), null));
        headers = new JTextArea();
        headers.setAutoscrolls(true);
        headers.setBackground(new Color(-1));
        headers.setDisabledTextColor(new Color(-4473925));
        headers.setEditable(true);
        headers.setEnabled(false);
        Font headersFont = this.$$$getFont$$$("Consolas", -1, 12, headers.getFont());
        if (headersFont != null) headers.setFont(headersFont);
        headers.setForeground(new Color(-16777216));
        headers.setLineWrap(true);
        headers.setMargin(new Insets(5, 5, 5, 5));
        headers.setText("// Works in POST and PUT methods\n// Defaults:\nContent-Type: application/json");
        headers.setWrapStyleWord(false);
        headersPanel.setViewportView(headers);
        bodyPanel = new JScrollPane();
        bodyPanel.setAutoscrolls(true);
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        valuePanel.add(bodyPanel, gbc);
        bodyPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), null));
        body = new JTextArea();
        body.setBackground(new Color(-1));
        body.setDisabledTextColor(new Color(-4473925));
        body.setEnabled(false);
        Font bodyFont = this.$$$getFont$$$("Consolas", -1, 12, body.getFont());
        if (bodyFont != null) body.setFont(bodyFont);
        body.setForeground(new Color(-16777216));
        body.setLineWrap(true);
        body.setMargin(new Insets(5, 5, 5, 5));
        body.setText("// Works in POST and PUT methods\n// Only JSON format\n// Defaults:\n{\n    \"user\":\"client\",\n    \"message\":\"hello!\"\n}\n");
        bodyPanel.setViewportView(body);
        resetHeaders = new JButton();
        resetHeaders.setBackground(new Color(-12808449));
        resetHeaders.setForeground(new Color(-1));
        resetHeaders.setText("Reset");
        resetHeaders.setToolTipText("恢复默认");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(0, 0, 0, 10);
        valuePanel.add(resetHeaders, gbc);
        resetBody = new JButton();
        resetBody.setBackground(new Color(-12808449));
        resetBody.setForeground(new Color(-1));
        resetBody.setText("Reset");
        resetBody.setToolTipText("恢复默认");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(0, 0, 0, 10);
        valuePanel.add(resetBody, gbc);
        final JLabel label1 = new JLabel();
        Font label1Font = this.$$$getFont$$$(null, Font.BOLD, 16, label1.getFont());
        if (label1Font != null) label1.setFont(label1Font);
        label1.setForeground(new Color(-16777216));
        label1.setText("Headers");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 10, 10);
        valuePanel.add(label1, gbc);
        final JLabel label2 = new JLabel();
        Font label2Font = this.$$$getFont$$$(null, Font.BOLD, 16, label2.getFont());
        if (label2Font != null) label2.setFont(label2Font);
        label2.setForeground(new Color(-16777216));
        label2.setPreferredSize(new Dimension(66, 22));
        label2.setText("Body");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 10, 10);
        valuePanel.add(label2, gbc);
        outputPanel = new JPanel();
        outputPanel.setLayout(new GridBagLayout());
        outputPanel.setBackground(new Color(-1));
        Font outputPanelFont = this.$$$getFont$$$("Courier New", -1, -1, outputPanel.getFont());
        if (outputPanelFont != null) outputPanel.setFont(outputPanelFont);
        outputPanel.setMaximumSize(new Dimension(2147483647, 2147483647));
        outputPanel.setPreferredSize(new Dimension(700, 362));
        outputPanel.setRequestFocusEnabled(true);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0.5;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 10, 10);
        rootPanel.add(outputPanel, gbc);
        outputPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5), null));
        responseLabel = new JLabel();
        Font responseLabelFont = this.$$$getFont$$$(null, Font.BOLD, 16, responseLabel.getFont());
        if (responseLabelFont != null) responseLabel.setFont(responseLabelFont);
        responseLabel.setForeground(new Color(-16777216));
        responseLabel.setText("Response");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 10, 10);
        outputPanel.add(responseLabel, gbc);
        responsePanel = new JScrollPane();
        responsePanel.setAutoscrolls(true);
        responsePanel.setBackground(new Color(-1));
        responsePanel.setForeground(new Color(-16777216));
        responsePanel.setRequestFocusEnabled(true);
        responsePanel.setWheelScrollingEnabled(true);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.weightx = 5.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        outputPanel.add(responsePanel, gbc);
        responsePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0), null));
        response = new JTextArea();
        response.setBackground(new Color(-1));
        response.setDisabledTextColor(new Color(-4473925));
        response.setEditable(false);
        Font responseFont = this.$$$getFont$$$("Microsoft YaHei", -1, 18, response.getFont());
        if (responseFont != null) response.setFont(responseFont);
        response.setForeground(new Color(-16777216));
        response.setLineWrap(false);
        response.setMargin(new Insets(5, 5, 5, 5));
        response.setRows(0);
        response.setText("\nJava 搭建简单的 RESTful API 服务器\n---\n面向对象的程序设计课程作业。\n此程序为 API 测试程序，仅用于此项目的测试。\n有关这个项目的更多信息，可以查看帮助文档。\n---\n作者：梁峰宁\n学号：2018201301\n时间：2019.12.25\n");
        responsePanel.setViewportView(response);
        logsPanel = new JScrollPane();
        logsPanel.setAutoscrolls(true);
        logsPanel.setBackground(new Color(-1));
        logsPanel.setForeground(new Color(-16777216));
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 5;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        outputPanel.add(logsPanel, gbc);
        logsPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, new Color(-4473925)));
        logs = new JTextArea();
        logs.setBackground(new Color(-1));
        logs.setDisabledTextColor(new Color(-4473925));
        logs.setEditable(false);
        Font logsFont = this.$$$getFont$$$("Consolas", -1, 11, logs.getFont());
        if (logsFont != null) logs.setFont(logsFont);
        logs.setForeground(new Color(-16777216));
        logs.setLineWrap(false);
        logs.setMargin(new Insets(5, 5, 5, 5));
        logs.setOpaque(true);
        logs.setText("");
        logsPanel.setViewportView(logs);
        final JLabel label3 = new JLabel();
        Font label3Font = this.$$$getFont$$$(null, Font.BOLD, 16, label3.getFont());
        if (label3Font != null) label3.setFont(label3Font);
        label3.setForeground(new Color(-16777216));
        label3.setPreferredSize(new Dimension(78, 22));
        label3.setText("Logs");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 10, 10);
        outputPanel.add(label3, gbc);
        clean = new JButton();
        clean.setBackground(new Color(-43467));
        clean.setForeground(new Color(-1));
        clean.setSelected(false);
        clean.setText("Clean");
        clean.setToolTipText("清除");
        gbc = new GridBagConstraints();
        gbc.gridx = 5;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(0, 10, 0, 10);
        outputPanel.add(clean, gbc);
        save = new JButton();
        save.setAutoscrolls(true);
        save.setBackground(new Color(-12808449));
        save.setForeground(new Color(-1));
        save.setText("Save");
        save.setToolTipText("保存");
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(0, 5, 0, 5);
        outputPanel.add(save, gbc);
        statusPanel = new JPanel();
        statusPanel.setLayout(new GridBagLayout());
        statusPanel.setBackground(new Color(-12808449));
        Font statusPanelFont = this.$$$getFont$$$("Courier New", -1, -1, statusPanel.getFont());
        if (statusPanelFont != null) statusPanel.setFont(statusPanelFont);
        statusPanel.setForeground(new Color(-16777216));
        statusPanel.setPreferredSize(new Dimension(700, 44));
        statusPanel.setToolTipText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 0, 10);
        rootPanel.add(statusPanel, gbc);
        statusPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, this.$$$getFont$$$(null, -1, -1, statusPanel.getFont())));
        status = new JLabel();
        status.setBackground(new Color(-1));
        Font statusFont = this.$$$getFont$$$("Consolas", Font.BOLD, 20, status.getFont());
        if (statusFont != null) status.setFont(statusFont);
        status.setForeground(new Color(-1));
        status.setRequestFocusEnabled(false);
        status.setText("Status");
        status.setToolTipText("状态");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 5, 10);
        statusPanel.add(status, gbc);
        request.setLabelFor(method);
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        return new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return rootPanel;
    }

}
