package BuildH2O.BuildH2O;

import java.util.concurrent.BrokenBarrierException;

public class Oxygen implements Runnable {

    /**
     * Shared variable of class BuildingH2O
     */
    private BuildingH2O buildingH2O;
    /**
     * Count indicating the total number of hydrogen atoms
     */
    private static int count = 1;

    /**
     * Constructor
     * @param buildingH2O shared variable
     */
    public Oxygen(BuildingH2O buildingH2O) {
        this.buildingH2O = buildingH2O;
    }


    public void run() {

        try {
            // Wait upon the barrier
            buildingH2O.hydroxyBarrier.await();

            System.out.println("Oxygen atom " + (count++) + " waiting at barrier.");

            // Check if enough number of atoms are available
            if(buildingH2O.hydrogen > 1) {
                // If available, bond
                buildingH2O.hydrogenQueue.release(2);
                buildingH2O.hydrogen-=2;
                buildingH2O.bond();
            }

            else {
                buildingH2O.oxygen++;
                buildingH2O.oxygenQueue.acquire();
            }

        } catch (InterruptedException | BrokenBarrierException ignored) {}

    }
}
