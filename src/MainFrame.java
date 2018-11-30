import java.awt.BorderLayout;
import java.awt.Color;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.border.TitledBorder;

/**
 * This is the main, entire frame of the gui
 * @author David Dowdell
 *
 */
public class MainFrame extends JFrame
{

    /**
     * Default serial ID
     */
    private static final long serialVersionUID = 1L;
    /**The panel for parameter and statistics selections */
    JPanel leftPanel = new JPanel();
    /**
     * The panel for the Heading
     */
    JPanel topHeading = new JPanel();
    /**
     * Panel for the left column of selectable data types
     */
    JPanel datatypes = new JPanel();
    /**Title for the datatypes border*/
    TitledBorder dtBorder = new TitledBorder("Parameter");
    
    /**
     * Panel for the second-from left column of statistic types
     */
    JPanel statTypes = new JPanel();
    /**Title for the statTypes border*/
    TitledBorder stBorder = new TitledBorder("Statistics");
    
    /**
     * Panel for the text box
     */
    JPanel textBox = new JPanel();
    /** Group of Buttons for selecting statistic type */
    ButtonGroup stats = new ButtonGroup();
    /** The button to run calculations */
    JButton calc = new JButton();
    /** The header for the text output */
    JLabel columns = new JLabel();
    /** Header for the whole thing */
    JLabel mainHeader = new JLabel();
    /** Table for station, parameter, statistics, value, reporting stations, and date */
    JTable table;
    String[] tableHeaders = new String[] {"Station", "Parameter", "Statistics",
            "Value", "Reporting Stations", "Date"};
    /** Selectable buttons for Parameter */
    JCheckBox wspd = new JCheckBox("WSPD");
    JCheckBox pres = new JCheckBox("PRES");
    JCheckBox srad = new JCheckBox("SRAD");
    JCheckBox ta9m = new JCheckBox("TA9M");
    JCheckBox tair = new JCheckBox("TAIR");
    
    /**RadioButtons for Statistics*/
    JRadioButton max = new JRadioButton("MAXIMUM");
    JRadioButton min = new JRadioButton("MINIMUM");
    JRadioButton avg = new JRadioButton("AVERAGE");
    
    
    
    
    /**
     * Inner class for the menu bar
     */
    class FileMenuBar extends JMenuBar
    {

        /**
         * Here is the default ID
         */
        private static final long serialVersionUID = 1L;
        
        JMenu fileMenu = new JMenu("File");
        
        JToolBar toolBar = new JToolBar(JToolBar.VERTICAL);
        
        
        protected FileMenuBar()
        {
            super();
            File dataFolder = new File("C:\\Users\\dtd12\\eclipse-workspace\\Project4\\data");
            File[] datafiles = dataFolder.listFiles();
            
         
            JMenuItem exit = new JMenuItem("Exit");
            JMenuItem choose = new JMenuItem("Open Data File");
        
            fileMenu.add(choose);
            fileMenu.add(exit);
            
            this.add(fileMenu);
            
        }
        
    }
    
    
    
    public MainFrame()
    {
        
        super("Mesonet");
        
        // The Setup Section
 
        //Set up file menu
        this.setJMenuBar(new FileMenuBar());
        
        //Set up for the header text
        mainHeader.setHorizontalAlignment(JTextField.CENTER);
        mainHeader.setText("Mesonet-we don't set records, we record them!");
        topHeading.add(mainHeader);
        topHeading.setBackground(Color.LIGHT_GRAY);
        add(topHeading, BorderLayout.NORTH);
        
        //Set up output text
        String[][] outputData = {{"","","","","",""}};
        table = new JTable(outputData, tableHeaders);
        JScrollPane sp = new JScrollPane(table);
        table.setShowGrid(false);
        
        textBox.add(sp);
       
        add(textBox, BorderLayout.EAST);
        
        
        //Set up the Parameter selection options
        datatypes.setLayout(new BoxLayout(datatypes, BoxLayout.Y_AXIS));
        datatypes.setBorder(dtBorder);
        datatypes.add(tair);
        datatypes.add(ta9m);
        datatypes.add(srad);
        datatypes.add(wspd);
        datatypes.add(pres);
        add(datatypes, BorderLayout.WEST);
        
        //Set up the Statistic selection options
        statTypes.setLayout(new BoxLayout(statTypes, BoxLayout.Y_AXIS));
        statTypes.setBorder(stBorder);
        stats.add(max);
        stats.add(min);
        stats.add(avg);
        statTypes.add(min);
        statTypes.add(avg);
        statTypes.add(max);
        add(statTypes, BorderLayout.CENTER);
        
        
        
     // Configuring of the frame
        setSize(800, 400);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
}
