package BuildH2O.BuildH2O;

import java.util.concurrent.BrokenBarrierException;

public class Hydrogen implements Runnable {

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
    public Hydrogen(BuildingH2O buildingH2O) {
        this.buildingH2O = buildingH2O;
    }


    @Override
    public void run() {

        try {
            // Wait upon the barrier
            buildingH2O.hydroxyBarrier.await();

            System.out.println("Hydrogen atom " + (count++) + " waiting at barrier.");

            // Check if enough number of atoms are available
            if(buildingH2O.hydrogen > 0 && buildingH2O.oxygen > 0) {
                // If available, bond
                buildingH2O.hydrogenQueue.release();
                buildingH2O.hydrogen--;
                buildingH2O.oxygenQueue.release();
                buildingH2O.oxygen--;
                buildingH2O.bond();
            }

            else {
                buildingH2O.hydrogen++;
                buildingH2O.hydrogenQueue.acquire();
            }

        } catch (InterruptedException | BrokenBarrierException ignored) {}

    }

}
