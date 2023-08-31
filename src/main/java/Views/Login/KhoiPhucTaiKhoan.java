package Views.Login;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class KhoiPhucTaiKhoan extends Container {

    JLabel jLabel;
    JTextField jTextField;
    JButton JButton;
    JButton backtologin, JB;

    public KhoiPhucTaiKhoan() {
        JFrame jFrame = new JFrame();
        jFrame.setLayout(new BorderLayout());

        jFrame.setBackground(Color.cyan);
        jFrame.setTitle("Đăng Nhập");
        jFrame.setSize(300, 400);
        ImageIcon imageIcon = new ImageIcon("C:\\Users\\admin\\Downloads\\icon.png");

        jFrame.setIconImage(imageIcon.getImage());

        JPanel north = new JPanel();
        north.setBackground(Color.CYAN);
        north.setLayout(new FlowLayout());
        JLabel forgot = new JLabel("Khôi Phục Tài Khoản ");
        forgot.setFont(new Font("Arial", Font.BOLD, 20));
        north.add(forgot);
        JPanel center = new JPanel();
        center.setBackground(Color.cyan);
        center.setLayout(new GridLayout(9, 1));
        jLabel = new JLabel("Tên Đăng Nhập ");
        jTextField = new JTextField();
        center.add(jLabel);
        center.add(jTextField);
        center.add(new JLabel(""));

        JB = new JButton("Khôi Phục Tài Khoản ");
        JB.setForeground(Color.cyan);
        JB.setBackground(Color.blue);
        JPanel west = new JPanel();
        center.add(JB);
        center.add(new JLabel(""));
        backtologin = new JButton("Quay Lại Đăng Nhập ");
        backtologin.setForeground(Color.blue);
        Border border1 = BorderFactory.createLineBorder(Color.cyan, 1);
        backtologin.setBorder(border1);
        backtologin.setBackground(Color.cyan);
        center.add(backtologin);
        backtologin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setContentPane(new DangNhap());
                jFrame.dispose();
            }
        });
        west.setLayout(new FlowLayout());
        west.add(new JLabel(" "));
        west.setBackground(Color.cyan);
        JPanel east = new JPanel();
        east.setLayout(new FlowLayout());
        east.add(new JLabel(" "));
        east.setBackground(Color.cyan);

        ;
        jFrame.add(center, BorderLayout.CENTER);
        jFrame.add(north, BorderLayout.NORTH);
        jFrame.add(west, BorderLayout.WEST);
        jFrame.add(east, BorderLayout.EAST);
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
    }

    public static void main(String[] args) {
        new KhoiPhucTaiKhoan();
    }
}