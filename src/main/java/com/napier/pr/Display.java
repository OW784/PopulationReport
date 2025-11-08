package com.napier.pr;

import java.util.List;

public class Display
{
    public void displayCountries(List<Country> Countries)
    {
        //for every country in the countries list, print the details
        if (Countries != null)
            for (Country country : Countries)
            {
                System.out.println("Name: " + country.name + ", " + "Continent: " + country.continent + ", " + "Population: " + country.population);
            }
    }
}
