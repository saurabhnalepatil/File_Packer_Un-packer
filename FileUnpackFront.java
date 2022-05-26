import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;

class InvalidFileException extends Exception {

    public InvalidFileException(String str) {
        super(str);
    }
}

public class FileUnpackFront extends Template implements ActionListener {

    JButton SUBMIT;
    JButton PREVIOUS;
    JLabel label1;
    JLabel title;

    final JTextField text1;

    public FileUnpackFront() {
        this.setTitle("File Packer Unpacker");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        title = new JLabel("UnPacking Portal.");
        Dimension size = title.getPreferredSize();
        title.setBounds(40, 50, size.width + 150, size.height);
        title.setFont(new Font("Century", Font.BOLD, 17));
        title.setForeground(Color.blue);

        label1 = new JLabel();
        label1.setText("File Name");
        label1.setForeground(Color.white);
        label1.setBounds(350, 50, size.width, size.height);

        text1 = new JTextField(15);
        Dimension tsize = text1.getPreferredSize();
        text1.setBounds(500, 50, tsize.width, tsize.height);
        text1.setToolTipText("Enter name of directory ");

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
        _content.add(text1);
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
                FileUnPackBackend obj = new FileUnPackBackend(text1.getText());
                this.dispose();
                NextPage t = new NextPage("sudarshan");
            } catch (InvalidFileException obj) {
                this.setVisible((false));
                this.dispose();
                JOptionPane.showMessageDialog(this, "Invalid Packed file", "ERROR", JOptionPane.ERROR_MESSAGE);
                NextPage t = new NextPage("sudarshan");
            } catch (Exception e) {
            }
        }
        if (ae.getSource() == PREVIOUS) {

            this.setVisible(false);
            this.dispose();
            NextPage t = new NextPage("sudarshan");
        }
    }

}