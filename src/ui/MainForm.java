package ui;

import org.jsoup.Jsoup;
import org.jsoup.parser.Parser;
import scraper.WebContentHelper;
import ui.dialogs.ScrapResultDialog;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MainForm {
    private JPanel root;
    private JTextField urlInput;
    private JButton scrapButton;

    public MainForm() {
        Border border = root.getBorder();
        Border margin = new EmptyBorder(39, 80, 0, 0);
        root.setBorder(new CompoundBorder(border, margin));

        scrapButton.addActionListener(e -> {
            String html = WebContentHelper.GetWebContent(urlInput.getText());

            var doc = Jsoup.parse(html, "", Parser.xmlParser());
            for (var element : doc.getAllElements()) {
                if (!element.ownText().isEmpty())
                    for (var node : element.textNodes())
                        node.remove();

                element.clearAttributes();
            }

            for (var element : doc.getElementsByTag("style"))
                element.text("");

            ScrapResultDialog.Show(doc);
        });
    }

    public static void main(String[] args) {
        var frame = new JFrame("JScraper");
        var form = new MainForm();

        frame.setContentPane(form.root);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.pack();
        frame.setVisible(true);
        frame.setSize(new Dimension(400, 220));
    }
}
