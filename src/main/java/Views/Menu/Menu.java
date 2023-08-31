package Views.Menu;

import Views.Login.DangNhap;
import Views.BieuDo.BieuDoCot;
import Views.BieuDo.BieuDoHinhTron;
import Views.Catagories.GiaoDienLoaiThietBi;
import Views.Devices.Giaodienthietbi;
import Views.Status.GiaoDienTinhTrang;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Menu extends Container {
    JButton viewdevice, viewstatus, viewcategories;

    public Menu() {
        JFrame jFrame = new JFrame();

        CardLayout cardLayout = new CardLayout();
        JPanel jFrame1 = new JPanel(cardLayout);

        GiaoDienTinhTrang viewStatus = new GiaoDienTinhTrang();
        JPanel panelstatus = viewStatus.getPanel();

        GiaoDienLoaiThietBi viewCategory = new GiaoDienLoaiThietBi();
        JPanel panelCategory = viewCategory.getPanel();

        Giaodienthietbi deviceManger = new Giaodienthietbi();
        JPanel jpaneldevice = deviceManger.getPanel();

        BieuDoHinhTron chartPanel1 = new BieuDoHinhTron();
        JPanel panel = chartPanel1.getPanel();


        BieuDoCot chartPanel = new BieuDoCot();
        JPanel bieudocot = chartPanel.getPanel();
        bieudocot.setBackground(Color.cyan);
        JPanel hai = new JPanel();
        hai.setLayout(new BorderLayout());
        JPanel panel2 = new JPanel(new GridLayout(10, 0));
        panel2.setBackground(Color.cyan);
        panel2.add(new JLabel(" "));
        panel2.add(new JLabel(" "));
        panel2.add(new JLabel(" "));
        panel2.add(new JLabel(" "));
        panel2.add(new JLabel(" "));
        panel2.add(new JLabel(" "));
        panel2.add(new JLabel(" "));
        panel2.add(new JLabel(" "));
        hai.add(panel2, BorderLayout.NORTH);

        hai.add(bieudocot, BorderLayout.CENTER);
        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayout(1, 1));
        panel1.setBackground(Color.cyan);
        panel1.add(panel);
        panel1.add(hai);

        jFrame1.add(jpaneldevice, "device");
        jFrame1.add(panelCategory, "category");
        jFrame1.add(panelstatus, "status");
        jFrame1.add(panel1, "pie");

        jFrame.setTitle("VIEW MENU");
        ImageIcon imageIcon = new ImageIcon("C:\\Users\\admin\\Downloads\\icon.png");
        jFrame.setIconImage(imageIcon.getImage());
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
        jFrame.setSize(1100, 600);
        jFrame.setLayout(new BorderLayout());
        Border border = BorderFactory.createLineBorder(Color.RED);
        TitledBorder borderTitle = BorderFactory.createTitledBorder(border, "Giao Diện");
        JPanel chucnang = new JPanel();
        chucnang.setBackground(Color.cyan);
        chucnang.setBorder(borderTitle);
        chucnang.setLayout(new GridLayout(10, 1));
        JButton viewstattus = new JButton("Tình Trạng Thiết Bị");
        JButton viewpie = new JButton("Thống Kê Thiết Bị");
        JButton viewcategory = new JButton("Loại Thiết Bị");
        JButton viewdevice = new JButton("Thiết Bị");
        Border border1 = BorderFactory.createLineBorder(Color.YELLOW, 2);
        viewstattus.setBorder(border1);
        viewstattus.setBackground(Color.cyan);
        viewstattus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewstattus.setBackground(Color.yellow);
                viewpie.setBackground(Color.cyan);
                viewcategory.setBackground(Color.cyan);
                viewdevice.setBackground(Color.cyan);
                cardLayout.show(jFrame1, "status");

            }
        });

        viewdevice.setBorder(border1);
        viewdevice.setBackground(Color.cyan);
        viewdevice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewstattus.setBackground(Color.cyan);
                viewpie.setBackground(Color.cyan);
                viewcategory.setBackground(Color.cyan);
                viewdevice.setBackground(Color.yellow);
                cardLayout.show(jFrame1, "device");

            }
        });

        viewcategory.setBorder(border1);
        viewcategory.setBackground(Color.cyan);
        viewcategory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewstattus.setBackground(Color.cyan);
                viewpie.setBackground(Color.cyan);
                viewcategory.setBackground(Color.yellow);
                viewdevice.setBackground(Color.cyan);
                cardLayout.show(jFrame1, "category");

            }
        });

        viewpie.setBorder(border1);
        viewpie.setBackground(Color.cyan);
        viewpie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewstattus.setBackground(Color.cyan);
                viewpie.setBackground(Color.yellow);
                viewcategory.setBackground(Color.cyan);
                viewdevice.setBackground(Color.cyan);
                cardLayout.show(jFrame1, "pie");
            }
        });
        chucnang.add(viewdevice);

        chucnang.add(viewstattus);
        chucnang.add(viewcategory);
        chucnang.add(viewpie);
        chucnang.add(viewpie);
        chucnang.add(new Label(""));
        chucnang.add(new Label(""));
        chucnang.add(new Label(""));
        chucnang.add(new Label(""));

        chucnang.add(new Label(""));
        JButton dangxuat= new JButton(" Đăng Xuất");
        dangxuat.setBorder(border1);
        dangxuat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setContentPane(new DangNhap());
                jFrame.dispose();
            }
        });
        dangxuat.setBackground(Color.cyan);
        Icon iconthem = new ImageIcon("C:\\Users\\admin\\Downloads\\Users.png");
        dangxuat.setIcon(iconthem);
        chucnang.add(dangxuat);
        jFrame.add(chucnang, BorderLayout.WEST);
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);


        jFrame.add(jFrame1, BorderLayout.CENTER);


        jFrame.add(jFrame1, BorderLayout.CENTER);

    }

    public static void main(String[] args) {
        new Menu();
    }

}
