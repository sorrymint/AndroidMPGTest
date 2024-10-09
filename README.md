# Project 1 Automated Tests

Given the MPGStarter project add automated testing. 
Using both local and Instrumented tests. 
Every method and Composable screen element should be covered by the tests. 
Turn in completed app with tests.

## Local

Build local tests for the calculateMPG() method. 
These tests should check several different values and assert the methods output.
Make sure to check for negative values (method should return 0). 
These tests should not use any UI elements and should utilize JUnit.

## Instrumented

Build Instrumented tests for all on screen composable.

-   DistanceInput() enter a value and assert that the text inside the TextField() changed.
-   FuelConsumedInput() enter a value and assert that the text inside the TextField() changed.
-   CalculateButton() enter a value into both distance and fuel consumed fields and assume the output field has the correct calculated value. You will need to use the preformClick() method.

## UI

User interface is given and look like this. 

<img src="./instructions/UI.png" alt="UI Example" style="width: 150px">

## Rubric

| Task                    | Points |
|-------------------------|--------|
| Local Tests             | 5pts   |
| Instrumented Tests (UI) | 15pts  |
