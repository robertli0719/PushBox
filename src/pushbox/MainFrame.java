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
public class MainFrame extends JFrame implements ActionListener
{
    MainFrame()
    {
        setSize(600,400);
        setLocation(400,100);
        setTitle("推箱子计算器");
        addButton("新建");
        tb.addSeparator();
        addButton("+行");
        addButton("-行");
        addButton("+列");
        addButton("-列");
        tb.addSeparator();
        tb.add(new JLabel("工具："));
        addTool("擦");
        addTool("墙");
        addTool("箱");
        addTool("位");
        addTool("人");
        tb.addSeparator();
        addButton("计算");
        addButton("计算2");

        add(tb,BorderLayout.NORTH);
        add(panel);

    }
    private void addTool(String name)
    {
        JRadioButton rBtn=new JRadioButton(name);
        toolBG.add(rBtn);
        tb.add(rBtn);
        rBtn.addActionListener(this);        
    }
    private void addButton(String name)
    {
        JButton btn=new JButton(name);
        btn.addActionListener(this);
        tb.add(btn);
    }
    public void actionPerformed(ActionEvent e)
    {
        String comd=e.getActionCommand();
        if(comd.equals("计算"))        
            panel.count();
        else if(comd.equals("计算2"))        
            panel.actionCount();        
        else if(comd.equals("擦"))
            panel.setToolName("擦");
        else if(comd.equals("墙"))
            panel.setToolName("墙");
        else if(comd.equals("箱"))
            panel.setToolName("箱");
        else if(comd.equals("位"))
            panel.setToolName("位");
        else if(comd.equals("人"))
            panel.setToolName("人");
        else if(comd.equals("+行"))
            panel.addY();
        else if(comd.equals("-行"))
            panel.reduceY();
        else if(comd.equals("+列"))
            panel.addX();
        else if(comd.equals("-列"))
            panel.reduceX();
        else if(comd.equals("新建"))
            panel.newPan();
       


    }
    private JToolBar tb=new JToolBar();
    private ButtonGroup toolBG=new ButtonGroup();
    private ShowPanel panel=new ShowPanel();
}
