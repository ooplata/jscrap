package ui.dialogs;

import javax.swing.*;

public class ScrapResultDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JTextArea resultArea;

    private ScrapResultDialog(String html) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        resultArea.setText(html);
        buttonOK.addActionListener(e -> onOK());
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    public static void Show(String html) {
        var dialog = new ScrapResultDialog(html);
        dialog.pack();
        dialog.setVisible(true);
    }
}
