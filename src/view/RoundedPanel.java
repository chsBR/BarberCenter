/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author 2004s Essa classe é responsável pelo painel com bordas arredondadas
 * no view
 */
import javax.swing.*;
import java.awt.*;

public class RoundedPanel extends JPanel {

    private int radius = 30;
    private Color borderColor = new Color(217, 217, 217);
    private int borderThickness = 1;

    public RoundedPanel() {
        setOpaque(false);
    }

    public void setRadius(int radius) {
        this.radius = radius;
        repaint();
    }

    public void setBorderColor(Color color) {
        this.borderColor = color;
        repaint();
    }

    public void setBorderThickness(int thickness) {
        this.borderThickness = thickness;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON
        );

        int width = getWidth();
        int height = getHeight();

        // FUNDO
        g2.setColor(getBackground());
        g2.fillRoundRect(
            borderThickness,
            borderThickness,
            width - borderThickness * 2,
            height - borderThickness * 2,
            radius,
            radius
        );

        // BORDA
        g2.setColor(borderColor);
        g2.setStroke(new BasicStroke(borderThickness));
        g2.drawRoundRect(
            borderThickness / 2,
            borderThickness / 2,
            width - borderThickness,
            height - borderThickness,
            radius,
            radius
        );

        g2.dispose();
        super.paintComponent(g);
    }
}

/* Texto arredondado */
class RoundedTextField extends JTextField {

    private int radius = 20;
    private Color borderColor = new Color(217, 217, 217);
    private int borderThickness = 1;

    public RoundedTextField() {
        setOpaque(false);
        setBorder(null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON
        );

        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);

        super.paintComponent(g);
        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
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
    }
}

/* Senha arredondado */
class RoundedPasswordField extends JPasswordField {

    private int radius = 20;
    private Color borderColor = new Color(217, 217, 217);
    private int borderThickness = 1;

    public RoundedPasswordField() {
        setOpaque(false);
        setBorder(null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON
        );

        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);

        super.paintComponent(g);
        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
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
    }
}