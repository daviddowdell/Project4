import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.IOException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

/**
 * This is the main, entire frame of the gui
 * @author David Dowdell
 *
 */
public class MainFrame extends JFrame implements ActionListener
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
    
    /**String for the chosen filename*/
    String fileName;
    
    /**Title for the datatypes border*/
    TitledBorder dtBorder = new TitledBorder("Parameter");
    
    /**Panel for the lower buttons*/
    JPanel options = new JPanel();
    
    /**
     * Panel for the second-from left column of statistic types
     */
    JPanel statTypes = new JPanel();
    /**Title for the statTypes border*/
    TitledBorder stBorder = new TitledBorder("Statistics");
    
    
    /** Group of Buttons for selecting statistic type */
    ButtonGroup stats = new ButtonGroup();
    /** The button to run calculations */
    JButton calc = new JButton("Calculate");
    /** The button to exit*/
    JButton exit = new JButton("Exit");
    /** The header for the text output */
    JLabel columns = new JLabel();
    /** Header for the whole thing */
    JLabel mainHeader = new JLabel();
    /** Table for station, parameter, statistics, value, reporting stations, and date */
    OutputTable table;
    JScrollPane sp;
    
    /** Table model for creation of the table*/
    DefaultTableModel dtm = new DefaultTableModel();
            
    /** JCheckBoxes for Parameter */
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
           
 
         
            JMenuItem Exit = new JMenuItem("Exit");
            JMenuItem choose = new JMenuItem("Open Data File");
        
            fileMenu.add(choose);
            fileMenu.add(Exit);
            //Exit-option
            this.add(fileMenu);
            
            Exit.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Exit the program
                    Exit();
                } 
            });
            
            //Open-file-option
            choose.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    //Open the data folders
                   JFileChooser fileChooser = new JFileChooser("C:\\Users\\dtd12\\eclipse-workspace\\Project4\\data"); 
                   int k = fileChooser.showOpenDialog(null);
                   if (k == JFileChooser.APPROVE_OPTION) {
                       fileName = fileChooser.getSelectedFile().getName();
                       try
                    {
                        table.setDataSheet(new MapData(fileName, "data"));
                    } catch (IOException e1)
                    {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                      
                   }
                }
            });
            
        }
        
    }
    
    
    /**
     * Constructor for the entire class
     */
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
        table = new OutputTable(dtm);
        sp = new JScrollPane(table);
        sp.setPreferredSize(new Dimension(600,400));
        sp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        add(sp, BorderLayout.EAST);
        
        
       
        
        //Set up the Parameter selection options
        datatypes.setLayout(new BoxLayout(datatypes, BoxLayout.Y_AXIS));
        datatypes.setBorder(dtBorder);
        tair.setBackground(Color.gray);
        ta9m.setBackground(Color.gray);
        srad.setBackground(Color.gray);
        wspd.setBackground(Color.gray);
        pres.setBackground(Color.gray);
        datatypes.add(Box.createVerticalGlue());
        datatypes.add(tair);
        datatypes.add(Box.createVerticalGlue());
        datatypes.add(Box.createVerticalGlue());
        datatypes.add(ta9m);
        datatypes.add(Box.createVerticalGlue());
        datatypes.add(Box.createVerticalGlue());
        datatypes.add(srad);
        datatypes.add(Box.createVerticalGlue());
        datatypes.add(Box.createVerticalGlue());
        datatypes.add(wspd);
        datatypes.add(Box.createVerticalGlue());
        datatypes.add(Box.createVerticalGlue());
        datatypes.add(pres);
        datatypes.add(Box.createVerticalGlue());
        add(datatypes, BorderLayout.WEST);
        datatypes.setPreferredSize(new Dimension(80,50));
        datatypes.setBackground(Color.gray);
        
        //Set up the Statistic selection options
        statTypes.setLayout(new BoxLayout(statTypes, BoxLayout.Y_AXIS));
        statTypes.setBorder(stBorder);
        stats.add(max);
        stats.add(min);
        stats.add(avg);
        min.setBackground(Color.gray);
        max.setBackground(Color.gray);
        avg.setBackground(Color.gray);
        statTypes.add(Box.createVerticalGlue());
        statTypes.add(min);
        statTypes.add(Box.createVerticalGlue());
        statTypes.add(Box.createVerticalGlue());
        statTypes.add(avg);
        statTypes.add(Box.createVerticalGlue());
        statTypes.add(Box.createVerticalGlue());
        statTypes.add(max);
        statTypes.add(Box.createVerticalGlue());
        add(statTypes, BorderLayout.CENTER);
        statTypes.setBackground(Color.gray);
        statTypes.setPreferredSize(new Dimension(50,50));
        
        
        //Set up the lower buttons: exit and calculate
        options.add(calc);
        options.add(exit);
        add(options, BorderLayout.SOUTH);
        
        
        
     // Configuring of the frame
        setSize(800, 400);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Exit JButton:
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Exit();
            } 
        });
        //Calculate JButton:
        calc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculate();
            }
        });
        //TAIR JButton:
        tair.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                table.swapTair();
               
            } 
        });
      //TA9M JButton:
        ta9m.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                table.swapTa9m();
               
            } 
        });
      //SRAD JButton:
        srad.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                table.swapSrad();
               
            } 
        });
      //WSPD JButton:
        wspd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                table.swapWspd();
               
            } 
        });
      //PRES JButton:
        pres.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                table.swapPres();
               
            } 
        });
        //MAXIMUM JRadioButton
        max.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                table.setMax();
            }
        });
        //MINIMUM JRadioButton
        min.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                table.setMin();
            }
        });
        //Average JRadioButton
        avg.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                table.setAvg();
            }
        });
       
    }


    /**This Method closes the window*/
    protected void Exit()
    {
        dispose(); 
    }

    /**This method calculates the data and displays it*/
    protected void calculate()
    {
        table.showData();
    }


    @Override
    public void actionPerformed(ActionEvent e)
    {
        // This is required to exist, but is not used.
        
    }
   
    
    
     
    
}
