package BuildH2O;

import BuildH2O.BuildH2O.*;

public class TestBuildingH2O {

	public static void main(String[] args) throws InterruptedException {

		BuildingH2O buildingH2O = new BuildingH2O();

		for (int i = 0; i < 100; i++) {
			if(i%2 == 0) {
				Hydrogen hydrogen = new Hydrogen(buildingH2O);
				Thread thread = new Thread(hydrogen);
				thread.start();
			}
			else {
				Oxygen myOxygen = new Oxygen(buildingH2O);
				Thread thread = new Thread(myOxygen);
				thread.start();
			}
		}

		Thread.sleep(3000);
		System.exit(0);

	}

}
/*
Output:
 */
