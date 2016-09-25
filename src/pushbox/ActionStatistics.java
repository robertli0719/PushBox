/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pushbox;
import java.util.Set;
import java.util.TreeSet;
import java.util.ArrayList;
/**
 *
 * @author 李理
 */
public class ActionStatistics
{
    public ActionStatistics(int x,int y,int arr[][],int r,int num)
    {        
        ASPan pn=new ASPan(x,y,arr,r);
        set.add(pn.toString());
        list.add(pn);
        this.num=num;

    }
    public void addToActionList(ArrayList<Action> al)
    {        
        rtnal=al;
        while(list.size()!=0)
        {            
            list=getNextArr(list,set);            
        }
       
    }
    private ArrayList<ASPan> getNextArr(ArrayList<ASPan> list,Set<String> set)
    {
        ArrayList<ASPan> rtnAl=new ArrayList<ASPan>();
        for(ASPan pn:list)
        for(int i=1;i<=4;i++)
            if(pn.cando(i))
            {
                ASPan p=pn.doit(i);
                if(set.contains(p.toString()))
                        continue;
                set.add(p.toString());
                //System.out.println(p);
                rtnAl.add(p);
                rtnal.add(new Action(num,p.x,p.y,p.r));
            }
        //System.out.println(rtnal.size());
        return rtnAl;
    }

    private class ASPan
    {
        public ASPan(int x,int y,int arr[][],int r)
        {
            ar=new int[arr.length][arr[0].length];
            for(int m=0;m<arr.length;m++)
                for(int n=0;n<arr[0].length;n++)
                {
                    if((arr[m][n]&4)!=0&&!(m==x&&n==y))
                        ar[m][n]=1;
                    else
                        ar[m][n]=arr[m][n];
                }
            areaArr=Action.getSpaceNumArea(ar);
            this.x=x;this.y=y;
            this.r=r;
        }
        public ASPan(int x,int y,int arr[][],int rx,int ry)
        {
            this(x,y,arr,0);
            r=areaArr[rx][ry];
        }
        public void show()
        {
            System.out.println("show pn: x:"+x+" y:"+y+" r"+r);
            for(int m=0;m<ar[0].length;m++)
            {
                for(int n=0;n<ar.length;n++)
                {
                    System.out.print(" "+ar[n][m]);
                }
                System.out.println();
            }
        }
        public boolean cando(int i)
        {
            int areaArr[][]=Action.getSpaceNumArea(ar);
            if(i==1)
            {
                if(y==ar[0].length-1 || y==0 || !Action.isRoad(x,y-1,ar))
                    return false;
                else if(areaArr[x][y+1]==r)
                    return true;
                else
                    return false;
            }
            else if(i==2)
            {
                if(y==ar[0].length-1 || y==0 || !Action.isRoad(x,y+1,ar))
                    return false;
                else if(areaArr[x][y-1]==r)
                    return true;
                else
                    return false;
            }
            else if(i==3)
            {
                if(x==0 || x==ar.length-1 || !Action.isRoad(x-1,y,ar))
                    return false;
                else if(areaArr[x+1][y]==r)
                    return true;
                else
                    return false;
            }
            else if(i==4)
            {
                if(x==0 || x==ar.length-1 || !Action.isRoad(x+1,y,ar))
                    return false;
                else if(areaArr[x-1][y]==r)
                    return true;
                else
                    return false;
            }
            return false;
        }
        public ASPan doit(int i)
        {
            int newAr[][]=new int[ar.length][ar[0].length];
            for(int m=0;m<ar.length;m++)
                for(int n=0;n<ar[0].length;n++)
                    newAr[m][n]=ar[m][n];
            if(i==1)
            {
                newAr[x][y]-=4;
                newAr[x][y-1]+=4;
                ASPan rtnp=new ASPan(x,y-1,newAr,x,y);
                return rtnp;
            }
            else if(i==2)
            {
                newAr[x][y]-=4;
                newAr[x][y+1]+=4;
                ASPan rtnp=new ASPan(x,y+1,newAr,x,y);
                return rtnp;
            }
            else if(i==3)
            {
                newAr[x][y]-=4;
                newAr[x-1][y]+=4;
                ASPan rtnp=new ASPan(x-1,y,newAr,x,y);
                return rtnp;
            }
            else if(i==4)
            {
                newAr[x][y]-=4;
                newAr[x+1][y]+=4;
                ASPan rtnp=new ASPan(x+1,y,newAr,x,y);
                return rtnp;
            }
                
            return null;
        }
        @Override
        public String toString()
        {
            String str="";
            str+=x;
            str+=y;
            str+=r;
            return str;
        }
        private char getChar(int i){return (char)i;}
        public int x;
        public int y;
        public int r;
        private int ar[][];
        private int areaArr[][];
    }
    private Set set=new TreeSet<String>();
    private ArrayList<ASPan> list=new ArrayList<ASPan>();
    private ArrayList<Action> rtnal;
    private int num;
}
