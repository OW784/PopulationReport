# USE CASE: 3 Sort Cities by Population

## CHARACTERISTIC INFORMATION

### Goal in Context

As an End User I want to sort the cities of the world, continents, or regions by population size so that I can analyze and compare the urbanization levels of different cities in a chosen area.

### Scope

Global Data System.

### Level

Primary task.

### Preconditions

The system must have access to current city population data.
The End User has access to the sorting interface.

### Success End Condition

A displayed list of cities is accurately sorted, either ascending or descending, based on population size within the specified scope.

### Failed End Condition

The list is not produced, or the sorting order is incorrect.

### Primary Actor

End User.

### Trigger

The End User selects a list of cities for viewing and wishes to order them based on population.

## MAIN SUCCESS SCENARIO

1. End User selects the geographic scope (World, Continent, or Region).
2. End User selects the "Population" attribute as the sorting criterion.
3. End User selects the desired sorting order (Ascending or Descending).
4. The system executes the sorting operation on the filtered list of cities.
5. The system displays the sorted list to the End User.

## EXTENSIONS

1. **No city data available for selected scope**:
    1. System displays an error message: "No city population data available for the selected scope."
    2. Use Case ends.

## SUB-VARIATIONS

- **Selecting geographic scope**: The End User may choose to filter the list by Country (e.g., sort cities in France). The system applies the filter before sorting.

## SCHEDULE

**DUE DATE**: Release 1.0
