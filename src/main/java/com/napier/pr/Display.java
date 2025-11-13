package com.napier.pr;

import java.util.List;

public class Display
{
    /**
     * Takes a list of countries and displays it with the string
     *
     *
     * @param Countries
     */
    public void displayCountries(List<Country> Countries)
    {
        if (Countries == null)
        {
            System.out.println("Countries is empty");
            return;
        }
        //for every country in the countries list, print the details
        for (Country country : Countries)
        {
            if (country != null)
                System.out.println("Name: " + country.name + ", " + "Continent: " + country.continent + ", " + "Population: " + country.population + ", " + "Region: " + country.region + ", " + "CountryCode: " + country.code + ", " +  "CountryCapital: " + country.capital);
        }


    }

    /**
     * Take a list of countries and display the string plus total population
     *
     *
     * @param continentCountries
     */
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
    /**
     * Take a list of countries and display the string plus total population
     *
     *
     * @param RegionsCountries
     */

    public void displayRegionsCountries(List<Country> RegionsCountries) {
        //for every country in the countries list, print the details
        int total_pop = 0;
        if (RegionsCountries != null)
            for (Country country : RegionsCountries) {
                if (country != null) {
                    System.out.println("Name: " + country.name + ", " + "Region: " + country.region + ", " + "Population: " + country.population);
                    total_pop += country.population;
                }
            }

        System.out.println("===========================<Population of specified region>=========================");
        System.out.println("The total population is: " + total_pop);
    }

    /**
     * Take a list of cities and display the string plus total population
     *
     *
     * @param DistricsCity
     */

    public void displayDistricsCity(List<City> DistricsCity) {
        //for every city in the citys list, print the details
        int total_pop = 0;
        if (DistricsCity == null) {
            System.out.println("DistricsCity is empty");
            return;
        }

        for (City city : DistricsCity)
        {
            if (city != null) {
                System.out.println("Name: " + city.name + ", " + "District: " + city.district + ", " + "Population: " + city.population);
                total_pop += city.population;
            }
        }

        System.out.println("===========================<Population of specified distric>=========================");
        System.out.println("The total population is: " + total_pop);
    }
    /**
     * Take a list of cities and display the string plus total population
     *
     *
     * @param cityCity
     */


    public void displayCityCity(List<City> cityCity) {
        //for every city in the citys list, print the details
        int total_pop = 0;
        if (cityCity != null)
            for (City city : cityCity) {
                if (city != null) {
                    System.out.println("Name: " + city.name + ", " + "District: " + city.district + ", " + "Population: " + city.population);
                    total_pop += city.population;
                }

            }

        System.out.println("===========================<Population of specified City>=========================");
        System.out.println("The total population is: " + total_pop);
    }


}


