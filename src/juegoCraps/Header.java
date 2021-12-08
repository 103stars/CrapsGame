package juegoCraps;

import javax.swing.*;
import java.awt.*;

public class Header extends JLabel {
    public Header(String titulo, Color colorFondo){
        this.setText(titulo);
        this.setBackground(colorFondo);
        this.setForeground(Color.WHITE);
        this.setFont(new Font(Font.DIALOG, Font.ITALIC,20 ));
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setOpaque(true);

    }
}
