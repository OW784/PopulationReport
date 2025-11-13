package com.napier.pr;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    private Connection con = null;


    public static void main(String[] args) {
        // Create new Application
        Main app = new Main();
        Display display = new Display();  // Create Display object

        if(args.length < 1){
            app.connect("localhost:33060", 30000);
        }else{
            app.connect(args[0], Integer.parseInt(args[1]));
        }

        List<Country> countries = app.getCountriesByPopulationLtS();
        display.displayCountries(countries);

        System.out.println("\n==================================================================================================================\n");


        List<Country> continentCountries = app.getCountriesByContinentAndPopulation("EUROPE");
        display.displayCountries(continentCountries);

        System.out.println("\n==================================================================================================================\n");

        // Get top 10 countries (can be changed to read from user input if needed)
        /**List<Country> userCountries = app.getCountriesByUserInput();
        display.displayCountries(userCountries);*/

        System.out.println("\n==================================================================================================================\n");

        /**List<Country> userContinentCountries = app.getContinentCountriesByUserInput();
        display.displayCountries(userContinentCountries);*/


        System.out.println("===========================<Population of specified continent>=========================");
        List<Country> continentCountries2 = app.getCountriesByContinentAndPopulation("EUROPE");
        display.displayContinentCountries(continentCountries2);

        System.out.println("===========================<Population of specified Regions>=========================");
        List<Country> RegionsCountries = app.getCountriesByRegionsAndPopulation("South America");
        display.displayRegionsCountries(RegionsCountries);

        System.out.println("===========================<Population of specified Districkt>=========================");

        List<City> DistricsCity = app.getCitysByDistricAndpopulation("Scotland");
        display.displayDistricsCity(DistricsCity);

        System.out.println("===========================<Population of specified City>=========================");

        List<City> cityCity = app.getCitysByCityAndpopulation("Edinburgh");
        display.displayCityCity(cityCity);


        // Disconnect from database
        app.disconnect();
    }
    /**
     * Method to connect to the database
     *
     */
    public void connect(String location, int delay) {
        try {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try {
                // Wait a bit for db to start
                Thread.sleep(delay);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://" + location
                                + "/world?allowPublicKeyRetrieval=true&useSSL=false",
                        "root", "password");
                System.out.println("Successfully connected");
                break;
            } catch (SQLException sqle) {
                System.out.println("Failed to connect to database attempt " +                                  Integer.toString(i));
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
            String strSelect = "SELECT Code, Name, Continent, Region, Population, Capital FROM country ORDER BY Population DESC";
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
                country.capital = rs.getString("Capital");
                countries.add(country);
            }
            return countries;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country details");
            return null;

        }
    }

    /**
     * gets list of countries by inputted continent
     *
     * @param continent
     * @return the list of countries
     */



    public List<Country> getCountriesByContinentAndPopulation(String continent) {
        try {

            Statement stmt = con.createStatement();
            String strSelect = "SELECT code, Name, Continent, Region, Population, Capital FROM country WHERE Continent = '" + continent + "' ORDER BY Population DESC";
            ResultSet rs = stmt.executeQuery(strSelect);

            List<Country> continentCountries = new ArrayList<>();
            while (rs.next()) {
                Country country = new Country();
                country.code = rs.getString("Code");
                country.name = rs.getString("Name");
                country.continent = rs.getString("Continent");
                country.region = rs.getString("Region");
                country.population = rs.getInt("Population");
                country.capital = rs.getString("Capital");
                continentCountries.add(country);
            }
            return continentCountries;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get details");
            return null;

        }
    }

    /**
     * user can input number of countries to be returned
     *
     * @return list of countries
     */


    public List<Country> getCountriesByUserInput()
    {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter Number of countries you would like to see:");
            int n = Integer.parseInt(scanner.nextLine());

            Statement stmt = con.createStatement();
            String strSelect = "SELECT Code, Name, Continent, Region, Population, Capital FROM country ORDER BY Population DESC LIMIT " + n;
            ResultSet rs = stmt.executeQuery(strSelect);

            List<Country> continentCountries = new ArrayList<>();
            while (rs.next()) {
                Country country = new Country();
                country.code = rs.getString("Code");
                country.name = rs.getString("Name");
                country.continent = rs.getString("Continent");
                country.region = rs.getString("Region");
                country.population = rs.getInt("Population");
                country.capital = rs.getString("Capital");
                continentCountries.add(country);
            }
            return continentCountries;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country details");
            return null;

        }
    }

    /**
     * user can input number of countries to be returned
     *
     * @return list of countries
     */

    public List<Country> getContinentCountriesByUserInput()
    {
        try
        {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter number of countries you would like to see from Asia:");
            int n = Integer.parseInt(scanner.nextLine());

            Statement stmt = con.createStatement();
            String strSelect = "SELECT Code, Name, Continent, Region, Population, Capital FROM country WHERE continent = 'ASIA' ORDER BY Population DESC LIMIT " + n;
            ResultSet rs = stmt.executeQuery(strSelect);

            List<Country> continentCountries = new ArrayList<>();
            while (rs.next())
            {
                Country country = new Country();
                country.code = rs.getString("Code");
                country.name = rs.getString("Name");
                country.continent = rs.getString("Continent");
                country.region = rs.getString("Region");
                country.population = rs.getInt("Population");
                country.capital = rs.getString("Capital");
                continentCountries.add(country);
            }
            return continentCountries;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country details");
            return null;

        }
    }

    // regions by population
    public List<Country> getCountriesByRegionsAndPopulation(String region) {
        try {
            Statement stmt = con.createStatement();
            String strSelect = "SELECT code, Name, Continent, Region, Population FROM country WHERE Region = '" + region + "' ORDER BY Population DESC";
            ResultSet rs = stmt.executeQuery(strSelect);

            List<Country> RegionsCountries = new ArrayList<>();
            while (rs.next()) {
                Country country = new Country();
                country.code = rs.getString("Code");
                country.name = rs.getString("Name");
                country.continent = rs.getString("Continent");
                country.region = rs.getString("Region");
                country.population = rs.getInt("Population");
                RegionsCountries.add(country);
            }
            return RegionsCountries;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get details");
            return null;

        }
    }

    // distric population
    public List<City> getCitysByDistricAndpopulation(String distric) {
        try {
            Statement stmt = con.createStatement();
            String strSelect = "SELECT ID, Name, CountryCode, District, Population FROM city WHERE District = '" + distric + "' ORDER BY Population DESC";
            ResultSet rs = stmt.executeQuery(strSelect);

            List<City> DistricsCity = new ArrayList<>();

            while (rs.next()) {
                City city = new City();
                city.id = rs.getInt("id");
                city.name = rs.getString("Name");
                city.countryCode = rs.getInt("CountryCode");
                city.district = rs.getString("District");
                city.population = rs.getInt("Population");
                DistricsCity.add(city);
            }
            return DistricsCity;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get details");
            return null;

        }
    }

    // city population
    public List<City> getCitysByCityAndpopulation(String UserCity) {
        try {
            Statement stmt = con.createStatement();
            String strSelect = "SELECT ID, Name, CountryCode, District, Population FROM city WHERE Name = '" + UserCity + "' ORDER BY Population DESC";
            ResultSet rs = stmt.executeQuery(strSelect);

            List<City> cityCity = new ArrayList<>();

            while (rs.next()) {
                City city = new City();
                city.id = rs.getInt("id");
                city.name = rs.getString("Name");
                city.countryCode = rs.getInt("CountryCode");
                city.district = rs.getString("District");
                city.population = rs.getInt("Population");
                cityCity.add(city);
            }
            return cityCity;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get details");
            return null;

        }
    }



}
