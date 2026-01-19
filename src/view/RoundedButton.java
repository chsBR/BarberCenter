/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;
import javax.swing.*;
import java.awt.*;
/**
 *
 * @author 2004s
 * Botão arredondado
 */
public class RoundedButton extends JButton {
    private int radius = 20;
    private Color borderColor = new Color(160, 160, 160);
    private int borderThickness = 1;
    
    

    public RoundedButton() {
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setOpaque(false);
    }
    
    public void setBorderColor(Color color) {
        this.borderColor = color;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON
        );

        g2.setColor(getBackground());
        g2.fillRoundRect(
            borderThickness,
            borderThickness,
            getWidth() - borderThickness * 2,
            getHeight() - borderThickness * 2,
            radius,
            radius
        );

        g2.setColor(borderColor);
        g2.setStroke(new BasicStroke(borderThickness));
        g2.drawRoundRect(
            borderThickness / 2,
            borderThickness / 2,
            getWidth() - borderThickness,
            getHeight() - borderThickness,
            radius,
            radius
        );

        g2.dispose();
        super.paintComponent(g);
    }
}