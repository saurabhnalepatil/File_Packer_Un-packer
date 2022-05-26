import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class FilePackFront extends Template implements ActionListener {

    JButton SUBMIT;
    JButton PREVIOUS;

    JLabel label1;
    JLabel label2;
    JLabel title;

    final JTextField text1;
    final JTextField text2;

    public FilePackFront() {
        this.setTitle("File Packer Unpacker");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        title = new JLabel("Packing Portal.");
        Dimension size = title.getPreferredSize();
        title.setBounds(40, 50, size.width + 70, size.height);
        title.setFont(new Font("Century", Font.BOLD, 17));
        title.setForeground(Color.blue);

        label1 = new JLabel();
        label1.setText("Directory Name");
        label1.setForeground(Color.white);
        label1.setBounds(350, 50, size.width, size.height);

        text1 = new JTextField(15);
        Dimension tsize = text1.getPreferredSize();
        text1.setBounds(500, 50, tsize.width, tsize.height);
        text1.setToolTipText("Enter name of directory ");

        label2 = new JLabel();
        label2.setText("Destination File Name");
        label2.setForeground(Color.white);
        label2.setBounds(350, 100, size.width + 60, size.height);

        text2 = new JTextField(15);
        text2.setBounds(500, 100, tsize.width, tsize.height);
        text2.setToolTipText("Enter Destination File Name ");

        SUBMIT = new JButton("SUBMIT");
        Dimension bsize = SUBMIT.getPreferredSize();
        SUBMIT.setBounds(350, 200, bsize.width, bsize.height);
        SUBMIT.addActionListener(this);

        PREVIOUS = new JButton("PREVIOUS");
        Dimension b2size = PREVIOUS.getPreferredSize();
        PREVIOUS.setBounds(500, 200, b2size.width, b2size.height);
        PREVIOUS.addActionListener(this);

        _header.add(title);
        _content.add(label1);
        _content.add(label2);
        _content.add(text1);
        _content.add(text2);
        _content.add(SUBMIT);
        _content.add(PREVIOUS);

        this.setSize(1000, 500);
        this.setResizable(false);
        this.setVisible(true);
        text1.requestFocusInWindow();
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == exit) {
            this.dispose();
            System.exit(0);
        }
        if (ae.getSource() == minimize) {
            setState(JFrame.ICONIFIED);
        }
        if (ae.getSource() == SUBMIT) {
            try {

                FilePackBackend obj = new FilePackBackend(text1.getText(), text2.getText());
                this.dispose();
                NextPage t = new NextPage("PackUnpackAdmin");
            } catch (Exception e) {

            }
        }
        if (ae.getSource() == PREVIOUS) {
            this.setVisible(false);
            this.dispose();
            NextPage t = new NextPage("PackUnpackAdmin");
        }
    }
}