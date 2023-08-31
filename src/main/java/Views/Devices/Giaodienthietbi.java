package Views.Devices;

import Daos.DeviceDao;
import Models.Category;
import Models.Device;
import Models.Notification;
import Models.Status;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Properties;
import java.util.Vector;

public class Giaodienthietbi {

    JPanel panel = new JPanel();
    private DeviceDao deviceDao = new DeviceDao();

    private JComboBox<Category> categoryJComboBox = new JComboBox<>();
    private JComboBox<Status> statusJComboBox = new JComboBox<>();
    private JTable table;
    private JTextField namedevice, id;
    private JTextField JTQuantity;
    private Vector vData, vTitle;
    private JTextArea mota;

    private JDatePickerImpl datePicker1;
    Vector<Device> deviceVector = this.deviceDao.findAll();
    Vector<Status> tinhtrangVector = this.deviceDao.getAllStatus();
    Vector<Category> loaithietbiVector = this.deviceDao.getAllCateory();


    private int id_update = 1;
    private Border border1;

    private Timer timer;

    public JPanel getPanel() {
        return panel;
    }

    public Giaodienthietbi() {

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
        JPanel jPanelmota = new JPanel();
        jPanelmota.setBackground(Color.cyan);
        jPanelmota.setLayout(new GridLayout(1, 1));
        mota = new JTextArea();
        mota.setFont(new Font("Monaco", Font.PLAIN, 20));
        jPanelmota.add(mota);
        jPanel.add(jPaneld);
        jPanel.add(jPanelmota);
        JPanel jPanela = new JPanel();
        jPanela.setBackground(Color.cyan);
        jPanela.setLayout(new GridLayout(7, 1));
        jPanela.add(new JLabel("  ID "));
        jPanela.add(new JLabel("  Tên Thiết Bị"));
        jPanela.add(new JLabel("  ID Loại Thiết Bị"));
        jPanela.add(new JLabel("  ID Tình Trạng Thiết Bị"));
        jPanela.add(new JLabel("  Ngày Nhập"));
        jPanela.add(new JLabel("  Số Lượng"));
        jPanela.add(new JLabel("  Mô Tả"));
        JPanel jPanelb = new JPanel();
        jPanelb.setBackground(Color.cyan);
        jPanelb.setLayout(new GridLayout(7, 1));

        id = new JTextField();
        id.disable();
        namedevice = new JTextField();
        border1 = BorderFactory.createLineBorder(Color.red, 1);
        namedevice.setBorder(border1);
        jPanelb.add(id);

        jPanelb.add(namedevice);

        categoryJComboBox = new JComboBox(this.loaithietbiVector);
        jPanelb.add(categoryJComboBox);
        categoryJComboBox.setBorder(border1);

        statusJComboBox = new JComboBox<>(this.tinhtrangVector);
        jPanelb.add(statusJComboBox);
        statusJComboBox.setBorder(border1);
        UtilDateModel model1 = new UtilDateModel();
        JDatePanelImpl datePanel1 = new JDatePanelImpl(model1, new Properties());
        datePicker1 = new JDatePickerImpl(datePanel1, new DateLabelFormatter());
        datePicker1.setBorder(border1);
        jPanelb.add(datePicker1);
        JTQuantity = new JTextField();
        JTQuantity.setBorder(border1);
        jPanelb.add(JTQuantity);
        jPaneld.add(jPanela);
        jPaneld.add(jPanelb);
        JPanel jPanel1 = new JPanel();
        jPanel1.setLayout(new GridLayout(2, 1));
        JPanel jpanel12 = new JPanel();
        jpanel12.setBackground(Color.cyan);
        //  Border border = BorderFactory.createLineBorder(Color.RED);
        TitledBorder borderTitle1 = BorderFactory.createTitledBorder(border1, "Danh Sách");
        jpanel12.setBorder(borderTitle1);
        jpanel12.setBackground(Color.cyan);
        jpanel12.setLayout(new GridLayout(1, 1));
        vTitle = new Vector();
        vTitle.add("ID");
        vTitle.add("Tên Thiết Bị");
        vTitle.add("Loại Thiết Bị");
        vTitle.add("Tình Trạng");
        vTitle.add("Mô Tả");
        vTitle.add("Ngày Nhập");
        vTitle.add("Số Lượng");
        vData = deviceDao.getData();
        table = new JTable();

        table.setModel(new DefaultTableModel(vData, vTitle));
        table.setRowHeight(30);
        JScrollPane sp = new JScrollPane(table);
        jpanel12.add(sp);
        JPanel jPanel3 = new JPanel();
        Border border2 = BorderFactory.createLineBorder(Color.RED);
        TitledBorder borderTitle2 = BorderFactory.createTitledBorder(border1, "Công Cụ");
        jPanel3.setBorder(borderTitle2);
        jPanel3.setBackground(Color.CYAN);
        jPanel3.setLayout(new FlowLayout());
        Icon iconthem = new ImageIcon("C:\\Users\\admin\\Downloads\\save1.png");
        JButton jbuttonthem = new JButton("Thêm ", iconthem);

        jbuttonthem.setBackground(Color.YELLOW);
        jPanel3.add(jbuttonthem);
//        Icon iconluu = new ImageIcon("C:\\Users\\admin\\Downloads\\save12.png");
//        JButton jp2 = new JButton("Lưu", iconluu);

        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {

                int row = table.getSelectedRow();
                Vector devices = deviceDao.findAll();

                id_update = ((Device) devices.get(row)).getId();

                Status status = ((Device) devices.get(row)).getStatus();
                for (int i = 0; i < statusJComboBox.getModel().getSize(); i++) {
                    if (statusJComboBox.getModel().getElementAt(i).getId() == status.getId())
                        statusJComboBox.setSelectedItem(statusJComboBox.getModel().getElementAt(i));
                }
                id.setText(table.getModel().getValueAt(row, 0) + "");
                namedevice.setText(table.getModel().getValueAt(row, 1) + "");
                JTQuantity.setText(table.getModel().getValueAt(row, 6) + "");
                mota.setText(table.getModel().getValueAt(row, 4) + "");
                datePicker1.getJFormattedTextField().setText(table.getModel().getValueAt(row, 5) + "");

                Category category = ((Device) devices.get(row)).getCategory();

                for (int i = 0; i < categoryJComboBox.getModel().getSize(); i++)

                    if (categoryJComboBox.getModel().getElementAt(i).getId() == category.getId())
                        categoryJComboBox.setSelectedItem(categoryJComboBox.getModel().getElementAt(i));

                //  System.out.println(row);


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

        timer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lay category cũ (đang chọn)
                Category category = (Category) categoryJComboBox.getSelectedItem();
                // Tạo Jcombobox mới
                categoryJComboBox.setModel(new DefaultComboBoxModel<>(deviceDao.getAllCateory()));
                // Set lại cho combobox mới cái item category cũ
                for (int i = 0; i < categoryJComboBox.getModel().getSize(); i++)
                    if (categoryJComboBox.getModel().getElementAt(i).getId() == category.getId())
                        categoryJComboBox.setSelectedItem(categoryJComboBox.getModel().getElementAt(i));

                Status status = (Status) statusJComboBox.getSelectedItem();
                statusJComboBox.setModel(new DefaultComboBoxModel<>(deviceDao.getAllStatus()));
                for (int i = 0; i < statusJComboBox.getModel().getSize(); i++) {
                    if (statusJComboBox.getModel().getElementAt(i).getId() == status.getId())
                        statusJComboBox.setSelectedItem(statusJComboBox.getModel().getElementAt(i));
                }

            }
        });
        timer.start();



        jbuttonthem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Device device = new Device();
                    device.setName(namedevice.getText());
                    Status status1 = (Status) statusJComboBox.getSelectedItem();
                    device.setStatus(status1);
                    Category category2 = (Category) categoryJComboBox.getSelectedItem();
                    device.setCategory(category2);
                    device.setQuantity(Integer.parseInt(JTQuantity.getText()));
                    device.setImportDate(datePicker1.getJFormattedTextField().getText());
                    device.setDescription(mota.getText());
                    Notification<Device> deviceNotification = deviceDao.create(device);
                    thongBao(deviceNotification);
                    System.out.println(deviceNotification);
                    nodata();
                } catch (Exception ee) {

                }
            }
        });


        Icon iconsua = new ImageIcon("C:\\Users\\admin\\Downloads\\sua.png");
        JButton sua = new JButton("Sửa", iconsua);
        sua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Device device = new Device();
                device.setId(id_update);
                device.setName(namedevice.getText());
                device.setDescription(device.getDescription());
                Category category2 = (Category) categoryJComboBox.getSelectedItem();
                device.setCategory(category2);
                Status status1 = (Status) statusJComboBox.getSelectedItem();
                device.setStatus(status1);
                device.setQuantity(Integer.parseInt(JTQuantity.getText()));
                device.setImportDate(datePicker1.getJFormattedTextField().getText());
                device.setDescription(mota.getText());
                Notification<Device> deviceNotification = deviceDao.update(device.getId(), device);
                thongBao(deviceNotification);
                System.out.println(deviceNotification);
                nodata();

                ;
            }
        });
        sua.setBackground(Color.YELLOW);
        jPanel3.add(sua);
        Icon iconxoa = new ImageIcon("C:\\Users\\admin\\Downloads\\delete.png");
        JButton xoa = new JButton("Xóa", iconxoa);
        xoa.setBackground(Color.YELLOW);
        jPanel3.add(xoa);
        xoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Device device = new Device();
                device.setId(id_update);
                int result = JOptionPane.showConfirmDialog(null, "Bạn có muốn xoá id = " + id_update + " không?", "Thông báo", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    Notification<Device> deviceNotification = deviceDao.delete(device.getId());
                    thongBao(deviceNotification);
                    System.out.println(deviceNotification);
                    nodata();
                }
            }
        });
        Icon icontimkiem = new ImageIcon("C:\\Users\\admin\\Downloads\\111.png");
        JButton timkiem = new JButton("Tìm Kiếm", icontimkiem);
        timkiem.setBackground(Color.YELLOW);

        timkiem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String result_input = JOptionPane.showInputDialog(null, "Nhập id hoặc tên thiết bị cần tìm kiếm", JOptionPane.INFORMATION_MESSAGE);
                if (result_input != null) {
                    if (result_input.length() > 0) {
                        int number = 0;
                        try {
                            number = Integer.parseInt(result_input);
                        } catch (Exception e1) {

                        }
                        vData = deviceDao.findByKeyword(result_input, number);
                        table.setModel(new DefaultTableModel(vData, vTitle));
                        nodata();
                    } else {
                        vData = deviceDao.getData();
                        table.setModel(new DefaultTableModel(vData, vTitle));
                    }
                } else {
                    vData = deviceDao.getData();
                    table.setModel(new DefaultTableModel(vData, vTitle));
                }
            }
        });

        jPanel3.add(timkiem, icontimkiem);
        jPanel1.add(jpanel12);
        jPanel1.add(jPanel3);
        panel.add(jPanel);
        panel.add(jPanel1);
        panel.setVisible(true);
    }

    public void showData() {
        vData = deviceDao.getData();
        table.setModel(new DefaultTableModel(vData, vTitle));
    }

    public void nodata() {
        namedevice.setText(null);
        datePicker1.getJFormattedTextField().setText(null);
        mota.setText(null);
        JTQuantity.setText(null);
    }


    private void thongBao(Notification<Device> notification) {
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

        new Giaodienthietbi();
    }
}
