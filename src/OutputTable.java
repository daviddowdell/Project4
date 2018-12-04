import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class OutputTable extends JTable

{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    public OutputTable (Object[][] data, String[] headers)
    {
        super(data, headers);
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
        
    }

}
