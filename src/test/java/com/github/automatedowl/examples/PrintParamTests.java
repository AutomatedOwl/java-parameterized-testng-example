package com.github.automatedowl.examples;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class PrintParamTests extends InventoryTestCase {

    @Test
    @Parameters("test-parameter")
    void printParamTest(String testParam)  {
        logger.info("Received parameter: " + testParam);
    }
}
