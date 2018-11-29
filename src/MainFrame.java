import javax.swing.JFrame;

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
    
    
    public MainFrame()
    {
        super("Mesonet");
     // Configuring of the frame
        setSize(400, 400);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
}
