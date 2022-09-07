package ui.dialogs;

import org.abego.treelayout.TreeLayout;
import org.abego.treelayout.demo.TextInBox;
import org.abego.treelayout.demo.TextInBoxNodeExtentProvider;
import org.abego.treelayout.demo.swing.TextInBoxTreePane;
import org.abego.treelayout.util.DefaultConfiguration;
import org.abego.treelayout.util.DefaultTreeForTreeLayout;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.swing.*;
import java.util.Objects;

public class ScrapResultDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JPanel scrollContent;

    private ScrapResultDialog(Document html) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        var tree = createTree(Objects.requireNonNull(html.firstElementChild()));

        var configuration = new DefaultConfiguration<TextInBox>(15, 10);
        var nodeExtentProvider = new TextInBoxNodeExtentProvider();

        var treeLayout = new TreeLayout<>(tree, nodeExtentProvider, configuration);
        var panel = new TextInBoxTreePane(treeLayout);

        scrollContent.add(panel);
        buttonOK.addActionListener(e -> onOK());
    }

    private DefaultTreeForTreeLayout<TextInBox> createTree(Element rootElm) {
        var root = new TextInBox(rootElm.tagName(), 40, 20);
        var tree = new DefaultTreeForTreeLayout<>(root);

        generate(tree, root, rootElm, rootElm.children());
        return tree;
    }

    private void generate(DefaultTreeForTreeLayout<TextInBox> tree, TextInBox curr, Element parent, Elements children) {
        int desired = children.size();
        for (int i = 0; i < desired; i++) {
            var child = parent.child(i);
            var txt = new TextInBox(child.tagName(), 50, 30);

            tree.addChild(curr, txt);
            generate(tree, txt, child, child.children());
        }
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    public static void Show(Document html) {
        var dialog = new ScrapResultDialog(html);
        dialog.pack();
        dialog.setVisible(true);
    }
}
