# USE CASE: 5 Order Capital Cities by Population

## CHARACTERISTIC INFORMATION

### Goal in Context

As an End User I want to order capital cities by population of the world, continents, or regions so that I can compare the relative sizes of political and administrative centers.

### Scope

Global Data System.

### Level

Primary task.

### Preconditions

The system must have access to current capital city population data.
The list of capital cities for the selected scope is known.

### Success End Condition

A displayed list of capital cities is accurately sorted, either ascending or descending, based on population size within the specified scope.

### Failed End Condition

The list is not produced, or the sorting order is incorrect.

### Primary Actor

End User.

### Trigger

The End User requests to view and sort the capital cities of a selected geographic area.

## MAIN SUCCESS SCENARIO

1. End User selects the geographic scope (World, Continent, or Region).
2. End User selects the "Population" attribute as the sorting criterion.
3. End User selects the desired sorting order (Ascending or Descending).
4. The system retrieves the list of capital cities for the selected scope.
5. The system executes the sorting operation on the list.
6. The system displays the sorted list to the End User.

## EXTENSIONS

1. **No capital city data available for selected scope**:
    1. System displays an error message: "No capital city population data available for the selected scope."
    2. Use Case ends.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0
