import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class NextPage extends Template implements ActionListener {
    JLabel label;
    JButton pack;
    JButton unpack;

    NextPage(String value) {
        this.setTitle("File Packer Unpacker");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        label = new JLabel("Welcome : " + value);
        Dimension size = label.getPreferredSize();
        label.setBounds(40, 50, size.width + 70, size.height);
        label.setFont(new Font("Century", Font.BOLD, 17));
        label.setForeground(Color.blue);

        pack = new JButton("Pack Files");
        Dimension bsize = pack.getPreferredSize();
        pack.setBounds(100, 100, bsize.width, bsize.height);
        pack.addActionListener(this);

        unpack = new JButton("Unpack Files");
        Dimension b2size = unpack.getPreferredSize();
        unpack.setBounds(300, 100, b2size.width, b2size.height);
        unpack.addActionListener(this);

        _header.add(label);
        _content.add(pack);
        _content.add(unpack);

        ClockHome();

        this.setSize(1000, 500);
        this.setResizable(false);
        this.setVisible(true);

    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == exit) {
            this.dispose();
            System.exit(0);
        }
        if (ae.getSource() == minimize) {
            setState(JFrame.ICONIFIED);
        }
        if (ae.getSource() == pack) {
            this.setVisible(false);
            try {
                FilePackFront obj = new FilePackFront();
            } catch (Exception e) {

            }
        }
        if (ae.getSource() == unpack) {
            this.setVisible(false);
            FileUnpackFront obj = new FileUnpackFront();
        }
    }
}