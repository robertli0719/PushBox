/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pushbox;
import java.util.ArrayList;

/**
 *
 * @author 李理
 */
public class ActionPan implements Cloneable
{
    public ActionPan(Pan pan)
    {
        arr=pan.getArr();
        int ar[][]=Action.getSpaceNumArea(arr);
        peopleArea=ar[pan.getPx()][pan.getPy()];        
    }
    public boolean bePass()
    {
        int i=0;
        for(int x=0;x<arr.length;x++)
            for(int y=0;y<arr[0].length;y++)
                if(arr[x][y]==4)i++;
                else if(arr[x][y]==2)i--;
        return i!=0;
    }
    public boolean complet()
    {
        for(int x=0;x<arr.length;x++)
            for(int y=0;y<arr[0].length;y++)
                if(arr[x][y]==4)return false;
        return true;
    }
    public String toString()
    {
        String str="";
        for(int y=0;y<arr[0].length;y++)
            for(int x=0;x<arr.length;x++)
                if((arr[x][y]&4)!=0)
                str+=(x)+""+(y);
        str+=(peopleArea);
        return str;
    }
    public ArrayList<Action> getAction()
    {
        int n=1;
        ArrayList<Action> al=new ArrayList<Action>();
        for(int y=0;y<arr[0].length;y++)
            for(int x=0;x<arr.length;x++)
                if((arr[x][y]&4)!=0)
                    addBoxAction(al,x,y,n++);
        return al;
    }
    
    private void doit_reduce(Action atn)
    {      
        int n=1;
        for(int y=0;y<arr[0].length;y++)
            for(int x=0;x<arr.length;x++)                         
                if((arr[x][y]&4)!=0)
                {
                    if(n<atn.getBox())
                        n++;
                    else
                    {
                        arr[x][y]-=4;
                        return;
                    }
                }            
    }
    public boolean canCut()
    {
        final int X=arr.length;
        final int Y=arr[0].length;
        for(int x=0;x<X-1;x++)
        {
            if(not0_2(arr[x][0]) && not0_2(arr[x+1][0]) && (arr[x][0]==4||arr[x+1][0]==4))
            return true;
            if(not0_2(arr[x][Y-1]) && not0_2(arr[x+1][Y-1]) && (arr[x][Y-1]==4||arr[x+1][Y-1]==4))
            return true;
        }
        for(int y=0;y<Y-1;y++)
        {
            if(not0_2(arr[0][y]) && not0_2(arr[0][y+1]) && (arr[0][y]==4||arr[0][y+1]==4))
            return true;
            if(not0_2(arr[X-1][y]) && not0_2(arr[X-1][y+1]) && (arr[X-1][y]==4||arr[X-1][y+1]==4))
            return true;
        }
        for(int x=0;x<X-1;x++)
            for(int y=0;y<Y-1;y++)
            if(not0_2(arr[x][y])&&not0_2(arr[x+1][y])&&not0_2(arr[x][y+1])&&not0_2(arr[x+1][y+1])&&
            (arr[x][y]==4||arr[x+1][y]==4||arr[x][y+1]==4||arr[x+1][y+1]==4))
                return true;

        return false;
    }
    private static boolean not0_2(int n){return n!=0 && n!=2;}
    public void doit(Action atn)
    {
        his+="\n b:"+atn.getBox();
        his+="x:"+atn.getX();
        his+="y:"+atn.getY();
        his+="p:"+atn.getP();        
        
        peopleArea=atn.getP();
        doit_reduce(atn);
        arr[atn.getX()][atn.getY()]+=4;
    }
    public ActionPan clone()
    {
        try{
            ActionPan rtnpan=(ActionPan)super.clone();
            rtnpan.arr=new int[arr.length][arr[0].length];
            for(int x=0;x<arr.length;x++)
                for(int y=0;y<arr[0].length;y++)
                    rtnpan.arr[x][y]=arr[x][y];
        return rtnpan;
        }catch(CloneNotSupportedException e)
        {
            System.err.println(e);
        }
        return null;
    }
    public String getHis(){return his;}
    private char getChar(int i){return (char)i;}
    private void addBoxAction(ArrayList<Action> al,int x,int y,int n)
    {        
        ActionStatistics as=new ActionStatistics(x,y,arr,peopleArea,n);
        as.addToActionList(al);        
    }
    
    private int peopleArea;
    private int arr[][];
    private String his="";
}
