# USE CASE: 8 Display Geographic Entity Population

## CHARACTERISTIC INFORMATION

### Goal in Context

As an End User I want to display the population of either the world, continents, regions, districts, and cities so that I can get an immediate figure for the size of a specific geographic entity.

### Scope

Global Data System.

### Level

Primary task.

### Preconditions

The system has access to current population data for the world, continents, regions, districts, and cities.

### Success End Condition

The system displays the current total population figure for the requested geographic entity.

### Failed End Condition

The population figure cannot be retrieved or displayed.

### Primary Actor

End User.

### Trigger

The End User asks a population query for a specific location (e.g., "What is the population of New Mexico?").

## MAIN SUCCESS SCENARIO

1. End User selects or inputs the name/type of the geographic entity (World, Continent, Region, District, or City).
2. The system identifies the specific entity and its type.
3. The system retrieves the current total population for the selected entity.
4. The system displays the population figure to the End User.

## EXTENSIONS

1. **Geographic entity does not exist or is ambiguous**:
    1. System prompts End User for clarification or displays an error message: "Entity not found or ambiguous. Please specify type (e.g., state, city)."
    2. Use Case ends.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0
