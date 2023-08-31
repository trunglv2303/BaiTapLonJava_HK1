package Daos;

import Models.Notification;
import Utlis.ConnectDatabase;
import Models.Status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class StatusDao implements Dao<Status> {

    private Connection connection = ConnectDatabase.getConnection();


    @Override
    public Notification<Status> create(Status data) {
        Notification<Status> notification = new Notification<>();
        if (connection != null) {
            try {
                String sql = "INSERT INTO status ( name) VALUES ( ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, data.getName());
                preparedStatement.executeUpdate();
                notification.setCheck(true);
                notification.setData(data);
                notification.setString("Tạo Thành Công");
            } catch (SQLException e) {
                e.printStackTrace();
                notification.setCheck(false);
                notification.setString(e.getMessage());
            }

        }
        return notification;
    }
    public Vector<Status> find(int number, String name) {
        Vector result = new Vector<>();
        String sql = "  SELECT * FROM  status  WHERE id = ? OR name = ? ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, number);
            preparedStatement.setString(2, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                Status status = new Status(resultSet.getInt("id"),
                        resultSet.getString("name"));
Vector temp=new Vector<>();
temp.add(status.getId());
temp.add(status.getName());
                result.add(temp);

            }
            return  result;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }


    }


        @Override
    public Status findOne(int id) {
        String sql = "SELECT * FROM status WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Status(resultSet.getInt("id"), resultSet.getString("name"));
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }


    @Override
    public Vector<Status> findAll() {
        Vector<Status> statuses = new Vector<>();
        String sql = "SELECT * FROM status";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                statuses.add(new Status(id, name));
            }
            return statuses;
        } catch (SQLException e) {
            e.printStackTrace();
            return statuses;
        }
    }

    @Override
    public Notification<Status> delete(int id) {
        Notification<Status> notification = new Notification<>();
        Status status = this.findOne(id);
        if (status == null) {
            notification.setCheck(false);
            notification.setString("Không tìm thấy trạng thái");
            return notification;
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Status WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            notification.setCheck(true);
            notification.setString("Xóa trạng thái thành công");
            notification.setData(status);
            return notification;
        } catch (SQLException e) {
            e.printStackTrace();
            notification.setCheck(false);
            notification.setString("Xóa thất bại");
            return notification;
        }

    }

    @Override
    public Notification<Status> update(int id, Status data) {
        Notification<Status> notification = new Notification<>();
        StatusDao statusDao = new StatusDao();
        Status status = statusDao.findOne(id);
        if (status == null) {
            notification.setCheck(false);
            notification.setString("Không tìm thấy trạng thái");
            return notification;
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("Update  Status SET name = ? WHERE id = ?");
            preparedStatement.setString(1, data.getName());
            preparedStatement.setInt(2, data.getId());
            preparedStatement.executeUpdate();
            notification.setCheck(true);
            notification.setString("Cập nhật thành công");
            notification.setData(status);
            return notification;
        } catch (SQLException e) {
            e.printStackTrace();
            notification.setCheck(false);
            notification.setString("Cập nhật thất bại");
            return notification;
        }
    }

    public Vector getData() {
        Vector<Status> statuses =findAll();
        Vector result = new Vector();
        for (Status status : statuses) {
            Vector temp = new Vector();
            temp.add(status.getId());
            temp.add(status.getName());
            result.add(temp);
        }
        return result;
    }

    ;

    public static void main(String[] args) {
        StatusDao statusDao = new StatusDao();

        Notification<Status> deviceNotification = statusDao.update(1, new Status(1, "Đang sử dụng"));
//        Notification<Status> deviceNotification1 = statusDao.create(new Status(2, "Đang được bảo hành"));
        System.out.println(deviceNotification);
    }
}
