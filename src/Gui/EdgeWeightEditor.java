package Gui;

import utils.Listener;

import javax.swing.*;


public class EdgeWeightEditor extends javax.swing.JDialog {

    private Listener onSave;

    /**
     * Creates new form EdgeWeightEditor
     */
    public EdgeWeightEditor(JFrame parent, boolean modal) {
        super(parent, modal);
        initComponents();

    }

    public void setOnSave(Listener listener) {
        onSave = listener;
    }


    private void initComponents() {

        footer = new javax.swing.JPanel();
        saveBtn = new javax.swing.JButton();
        cancelBtn = new javax.swing.JButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(50, 0), new java.awt.Dimension(50, 0), new java.awt.Dimension(50, 32767));
        editor = new javax.swing.JPanel();
        label = new javax.swing.JLabel();
        weightTextField = new javax.swing.JTextField();
        errorMsg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        footer.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));
        footer.setLayout(new java.awt.BorderLayout());

        saveBtn.setText("save");
        saveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBtnActionPerformed(evt);
            }
        });
        footer.add(saveBtn, java.awt.BorderLayout.LINE_END);

        cancelBtn.setText("Cancel");
        footer.add(cancelBtn, java.awt.BorderLayout.LINE_START);
        footer.add(filler1, java.awt.BorderLayout.CENTER);

        getContentPane().add(footer, java.awt.BorderLayout.PAGE_END);

        editor.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));
        editor.setToolTipText("");
        editor.setMinimumSize(new java.awt.Dimension(244, 100));
        editor.setPreferredSize(new java.awt.Dimension(244, 100));
        editor.setLayout(new java.awt.BorderLayout());

        label.setText("Edge weight:");
        editor.add(label, java.awt.BorderLayout.LINE_START);

        weightTextField.setText("0.0");
        weightTextField.setMinimumSize(new java.awt.Dimension(120, 25));
        weightTextField.setPreferredSize(new java.awt.Dimension(120, 25));
        weightTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                weightTextFieldActionPerformed(evt);
            }
        });
        editor.add(weightTextField, java.awt.BorderLayout.CENTER);

        errorMsg.setForeground(new java.awt.Color(204, 0, 0));
        errorMsg.setText(" ");
        editor.add(errorMsg, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(editor, java.awt.BorderLayout.CENTER);

        pack();
    }

    private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {
        double weight = Double.parseDouble(weightTextField.getText());
        if (weight < 0) {
            errorMsg.setText("Enter a positive weight");
        } else {
            onSave.onAction(weight);
            dispose();
        }
    }

    private void weightTextFieldActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
    }



    private javax.swing.JButton cancelBtn;
    private javax.swing.JPanel editor;
    private javax.swing.JLabel errorMsg;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JPanel footer;
    private javax.swing.JLabel label;
    private javax.swing.JButton saveBtn;
    private javax.swing.JTextField weightTextField;

}
