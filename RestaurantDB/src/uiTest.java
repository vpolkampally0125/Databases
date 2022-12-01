import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
public class uiTest {

    JFrame frame;

    JPanel border;
    JPanel panel;
    JPanel header;
    JPanel body;
    JPanel footer;

    JLabel title;
    JLabel recL;

    JButton play;
    JButton clear;
    JButton recB;

    JButton g1 = new JButton("test1");
    JButton g2 = new JButton("-");
    JButton g3 = new JButton("");
    JButton g4 = new JButton("");
    JButton test1[] = {g1, g2, g3, g4};

    JButton h1 = new JButton("test2");
    JButton h2 = new JButton("-");
    JButton h3 = new JButton("-");
    JButton h4 = new JButton("");
    JButton test2[] = {h1, h2, h3, h4};

    JButton b1 = new JButton("test3");
    JButton b2 = new JButton("-");
    JButton b3 = new JButton("-");
    JButton b4 = new JButton("");
    JButton test3[] = {b1, b2, b3, b4};

    JButton c1 = new JButton("test4");
    JButton c2 = new JButton("");
    JButton c3 = new JButton("");
    JButton c4 = new JButton("");
    JButton test4[] = {c1, c2, c3, c4};



    public static void main(String[] args) {
        uiTest t = new uiTest();
        t.buttonSetup();
        t.makeGUI();
    }

    public void buttonSetup() {
        for (JButton b : test1) {
            b.setOpaque(true);
            b.setForeground(Color.RED);
            b.setBackground(Color.RED);
            //b.addActionListener(this);
        }
        for (JButton b : test2) {
            b.setOpaque(true);
            b.setForeground(Color.ORANGE);
            b.setBackground(Color.YELLOW);
            //b.addActionListener(this);
        }
        for (JButton b : test3) {
            b.setOpaque(true);
            b.setForeground(Color.blue);
            b.setBackground(Color.cyan);
            //b.addActionListener(this);
        }
        for (JButton b : test4) {
            b.setOpaque(true);
            b.setForeground(Color.green);
            b.setBackground(Color.green);
            //b.addActionListener(this);
        }
    }

    public void makeGUI() {
        frame = new JFrame();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // background panel
        border = new JPanel();
        border.setBorder(new EmptyBorder(new Insets(20, 20, 20, 20)));
        border.setBackground(Color.magenta);

        // top panel
        header = new JPanel();
        title = new JLabel("LABEL");
        header.add(title);

        // button panel
        body = new JPanel();
        body.setLayout(new GridLayout(4, 4));
        for (int i = 0; i < test1.length; i++) {
            body.add(test1[i]);
            body.add(test2[i]);
            body.add(test3[i]);
            body.add(test4[i]);
        }

        // bottom panel
        footer = new JPanel();
        recL = new JLabel("1");
        recB = new JButton("2");
        play = new JButton("3");
        clear = new JButton("4");
        //clear.addActionListener(this);
        //recB.addActionListener(this);
        //play.addActionListener(this);
        play.setOpaque(true);
        play.setForeground(new Color(73,156,84));
        clear.setOpaque(true);
        clear.setForeground(new Color(199,84,80));
        footer.add(recB);
        footer.add(recL);
        footer.add(play);
        footer.add(clear);


        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        border.add(panel);
        frame.add(border);
        panel.add(header, BorderLayout.NORTH);
        panel.add(body, BorderLayout.CENTER);
        panel.add(footer, BorderLayout.SOUTH);
        frame.pack();
    }
}
