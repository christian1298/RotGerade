/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rotgerade;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;
import javax.swing.JComponent;
import rotgerade.util.OhmLogger;

/**
 *
 * @author Christian
 */
public class ZeichenFl extends JComponent implements Runnable
{
  private static Logger lg = OhmLogger.getLogger();
  private final float DICKE = 4;
  private AffineTransform at;
  private Line2D.Float line;
  private float radius;
  private float angle;
  private int w;
  private int h;
  private float length;
  
  
  //private Thread thd;
  private ExecutorService eService;
  private long schlafzeit;
  
  public ZeichenFl(long schlafzeit, float time, float length)
  {
    this.schlafzeit = schlafzeit;
    this.length = length;
    this.at = new AffineTransform();
    this.line = new Line2D.Float(0,0 ,100,0);
    eService = Executors.newSingleThreadExecutor();
    w = this.getWidth();
    h = this.getHeight();
    angle = (time + 44)*6;
  }
  
  public void start()
  {
    eService.submit(this);
    
  }
  
  @Override
  public void paintComponent(Graphics g)
  {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D)g;
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g2.setTransform(this.at);
    
    w = this.getWidth() - 1;
    h = this.getHeight() -1;
    radius = -DICKE/2 + Math.min(w, h) / 10;
    line.setLine(0,0,radius * length,0);
    
    //at.setToRotation(Math.toRadians(angle));
    g2.rotate(Math.toRadians(angle));
    at.setToTranslation(w/2, h/2);
    
    //this.at.rotate(Math.toRadians(6));
    g2.setStroke(new BasicStroke(DICKE));
    g2.setColor(Color.RED);

    g2.draw(this.line);
//    at.setToIdentity();
  }

  @Override
  public void run()
  {
    while(true)
    {
      this.repaint();
      angle += 6;
      angle %= 360;
      
    
    try
      {
        Thread.sleep(schlafzeit);
      }
      catch (InterruptedException ex)
      {
        lg.warning(ex.toString());
      }
    }
    
  }
}
