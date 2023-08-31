package Views.Status;

import Daos.StatusDao;
import Models.Notification;
import Models.Status;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

public class GiaoDienTinhTrang extends Container {
    JPanel panel= new JPanel();
    private static StatusDao statusDao = new StatusDao();
    private Vector<Status> statuses = statusDao.findAll();
    private Vector vtdata, colum;
    private JTable jtable;
    private JTextField name;
    private int id_update = 1;
    public JPanel getPanel() {
        return panel;
    }
    public GiaoDienTinhTrang() {





        ImageIcon imageIcon = new ImageIcon("C:\\Users\\admin\\Downloads\\icon.png");

        panel.setVisible(true);

        panel.setLayout(new GridLayout(1, 2));
        JPanel jPanel = new JPanel();


        Border border = BorderFactory.createLineBorder(Color.RED);
        TitledBorder borderTitle = BorderFactory.createTitledBorder(border, "Thông Tin");
        jPanel.setBorder(borderTitle);
        jPanel.setBackground(Color.CYAN);
        jPanel.setLayout(new GridLayout(2, 1));
        JPanel jPaneld = new JPanel();
        jPaneld.setLayout(new GridLayout(1, 2));
        JPanel jPanelc = new JPanel();
//        jPanelc.setLayout(new GridLayout(1, 1));
//        jPanelc.add(new JTextField());
        jPanel.add(jPaneld);
        jPanel.add(jPanelc);
        JPanel jPanela = new JPanel();
        jPanela.setLayout(new GridLayout(5, 1));
        jPanela.add(new JLabel("  ID"));
        jPanela.add(new JLabel("  Tên Tình Trạng "));
        JPanel jPanelb = new JPanel();
        jPanelb.setLayout(new GridLayout(5, 1));

        JTextField idtextfield= new JTextField();
        idtextfield.disable();
        name = new JTextField();

        jPanelb.add(idtextfield);
        jPanelb.add(name);
        jPaneld.add(jPanela);
        jPaneld.add(jPanelb);
        JPanel jPanel1 = new JPanel();
        jPanel1.setLayout(new GridLayout(2, 1));
        JPanel jpanel12 = new JPanel();
        Border border1 = BorderFactory.createLineBorder(Color.RED);
        TitledBorder borderTitle1 = BorderFactory.createTitledBorder(border1, "Danh Sách");
        jpanel12.setBorder(borderTitle1);
        jpanel12.setBackground(Color.cyan);
        jpanel12.setLayout(new GridLayout(1, 1));
        colum = new Vector();
        colum.add("ID");
        colum.add("Tên Tình Trạng");
        vtdata = statusDao.getData();
        jtable = new JTable();
        jtable.setModel((new DefaultTableModel(vtdata, colum)));
        jtable.setRowHeight(30);
        jtable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                int row = jtable.getSelectedRow();
                Vector status = statusDao.findAll();
                name.setText(jtable.getModel().getValueAt(row, 1) + "");
                idtextfield.setText(jtable.getModel().getValueAt(row, 0) + "");
                id_update = ((Status) status.get(row)).getId();


            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        JScrollPane sp = new JScrollPane(jtable);
        jpanel12.add(sp);
        JPanel jPanel3 = new JPanel();
        Border border2 = BorderFactory.createLineBorder(Color.RED);
        TitledBorder borderTitle2 = BorderFactory.createTitledBorder(border1, "Công Cụ");
        jPanel3.setBorder(borderTitle2);
        jPanel3.setBackground(Color.CYAN);
        jPanel3.setLayout(new FlowLayout());
        Icon iconthem = new ImageIcon("C:\\Users\\admin\\Downloads\\save1.png");

        JButton jbuttonthem = new JButton("Thêm ", iconthem);

        jbuttonthem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Status status = new Status();
                    status.setName(name.getText());
                    Notification<Status> notification = statusDao.create(status);
                    thongBao(notification);
                    System.out.println(notification);
                    nodata();

                } catch (Exception ex) {

                }


            }
        });

        jbuttonthem.setBackground(Color.YELLOW);
        jPanel3.add(jbuttonthem);

        Icon iconsua = new ImageIcon("C:\\Users\\admin\\Downloads\\sua.png");
        JButton sua = new JButton("Sửa", iconsua);
        sua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Status status = new Status();
                    status.setId(id_update);
                    status.setName(name.getText());
                    Notification<Status> notification = statusDao.update(id_update, status);
                    thongBao(notification);
                    System.out.println(notification);
                    nodata();

                } catch (Exception ex) {

                }
            }
        });
        sua.setBackground(Color.YELLOW);
        jPanel3.add(sua);

        Icon iconxoa = new ImageIcon("C:\\Users\\admin\\Downloads\\delete.png");
        JButton xoa = new JButton("Xóa", iconxoa);
        xoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int result = JOptionPane.showConfirmDialog(null, "Bạn có muốn xoá id = " + id_update + " không?", "Thông báo", JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.YES_OPTION) {
                        Notification<Status> deviceNotification = statusDao.delete(id_update);

                        thongBao(deviceNotification);
                        System.out.println(deviceNotification);
                        nodata();
                    }


                } catch (Exception ex) {

                }
            }
        });
        xoa.setBackground(Color.YELLOW);
        jPanel3.add(xoa);
        Icon icontimkiem = new ImageIcon("C:\\Users\\admin\\Downloads\\111.png");
        JButton timkiem = new JButton("Tìm Kiếm", icontimkiem);
        timkiem.addActionListener(new ActionListener() {
                                      @Override
                                      public void actionPerformed(ActionEvent e) {

                                          String result_input = JOptionPane.showInputDialog(null, "Nhập id hoặc tên thiết bị cần tìm kiếm", JOptionPane.INFORMATION_MESSAGE);

                                          if (result_input != null) {


                                              if (result_input.length() > 0) {
                                                  int number = 0;
                                                  try {
                                                      number = Integer.parseInt(result_input);
                                                  } catch (NumberFormatException ex) {

                                                  }

                                                  vtdata = statusDao.find(number, result_input);
                                                  jtable.setModel(new DefaultTableModel(vtdata, colum));
                                                  nodata();


                                              } else {
                                                  vtdata = statusDao.getData();
                                                  jtable.setModel(new DefaultTableModel(vtdata, colum));

                                              }
                                          } else {
                                              vtdata = statusDao.getData();
                                              jtable.setModel(new DefaultTableModel(vtdata, colum));
                                          }

                                      }
                                  }
        );
        timkiem.setBackground(Color.YELLOW);
        jPanel3.add(timkiem, icontimkiem);
        jPanel1.add(jpanel12);
        jPanel1.add(jPanel3);
        panel.add(jPanel);
        panel.add(jPanel1);
        panel.setVisible(true);


    }

    public void nodata() {
        name.setText(null);

    }

    public void showData() {
        vtdata = statusDao.getData();
        jtable.setModel(new DefaultTableModel(vtdata, colum));
    }


    private void thongBao(Notification<Status> notification) {
        if (notification.getCheck() == true) {
            showData();
            viewNotification(notification.getString());

        } else {
            viewNotification(notification.getString());
        }
    }

    private void viewNotification(String message) {
        JOptionPane.showMessageDialog(null, message);

    }

    public static void main(String[] args) {
        new GiaoDienTinhTrang();
    }
}
