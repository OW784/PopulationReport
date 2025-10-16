package com.napier.pr;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class Main {
    private Connection con = null;


    public static void main(String[] args) {
        // Create new Application
        Main app = new Main();

        // Connect to database
        app.connect();

        List<Country> countries = app.getCountriesByPopulationLtS();
        app.displayCountriesByPopulationLtS(countries);

        System.out.println("====================================================");

        List<Country> continentCountries = app.getCountriesByContinentAndPopulation("Europe");
        app.displayContinentCountries(continentCountries);


        // Disconnect from database
        app.disconnect();


    }

    /**
     * Method to connect to the database
     *
     */
    public void connect() {
        try {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        // Connection to the database
        int retries = 100;
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try {
                // Waiting for db to start
                Thread.sleep(30000);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://db:3306/world?allowPublicKeyRetrieval=true&useSSL=false", "root", "password");
                System.out.println("Successfully connected");
                // Wait a bit
                Thread.sleep(10000);
                // Exit for loop
                break;
            } catch (SQLException sqle) {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            } catch (InterruptedException ie) {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    /**
     * Method to disconnect to the database
     *
     */
    public void disconnect() {
        if (con != null) {
            try {
                // Close connection
                con.close();
            } catch (Exception e) {
                System.out.println("Error closing connection to database");
            }
        }
    }


    /**
     *
     * Get all countries from the world database in descending order by population
     *
     * @return the list of countries sorted by population in descending order
     */

    public List<Country> getCountriesByPopulationLtS() {
        try {
            Statement stmt = con.createStatement();
            String strSelect = "SELECT Code, Name, Continent, Region, Population FROM country ORDER BY Population DESC";
            ResultSet rs = stmt.executeQuery(strSelect);

            List<Country> countries = new ArrayList<>();
            //while there is new lines in the database
            // create a country object and add the details
            //add to the array list
            while (rs.next()) {
                Country country = new Country();
                country.code = rs.getString("Code");
                country.name = rs.getString("Name");
                country.continent = rs.getString("Continent");
                country.region = rs.getString("Region");
                country.population = rs.getInt("Population");
                countries.add(country);
            }
            return countries;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get employee details");
            return null;

        }
    }


    /**
     * Shows the countries list when you run the program
     *
     */
    public void displayCountriesByPopulationLtS(List<Country> countries) {
        //for every country in the countries list, print the details
        if (countries != null)
            for (Country country : countries) {
                System.out.println("Name: " + country.name + ", " + "Population: " + country.population);
            }
    }

    public List<Country> getCountriesByContinentAndPopulation(String continent) {
        try {
            Statement stmt = con.createStatement();
            String strSelect = "SELECT code, Name, Continent, Region, Population FROM country WHERE Continent = 'EUROPE' ORDER BY Population DESC";
            ResultSet rs = stmt.executeQuery(strSelect);

            List<Country> continentCountries = new ArrayList<>();
            while (rs.next()) {
                Country country = new Country();
                country.code = rs.getString("Code");
                country.name = rs.getString("Name");
                country.continent = rs.getString("Continent");
                country.region = rs.getString("Region");
                country.population = rs.getInt("Population");
                continentCountries.add(country);
            }
            return continentCountries;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get employee details");
            return null;

        }
    }

    public void displayContinentCountries(List<Country> continentCountries) {
        //for every country in the countries list, print the details
        if (continentCountries != null)
            for (Country country : continentCountries) {
                System.out.println("Name: " + country.name + ", " + "Continent: " + country.continent + ", " + "Population: " + country.population);
            }
    }

}









