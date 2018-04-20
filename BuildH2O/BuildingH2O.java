package BuildH2O.BuildH2O;
 
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class BuildingH2O {

    /**
     * Number of hydrogen atoms
     */
    int hydrogen;
    /**
     * Number of oxygen atoms
     */
    int oxygen;
    /**
     * Hydrogen atoms queue on this semaphore
     */
    Semaphore hydrogenQueue;
    /**
     * Oxygen atoms queue on this semaphore
     */
    Semaphore oxygenQueue;
    /**
     * All atoms wait upon this barrier
     */
    CyclicBarrier hydroxyBarrier;

    /**
     * Constructor initializes all member variables to their correct values
     */
    public BuildingH2O() {

        hydroxyBarrier = new CyclicBarrier(3);
        hydrogen = oxygen = 0;
        hydrogenQueue = new Semaphore(0);
        oxygenQueue = new Semaphore(0);
    }

    /**
     * This method indicates that three atoms have bonded.
     * It then resets the counts and the barrier to their initial states.
     */
    void bond() {

        System.out.println("Water molecule Produced!");
        hydrogen = 0;
        oxygen = 0;
        hydroxyBarrier.reset();
    }

}
