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

        System.out.println("===========================<Population of specified areas>=========================");

        System.out.println("===========================<Population of specified continent>=========================");
        List<Country> continentCountries = app.getCountriesByContinentAndPopulation("Asia");
        app.displayContinentCountries(continentCountries);

        System.out.println("===========================<Population of specified Regions>=========================");
        List<Country> RegionsCountries = app.getCountriesByRegionsAndPopulation("South America");
        app.displayRegionsCountries(RegionsCountries);

        System.out.println("===========================<Population of specified Districkt>=========================");
        //New list for city data type (Finns attempt so it may be borked)
        List<City> DistricsCity = app.getCitysByDistricAndpopulation("Scotland");
        app.displayDistricsCity(DistricsCity);

        System.out.println("===========================<Population of specified City>=========================");
        //New list for city data type (Finns attempt so it heckin works!)
        List<City> cityCity = app.getCitysByCityAndpopulation("Edinburgh");
        app.displayCityCity(cityCity);


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

    //usecase #15
    // continent by population [90% COMPLEATED | currently can't figure out a way to display witch continient in final diplay method as no string can be used? - finn]
    public List<Country> getCountriesByContinentAndPopulation(String continent) {
        try {
            Statement stmt = con.createStatement();
            String strSelect = "SELECT code, Name, Continent, Region, Population FROM country WHERE Continent = '" + continent + "' ORDER BY Population DESC";
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
            System.out.println("Failed to get details");
            return null;

        }
    }

    public void displayContinentCountries(List<Country> continentCountries) {
        //for every country in the countries list, print the details
        int total_pop = 0;
        if (continentCountries != null)
            for (Country country : continentCountries) {
                System.out.println("Name: " + country.name + ", " + "Continent: " + country.continent + ", " + "Population: " + country.population);
                total_pop += country.population;
            }
        // Issue is here in final message
        System.out.println("===========================<Population of specified continent>=========================");
        System.out.println("The total population is: " + total_pop * -1);
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

    public void displayRegionsCountries(List<Country> RegionsCountries) {
        //for every country in the countries list, print the details
        int total_pop = 0;
        if (RegionsCountries != null)
            for (Country country : RegionsCountries) {
                System.out.println("Name: " + country.name + ", " + "Region: " + country.region + ", " + "Population: " + country.population);
                total_pop += country.population;
            }
        // Issue is here in final message
        System.out.println("===========================<Population of specified region>=========================");
        System.out.println("The total population is: " + total_pop);
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
                city.id = rs.getString("id");
                city.name = rs.getString("Name");
                city.countryCode = rs.getString("CountryCode");
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


    public void displayDistricsCity(List<City> DistricsCity) {
        //for every city in the citys list, print the details
        int total_pop = 0;
        if (DistricsCity != null)
            for (City city : DistricsCity) {
                System.out.println("Name: " + city.name + ", " + "District: " + city.district + ", " + "Population: " + city.population);
                total_pop += city.population;
            }
        // Issue is here in final message
        System.out.println("===========================<Population of specified distric>=========================");
        System.out.println("The total population is: " + total_pop);
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
                city.id = rs.getString("id");
                city.name = rs.getString("Name");
                city.countryCode = rs.getString("CountryCode");
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


    public void displayCityCity(List<City> cityCity) {
        //for every city in the citys list, print the details
        int total_pop = 0;
        if (cityCity != null)
            for (City city : cityCity) {
                System.out.println("Name: " + city.name + ", Population: " + city.population);
                total_pop += city.population;
            }
        // Issue is here in final message
        System.out.println("===========================<Population of specified City>=========================");
        System.out.println("The total population is: " + total_pop);
    }







}











