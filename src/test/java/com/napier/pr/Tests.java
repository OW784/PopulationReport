package com.napier.pr;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class Tests {

    static Display display;

    @BeforeAll
    static void init()
    {
        display = new Display();
    }

    @Test
    void displayCountryTestEmpty()
    {
        List<Country> countries = new ArrayList<>();
        display.displayCountries(countries);

    }

    @Test
    void displayCountryTestNull()
    {
        List<Country> countries = new ArrayList<>();
        countries.add(null);
        display.displayCountries(countries);
    }

    @Test
    void displayCountry()
    {
        List<Country> countries = new ArrayList<>();
        Country country = new Country();
        country.code = "ABC";
        country.name = "China";
        country.population = 1;
        country.capital = "Beijing";
        country.region = "China";
        countries.add(country);
        display.displayCountries(countries);

    }

    @Test
    void displaydisrictscityTestEmpty()
    {
        List<City> citycity = new ArrayList<>();
        display.displayDistricsCity(citycity);
    }

    @Test
    void displaydistrictscityTestNull()
    {
        List<City> districtcity = new ArrayList<>();
        districtcity.add(null);
        display.displayDistricsCity(districtcity);
    }

    void displaydistrictcity()
    {
        List<City> districtcity = new ArrayList<>();
        City city = new City();
        city.population = 1;
        city.district = "China";
        city.id= 1;
        city.name = "Beijing";
        city.countryCode = 1234;

        districtcity.add(city);
    }




}
