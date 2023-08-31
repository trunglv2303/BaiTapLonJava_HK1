package Daos;

import Models.Category;
import Models.Notification;
import Models.Status;
import Utlis.ConnectDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class CategoryDao implements Dao<Category> {
    private Connection connection = ConnectDatabase.getConnection();

    @Override
    public Notification<Category> create(Category data) {
        Notification<Category> notification = new Notification<>();
        if (connection != null) {
            try {
                String sql = "INSERT INTO categories ( name) VALUES ( ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, data.getName());
                preparedStatement.executeUpdate();
                notification.setCheck(true);
                notification.setString("Create successfully");
                notification.setData(data);
            } catch (SQLException e) {
                e.printStackTrace();
                notification.setCheck(false);
                notification.setString(e.getMessage());
            }

        }

        return notification;
    }

    @Override
    public Category findOne(int id) {
        String sql = "SELECT * FROM  categories WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Category(resultSet.getInt("id"), resultSet.getString("name"));
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }


    }

    @Override
    public Vector<Category> findAll() {
        Vector<Category> categories = new Vector<>();
        String sql = "SElect * From categories";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");


                categories.add(new Category(id, name));


            }
            return categories;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public Notification<Category> delete(int id) {
        Notification<Category> notification = new Notification<>();
        CategoryDao categoryDao = new CategoryDao();
        Category category = categoryDao.findOne(id);
        if (category == null) {
            notification.setCheck(false);
            notification.setString("Không tìm thấy trạng thái");
            return notification;
        }
        try {
            String sql = "DELETE from categories where  id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            notification.setCheck(true);
            notification.setString("Xóa trạng thái thành công");
            notification.setData(category);
            return notification;


        } catch (SQLException e) {
            notification.setCheck(false);
            notification.setString("Cập nhật thất bại");
            return notification;
        }

    }

    @Override
    public Notification<Category> update(int id, Category data) {
        Notification<Category> notification = new Notification<>();
        CategoryDao categoryDao = new CategoryDao();
        Category category = categoryDao.findOne(id);
        if (category == null) {
            notification.setCheck(false);
            notification.setString("Không tìm thấy trạng thái");
            return notification;
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("Update Categories SET name = ? WHERE id =  ? ");

            preparedStatement.setString(1, data.getName());
            preparedStatement.setInt(2,data.getId());
            preparedStatement.executeUpdate();
            notification.setCheck(true);
            notification.setString("Cập nhật thành công");

            notification.setData(category);
            return notification;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

public Vector getdata(){
        CategoryDao categoryDao=new CategoryDao();
        Vector<Category> categories=categoryDao.findAll();
        Vector result= new Vector();
        for( Category category: categories) {
            Vector temp = new Vector();
            temp.add(category.getId());
            temp.add(category.getName());
            result.add(temp);
        }
        return result;

}
public Vector<Category> find (int number, String name){

        Vector result = new Vector();
        String sql = " Select * from categories where id = ? or name =? ";
try{
    PreparedStatement preparedStatement=connection.prepareStatement(sql);
    preparedStatement.setInt(1,number);
    preparedStatement.setString(2,name);
    ResultSet resultSet=preparedStatement.executeQuery();
    while (resultSet.next()){
        Status status=new Status
                (resultSet.getInt("id"),
                (resultSet.getString("name")));
        Vector temp= new Vector<>();
        temp.add(status.getId());
        temp.add(status.getName());
        result.add(temp);
    }
    return result;
}
catch (SQLException e) {
    e.printStackTrace();
    return null;
}
}

    public static void main(String[] args) {
        CategoryDao categoryDao= new CategoryDao();
        Category device= categoryDao.findOne(1005);
        System.out.println(device);

        Notification<Category>notification= categoryDao.delete(1004);
        System.out.println(notification);
    }
}
