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
    /** Textbox for output */
    JTextField output = new JTextField(5);
    
    
    
    /**
     * Inner class for the menu bar
     */
    protected class FileMenuBar extends JMenuBar
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
        //First set up the border Layout:
        super("Mesonet");
        
        // The Setup Section
        mainHeader.setHorizontalAlignment(JTextField.CENTER);
  
        //topMenu.add(new FileMenuBar());
       // add(topMenu, BorderLayout.NORTH);
        this.setJMenuBar(new FileMenuBar());
        
        //Set up for the header text
        mainHeader.setText("Mesonet-we don't set records, we record them!");
        topHeading.add(mainHeader);
        topHeading.setBackground(Color.LIGHT_GRAY);
        add(topHeading, BorderLayout.NORTH);
        
        
     // Configuring of the frame
        setSize(400, 400);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
}
