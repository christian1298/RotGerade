/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rotgerade;

import java.awt.Color;
import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.OverlayLayout;
import javax.swing.WindowConstants;

/**
 *
 * @author Christian
 */
public class Start 
{
  public Start()
  {
    JFrame fenster = new JFrame();
    fenster.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    
    Container c = fenster.getContentPane();
    c.setLayout(new OverlayLayout(c));
    c.setBackground(Color.WHITE);
    
    ZeichenFl[] area = new ZeichenFl[3];
    for(int i = 0; i < area.length; i++)
    {
      area[i] = new ZeichenFl((long)Math.pow(60, i)*1000, 0, i+1);
      area[i].setOpaque(false);
      c.add(area[i]);
      area[i].start();
    }
    
    fenster.setSize(800,600);
    fenster.setVisible(true);
  }

  public static void main(String[] args) 
  {
    new Start();
  }

}
