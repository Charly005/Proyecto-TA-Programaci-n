package Style;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JButton;

public class Boton extends JButton{
    public Boton(String text){
        super(text);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
    }

    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        int size = Math.min(getWidth(), getHeight());
        int x = (getWidth()-size)/2;
        int y = (getHeight()-size)/2;
        g2.setColor(getModel().isPressed() ? Color.DARK_GRAY : getBackground());
        g2.fillOval(x, y, size, size);

        FontMetrics fm = g2.getFontMetrics();
        int textx = (getWidth()-fm.stringWidth(getText()))/2;
        int texty = (getHeight() + fm.getAscent())/2-2;
        g2.setColor(getForeground());
        g2.drawString(getText(), textx, texty);

        g2.dispose();
    }
}