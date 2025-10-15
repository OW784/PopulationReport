# USE CASE: 6 Search Top Capital Cities by Population

## CHARACTERISTIC INFORMATION

### Goal in Context

As an End User I want to search the top user-inputted number of highest population capital cities in the world, continents, or regions so that I can quickly identify the largest political centers in a chosen geographic area.

### Scope

Global Data System.

### Level

Primary task.

### Preconditions

The system has access to current capital city population data.
The End User has specified a number (N>0) for the top capital cities.

### Success End Condition

A list containing the top N highest population capital cities within the specified geographic entity is displayed.

### Failed End Condition

No list is produced, or the list is incomplete or incorrectly ordered.

### Primary Actor

End User.

### Trigger

The End User requests a filtered list of capital cities based on population rank.

## MAIN SUCCESS SCENARIO

1. End User selects the geographic scope (World, Continent, or Region).
2. End User inputs the desired number (N) of top capital cities to display (e.g., 10, 15).
3. The system retrieves and sorts all capital cities within the selected scope by population (Descending).
4. The system limits the result set to the top N capital cities.
5. The system displays the resulting list to the End User.

## EXTENSIONS

1. **User inputs N=0 or invalid number**:
    1. System prompts End User to enter a valid number (N>0).
    2. Return to Step 2.

2. **Fewer than N capital cities exist in the scope**:
    1. System displays all available capital cities in that scope.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0
