/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pushbox;
import java.util.BitSet;
/**
 *
 * @author 李理
 */
public class Pan implements Cloneable
{
    public Pan(int Arr[][])
    {
        arr=Arr.clone();        
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
    public void addX()
    {
        int newarr[][]=new int[arr.length+1][arr[0].length];
        for(int x=0;x<arr.length;x++)
            for(int y=0;y<arr[0].length;y++)
                newarr[x][y]=arr[x][y];
        arr=newarr;
    }
    public void reduceX()
    {
        if(arr.length-1<3)return;
        int newarr[][]=new int[arr.length-1][arr[0].length];
        for(int x=0;x<newarr.length;x++)
            for(int y=0;y<newarr[0].length;y++)
                newarr[x][y]=arr[x][y];
        arr=newarr;
    }
    public void addY()
    {
        int newarr[][]=new int[arr.length][arr[0].length+1];
        for(int x=0;x<arr.length;x++)
            for(int y=0;y<arr[0].length;y++)
                newarr[x][y]=arr[x][y];
        arr=newarr;
    }
    public void reduceY()
    {
        if(arr[0].length-1<3)return;
        int newarr[][]=new int[arr.length][arr[0].length-1];
        for(int x=0;x<newarr.length;x++)
            for(int y=0;y<newarr[0].length;y++)
                newarr[x][y]=arr[x][y];
        arr=newarr;
    }
    public void clear(int x,int y)
    {
        arr[x][y]=0;
    }
    public void addBall(int x,int y)
    {
        if(px==x && py==y)return;
        if(arr[x][y]==0 || arr[x][y]==2)
        arr[x][y]+=4;
    }
    public int getX(){return arr.length;}
    public int getY(){return arr[0].length;}
    public int getPx(){return px;}
    public int getPy(){return py;}
    public void setPerson(int x,int y)
    {
        if(arr[x][y]==1 || arr[x][y]>2)return;
        px=(byte)x;py=(byte)y;
    }
    public void setWall(int x,int y){if(px==x&&py==y)return;arr[x][y]=1;}
    public void setRightBox(int x,int y){arr[x][y]=2;}
    public boolean haveBall(int x,int y){return (arr[x][y]&4)!=0;}
    public boolean isWall(int x,int y){return arr[x][y]==1;}
    public boolean isRightBox(int x,int y){return (arr[x][y]&2)!=0;}
    public boolean complet()
    {
        for(int x=0;x<arr.length;x++)
            for(int y=0;y<arr[0].length;y++)
                if(arr[x][y]==4)return false;
        return true;
    }
    public boolean cando(int i)
    {
        switch(i)
        {
            case 1:
                if(py==0 || arr[px][py-1]==1)return false;
                if(arr[px][py-1]==0 || arr[px][py-1]==2)return true;
                if(py-2>=0 && (arr[px][py-2]==0 || arr[px][py-2]==2))
                    return true;
                return false;
            case 2:
                if(py==arr[0].length-1 || arr[px][py+1]==1)return false;
                if(arr[px][py+1]==0 || arr[px][py+1]==2)return true;
                if(py+2<arr[0].length && (arr[px][py+2]==0 || arr[px][py+2]==2))
                    return true;
                return false;
            case 3:
                if(px==0 || arr[px-1][py]==1)return false;
                if(arr[px-1][py]==0 || arr[px-1][py]==2)return true;
                if(px-2>=0 && (arr[px-2][py]==0 || arr[px-2][py]==2))
                    return true;
                return false;
            case 4:
                if(px==arr.length-1 || arr[px+1][py]==1)return false;
                if(arr[px+1][py]==0 || arr[px+1][py]==2)return true;
                if(px+2<arr.length && (arr[px+2][py]==0 || arr[px+2][py]==2))
                    return true;
                return false;
        }
        return false;
    }
    public void doit(int i)
    {
        switch(i)
        {
            case 1:
                py--;
                his+="上";
                if((arr[px][py]&4)==0)return;
                arr[px][py]-=4;
                arr[px][py-1]+=4;                
                return;
            case 2:
                py++;
                his+="下";
                if((arr[px][py]&4)==0)return;
                arr[px][py]-=4;
                arr[px][py+1]+=4;                
                return;
            case 3:
                px--;
                his+="左";
                if((arr[px][py]&4)==0)return;
                arr[px][py]-=4;
                arr[px-1][py]+=4;                
                return;
            case 4:
                px++;
                his+="右";
                if((arr[px][py]&4)==0)return;
                arr[px][py]-=4;
                arr[px+1][py]+=4;                
                return;
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
    public int[][] getArr()
    {
        int rtnarr[][]=new int[getX()][getY()];
        for(int x=0;x<getX();x++)
            for(int y=0;y<getY();y++)
                rtnarr[x][y]=this.arr[x][y];
        return rtnarr;
    }
    private static boolean not0_2(int n){return n!=0 && n!=2;}
    public void show()
    {
        System.out.println();
        for(int y=0;y<arr[0].length;y++)
        {
            System.out.println();
            for(int x=0;x<arr.length;x++)
                System.out.print((px==x&&py==y)?"* ":(arr[x][y]+" "));

        }
    }
    public Pan clone()
    {
        try{
            Pan rtnpan=(Pan)super.clone();
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
    public String toString()
    {
        String str="";
        for(int y=0;y<arr[0].length;y++)
            for(int x=0;x<arr.length;x++)
                if((arr[x][y]&4)!=0)
                str+=getChar(x)+""+getChar(y);
        str+=getChar(px);
        str+=getChar(py);
        //System.out.println(str+" ");
        return str;
    }
    
    public String getHis()
    {return his;}
    private char getChar(int i)
    {
        return (char)i;
    }
    private String his="";
    private byte px=-1;
    private byte py=-1;
    private int arr[][];
}
