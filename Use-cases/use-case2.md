# USE CASE: 2 Search Top Countries by Population

## CHARACTERISTIC INFORMATION

### Goal in Context

As an End User I want to search the top user-inputted number of highest population countries in the world, continents, or regions so that I can quickly identify and compare the most populous nations within a chosen geographic area.

### Scope

Global Data System.

### Level

Primary task.

### Preconditions

The system has access to current country population data.
The End User has specified a number (N>0) for the top countries.

### Success End Condition

A list containing the top N highest population countries within the specified geographic entity is displayed.

### Failed End Condition

No list is produced, or the list is incomplete or incorrectly ordered.

### Primary Actor

End User.

### Trigger

The End User requests a filtered list of countries based on population rank.

## MAIN SUCCESS SCENARIO

1. End User selects the geographic scope (World, Continent, or Region).
2. End User inputs the desired number (N) of top countries to display (e.g., 10, 15).
3. The system retrieves and sorts all countries within the selected scope by population (Descending).
4. The system limits the result set to the top N countries.
5. The system displays the resulting list to the End User.

## EXTENSIONS

1. **User inputs N=0 or invalid number**:
    1. System prompts End User to enter a valid number (N>0).
    2. Return to Step 2.

2. **Fewer than N countries exist in the scope**:
    1. System displays all available countries in that scope, along with a message indicating the full list is displayed.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0
