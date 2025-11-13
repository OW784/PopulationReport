package com.napier.pr;

import java.sql.*;
import java.util.*;

public class Main {

    private Connection con = null;

    public static void main(String[] args) {
        Main app = new Main();
        app.connect();

      
        app.disconnect();
    }

    /**
     * Method to connect to the database
     */
    public void connect() {
        try {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 100;
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try {
                Thread.sleep(30000);
                con = DriverManager.getConnection(
                        "jdbc:mysql://db:3306/world?allowPublicKeyRetrieval=true&useSSL=false",
                        "root",
                        "password"
                );
                System.out.println("Successfully connected");
                Thread.sleep(10000);
                break;
            } catch (SQLException sqle) {
                System.out.println("Failed to connect to database attempt " + i);
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
                con.close();
            } catch (Exception e) {
                System.out.println("Error closing connection to database");
            }
        }
    }

    /**
     * Produces a report of all capital cities ordered by population (largest to smallest).
     */
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

    /**
     * Shows the top N highest population capital cities in the world, a continent or a region.
     */
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

    /**
     * Shows the total, urban and rural population of a continent, region or country.
     */
    public void populationReport(String areaType, String areaName) {
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
            System.out.println("Failed to produce population report");
        }
    }
}








