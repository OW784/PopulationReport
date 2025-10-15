# USE CASE: 1 Sort Geographic Entities by Population

## CHARACTERISTIC INFORMATION

### Goal in Context

As an End User I want to sort the countries of the world, continents, or regions by population size so that I can analyze population distribution and comparisons across different geographic entities.

### Scope

Global Data System.

### Level

Primary task.

### Preconditions

The system must have access to current population data for countries, continents, and regions.
The End User has access to the sorting interface.

### Success End Condition

A displayed list of geographic entities (countries, continents, or regions) is sorted accurately, either ascending or descending, based on population size.

### Failed End Condition

The list is not produced, or the sorting order is incorrect.

### Primary Actor

End User.

### Trigger

The End User selects a list of geographic entities for viewing and wishes to order them based on population.

## MAIN SUCCESS SCENARIO

1. End User selects the geographic scope (e.g., "Countries of the World," "All Continents," "A Specific Region").
2. End User selects the "Population" attribute as the sorting criterion.
3. End User selects the desired sorting order (Ascending or Descending).
4. The system executes the sorting operation on the list.
5. The system displays the sorted list to the End User.

## EXTENSIONS

1. **No data available for selected scope**:
    1. System displays an error message: "No population data available for the selected scope."
    2. Use Case ends.

2. **Sorting fails (e.g., data inconsistency)**:
    1. System displays an error message: "Sorting failed due to data error. Please try again."
    2. Use Case ends.

## SUB-VARIATIONS

- **Selecting geographic scope**: The End User may choose to filter the list further (e.g., sort European countries by population). The system applies the filter before sorting.
- **Sorting order selection**: The system may default to Descending order (highest population first), which the End User can accept or change.

## SCHEDULE

**DUE DATE**: Release 1.0
