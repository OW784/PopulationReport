# USE CASE: 12 Produce Capital City Report

## CHARACTERISTIC INFORMATION

### Goal in Context

As an End User I want to produce a capital city report that displays Name, Country, and Population so that I can get a summary of key facts for a specific national capital.

### Scope

Global Data System.

### Level

Primary task.

### Preconditions

The system has access to all required capital city-level data (Name, Country, Population).
The city is known to be a capital city.

### Success End Condition

A formatted report containing the required attributes for the requested capital city is displayed.

### Failed End Condition

The capital city report cannot be generated or is missing key data fields.

### Primary Actor

End User.

### Trigger

The End User requests a detailed report on a specific capital city (e.g., "Produce a report on London").

## MAIN SUCCESS SCENARIO

1. End User selects or inputs the name of the desired capital city.
2. The system validates the city name and retrieves all required attributes: Name, Country, and Population.
3. The system verifies the city is a capital city (optional check).
4. The system formats the retrieved data into a clear report structure.
5. The system displays the Capital City Report to the End User.

## EXTENSIONS

1. **City name is ambiguous or not found**:
    1. System prompts End User for clarification or displays an error message: "Capital city not found. Please clarify the country."
    2. Use Case ends.

2. **City found but is NOT a capital**:
    1. System displays a message: "[City Name] is a city, but not a capital. Use the City Report function instead."

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0
