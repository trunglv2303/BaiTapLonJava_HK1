package Views.BieuDo;

import javax.swing.*;

import Daos.ChartDAO;
import Models.Status;
import org.jfree.chart.*;
import org.jfree.chart.plot.*;
import org.jfree.data.general.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class BieuDoHinhTron {
    JPanel panel = new JPanel();
    private DefaultPieDataset dataset;


    public JPanel getPanel() {
        return panel;
    }

    public BieuDoHinhTron() {

        panel.setLayout(new GridLayout(0, 1));
        Timer timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Vector results = ChartDAO.getStatus();

                dataset = new DefaultPieDataset();
                for (int i = 0; i < results.size(); i++) {
                    Vector vector = (Vector) results.get(i);
                    Status status = (Status) vector.get(0);
                    Double quantity = Double.parseDouble(vector.get(1) + "");
                    dataset.setValue(status.getName() + " - " + Math.round(quantity), quantity);
                }
                JFreeChart chart = ChartFactory.createPieChart(
                        "Biều Đồ Thống  Kê Tình Trạng Thiêt Bị",  // title
                        dataset,        // data
                        true,           // legend
                        true,           // tooltips
                        false       // URLs

                );
                chart.setBackgroundPaint(new Color(222, 222, 255));
                final PiePlot plot = (PiePlot) chart.getPlot();
                plot.setBackgroundPaint(Color.cyan);
                plot.setCircular(true);


                ChartPanel chartPanel = new ChartPanel(chart);
//                panel = new JPanel();
                panel.removeAll();
                panel.add(chartPanel);
                panel.setVisible(true);
            }
        });
        timer.start();



    }


}



