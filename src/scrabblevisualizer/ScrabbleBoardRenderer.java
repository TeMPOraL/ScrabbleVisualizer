package scrabblevisualizer;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.Hashtable;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author TeMPOraL
 */
public class ScrabbleBoardRenderer extends DefaultTableCellRenderer {
    static Color fieldColor = new Color(195, 176, 161);
    static Color word3xColor = new Color(229, 71, 94);
    static Color word2xColor = new Color(234, 158, 171);
    static Color letter3xColor = new Color(27, 149, 224);
    static Color letter2xColor = new Color(180, 207, 228);
    
    static Color[][] field;
    
    static Font scrabbleFont = new Font ("Monospaced", Font.BOLD, 16);
    
    ScrabbleBoardRenderer() {
        field = new Color[15][15];
        for(int i = 0 ; i < 15 ; ++i) {
            field[i] = new Color[15];
            for(int j = 0 ; j < 15 ; ++j) {
                field[i][j] = fieldColor;
            }
        }
        ScrabbleBoardRenderer.resetBonuses();
    }
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
    {
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        c.setBackground(field[row][column]);
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setFont(scrabbleFont);

        return c;
    }
    
    public static void resetBonuses() {
        //3x word
        field[0][0] = word3xColor;
        field[0][14] = word3xColor;
        field[14][0] = word3xColor;
        field[14][14] = word3xColor;
        
        field[0][7] = word3xColor;
        field[7][0] = word3xColor;
        field[14][7] = word3xColor;
        field[7][14] = word3xColor;
        
        //2x word
        for(int i = 1 ; i < 14 ; ++i) {
            field[i][i] = word2xColor;
            field[14-i][i] = word2xColor;
        }
        
        //3x letter
        field[5][1] = letter3xColor;
        field[5][5] = letter3xColor;
        field[5][9] = letter3xColor;
        field[5][13] = letter3xColor;
        
        field[9][1] = letter3xColor;
        field[9][5] = letter3xColor;
        field[9][9] = letter3xColor;
        field[9][13] = letter3xColor;
        
        field[1][5] = letter3xColor;
        field[1][9] = letter3xColor;
        field[13][5] = letter3xColor;
        field[13][9] = letter3xColor;
        
        //2x letter
        field[0][3] = letter2xColor;
        field[0][11] = letter2xColor;
        
        field[2][6] = letter2xColor;
        field[2][8] = letter2xColor;
        
        field[3][0] = letter2xColor;
        field[3][7] = letter2xColor;
        field[3][14] = letter2xColor;
        
        field[6][2] = letter2xColor;
        field[6][6] = letter2xColor;
        field[6][8] = letter2xColor;
        field[6][12] = letter2xColor;
        
        field[7][3] = letter2xColor;
        field[7][11] = letter2xColor;
        
        field[8][2] = letter2xColor;
        field[8][6] = letter2xColor;
        field[8][8] = letter2xColor;
        field[8][12] = letter2xColor;
        
        field[11][0] = letter2xColor;
        field[11][7] = letter2xColor;
        field[11][14] = letter2xColor;
        
        field[12][6] = letter2xColor;
        field[12][8] = letter2xColor;
        
        field[14][3] = letter2xColor;
        field[14][11] = letter2xColor;
    }
}
