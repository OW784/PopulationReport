# USE CASE: 11 Produce City Report

## CHARACTERISTIC INFORMATION

### Goal in Context

As an End User I want to produce a city report that displays Name, Country, District, and Population so that I can get a summary of key location and demographic facts for a specific city.

### Scope

Global Data System.

### Level

Primary task.

### Preconditions

The system has access to all required city-level data (Name, Country, District, Population).

### Success End Condition

A formatted report containing the required attributes for the requested city is displayed.

### Failed End Condition

The city report cannot be generated or is missing key data fields.

### Primary Actor

End User.

### Trigger

The End User requests a detailed report on a specific city (e.g., "Produce a report on Glasgow").

## MAIN SUCCESS SCENARIO

1. End User selects or inputs the name of the desired city.
2. The system validates the city name and retrieves all required attributes: Name, Country, District, and Population.
3. The system formats the retrieved data into a clear report structure.
4. The system displays the City Report to the End User.

## EXTENSIONS

1. **City name is ambiguous or not found**:
    1. System prompts End User for clarification (e.g., which country/district?) or displays an error message: "City not found. Please clarify the country or district."
    2. Use Case ends.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0
