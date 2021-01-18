import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.Serializable;

public class Lienzo  extends Canvas implements Serializable{
    Lienzo puntero;
    
    double[] values={1,1,1}; //Vector x
    String[] titulos = {"Vector 1","Vector 2","Vector 3"};
    String title = "U2. Práctica 1. Componente graficador de barras"; 
    
    public Lienzo(){
        
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D)g;
        this.setBackground(new Color(25,25,25));
        
        g2.setColor(Color.white);
        g2.setFont(new Font("Arial",Font.PLAIN,25));
        g2.drawLine(700, 500, 32, 500);
        g2.drawLine(30, 500, 30, 50);
        
        if (values == null || values.length == 0) return;
        
        double minValue = 0;
        double maxValue = 0;
        
        for (int i = 0; i < values.length; i++) {
            if (minValue > values[i]){
                minValue = values[i]; 
            }
            if (maxValue < values[i]){
                maxValue = values[i];
            }
        }
        
        Dimension d = getSize();
        int clientWidth = d.width;
        int clientHeight = d.height;
        int barWidth = clientWidth / values.length; 
        
        Font titleFont = new Font("SansSerif", Font.BOLD, 20);
        FontMetrics titleFontMetrics = g.getFontMetrics(titleFont);
        Font labelFont = new Font("SansSerif", Font.PLAIN, 10);
        FontMetrics labelFontMetrics = g.getFontMetrics(labelFont);
        
        int titleWidth = titleFontMetrics.stringWidth(title);
        int y = titleFontMetrics.getAscent();
        int x = (clientWidth - titleWidth) / 2;
        
        g.setFont(titleFont);
        g.drawString(title, x, y);
        
        int top = titleFontMetrics.getHeight();
        int bottom = labelFontMetrics.getHeight();
        
        if (maxValue == minValue) return; 
    
        double scale = (clientHeight - top - bottom) / (maxValue - minValue);
        y = clientHeight - labelFontMetrics.getDescent();
        g.setFont(labelFont);
        
        for (int i = 0; i < values.length; i++) {
            int valueX = i * barWidth + 40;
            int valueY = 15;
            int height = (int) (values[i] * scale);
            if (values[i] >= 0){
                valueY += (int) ((maxValue - values[i]) * scale);
            }
            else {
                valueY += (int) (maxValue * scale);
                height = -height;
            }

            g.setColor(Color.gray);
            g.fillRect(valueX, valueY, barWidth-2, height);
            g.setColor(Color.white);
            
            g.drawRect(valueX, valueY, barWidth-2, height);
            int labelWidth = labelFontMetrics.stringWidth(titulos[i]);
            x = i * barWidth + (barWidth - labelWidth) / 2;
            g.drawString(titulos[i], x, y);
        }
    }
}
