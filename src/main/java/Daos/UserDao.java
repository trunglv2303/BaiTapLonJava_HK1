package Daos;

import Models.Notification;
import Models.User;
import Utlis.ConnectDatabase;

import java.sql.*;
import java.util.Vector;

public class UserDao implements Dao<User> {
    private Connection connection = ConnectDatabase.getConnection();

    @Override
    public Notification<User> create(User data) {
        Notification<User> notification = new Notification<>();
        if (connection != null) {
            try {
                String sql = " INSERT INTO Users (name , email , password ) VALUES( ? , ? , ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, data.getName());
                preparedStatement.setString(2, data.getEmail());
                preparedStatement.setString(3, data.getPassword());
                preparedStatement.executeUpdate();
                notification.setData(data);
                notification.setCheck(true);
                notification.setString("Create successfully");


            } catch (SQLException e) {

                e.printStackTrace();
                notification.setCheck(false);
            }


        }
        return notification;
    }


    public User findnameuser( String username) {
        String sql = " select * from users where name =  ? ";
        try {
            PreparedStatement preparedStatement= connection.prepareStatement(sql);
            preparedStatement.setString(1,username);
            ResultSet resultSet= preparedStatement.executeQuery();
            while (resultSet.next()){
                return new User(resultSet.getString("name"),resultSet.getInt("id"),resultSet.getString("email"),resultSet.getString("password"));}
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public User findemail( String email) {
        String sql = " select * from users where email =  ? ";
        try {
            PreparedStatement preparedStatement= connection.prepareStatement(sql);
            preparedStatement.setString(1,email);
            ResultSet resultSet= preparedStatement.executeQuery();
            while (resultSet.next()){
                return new User(resultSet.getString("name"),resultSet.getInt("id"),resultSet.getString("email"),resultSet.getString("password"));}
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public User findOne(int id) {

        String sql= " select * from users where id = ?";
        try {
            PreparedStatement preparedStatement= connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            ResultSet resultSet= preparedStatement.executeQuery();
            while (resultSet.next()){
                return new User(resultSet.getString("name"),resultSet.getInt("id"),resultSet.getString("password"),resultSet.getString("email"));

            }
            return  null;


        } catch (SQLException e) {
           e.printStackTrace();
           return null;
        }

    }

    @Override
    public Vector<User> findAll() {
        Vector<User>users= new Vector<>();
        String sql= "select * from users";
        try {
            PreparedStatement preparedStatement= connection.prepareStatement(sql);
            ResultSet resultSet= preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("Id");
                String name= resultSet.getString("name");
                String email  = resultSet.getString("email");
                String password= resultSet.getString("password");
                users.add(new User(name,id,email,password));

            }
            return  users;
        } catch (SQLException e) {
            e.printStackTrace();
            return users;
        }
    }

    @Override
    public Notification<User> delete(int id) {
        Notification<User>notification= new Notification<>();
        String sql =" delete from users where id =?";
        User user=this.findOne(id);
        if (user==null){notification.setCheck(false);
            notification.setString("Không tìm thấy trạng thái");
            return notification;}
        try {
            PreparedStatement preparedStatement= connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
            notification.setCheck(true);
            notification.setData(user);
            notification.setString(" Xóa Thành Công Trạng Thái");
            return notification;
        } catch (SQLException e) {
            e.printStackTrace();
            e.printStackTrace();
            notification.setCheck(false);
            notification.setString("Xóa thất bại");
            return notification;
        }

    }

    @Override
    public Notification<User> update(int id, User data) {
        Notification<User>notification= new Notification<>();
        UserDao userDao= new UserDao();
        User user= userDao.findOne(id);
        String sql= " Update users set name = ? , email = ?, password = ? where id =?";
        if ( user==null){
            notification.setCheck(false);
            notification.setString("Không tìm thấy trạng thái");
            return notification;

        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, data.getName());
            preparedStatement.setString(2, data.getEmail());
            preparedStatement.setString(3, data.getPassword());
            preparedStatement.setInt(4, data.getId());
            preparedStatement.executeUpdate();
            notification.setCheck(true);
            notification.setData(user);
            notification.setString("Cập Nhật Thành Công");

            return notification;
        }

        catch (SQLException e) {
           e.printStackTrace();
            notification.setCheck(false);

            notification.setString("Cập Nhật Không Thành Công");
        }
        return null;
    }

    public static void main(String[] args) {
        UserDao userDao= new UserDao();
        User userq =userDao.findnameuser("Trung2303");
        User user =userDao.findemail("Hoang2303@gmai.com");
        System.out.println(user);
        System.out.println(userq);

    }


    }



