package com.napier.pr;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;


public class IntegrationTest
{
    static Main app;

    @BeforeAll
    static void init()
    {
        app = new Main();
        app.connect("localhost:33060", 30000);
    }

    @Test
    void testGetCountriesByContinentAndPopulation()
    {
        List<Country> countries = app.getCountriesByContinentAndPopulation("Europe");
        
        assertNotNull(countries);

        assertFalse(countries.isEmpty());

        assertEquals("Russian Federation", countries.get(0).name);

    }

    @Test
    void testgetCountriesByPopulationLtS()
    {
        List<Country> countries = app.getCountriesByPopulationLtS();
        assertNotNull(countries);
        assertFalse(countries.isEmpty());

        Assertions.assertEquals("China", countries.get(0).name);

    }




}





