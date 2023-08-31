package Views.Login;

import Daos.UserDao;

import Models.User;
import Views.Menu.Menu;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class DangNhap extends Container {

    JLabel username, password;
    JTextField user;
    JPasswordField  pass;
    JButton JButton;
    JButton registernow;

    public DangNhap()  {
        JFrame jFrame = new JFrame();
        jFrame.setLayout(new BorderLayout());

        jFrame.setTitle("LOGIN");
        jFrame.setSize(300, 400);
        JPanel west = new JPanel();
        ImageIcon imageIcon = new ImageIcon("C:\\Users\\admin\\Downloads\\icon.png");
        jFrame.setIconImage(imageIcon.getImage());
        west.setLayout(new FlowLayout());
        west.add(new JLabel(" "));
        west.setBackground(Color.cyan);
        JPanel east = new JPanel();
        east.setLayout(new FlowLayout());
        east.add(new JLabel(" "));
        east.setBackground(Color.cyan);

        JPanel north = new JPanel();
        north.setBackground(Color.CYAN);
        north.setLayout(new FlowLayout());
        JLabel login = new JLabel(" Đăng Nhập ");
        login.setFont(new Font("Arial", Font.BOLD, 40));
        login.setForeground(Color.red);
        north.add(login);
        JPanel center = new JPanel();
        center.setBackground(Color.CYAN);
        center.setLayout(new GridLayout(10, 1));
        username = new JLabel("Tên Đăng Nhập");
        JLabel cach = new JLabel(" ");
        password = new JLabel("Mật Khẩu");
        user = new JTextField();
        Border border = BorderFactory.createLineBorder(Color.blue, 2);
        user.setBorder(border);
        JButton = new JButton(" Đăng Nhập");
        JButton.setForeground(Color.cyan);
        JButton.setBackground(Color.blue);
        JButton.setFont(new Font("Arial", Font.BOLD, 15));
        JButton.setBorder(border);
        JPanel Forgotpassword = new JPanel();

        Forgotpassword.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JButton Forgotpassword1 = new JButton("Khôi Phục Tài Khoản ");
        Border border1 = BorderFactory.createLineBorder(Color.cyan, 1);
        Forgotpassword1.setBorder(border1);
        Forgotpassword1.setForeground(Color.blue);
        Forgotpassword1.setBackground(Color.cyan);

        Forgotpassword.setBackground(Color.cyan);
        Forgotpassword.add(Forgotpassword1);
        Forgotpassword1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setContentPane(new KhoiPhucTaiKhoan());
                jFrame.dispose();
            }
        });


        registernow = new JButton(" Tạo Tài Khoản");
        pass = new JPasswordField();
        pass.setBorder(border);
        center.add(username);

        center.add(user);
        center.add(password);
        center.add(pass);

        center.add(cach);

        center.add(JButton);


        center.add(Forgotpassword);
        JPanel sout = new JPanel();
        sout.setBackground(Color.cyan);
        registernow = new JButton(" Tạo Tài Khoản ");
        registernow.setForeground(Color.blue);

        sout.setLayout(new GridLayout(3, 1));
        registernow.setBorder(border1);
        registernow.setBackground(Color.cyan);
        registernow.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                jFrame.setContentPane(new Taotaikhoan());
                jFrame.dispose();

            }
        });
        sout.add(registernow);
        jFrame.add(north, BorderLayout.NORTH);
        jFrame.add(center, BorderLayout.CENTER);
        jFrame.add(west, BorderLayout.WEST);
        jFrame.add(east, BorderLayout.EAST);
        jFrame.add(sout, BorderLayout.SOUTH);
        jFrame.setVisible(true);
        jFrame.setLocationRelativeTo(null);


        JButton.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {
                String username= user.getText();
                UserDao userDao= new UserDao();
                User user1= userDao.findnameuser(username);

               if (user1!=null){

                   String originalString = pass.getText();
                   try {
                       MessageDigest md = MessageDigest.getInstance("MD5");
                       md.update(originalString.getBytes());
                       byte[] digest = md.digest();
                       String encoded = Base64.getEncoder().encodeToString(digest);


                      if(encoded.equals(user1.getPassword())){
                           jFrame.setContentPane(new Menu());
                           jFrame.dispose();
                       }
                       else {
                          JOptionPane.showMessageDialog(null, "Sai Mật Khẩu ");
                      }

                   }
                   catch (
                           NoSuchAlgorithmException ex) {
                       System.out.println("");
                   }

               }
               else
               {
                   JOptionPane.showMessageDialog(null, "User  Không Hợp Lệ ");
               }

            }

        });
    }


    public static void main(String[] args) {
        new DangNhap();
    }
}
