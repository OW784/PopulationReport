# USE CASE: 7 Display Population Split

## CHARACTERISTIC INFORMATION

### Goal in Context

As an End User I want to display the total population, urban population, and rural population of each continent, region, or country so that I can analyze the urbanization levels and distribution of people in a given area.

### Scope

Global Data System.

### Level

Primary task.

### Preconditions

The system has access to current and accurate total, urban, and rural population data for continents, regions, and countries.

### Success End Condition

A report or display shows the total, urban, and rural populations for the requested geographic entity.

### Failed End Condition

The population breakdown data cannot be retrieved or displayed.

### Primary Actor

End User.

### Trigger

The End User requests the population split information for a specific continent, region, or country (e.g., "What is the population split of Russia?").

## MAIN SUCCESS SCENARIO

1. End User selects or inputs the name of the geographic entity (Continent, Region, or Country).
2. The system retrieves the total population, urban population, and rural population data for the selected entity.
3. The system displays the three population figures to the End User.

## EXTENSIONS

1. **Geographic entity does not exist or is invalid**:
    1. System displays an error message: "Entity not found or not supported for population split report."
    2. Use Case ends.

2. **Missing urban/rural data for the entity**:
    1. The system displays the available data (e.g., only Total Population) and a message indicating missing breakdown data.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0
