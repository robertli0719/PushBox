/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pushbox;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;
/**
 *
 * @author 李理
 */
public class PushBoxer
{
    public String push(Pan pan)
    {        
        ArrayList<Pan> list=new ArrayList<Pan>();
        list.add(pan);
        Set set=new TreeSet<String>();
        set.add(pan.toString());
        
        String rtnStr;
        while((rtnStr=getStr(list))==null && list.size()!=0)                
            list=getNextArr(list,set);
        
        return rtnStr;
    }
    private String getStr(ArrayList<Pan> list)
    {
        for(Pan pan:list)
            if(pan.complet())
                return pan.getHis();
        return null;
    }
    private ArrayList<Pan> getNextArr(ArrayList<Pan> list,Set<String> set)
    {
        ArrayList<Pan> rtnAl=new ArrayList<Pan>();
        for(Pan pan:list)
        {
            for(int i=1;i<=4;i++)
                if(pan.cando(i))
                {
                    Pan p=pan.clone();
                    p.doit(i);
                    if(p.canCut())continue;
                    if(set.contains(p.toString()))
                        continue;
                    rtnAl.add(p);
                    set.add(p.toString());
                    
                }
        }
        return rtnAl;
    }
}
