# USE CASE: 10 Produce Country Report

## CHARACTERISTIC INFORMATION

### Goal in Context

As an End User I want to produce a country report that displays Code, Name, Continent, Region, Population, and Capital so that I can get a comprehensive summary of key demographic and geographic facts for a specific nation.

### Scope

Global Data System.

### Level

Primary task.

### Preconditions

The system has access to all required country-level data (Code, Name, Continent, Region, Population, Capital).

### Success End Condition

A formatted report containing the required attributes for the requested country is displayed.

### Failed End Condition

The country report cannot be generated or is missing key data fields.

### Primary Actor

End User.

### Trigger

The End User requests a detailed report on a specific country (e.g., "Produce a report on The United Kingdom").

## MAIN SUCCESS SCENARIO

1. End User selects or inputs the name of the desired country.
2. The system validates the country name and retrieves all required attributes: Code, Name, Continent, Region, Population, and Capital.
3. The system formats the retrieved data into a clear report structure.
4. The system displays the Country Report to the End User.

## EXTENSIONS

1. **Country name is invalid or not found**:
    1. System displays an error message: "Country not found or spelling is incorrect. Please try again."
    2. Use Case ends.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0
