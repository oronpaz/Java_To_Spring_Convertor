import ProjectScanning.BeanDetails;
import ProjectScanning.InnerClassDetails;
import ProjectScanning.ScanChecker;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScanCheckerTest {
    private ScanChecker scanChecker = new ScanChecker();

    @Test
    public void isCreationWithReturnStatementTestPositive() {
        boolean actualResult = scanChecker.isCreationWithReturnStatement(getBeanDetailsISCreationWithReturnStatementPositive());
        Assert.assertTrue(actualResult);
    }

    @Test
    public void isCreationWithReturnStatementTestNegative() {
        boolean actualResult = scanChecker.isCreationWithReturnStatement(getBeanDetailsISCreationWithReturnStatementNegative());
        Assert.assertFalse(actualResult);
    }

    @Test
    public void isInternalClassTestPositive() {
        Map<String, InnerClassDetails> testMap = new HashMap<>();
        InnerClassDetails innerClassDetails1 = new InnerClassDetails("Path1", null);
        testMap.put("A", innerClassDetails1);
        InnerClassDetails innerClassDetails2 = new InnerClassDetails("Path2", "model");
        testMap.put("B", innerClassDetails2);

        boolean actualResult = scanChecker.isInternalClass("A", testMap);
        Assert.assertTrue(actualResult);
    }

    @Test
    public void isInternalClassTestNegative() {
        Map<String, InnerClassDetails> testMap = new HashMap<>();
        InnerClassDetails innerClassDetails1 = new InnerClassDetails("Path1", null);
        testMap.put("A", innerClassDetails1);
        InnerClassDetails innerClassDetails2 = new InnerClassDetails("Path2", "model");
        testMap.put("B", innerClassDetails2);

        boolean actualResult = scanChecker.isInternalClass("C", testMap);
        Assert.assertFalse(actualResult);
    }

    @Test
    public void checkIFNewInstanceIsBlackListTestPositive() {
        List<String> classLines = geClassLineForBlackListTest();
        boolean actualResult = scanChecker.checkIFNewInstanceIsBlackList(classLines, 3);
        Assert.assertTrue(actualResult);
    }

    @Test
    public void checkIFNewInstanceIsBlackListTestNegative() {
        List<String> classLines = geClassLineForBlackListTest();
        boolean actualResult = scanChecker.checkIFNewInstanceIsBlackList(classLines, 4);
        Assert.assertFalse(actualResult);
    }

    private List<String> geClassLineForBlackListTest() {
        List<String> lines = new ArrayList<>();
        lines.add("public class A {\n");
        lines.add("\n");
        lines.add("@BlackList\n");
        lines.add("Actor actor1 = new Actor(40, Tom Henks);");
        lines.add("}\n");

        return lines;

    }

    private BeanDetails getBeanDetailsISCreationWithReturnStatementPositive() {
        return new BeanDetails("A", "var", null, "38, Brad Pit", "return new Actor(38, Brad Pit);", new ArrayList<>());
    }

    private BeanDetails getBeanDetailsISCreationWithReturnStatementNegative() {
        return new BeanDetails("A", "var", null, "38, Brad Pit", "Actor var = new Actor(38, Brad Pit);", new ArrayList<>());
    }
}
