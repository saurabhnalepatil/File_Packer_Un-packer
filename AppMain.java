import javax.swing.*;
import java.awt.Toolkit;
import java.awt.*;
import java.awt.event.*;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Color;

//import java.swing.JLable;

class PackerUnpackerLogin extends Template implements ActionListener, Runnable {
    JButton SUBMIT;
    JLabel label1;
    JLabel label2;
    JLabel label3;
    JLabel TopLable;
    final JTextField text1;
    final JTextField text2;
    private static int attempt = 3;

    PackerUnpackerLogin() {
        TopLable = new JLabel();
        TopLable.setHorizontalAlignment(SwingConstants.CENTER);
        TopLable.setText("File Packer Unpacker : Login");
        TopLable.setForeground(Color.BLUE);

        Dimension topsize = TopLable.getPreferredSize();
        TopLable.setBounds(50, 40, topsize.width, topsize.height);
        _header.add(TopLable);

        label1 = new JLabel();
        label1.setText("Username :");
        label1.setForeground(Color.white);
        Dimension size = label1.getPreferredSize();
        label1.setBounds(50, 135, size.width, size.height);
        label1.setHorizontalAlignment(SwingConstants.CENTER);

        text1 = new JTextField(15);
        Dimension tsize = text1.getPreferredSize();
        text1.setBounds(200, 135, tsize.width, tsize.height);
        text1.setToolTipText("Enter Username");

        label2 = new JLabel();
        label2.setText("Password :");
        label2.setForeground(Color.white);
        label2.setBounds(50, 175, size.width, size.height);
        label2.setHorizontalAlignment(SwingConstants.CENTER);

        text2 = new JPasswordField(15);
        text2.setBounds(200, 175, tsize.width, tsize.height);
        text2.setToolTipText("Enter Password");

        text2.addFocusListener(new FocusListener() {

            @Override
            public void focusLost(FocusEvent e) {
                label3.setText("");
            }

            @Override
            public void focusGained(FocusEvent e) {

            }
        });

        SUBMIT = new JButton("SUBMIT");
        SUBMIT.setHorizontalAlignment(SwingConstants.CENTER);
        Dimension ssize = SUBMIT.getPreferredSize();
        SUBMIT.setBounds(50, 220, ssize.width, ssize.height);

        label3 = new JLabel();
        label3.setText("");
        Dimension l3size = label3.getPreferredSize();
        label3.setForeground(Color.red);
        label3.setBounds(50, 220, l3size.width, l3size.height);

        Thread t = new Thread();
        t.start();

        _content.add(label1);
        _content.add(text1);

        _content.add(label2);
        _content.add(text2);

        _content.add(label3);
        _content.add(SUBMIT);

        // pack();
        // Validate();
        // Clock();
        ClockHome();
        setVisible(true);
        this.setSize(1000, 500);
        this.setResizable(false);

        setLocationRelativeTo(null);

        SUBMIT.addActionListener(this);
    }

    public boolean Validate(String Username, String Password) {

        if ((Username.length() < 8) && (Password.length() < 8)) {
            return false;
        } else {
            return true;
        }

    }

    public void actionPerformed(ActionEvent ae) {
        String value1 = text1.getText();
        String value2 = text2.getText();
        if (ae.getSource() == exit) {
            this.setVisible(false);
            System.exit(0);
        }
        if (ae.getSource() == minimize) {
            setState(JFrame.ICONIFIED);
        }
        if (ae.getSource() == SUBMIT) {
            if (Validate(value1, value2) == false) {
                text1.setText("");
                text2.setText("");
                JOptionPane.showMessageDialog(this, "Short username", "File Packer Unpacker",
                        JOptionPane.ERROR_MESSAGE);
            }
            if (value1.equals("PackUnpackAdmin") && value2.equals("PackUnpackAdmin")) {
                NextPage page = new NextPage(value1);
                this.setVisible(false);
                page.pack();
                page.setVisible(true);
                page.setSize(1000, 500);
            } else {
                attempt--;
                if (attempt == 0) {
                    JOptionPane.showMessageDialog(this, "Number of attempts finished", "File Packer Unpacker",
                            JOptionPane.ERROR_MESSAGE);
                    this.dispose();
                    System.exit(0);
                }
                JOptionPane.showMessageDialog(this, "Incorrect Username or Password", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void run() {
        for (;;) {
            if (text2.isFocusOwner()) {
                if (Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK)) {
                    text2.setToolTipText("Warning : CAPS LOCK is on");
                } else {
                    text2.setText("");
                }
                if ((text2.getText()).length() < 8) {
                    label3.setText("Weak Password");
                } else {
                    label3.setText("");
                }
            }
        }
    }
}

class PackerUnpackerMain {
    public static void main(String arg[]) {
        try {
            PackerUnpackerLogin frame = new PackerUnpackerLogin();
            frame.setVisible(true);
            frame.setTitle("File Packer Unpacker");
        } catch (Exception e) {
            JOptionPane.showInputDialog(null, e.getLocalizedMessage());
        }
    }
}