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
        //List<Country> userCountries = app.getCountriesByUserInput();
        //display.displayCountries(userCountries);

        System.out.println("\n==================================================================================================================\n");

        //List<Country> userContinentCountries = app.getContinentCountriesByUserInput();
        //display.displayCountries(userContinentCountries);


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

        List<City> cityReport = app.cityReport("Edinburgh");
        app.displayCityReport(cityReport);




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
     * Method to disconnect from the database
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

    //usecase #15
    // continent by population [90% COMPLEATED | currently can't figure out a way to display witch continient in final diplay method as no string can be used? - finn]
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
            System.out.println("Failed to get top N capital cities");
            return null;
        }
    }

    /**
     * Shows the total, urban and rural population of a continent, region or country.
     */
    public int populationReport(String areaType, String areaName) {
        try {
            Statement stmt = con.createStatement();

            String totalPopQuery = "";
            String urbanPopQuery = "";

            if (areaType.equalsIgnoreCase("continent")) {
                totalPopQuery =
                        "SELECT SUM(Population) AS TotalPop FROM country WHERE Continent = '" + areaName + "'";

                urbanPopQuery =
                        "SELECT SUM(city.Population) AS UrbanPop FROM city " +
                        "JOIN country ON city.CountryCode = country.Code " +
                        "WHERE country.Continent = '" + areaName + "'";

            } else if (areaType.equalsIgnoreCase("region")) {
                totalPopQuery =
                        "SELECT SUM(Population) AS TotalPop FROM country WHERE Region = '" + areaName + "'";

                urbanPopQuery =
                        "SELECT SUM(city.Population) AS UrbanPop FROM city " +
                        "JOIN country ON city.CountryCode = country.Code " +
                        "WHERE country.Region = '" + areaName + "'";

            } else if (areaType.equalsIgnoreCase("country")) {
                totalPopQuery =
                        "SELECT Population AS TotalPop FROM country WHERE Name = '" + areaName + "'";

                urbanPopQuery =
                        "SELECT SUM(city.Population) AS UrbanPop FROM city " +
                        "JOIN country ON city.CountryCode = country.Code " +
                        "WHERE country.Name = '" + areaName + "'";
            }

            ResultSet rsTotal = stmt.executeQuery(totalPopQuery);
            int totalPop = 0;
            if (rsTotal.next()) {
                totalPop = rsTotal.getInt("TotalPop");
            }

            ResultSet rsUrban = stmt.executeQuery(urbanPopQuery);
            int urbanPop = 0;
            if (rsUrban.next()) {
                urbanPop = rsUrban.getInt("UrbanPop");
            }

            int ruralPop = totalPop - urbanPop;

            System.out.println("Area Type: " + areaType);
            System.out.println("Area Name: " + areaName);
            System.out.println("Total Population: " + totalPop);
            System.out.println("Urban Population: " + urbanPop);
            System.out.println("Rural Population: " + ruralPop);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get details");
            return 0;

        }
        return 0;
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


    // city report | use case #18 and #15 on gtt? appretnly they have 2 numbers lmao
    public List<City> cityReport(String UserCity) {
        try {
            Statement stmt = con.createStatement();
            String strSelect = "SELECT ID, Name, CountryCode, District, Population FROM city WHERE Name = '" + UserCity + "' ORDER BY Population DESC";
            ResultSet rs = stmt.executeQuery(strSelect);

            List<City> cityReport = new ArrayList<>();

            while (rs.next()) {
                City city = new City();
                city.id = rs.getInt("id");
                city.name = rs.getString("Name");
                city.countryCode = rs.getInt("CountryCode");
                city.district = rs.getString("District");
                city.population = rs.getInt("Population");
                cityReport.add(city);
            }
            return cityReport;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get details");
            return null;

        }
    }


    public void displayCityReport(List<City> cityReport) {
        if (cityReport != null)
            for (City city : cityReport) {
                System.out.println("Name: " + city.name + ", Districkt: " + city.district + ", Population: " + city.population);

            }

    }

    public void topNCapitalCities(int limit, String areaType, String areaName) {
        try {
            Statement stmt = con.createStatement();

            String strSelect =
                    "SELECT city.Name AS CapitalName, country.Name AS CountryName, city.Population AS Population " +
                            "FROM city JOIN country ON country.Capital = city.ID ";

            if (areaType.equalsIgnoreCase("continent")) {
                strSelect += "WHERE country.Continent = '" + areaName + "' ";
            } else if (areaType.equalsIgnoreCase("region")) {
                strSelect += "WHERE country.Region = '" + areaName + "' ";
            }

            strSelect += "ORDER BY city.Population DESC LIMIT " + limit;

            ResultSet rs = stmt.executeQuery(strSelect);

            while (rs.next()) {
                String capital = rs.getString("CapitalName");
                String country = rs.getString("CountryName");
                int population = rs.getInt("Population");

                System.out.println("Capital: " + capital + ", Country: " + country + ", Population: " + population);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get top N capital cities");
        }
    }

    public void capitalCityReport() {
        try {
            Statement stmt = con.createStatement();
            String strSelect =
                    "SELECT city.Name AS CapitalName, country.Name AS CountryName, city.Population AS Population " +
                            "FROM city JOIN country ON country.Capital = city.ID " +
                            "ORDER BY city.Population DESC";

            ResultSet rs = stmt.executeQuery(strSelect);

            while (rs.next()) {
                String name = rs.getString("CapitalName");
                String country = rs.getString("CountryName");
                int population = rs.getInt("Population");

                System.out.println("Capital: " + name + ", Country: " + country + ", Population: " + population);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to produce capital city report");
        }
    }







}
