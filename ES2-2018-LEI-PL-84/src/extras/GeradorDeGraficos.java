package extras;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.Series;
import org.jfree.data.xy.XYDataItem;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class GeradorDeGraficos extends ApplicationFrame {

    private static final long serialVersionUID = 1L;
    private XYSeriesCollection dataset;
    private JFreeChart chart;
    private final ChartPanel chartPanel;
    private final int chartWidth = 560;
    private final int chartHeight = 400;;
    private XYSeries series;
    private String alg;
    private int x;
    
    public String getAlg() {
		return alg;
	}

    public GeradorDeGraficos(String applicationTitle, String algoritmo,String Path) throws IOException {
        super(applicationTitle);
        this.alg=algoritmo;
        dataset = createDataset(Path);
        chart = createChart(dataset);
        chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(chartHeight,chartWidth));
        this.add(chartPanel);
    }

    public XYSeriesCollection createDataset(String Path) throws NumberFormatException, IOException {
        dataset = new XYSeriesCollection();
        ArrayList<XYSeries> Series1 = new ArrayList<XYSeries>();
      
        try {
        	Scanner scanner = new Scanner(new File(Path));

            XYSeries seriesX = new XYSeries("Solução1");
            int contador=0;
            
			while(scanner.hasNextLine()) {		
				String line=scanner.nextLine();
				String []line1 = line.split(" ");
			    
				if(line1.length<=2) {
	                double X = Double.valueOf(line1[0]);
	                double Y = Double.valueOf(line1[1]);
	                seriesX.add(X, Y);
	                x=0;
				}else {
					Series1.add(new XYSeries("Solução" + contador));
					for (int i = 0; i < line1.length; i++) {
						double X = Double.valueOf(line1[i]);
						Series1.get(contador).add(i+1, X);
					}
	                contador++;
	                x=1;
				}
            }
			if(x==0) {
				dataset.addSeries(seriesX);
			}else {
				for(int i=0; i<=Series1.size()-1;i++) {
					dataset.addSeries(Series1.get(i));
				}
			}
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
        return dataset;
    }

    public JFreeChart createChart(XYDataset dataset)
            throws NumberFormatException, IOException {
    	if(x==0) {
    		chart=ChartFactory.createScatterPlot("Soluções","X","Y",dataset,PlotOrientation.VERTICAL,true,true, false);
    	}else {
    		chart = ChartFactory.createXYLineChart("Soluções","X","Y",dataset,PlotOrientation.VERTICAL,true,true, false); 	
    	}
    		return chart;
    }


}
