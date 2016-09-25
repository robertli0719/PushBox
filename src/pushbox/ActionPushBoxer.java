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
public class ActionPushBoxer implements Cloneable
{
    public String push(ActionPan pan)
    {
        if(pan.bePass())return null;
        ArrayList<ActionPan> list=new ArrayList<ActionPan>();
        list.add(pan);
        Set set=new TreeSet<String>();
        set.add(pan.toString());

        String rtnStr;
        int i=0;
        while((rtnStr=getStr(list))==null && list.size()!=0)
        {
            System.out.println(i+" "+list.size()+" "+set.size());i++;
            list=getNextArr(list,set);
        }
        
        return rtnStr;
    }
    private String getStr(ArrayList<ActionPan> list)
    {
        for(ActionPan pan:list)
            if(pan.complet())                            
                return pan.getHis();
            
        return null;
    }
    private ArrayList<ActionPan> getNextArr(ArrayList<ActionPan> list,Set<String> set)
    {
        ArrayList<ActionPan> rtnAl=new ArrayList<ActionPan>();
        for(ActionPan pan:list)
        {
            ArrayList<Action> doList=pan.getAction();
            
            for(Action atn:doList)
            {                
                    ActionPan p=pan.clone();                
                    p.doit(atn);                   
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
