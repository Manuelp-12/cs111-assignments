/**
 * Class represents solar panels, street map, and
 * an array of parking lot projects.
 * 
 * @author Jessica De Brito
 * @author Kal Pandit
 */
public class SolarPanels {
    
    private Panel[][] panels;
    private String[][] streetMap;
    private ParkingLot[] lots;

    /**
     * Default constructor: initializes empty panels and objects.
     */
    public SolarPanels() {
        panels = null;
        streetMap = null;
        lots = null;
        StdRandom.setSeed(2023);
    }

    /**
     * Updates the instance variable streetMap to be an l x w
     * array of Strings. Reads each label from input file in parameters.
     * 
     * @param streetMapFile the input file to read from
     */
    public void setupStreetMap(String streetMapFile) {
        // Set the input file to the one provided in the parameter
        StdIn.setFile(streetMapFile);

        // Read the length and width of the street map
        int length = StdIn.readInt(); // Read the number of rows
        int width = StdIn.readInt();  // Read the number of columns

        // Initialize the streetMap 2D array with the given dimensions
        streetMap = new String[length][width];

        // Populate the streetMap array with values from the file
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                // Read the next string and assign it to the current position
                streetMap[i][j] = StdIn.readString();
            }
        }
    }

    /**
     * Adds parking lot information to an array of parking lots.
     * Updates the instance variable lots to store these parking lots.
     * 
     * @param parkingLotFile the lot input file to read
     */
    public void setupParkingLots(String parkingLotFile) {
        // Set the input file to the one provided in the parameter
        StdIn.setFile(parkingLotFile);

        // Read the number of parking lots
        int n = StdIn.readInt();

        // Initialize the lots array to store n ParkingLot objects
        lots = new ParkingLot[n];

        // Populate the lots array with data from the file
        for (int i = 0; i < n; i++) {
            // Read the data for each parking lot
            String name = StdIn.readString();
            int maxPanels = StdIn.readInt();
            double budget = StdIn.readDouble();
            int energyCapacity = StdIn.readInt();
            double efficiency = StdIn.readDouble();

            // Create a new ParkingLot object and add it to the array
            lots[i] = new ParkingLot(name, maxPanels, budget, energyCapacity, efficiency);
        }
    }

    /**
     * Insert panels on each lot as much as space and budget allows.
     * Updates the instance variable panels to be a 2D array parallel to
     * streetMap, storing panels placed.
     * 
     * Panels have a 95% chance of working. Use StdRandom.uniform(); if
     * the resulting value is < 0.95 the panel works.
     * 
     * @param costPerPanel the fixed cost per panel, as a double
     */
    public void insertPanels(double costPerPanel) {
        // Initialize the panels array to match the dimensions of the streetMap
        int rows = streetMap.length;
        int cols = streetMap[0].length;
        panels = new Panel[rows][cols];

        // Iterate through each parking lot
        for (ParkingLot lot : lots) {
            String lotName = lot.getLotName();
            double budgetRemaining = lot.getBudget();
            int maxPanels = lot.getMaxPanels();
            int currentPanels = 0;

            // Traverse the streetMap to place panels
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    // Check if the cell matches the lot's name
                    if (streetMap[i][j].equals(lotName)) {
                        // Check if there's enough budget and room for more panels
                        if (budgetRemaining >= costPerPanel && currentPanels < maxPanels) {
                            // Determine if the panel works
                            boolean works = StdRandom.uniform() < 0.95;

                            // Create and place the panel
                            panels[i][j] = new Panel(lot.getPanelEfficiency(), lot.getEnergyCapacity(), works);

                            // Update budget and panel count
                            budgetRemaining -= costPerPanel;
                            currentPanels++;
                        }
                    }
                }
            }
        }
    }

    /**
     * Given a temperature and coefficient, update panels' actual efficiency
     * values. Panels are most optimal at 77 degrees F.
     * 
     * Panels perform worse in hotter environments and better in colder ones.
     * worse = efficiency loss, better = efficiency gain.
     * 
     * Coefficients are usually negative to represent energy loss.
     * 
     * @param temperature the current temperature, in degrees F
     * @param coefficient the coefficient to use
     */
    public void updateActualEfficiency(int temperature, double coefficient) {
        // Traverse through the panels array
        for (int i = 0; i < panels.length; i++) {
            for (int j = 0; j < panels[i].length; j++) {
                // Check if there is a panel at this location
                if (panels[i][j] != null) {
                    // Calculate efficiency loss or gain
                    double efficiencyChange = coefficient * (temperature - 77);

                    // Update the panel's actual efficiency
                    double updatedEfficiency = panels[i][j].getRatedEfficiency() - efficiencyChange;

                    // Ensure the efficiency is capped at 100% (no panel is more than 100% efficient)
                    if (updatedEfficiency > 100) {
                        updatedEfficiency = 100;
                    }

                    // Update the panel's actual efficiency in the object
                    panels[i][j].setActualEfficiency(updatedEfficiency);
                }
            }
        }
    }

    /**
     * For each WORKING panel, update the electricity generated for 4 hours 
     * of sunlight as follows:
     * 
     * (actual efficiency / 100) * 1500 * 4
     * 
     * RUN updateActualEfficiency BEFORE running this method.
     */
    public void updateElectricityGenerated() {
        // Traverse through the panels array
        for (int i = 0; i < panels.length; i++) {
            for (int j = 0; j < panels[i].length; j++) {
                // Check if there is a panel at this location
                if (panels[i][j] != null && panels[i][j].isWorking() == true) {
                    // Calculate the power output in watts
                    double actualEfficiency = panels[i][j].getActualEfficiency();
                    double powerOutput = (actualEfficiency / 100) * 1500;

                    // Calculate daily electricity generated (in watt-hours)
                    double dailyElectricityGenerated = powerOutput * 4; // 4 hours of peak sunlight
                    int intdailyElectricityGenerated = (int)dailyElectricityGenerated;

                    // Update the panel's electricityGenerated field
                    panels[i][j].setElectricityGenerated(intdailyElectricityGenerated);
                }
            }
        }
    }

    /**
     * Count the number of working panels in a parking lot.
     * 
     * @param parkingLot the parking lot name
     * @return the number of working panels
     */
    public int countWorkingPanels(String parkingLot) {
        int workingPanelsCount = 0; // Initialize the count of working panels

        // Traverse through the panels array
        for (int i = 0; i < panels.length; i++) {
            for (int j = 0; j < panels[i].length; j++) {
                // Check if there is a panel at this location
                if (panels[i][j] != null) {
                    // Check if the panel is in the specified parking lot and is working
                    if (streetMap[i][j].equals(parkingLot) && panels[i][j].isWorking()) {
                        workingPanelsCount++; // Increment the count of working panels
                    }
                }
            }
        }

        return workingPanelsCount; // Return the total count
    }

    /**
     * Find the broken panels in the map and repair them.
     * @return the count of working panels in total, after repair
     */
    public int updateWorkingPanels() {
        int totalWorkingPanels = 0; // Initialize the counter for working panels

        // Traverse through the panels array
        for (int i = 0; i < panels.length; i++) {
            for (int j = 0; j < panels[i].length; j++) {
                // Check if there is a panel at this location
                if (panels[i][j] != null) {
                    // Check if the panel is not working
                    if (!panels[i][j].isWorking()) {
                        // Repair the panel by setting it to working
                        panels[i][j].setIsWorking(true);
                    }

                    // Increment the count since it's now working
                    totalWorkingPanels++;
                }
            }
        }

        return totalWorkingPanels; // Return the total count of working panels
    }

    /**
     * Calculate Rutgers' savings on energy by using
     * these solar panels.
     * 
     * ASSUME:
     * - Multiply total electricity generated by 0.001 to convert to KwH.
     * - There are 365 days in a year.
     * 
     * RUN electricityGenerated before running this method.
     */
    public double calculateSavings() {
        double totalElectricityGenerated = 0.0; // Initialize the total electricity counter

        // Traverse through the panels array
        for (int i = 0; i < panels.length; i++) {
            for (int j = 0; j < panels[i].length; j++) {
                // Check if there is a panel at this location
                if (panels[i][j] != null) {
                    // Add the panel's daily electricity generated to the total
                    totalElectricityGenerated += panels[i][j].getElectricityGenerated();
                }
            }
        }

        // Convert total electricity from watt-hours to kilowatt-hours for one year
        double yearlyKilowattHours = totalElectricityGenerated * 0.001 * 365;

        // Calculate the percentage of Rutgers' yearly electricity needs this meets
        double percentOfNeedsMet = yearlyKilowattHours / 4270000;

        // Calculate the monetary savings
        double savings = percentOfNeedsMet * 60000000; // $60 million total electricity spending

        return savings; // Return the calculated savings
    }

    /*
     * Getter and Setter methods
     */
    public Panel[][] getPanels() {
        // DO NOT TOUCH THIS METHOD
        return this.panels;
    }

    public void setPanels(Panel[][] panels) {
        // DO NOT TOUCH THIS METHOD
        this.panels = panels;
    }

    public String[][] getStreetMap() {
        // DO NOT TOUCH THIS METHOD
        return this.streetMap;
    }

    public void setStreetMap(String[][] streetMap) {
        // DO NOT TOUCH THIS METHOD
        this.streetMap = streetMap;
    }

    public ParkingLot[] getLots() {
        // DO NOT TOUCH THIS METHOD
        return this.lots;
    }

    public void setLots(ParkingLot[] lots) {
        // DO NOT TOUCH THIS METHOD
        this.lots = lots;
    }
}