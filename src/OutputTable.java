
import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
/**
 * This class is the table to hold all the data and show it
 * @author dtd12
 *
 */
public class OutputTable extends JTable

{
    
    
    /** The headers for the table*/ 
    private static final String[] headers = new String[] {"Station", "Parameter", "Statistics",
            "Value", "Reporting Stations", "Date"};
    
    /** The DefaultTableModel for the JTable*/
    private DefaultTableModel model;
  
    /**Settings for all the selectable buttons */
    private boolean tair = false;
    private boolean ta9m = false;
    private boolean srad = false;
    private boolean wspd = false;
    private boolean pres = false;
    private boolean min = false;
    private boolean max = false;
    private boolean avg = false;
    
    /**The data, based on the chosen file*/
    private MapData dataSheet;

    /**
     * Default ID
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * Constructor for the table
     * @param a
     */
    public OutputTable (DefaultTableModel a)
    {
        super(a);
        model = a;
        
        //ALl the size-formatting and header-labeling
        this.setPreferredSize(new Dimension(600,400));
        ((DefaultTableCellRenderer)this.getTableHeader().getDefaultRenderer())
        .setHorizontalAlignment(JLabel.LEFT);
        setShowGrid(false);
        setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        a.addColumn(headers[0]);
        a.addColumn(headers[1]);
        a.addColumn(headers[2]);
        a.addColumn(headers[3]);
        a.addColumn(headers[4]);
        a.addColumn(headers[5]);
        getColumnModel().getColumn(0).setPreferredWidth(45);
        getColumnModel().getColumn(1).setPreferredWidth(70);
        getColumnModel().getColumn(2).setPreferredWidth(80);
        getColumnModel().getColumn(3).setPreferredWidth(60);
        getColumnModel().getColumn(4).setPreferredWidth(120);
        getColumnModel().getColumn(5).setPreferredWidth(140);
        setFillsViewportHeight(true);
     
    }
    
   

    /**
     * Getter for tair
     * @return boolean
     */
    public boolean isTair()
    {
        return tair;
    }

    /**
     * Represents the button-press, setting to true or false.
     */
    public void swapTair()
    {
        this.tair = !tair;
    }

    public boolean isTa9m()
    {
        return ta9m;
    }

    /**
     * Represents the button-press, setting to true or false.
     */
    public void swapTa9m()
    {
        this.ta9m = !ta9m;
    }

    public boolean isSrad()
    {
        return srad;
    }

    /**
     * Represents the button-press, setting to true or false.
     */
    public void swapSrad()
    {
        this.srad = !srad;
    }

    public boolean isWspd()
    {
        return wspd;
    }

    /**
     * Represents the button-press, setting to true or false.
     */
    public void swapWspd()
    {
        this.wspd = !wspd;
       
    }

    public boolean isPres()
    {
        return pres;
    }

    /**
     * Represents the button-press, setting to true or false.
     */
    public void swapPres()
    {
        this.pres = !pres;
    }

    public boolean isMin()
    {
        return min;
    }

    /**
     * Represents the button-press, setting to true or false.
     */
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

    /**
     * Represents the button-press, setting to true or false.
     */
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

    /**
     * Represents the button-press, setting to true or false.
     */
    public void setAvg()
    {
        this.avg = true;
        this.max = false;
        this.min = false;
    }



    /**
     * Getter method for the datasheet
     * @return
     */
    public MapData getDataSheet()
    {
        return dataSheet;
    }



    /**
     * This method sets the datasheet. It is called when 
     * a user selects a file
     * @param dataSheet
     * @throws IOException
     */
    public void setDataSheet(MapData dataSheet) throws IOException
    {
        this.dataSheet = dataSheet;
        dataSheet.parseFile();
        System.out.println(dataSheet.toString());
    }


    /**
     * THis method makes a new Object[][] to hold the old and new data
     * and then returns an OutputTable with all of it
     * @return
     */
    public Object[][] showData()
    {
       int r=0;
       
       if (tair) {++r;}
       if (ta9m) {++r;}
       if (srad) {++r;}
       if (wspd) {++r;}
       if (pres) {++r;}
       
       
       Object[][] plus= new Object[r][6];
     
       int k = 0;
       if (max)
       {
           if (tair) {
               plus[k] = dataSheet.getMax("TAIR");
               model.addRow(plus[k]);
               ++k;
           }
           if (ta9m) {
               plus[k] = dataSheet.getMax("TA9M");
               model.addRow(plus[k]);               
               ++k;
           }
           if (srad) {
               plus[k] = dataSheet.getMax("SRAD");
               model.addRow(plus[k]);               
               ++k;
           }
           if (wspd) {
               plus[k] = dataSheet.getMax("WSPD");
               model.addRow(plus[k]);               
               ++k;
           }
           if (pres) {
               plus[k] = dataSheet.getMax("PRES");
               model.addRow(plus[k]);               
               ++k;
           }
       }
       else if (min)
       {
           if (tair) {
               plus[k] = dataSheet.getMin("TAIR");
               model.addRow(plus[k]);               
               ++k;
           }
           if (ta9m) {
               plus[k] = dataSheet.getMin("TA9M");
               model.addRow(plus[k]);               
               ++k;
           }
           if (srad) {
               plus[k] = dataSheet.getMin("SRAD");
               model.addRow(plus[k]);               
               ++k;
           }
           if (wspd) {
               plus[k] = dataSheet.getMin("WSPD");
               model.addRow(plus[k]);               
               ++k;
           }
           if (pres) {
               plus[k] = dataSheet.getMin("PRES");
               model.addRow(plus[k]);               
               ++k;
           }
       }
       else if (avg)
       {
           if (tair) {
               plus[k] = dataSheet.getAvg("TAIR");
               model.addRow(plus[k]);               
               ++k;
           }
           if (ta9m) {
               plus[k] = dataSheet.getAvg("TA9M");
               model.addRow(plus[k]);               
               ++k;
           }
           if (srad) {
               plus[k] = dataSheet.getAvg("SRAD");
               model.addRow(plus[k]);               
               ++k;
           }
           if (wspd) {
               plus[k] = dataSheet.getAvg("WSPD");
               model.addRow(plus[k]);               
               ++k;
           }
           if (pres) {
               plus[k] = dataSheet.getAvg("PRES");
               model.addRow(plus[k]);               
               ++k;
           }
          
           
       }
       
       return plus;
    }



  

   
}
