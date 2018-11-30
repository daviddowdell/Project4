import java.awt.BorderLayout;
import java.awt.Color;
import java.io.File;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;

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
    /**The panel for the menu bar */
    JPanel topMenu = new JPanel();
    /**
     * The panel for the Heading
     */
    JPanel topHeading = new JPanel();
    /**
     * Panel for the left column of selectable data types
     */
    JPanel datatypes = new JPanel();
    
    /**
     * Panel for the second-from left column of statistic types
     */
    JPanel statTypes = new JPanel();
    
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
    /** Text fields for station, parameter, statistics, value, reporting stations, and date */
    JTable table;
    String[] tableHeaders = new String[] {"Station", "Parameter", "Statistics",
            "Value", "Reporting Stations", "Date"};
    
    
    
    
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
        
     // Configuring of the frame
        setSize(800, 400);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
}
