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
    XYSeriesCollection dataset;
    JFreeChart chart;
    final ChartPanel chartPanel;
    final int chartWidth = 560;
    final int chartHeight = 400;;
    XYSeries series;
    String alg;
    int x;

    public GeradorDeGraficos(String applicationTitle, String algoritmo) throws IOException {
        super(applicationTitle);
        this.alg=algoritmo;
        dataset = createDataset();
        chart = createChart(dataset);
        chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(chartHeight,chartWidth));
        this.add(chartPanel);
    }

    public XYSeriesCollection createDataset() throws NumberFormatException, IOException {
        dataset = new XYSeriesCollection();
        ArrayList<XYSeries> Series1 = new ArrayList<XYSeries>();
      
        try {
        	//ALTERAR O PATH
        	System.out.println("experimentBaseDirectory\\referenceFronts\\" + alg +".tsv");
        	Scanner scanner = new Scanner(new File("experimentBaseDirectory\\referenceFronts\\" + alg +".tsv"));

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

//    public static void main(String[] args) throws IOException {
//        GeradorDeGraficos demo = new GeradorDeGraficos("Soluções ótimas geradas pelo processo de otimização","BEST_HV_FUN");
//        GeradorDeGraficos demo = new GeradorDeGraficos("Soluções ótimas geradas pelo processo de otimização","FUN0");
//        demo.pack();
//        RefineryUtilities.centerFrameOnScreen(demo);
//        demo.setVisible(true);
//    }

}
