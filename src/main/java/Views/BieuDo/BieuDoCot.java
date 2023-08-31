package Views.BieuDo;

import javax.swing.*;

import Daos.ChartDAO;
import Models.Device;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

/**
 * @author TVD
 */
public class BieuDoCot {
    JPanel panel = new JPanel();

    public JPanel getPanel() {
        return panel;
    }

    public BieuDoCot() {
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vector<Device> devices = ChartDAO.getdevice();
                DefaultCategoryDataset dataset = new DefaultCategoryDataset();
                for (Device device : devices) {
                    dataset.addValue(device.getQuantity(), "Số Lượng", device.getName());
            }
                JFreeChart barChart = ChartFactory.createBarChart(
                        "Thống Kê Số Lượng Thiết Bị ",
                        "Tên Thiết Bị", "SỐ Lượng",
                        dataset, PlotOrientation.VERTICAL, false, false, false);
                barChart.setBackgroundPaint(new Color(222, 222, 255));

                ChartPanel chartPanel = new ChartPanel(barChart);
                chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
                panel.removeAll();
                panel.add(chartPanel);
                panel.setVisible(true);
            }
        });
timer.start();




    }


}
