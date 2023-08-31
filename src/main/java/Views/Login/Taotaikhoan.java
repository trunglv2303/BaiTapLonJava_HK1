package Views.Login;

import Daos.UserDao;
import Models.Notification;
import Models.User;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Taotaikhoan extends Container {

    JLabel usernameLabel, passwordLabel, confirmPasswordLabel, emailLabel;
    JTextField nameField, emailField;
    JPasswordField passwordField, confirmPasswordField;
    JButton registerBtn, backtologin;
    UserDao userDao = new UserDao();


    public Taotaikhoan() {
        JFrame jFrame1 = new JFrame();
        jFrame1.setLayout(new BorderLayout());
        ImageIcon imageIcon = new ImageIcon("C:\\Users\\admin\\Downloads\\icon.png");
        jFrame1.setIconImage(imageIcon.getImage());
        jFrame1.setTitle("Tạo Tài Khoản");
        jFrame1.setLocationRelativeTo(null);
        jFrame1.setSize(300, 400);
        JPanel west = new JPanel();
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
        JLabel login = new JLabel(" Tạo Tài Khoản ");
        login.setFont(new Font("Arial", Font.BOLD, 40));
        login.setForeground(Color.red);
        north.add(login);
        JPanel center = new JPanel();
        center.setBackground(Color.CYAN);
        center.setLayout(new GridLayout(12, 1));
        usernameLabel = new JLabel("Tên Đăng Nhập");
        JLabel cach = new JLabel(" ");
        passwordLabel = new JLabel("Mật Khẩu");
        nameField = new JTextField();
        Border border = BorderFactory.createLineBorder(Color.blue, 2);
        nameField.setBorder(border);
        registerBtn = new JButton(" Tạo Tài Khoản");


        registerBtn.setForeground(Color.cyan);
        registerBtn.setBackground(Color.blue);
        registerBtn.setFont(new Font("Arial", Font.BOLD, 15));
        registerBtn.setBorder(border);
        passwordField = new JPasswordField();
        passwordField.setBorder(border);

        confirmPasswordLabel = new JLabel("Nhập Lại Mật Khẩu");
        emailLabel = new JLabel("Email");

        emailField = new JTextField();
        emailField.setBorder(border);
        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBorder(border);
        center.add(emailLabel);
        center.add(emailField);
        center.add(usernameLabel);
        center.add(nameField);
        center.add(passwordLabel);
        center.add(passwordField);
        center.add(confirmPasswordLabel);
        center.add(confirmPasswordField);
        center.add(cach);
        center.add(registerBtn);
        JPanel jPanel1 = new JPanel();
        jPanel1.setLayout(new GridLayout(3, 1));
        backtologin = new JButton("Quay Lại Đăng Nhập ");
        backtologin.setForeground(Color.blue);
        Border border1 = BorderFactory.createLineBorder(Color.cyan, 1);
        jPanel1.setBackground(Color.cyan);
        backtologin.setBorder(border1);
        backtologin.setBackground(Color.cyan);
        jPanel1.add(backtologin);
        backtologin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame1.setContentPane(new DangNhap());
                jFrame1.dispose();
            }
        });
        registerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String nameuser = nameField.getText();
                String pass = passwordField.getText();
                String CHECKpass = passwordField.getText();
                UserDao userDao1 = new UserDao();

                User user1 = userDao1.findnameuser(nameuser);
                User user2 = userDao1.findemail(email);
                String pattern = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
                String pattern1 = "^(?=.{8,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$";
                String pattern2 = "^(?=.{8,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$";
                String pattern3 = "^(?=.{8,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$";
                Pattern r = Pattern.compile(pattern);
                Pattern r1 = Pattern.compile(pattern1);
                Pattern r2 = Pattern.compile(pattern2);
                Pattern r3 = Pattern.compile(pattern3);
                Matcher m = r.matcher(email);
                Matcher m1 = r1.matcher(nameuser);
                Matcher m2 = r2.matcher(pass);
                Matcher m3 = r3.matcher(CHECKpass);

                if (m.matches()) {
                    nameField.setBackground(Color.red);
                    confirmPasswordField.setBackground(Color.red);
                    passwordField.setBackground(Color.red);
                    if (user2 == null) {
                        emailField.setBackground(Color.white);
                        if (m1.matches()) {
                            nameField.setBackground(Color.white);
                            emailField.setBackground(Color.white);
                            confirmPasswordField.setBackground(Color.red);
                            passwordField.setBackground(Color.red);
                            if (user1 == null) {
                                nameField.setBackground(Color.white);
                                confirmPasswordField.setBackground(Color.red);
                                passwordField.setBackground(Color.red);
                                if (m2.matches()) {
                                    nameField.setBackground(Color.white);
                                    emailField.setBackground(Color.white);
                                    passwordField.setBackground(Color.white);
                                    confirmPasswordField.setBackground(Color.red);
                                    if (m3.matches()) {
                                        String originalString = passwordField.getText();
                                        try {
                                            MessageDigest md = MessageDigest.getInstance("MD5");
                                            md.update(originalString.getBytes());
                                            byte[] digest = md.digest();
                                            String encoded = Base64.getEncoder().encodeToString(digest);
                                            confirmPasswordField.setBackground(Color.white);
                                            User user = new User();
                                            user.setEmail(emailField.getText());
                                            user.setName(nameField.getText());
                                            user.setPassword(encoded);
                                            if (passwordField.getText().equals(confirmPasswordField.getText())) {
                                                Notification<User> notification = userDao.create(user);
                                                System.out.println(notification);
                                                JOptionPane.showMessageDialog(null, "Đăng kí  thành công ");
                                                nodata();
                                            } else if (passwordField.getText() != confirmPasswordField.getText()) {

                                                passwordField.setBackground(Color.red);

                                                confirmPasswordField.setBackground(Color.red);
                                                JOptionPane.showMessageDialog(null, "Mật Khẩu Không Trùng Nhau ");


                                            }
                                        } catch (NoSuchAlgorithmException ex) {
                                            throw new RuntimeException(ex);
                                        }
                                    } else {
                                        JOptionPane.showMessageDialog(null, "ConfirmPasswordField  Không Hợp Lệ ");

                                    }
                                } else JOptionPane.showMessageDialog(null, "Password  Không Hợp Lệ ");

                            } else {
                                nameField.setBackground(Color.red);
                                JOptionPane.showMessageDialog(null, "UserName  Đã Tồn Tại ");

                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "UserName  Không Hợp Lệ ");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Email  Đã Tồn Tại ");
                    }

                } else if (emailField.getText().equals("")) {


                    emailField.setBackground(Color.red);
                    nameField.setBackground(Color.red);
                    confirmPasswordField.setBackground(Color.red);
                    passwordField.setBackground(Color.red);
                    JOptionPane.showMessageDialog(null, "Vui Lòng Nhập Thông Tin ");
                } else {
                    emailField.setBackground(Color.red);
                    JOptionPane.showMessageDialog(null, "Email Không Hợp Lệ ");
                    JOptionPane.showMessageDialog(null, "Đăng kí không thành công ");
                }
            }
        });


        jFrame1.add(north, BorderLayout.NORTH);
        jFrame1.add(center, BorderLayout.CENTER);
        jFrame1.add(west, BorderLayout.WEST);
        jFrame1.add(east, BorderLayout.EAST);
        jFrame1.add(jPanel1, BorderLayout.SOUTH);
        jFrame1.setVisible(true);
        jFrame1.setLocationRelativeTo(null);

    }

    public void nodata() {
        emailField.setText("");
        nameField.setText("");
        confirmPasswordField.setText("");
        passwordField.setText("");
        passwordField.setBackground(Color.white);
        confirmPasswordField.setBackground(Color.white);

    }


    public static void main(String[] args) {
        new Taotaikhoan();


    }
}