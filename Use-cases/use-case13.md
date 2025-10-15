# USE CASE: 13 Produce Detailed Population Report

## CHARACTERISTIC INFORMATION

### Goal in Context

As an End User I want to produce a population report on a requested continent, region, or country that displays total population, total urban population including percentage of total, and total rural population including percentage of total so that I can perform detailed demographic analysis, particularly on urbanization rates.

### Scope

Global Data System.

### Level

Primary task.

### Preconditions

The system has access to current total, urban, and rural population data for continents, regions, and countries.
The system can calculate percentages accurately.

### Success End Condition

A detailed report displaying total, urban (with percentage), and rural (with percentage) populations for the requested entity is displayed.

### Failed End Condition

The report cannot be generated, or the percentage calculations are incorrect.

### Primary Actor

End User.

### Trigger

The End User requests a detailed population breakdown (e.g., "Produce a population report on England").

## MAIN SUCCESS SCENARIO

1. End User selects or inputs the name of the geographic entity (Continent, Region, or Country).
2. The system retrieves the Total Population, Urban Population, and Rural Population data.
3. The system calculates:
   - Urban Percentage = (Urban Population/Total Population) × 100
   - Rural Percentage = (Rural Population/Total Population) × 100
4. The system formats the data, including the calculated percentages, into a clear report structure.
5. The system displays the Detailed Population Report to the End User.

## EXTENSIONS

1. **Geographic entity does not exist or is invalid**:
    1. System displays an error message: "Entity not found or not supported for detailed population report."
    2. Use Case ends.

2. **Missing urban/rural data for the entity**:
    1. The system displays the Total Population and a message indicating that the urban/rural split cannot be calculated.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0
