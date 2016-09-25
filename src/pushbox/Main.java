/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pushbox;
import java.util.Scanner;
/**
 *
 * @author 李理
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        MainFrame aMainFrame=new MainFrame();
        aMainFrame.setDefaultCloseOperation(aMainFrame.EXIT_ON_CLOSE);
        aMainFrame.setVisible(true);/*
        Scanner scan =new Scanner(System.in);
        int arr[][]=new int[scan.nextInt()][scan.nextInt()];
        for(int y=0;y<arr[0].length;y++)
            for(int x=0;x<arr.length;x++)
                arr[x][y]=scan.nextInt();
        Pan pan=new Pan(arr);
         int ballN=scan.nextInt();
        for(int x=0;x<ballN;x++)
            pan.addBall(scan.nextInt(),scan.nextInt());
         pan.setPerson(scan.nextInt(),scan.nextInt());
        pan.show();        
     
        PushBoxer aPushBoxer=new PushBoxer();
        System.out.println(aPushBoxer.push(pan));*/

        /*
       countBest.PanBuilder pber=new countBest.PanBuilder();
       PushBoxer aPushBoxer=new PushBoxer();
       long max=0;
       Pan bestPan=null;
       while(pber.haveNext())
       {
           Pan pn=pber.getPan();
           String str=aPushBoxer.push(pn);
           long n=str.length();
           if(n>max){max=n;bestPan=pn;
           System.out.println("d");
           }
           pber.next();
       }
       bestPan.show();
        */
       
        
    }

}
