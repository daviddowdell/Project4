import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class OutputTable extends JTable

{
    private static final String[] headers = new String[] {"Station", "Parameter", "Statistics",
            "Value", "Reporting Stations", "Date"};
    
   
    private int rows;
    private Object[][] dataArray;
  
    private boolean tair = false;
    private boolean ta9m = false;
    private boolean srad = false;
    private boolean wspd = false;
    private boolean pres = false;
    private boolean min = false;
    private boolean max = false;
    private boolean avg = false;
    
    private MapData dataSheet;

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    public OutputTable (Object[][] a)
    {
        super(a, headers);
        dataArray = a;
        this.setPreferredSize(new Dimension(600,400));
        this.setLayout(new BorderLayout());
        this.add(this.getTableHeader(), BorderLayout.PAGE_START);
        
        ((DefaultTableCellRenderer)this.getTableHeader().getDefaultRenderer())
        .setHorizontalAlignment(JLabel.LEFT);
        setShowGrid(false);
        setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        getColumnModel().getColumn(0).setPreferredWidth(45);
        getColumnModel().getColumn(1).setPreferredWidth(70);
        getColumnModel().getColumn(2).setPreferredWidth(80);
        getColumnModel().getColumn(3).setPreferredWidth(60);
        getColumnModel().getColumn(4).setPreferredWidth(120);
        getColumnModel().getColumn(5).setPreferredWidth(120);
        setFillsViewportHeight(true);
        rows = 0;
       // tableModel = (DefaultTableModel) this.createDefaultDataModel();
       
        
    }
    
    

    public boolean isTair()
    {
        return tair;
    }

    public void swapTair()
    {
        this.tair = !tair;
    }

    public boolean isTa9m()
    {
        return ta9m;
    }

    public void swapTa9m()
    {
        this.ta9m = !ta9m;
    }

    public boolean isSrad()
    {
        return srad;
    }

    public void swapSrad()
    {
        this.srad = !srad;
    }

    public boolean isWspd()
    {
        return wspd;
    }

    public void swapWspd()
    {
        this.wspd = !wspd;
    }

    public boolean isPres()
    {
        return pres;
    }

    public void swapPres()
    {
        this.pres = !pres;
    }

    public boolean isMin()
    {
        return min;
    }

    public void setMin()
    {
        this.min = true;
        this.max = false;
        this.avg = false;
    }

    public boolean isMax()
    {
        return max;
    }

    public void setMax()
    {
        this.max = true;
        this.min = false;
        this.avg = false;
    }

    public boolean isAvg()
    {
        return avg;
    }

    public void setAvg()
    {
        this.avg = true;
        this.max = false;
        this.min = false;
    }



    public MapData getDataSheet()
    {
        return dataSheet;
    }



    public void setDataSheet(MapData dataSheet) throws IOException
    {
        this.dataSheet = dataSheet;
        dataSheet.parseFile();
        System.out.println(dataSheet.toString());
    }



    public OutputTable showData(String fileName)
    {
       int r=0;
       if (tair) {++r;}
       if (ta9m) {++r;}
       if (srad) {++r;}
       if (wspd) {++r;}
       if (pres) {++r;}
       
       Object[][] plus= new Object[r][6];
       Object[] newRow = new Object[6];
       int k = 0;
       if (max)
       {
           if (tair) {
               plus[k] = dataSheet.getMax("TAIR");
               ++k;
           }
           if (ta9m) {
               plus[k] = dataSheet.getMax("TA9M");
               ++k;
           }
           if (srad) {
               plus[k] = dataSheet.getMax("SRAD");
               ++k;
           }
           if (wspd) {
               plus[k] = dataSheet.getMax("WSPD");
               ++k;
           }
           if (pres) {
               plus[k] = dataSheet.getMax("PRES");
               ++k;
           }
       }
       else if (min)
       {
           if (tair) {
               plus[k] = dataSheet.getMin("TAIR");
               ++k;
           }
           if (ta9m) {
               plus[k] = dataSheet.getMin("TA9M");
               ++k;
           }
           if (srad) {
               plus[k] = dataSheet.getMin("SRAD");
               ++k;
           }
           if (wspd) {
               plus[k] = dataSheet.getMin("WSPD");
               ++k;
           }
           if (pres) {
               plus[k] = dataSheet.getMin("PRES");
               ++k;
           }
       }
       else if (avg)
       {
           if (tair) {
               plus[k] = dataSheet.getAvg("TAIR");
               ++k;
           }
           if (ta9m) {
               plus[k] = dataSheet.getAvg("TA9M");
               ++k;
           }
           if (srad) {
               plus[k] = dataSheet.getAvg("SRAD");
               ++k;
           }
           if (wspd) {
               plus[k] = dataSheet.getAvg("WSPD");
               ++k;
           }
           if (pres) {
               plus[k] = dataSheet.getAvg("PRES");
               ++k;
           }
       }
       //TODO: add the info into the new []
       
     
    }



    private OutputTable OutputTable(OutputTable outputTable, Object[][] plus)
    {
        //TODO: combine into new [] and new table
        return null;
    }



   
}
