package Daos;

import Models.Device;
import Models.Status;

import java.util.Vector;

public class ChartDAO {
    public static Vector getStatus() {
        int arr[] = new int[1000];
        // arr[k] chinh la so luong device co status_id bang k arr[10] = 5, co 5 thiet bi co status_id = 10
        StatusDao statusDao = new StatusDao();
        DeviceDao deviceDao = new DeviceDao();
        Vector<Status> statuses = statusDao.findAll();
        Vector<Device> devices = deviceDao.findAll();

        for (Device device : devices)
            arr[device.getStatus().getId()] =        arr[device.getStatus().getId()]+ 1;


        Vector results = new Vector();
        for (Status status : statuses) {
            Vector temp = new Vector();
            temp.add(status);
            temp.add(arr[status.getId()]);
            results.add(temp);
        }

        return results;
    }

    public  static Vector getdevice(){
        DeviceDao deviceDao= new DeviceDao();
        Vector<Device> devices = deviceDao.findAll();
        return devices;
    }

    public static void main(String[] args) {
        Vector results = ChartDAO.getStatus();
        System.out.println(results);
    }
}
