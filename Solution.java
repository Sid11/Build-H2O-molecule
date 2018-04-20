package BuildH2O;

//import java.util.concurrent.Semaphore;
//import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.BrokenBarrierException;

public class Solution {

    public static void main(String[] args) {

        MyBuildingH2O myBuildingH2O = new MyBuildingH2O();
        //	System.out.println(myBuildingH2O.hydroxyBarrier.getParties());
    }

}

/**
 * This class encapsulates the functioning of the code.
 */

class MyBuildingH2O {

    static int hydrogen =4;
    int oxygen = 4;
    java.util.concurrent.Semaphore mutex;
    java.util.concurrent.Semaphore hydrogenQueue, oxygenQueue;
    static java.util.concurrent.CyclicBarrier hydroxyBarrier;

    /**
     * Constructor
     */

    MyBuildingH2O() {

        mutex = new java.util.concurrent.Semaphore(0);
        /*  Size of Cyclic Barrier is set to 3
        *	1 for Oxygen Thread
        *	2 for Hydrogen Thread(2 molecules of Hydrogen)
        */	
        hydroxyBarrier = new java.util.concurrent.CyclicBarrier(3);
        System.out.println("Initial Oxygen number :"+oxygen);
        hydroxyBarrier = new java.util.concurrent.CyclicBarrier(4);
        System.out.println("Initial MyOxygen number :"+oxygen);
        System.out.println("Initial Hydrogen number :"+hydrogen);

        //Objects of classes
        MyOxygen o = new MyOxygen();
        Hydrogen1 h1 = new Hydrogen1();
        Hydrogen2 h2 = new Hydrogen2();

        //Condition for H2O to form
        while(oxygen != 0 && hydrogen >1 ) {
            System.out.println("MyOxygen Left:"+oxygen+" Hydrogen Left:"+hydrogen);

            //Creation of thread
            Thread OxygenThread = new Thread(o);
            Thread HydrogenOneThread = new Thread(h1);
            Thread HydrogenTwoThread = new Thread(h2);

            OxygenThread.start();
            HydrogenOneThread.start();
            HydrogenTwoThread.start();
            try
            {
                //Reducing the number of MyOxygen and Hydrogen after creation of a H2O molecule
                oxygen--;
                hydrogen--;
                hydrogen--;
                hydroxyBarrier.await();
            }
            catch (InterruptedException | BrokenBarrierException e)
            {
                e.printStackTrace();
            }

            System.out.println("MyOxygen number :"+oxygen);
            System.out.println("Hydrogen number :"+hydrogen);
            
            // Reseting the Barrier for creation of new molecule
            hydroxyBarrier.reset();
        }
    }

}

class MyOxygen implements Runnable {

    public void run() {
        try {
            MyBuildingH2O.hydroxyBarrier.await();
        }
        catch(InterruptedException | java.util.concurrent.BrokenBarrierException e) {}

    }

}

class Hydrogen1 implements Runnable {



    public void run() {
        try {
            MyBuildingH2O.hydroxyBarrier.await();
        }
        catch(InterruptedException | java.util.concurrent.BrokenBarrierException e) {}

    }

}
class Hydrogen2 implements Runnable {

    public void run() {
        try {
            MyBuildingH2O.hydroxyBarrier.await();
        }
        catch(InterruptedException | java.util.concurrent.BrokenBarrierException e) {}

    }

}
