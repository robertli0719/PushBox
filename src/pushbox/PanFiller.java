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
public class PanFiller
{
    public int[] getFill(Pan pan,int arr[][])
    {
        int i=0;
        ArrayList<Integer> al=new ArrayList<Integer>();        
        for(int x=0;x<pan.getX();x++)
            for(int y=0;y<pan.getY();y++)
                if(arr[x][y]==0)                
                    al.add(fillArr(arr,x,y,-(++i)));
                
        int[] rtnArr=new int[al.size()];
        for(int x=0;x<rtnArr.length;x++)
            rtnArr[x]=al.get(x);

        return rtnArr;
    }
    private int fillArr(int arr[][],int x,int y,int fill)
    {
        if(x<0 || x>=arr.length || y<0 || y>=arr[0].length || arr[x][y]!=0)
            return 0;
        arr[x][y]=fill;
        int n=1;
        n+=fillArr(arr,x+1,y,fill);
        n+=fillArr(arr,x+1,y,fill);
        n+=fillArr(arr,x+1,y,fill);
        n+=fillArr(arr,x+1,y,fill);
        return n;
    }
}
