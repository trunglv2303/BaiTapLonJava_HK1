package Daos;

import Models.Category;
import Models.Device;
import Models.Notification;
import Models.Status;
import Utlis.ConnectDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class DeviceDao implements Dao<Device> {
    private Connection connection = ConnectDatabase.getConnection();
    private CategoryDao categoryDao = new CategoryDao();
    private StatusDao statusDao = new StatusDao();
    @Override
    public Notification<Device> create(Device data) {
        Notification<Device> notification = new Notification<>();
        if (notification != null) {
            try {
                String sql = "INSERT INTO devices ( name,description,importDate,quantity,id_categories,id_status) VALUES ( ?,?,?,?,?,?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, data.getName());
                preparedStatement.setString(2, data.getDescription());
                preparedStatement.setString(3, data.getImportDate());
                preparedStatement.setInt(4, data.getQuantity());
                preparedStatement.setInt(5, data.getCategory().getId());
                preparedStatement.setInt(6, data.getStatus().getId());
                preparedStatement.executeUpdate();
                notification.setCheck(true);
                notification.setData(data);
                notification.setString("Create successfully");
            } catch (SQLException e) {
                e.printStackTrace();
                notification.setCheck(false);
                notification.setString(e.getMessage());
            }

        }
        return notification;
    }

    @Override
    public Device findOne(int id) {
        String sql = "SELECT * FROM  devices WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                CategoryDao categoryDao = new CategoryDao();
                Category category = categoryDao.findOne(resultSet.getInt("id_categories"));
                StatusDao statusDao = new StatusDao();
                Status status = statusDao.findOne(resultSet.getInt("id_status"));
                return new
                        Device(resultSet.getInt("Id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getString("importDate"),
                        resultSet.getInt("quantity"),
                        category,
                        status);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Device findByKeyword(String keyword) {
        String sql = "SELECT * FROM  devices WHERE name = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, keyword);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                CategoryDao categoryDao = new CategoryDao();
                Category category = categoryDao.findOne(resultSet.getInt("id_categories"));
                StatusDao statusDao = new StatusDao();
                Status status = statusDao.findOne(resultSet.getInt("id_status"));
                return new
                        Device(resultSet.getInt("Id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getString("importDate"),
                        resultSet.getInt("quantity"),
                        category,
                        status);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Vector<Status> getAllStatus() {
        return this.statusDao.findAll();
    }
    public Vector<Category> getAllCateory(){
        return this.categoryDao.findAll();
    }
    @Override
    public Vector<Device> findAll() {
        Vector<Device> devices  = new Vector<>();
        String sql = "SELECT * FROM  devices";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Category category = categoryDao.findOne(resultSet.getInt("id_categories"));
                Status status = statusDao.findOne(resultSet.getInt("id_status"));
                Device device = new
                        Device(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getString("importDate"),
                        resultSet.getInt("quantity"),
                        category,
                        status);
                devices.add(device);


            }
            return devices;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public Vector<Device> findByKeyword(String keyword, int number) {
        Vector result = new Vector();
        String sql = "SELECT * FROM  devices WHERE name = ? OR id = ? OR quantity = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, keyword);
            preparedStatement.setInt(2, number);
            preparedStatement.setInt(3, number);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Category category = categoryDao.findOne(resultSet.getInt("id_categories"));
                Status status = statusDao.findOne(resultSet.getInt("id_status"));
                Device device = new
                        Device(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getString("importDate"),
                        resultSet.getInt("quantity"),
                        category,
                        status);
                Vector temp = new Vector();
                temp.add(device.getId());
                temp.add(device.getName());
                temp.add(device.getCategory().getName());
                temp.add(device.getStatus().getName());
                temp.add(device.getDescription());
                temp.add(device.getImportDate());
                temp.add(device.getQuantity());
                result.add(temp);
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public Vector getData() {
        Vector<Device> devices = this.findAll();
        Vector result = new Vector();
        for (Device device: devices) {
            Vector temp = new Vector();
            temp.add(device.getId());
            temp.add(device.getName());
            temp.add(device.getCategory().getName());
            temp.add(device.getStatus().getName());
            temp.add(device.getDescription());
            temp.add(device.getImportDate());
            temp.add(device.getQuantity());

            result.add(temp);
        }
        return result;
    }

    @Override
    public Notification<Device> delete(int id) {
        Notification<Device> notification = new Notification<>();
        Device device = this.findOne(id);
        if (device == null) {
            notification.setCheck(false);
            notification.setString("Không tìm thấy trạng thái");
            return notification;
        }
        try {
            String sql = "DELETE FROM devices WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            notification.setCheck(true);
            notification.setString("Xóa trạng thái thành công");
            notification.setData(device);
            return notification;
        } catch (SQLException e) {
            notification.setCheck(false);
            notification.setString("Cập nhật thất bại");
            return notification;
        }


    }
    public Vector getDatadevice() {
        Vector<Device> devices =findAll();
        Vector result = new Vector();
        for (Device device : devices) {
            Vector temp = new Vector();
            temp.add(device.getName());
            result.add(temp);
        }
        return result;
    }

    @Override
    public Notification<Device> update(int id, Device data) {
        DeviceDao deviceDao = new DeviceDao();
        Notification<Device> notification = new Notification<>();
        Device device = deviceDao.findOne(id);
        if (device == null) {
            notification.setCheck(false);
            notification.setString("Không tìm thấy trạng thái");
            return notification;
        }
        try {
            String sqlupdate = "UPDATE devices SET name =?, description =?, importDate =?, quantity=? ,id_categories=? ,id_status=? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlupdate);

            preparedStatement.setString(1, data.getName());
            preparedStatement.setString(2, data.getDescription());
            preparedStatement.setString(3, data.getImportDate());
            preparedStatement.setInt(4, data.getQuantity());
            preparedStatement.setInt(5, data.getCategory().getId());
            preparedStatement.setInt(6, data.getStatus().getId());
            preparedStatement.setInt(7, id);
            preparedStatement.executeUpdate();
            notification.setCheck(true);
            notification.setString("Cập Nhật Thành Công");
            System.out.println("Cập Nhật Thành Công");
            notification.setData(device);
            return notification;
        } catch (SQLException e) {
            System.out.println("Cập Nhật Thất Bại");
            return notification;

        }


    }

    public static void main(String[] args) {
        DeviceDao deviceDao = new DeviceDao();
//       Notification<Device> deviceNotification = deviceDao.delete(5);
//     System.out.println(deviceNotification);
//        Vector<Device> devices = deviceDao.getData();
//        System.out.println(devices);
//        for (Device device : devices) {
//            System.out.println(device);
//
//        }
//        Device deviceNotification = deviceDao.findOne(1);
//
//        System.out.println(deviceNotification);
//
//    }sout
        Vector<Device> deviceNotification = deviceDao.getData();
        System.out.println(deviceNotification);
    }
}
