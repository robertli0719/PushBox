/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pushbox;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 *
 * @author 李理
 */
public class ShowPanel extends JPanel implements java.awt.event.MouseListener
{
    public ShowPanel()
    {       
        addMouseListener(this);
        newPan();
    }
    public void setToolName(String name){toolName=name;}
    public void newPan()
    {
        int arr[][]=new int[6][6];
        pan=new Pan(arr);
        pan.setPerson(0,0);
        repaint();
    }
    public void actionCount()
    {
        ActionPushBoxer pb=new ActionPushBoxer();
        ActionPan ap=new ActionPan(pan);
        String str=pb.push(ap);
        javax.swing.JOptionPane.showMessageDialog(this,str);
    }
    public void count()
    {
        PushBoxer pb=new PushBoxer();
        String str=pb.push(pan);
        javax.swing.JOptionPane.showMessageDialog(this,str);
    }
    public void setPanel(Pan pan){this.pan=pan;}
    public void addX(){pan.addX();repaint();repaint();}
    public void reduceX(){pan.reduceX();repaint();repaint();}
    public void addY(){pan.addY();repaint();repaint();}
    public void reduceY(){pan.reduceY();repaint();repaint();}
    public void mouseExited(MouseEvent e){mouseHasPress=false;}
    public void mouseEntered(MouseEvent e){mouseHasPress=false;}
    public void mouseReleased(MouseEvent e)
    {
        if(mouseHasPress==false)return;
        mouseHasPress=false;
        if(toolName==null)return;

        if(mfx>e.getX()){mtx=mfx;mfx=e.getX();}
        else mtx=e.getX();
        if(mfy>e.getY()){mty=mfy;mfy=e.getY();}
        else mty=e.getY();

        int fx=mfx/getBoxSize();
        int tx=mtx/getBoxSize();
        int fy=mfy/getBoxSize();
        int ty=mty/getBoxSize();

        if(toolName.equals("人"))
            pan.setPerson(fx, fy);
        else if(toolName.equals("墙"))
        {
            for(int x=fx;x<=tx;x++)
                for(int y=fy;y<=ty;y++)
                    pan.setWall(x,y);
        }
        else if(toolName.equals("位"))
        {
            for(int x=fx;x<=tx;x++)
                for(int y=fy;y<=ty;y++)
                    pan.setRightBox(x,y);
        }
        else if(toolName.equals("擦"))
        {
            for(int x=fx;x<=tx;x++)
                for(int y=fy;y<=ty;y++)
                    pan.clear(x,y);
        }
        else if(toolName.equals("箱"))
        {
            for(int x=fx;x<=tx;x++)
                for(int y=fy;y<=ty;y++)
                    pan.addBall(x,y);
        }
        repaint();
    }
    public void mousePressed(MouseEvent e)
    {
        mouseHasPress=true;
        mfx=e.getX();mfy=e.getY();

    }
    public void mouseClicked(MouseEvent e){}

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2=(Graphics2D)g;
        for(int x=0;x<pan.getX();x++)
            for(int y=0;y<pan.getY();y++)
            {
                if(pan.isWall(x,y))
                {
                    g2.setColor(Color.BLACK);
                    g2.fillRect(x*getBoxSize(),y*getBoxSize(),getBoxSize(),getBoxSize());
                }else if(pan.isRightBox(x,y))
                {
                    g2.setColor(Color.BLUE);
                    g2.fillRect(x*getBoxSize(),y*getBoxSize(),getBoxSize(),getBoxSize());
                }else
                {
                    g2.setColor(Color.BLACK);
                    g2.drawRect(x*getBoxSize(),y*getBoxSize(),getBoxSize(),getBoxSize());
                }
                if(pan.haveBall(x,y))
                {
                    g2.setColor(Color.GREEN);
                    g2.fillRect(x*getBoxSize()+getGridSize(),y*getBoxSize()+getGridSize(),getBoxSize()-getGridSize()*2,getBoxSize()-getGridSize()*2);
                }
                g2.setColor(Color.PINK);
                g2.fill(new java.awt.geom.Ellipse2D.Double(pan.getPx()*getBoxSize()+getGridSize(),pan.getPy()*getBoxSize()+getGridSize(),getBoxSize()-getGridSize()*2,getBoxSize()-getGridSize()*2));
            }        
    }
    private int getBoxSize()
    {
        int n=getWidth()/pan.getX();
        if(n*pan.getY()>getHeight())
            n=getHeight()/pan.getY();
        
        return n;
    }
    private int getGridSize()
    {
        return (int)(getBoxSize()*0.2);
    }
    private boolean mouseHasPress=false;
    private int mfx;
    private int mfy;
    private int mtx;
    private int mty;
    private Pan pan;
    private String toolName;
}
