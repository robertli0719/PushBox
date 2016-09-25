/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pushbox;

/**
 *
 * @author 李理
 */
public class Action
{
    public Action(int box,int x,int y,int p)
    {
        this.box=box;
        this.x=x;this.y=y;
        this.p=p;
    }
    public static boolean isRoad(int x,int y,int arr[][])
    {
        return arr[x][y]!=1 && (arr[x][y]&4)==0;
    }
    public static void fill(int farr[][],int arr[][],int x,int y,int n)
    {
        if(x<0 || x>=farr.length || y<0 || y>=farr[0].length)
            return;
        if(farr[x][y]!=0)
            return;
        if(!isRoad(x,y,arr))
            return;
        farr[x][y]=n;
        fill(farr,arr,x+1,y,n);
        fill(farr,arr,x-1,y,n);
        fill(farr,arr,x,y+1,n);
        fill(farr,arr,x,y-1,n);
    }
    public static int[][] getSpaceNumArea(int arr[][])
    {
        int area[][]=new int[arr.length][arr[0].length];
        for(int x=0;x<area.length;x++)
            for(int y=0;y<area[0].length;y++)
                area[x][y]=0;
        int n=1;
        for(int y=0;y<area[0].length;y++)
            for(int x=0;x<area.length;x++)
                if(area[x][y]==0 && isRoad(x,y,arr))
                    fill(area,arr,x,y,n++);
        return area;
    }
    public String toString()
    {
        return " b:"+box+" x:"+x+" y:"+y+" "+" p"+p;
    }

    public int getBox(){return box;}
    public int getX(){return x;}
    public int getY(){return y;}
    public int getP(){return p;}

    private int box;//箱子位置
    private int x;
    private int y;//移动到的坐标
    private int p;//移动后人的所在区域
}
