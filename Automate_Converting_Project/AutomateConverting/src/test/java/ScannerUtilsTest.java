import ProjectScanning.InnerClassDetails;
import ProjectScanning.ScannerUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScannerUtilsTest {
    private ScannerUtils scannerUtils = new ScannerUtils();

    @Test
    public void replaceLinesTest() {
        List<String> allLines = getAllLinesForTest();
        String lineToRemove = "Hello second line";
        String lineToAdd = "Hello second line - new";
        scannerUtils.replaceLines(lineToRemove, lineToAdd, allLines);
        validateNewLineExistsAndOldLineRemoved(lineToRemove, lineToAdd, allLines);
    }

    @Test
    public void getDisplayClassNameTest() {
        String actualResult = scannerUtils.getDisplayClassName("engine.getType()");
        Assert.assertEquals("engine", actualResult);
    }

    @Test
    public void getConfigurationFileByClassNameTest() throws IOException {
        Map<String, InnerClassDetails> classNameToPath = new HashMap<>();
        InnerClassDetails innerClassDetails1 = new InnerClassDetails("src/test/java/testFiles" + "/MainConfiguration.java", null);
        classNameToPath.put("MainConfiguration", innerClassDetails1);
        InnerClassDetails innerClassDetails2 = new InnerClassDetails("src/test/java/testFiles" + "/ModelConfiguration.java", "model");
        classNameToPath.put("ModelConfiguration", innerClassDetails2);
        InnerClassDetails innerClassDetails3 = new InnerClassDetails("src/test/java/testFiles" + "/A.java", null);
        classNameToPath.put("A", innerClassDetails3);
        InnerClassDetails innerClassDetails4 = new InnerClassDetails("src/test/java/testFiles" + "/B.java", "model");
        classNameToPath.put("B", innerClassDetails4);
        File[] files = createConfigurationFiles();
        File file1Result = scannerUtils.getConfigurationFileByClassName(classNameToPath, "A");
        File file2Result = scannerUtils.getConfigurationFileByClassName(classNameToPath, "B");
        Assert.assertEquals("MainConfiguration.java", file1Result.getName());
        Assert.assertEquals("ModelConfiguration.java", file2Result.getName());
        files[0].delete();
        files[1].delete();
    }


    private File[] createConfigurationFiles() throws IOException {
        Path filePath1 = Paths.get("src/test/java/testFiles" + "/MainConfiguration.java");
        File file1 = new File(filePath1.toString());
        List<String> lines1 = new ArrayList<>();
        lines1.add("import org.springframework.context.annotation.Configuration;\n");
        lines1.add("@Configuration\n");
        lines1.add("public class MainConfiguration {}");
        scannerUtils.writeNewContentToFile(file1, lines1);

        Path filePath2 = Paths.get("src/test/java/testFiles" + "/ModelConfiguration.java");
        File file2 = new File(filePath2.toString());
        List<String> lines2 = new ArrayList<>();
        lines2.add("import org.springframework.context.annotation.Configuration;\n");
        lines2.add("@Configuration\n");
        lines2.add("public class ModelConfiguration {}");
        scannerUtils.writeNewContentToFile(file2, lines2);

        File[] files = new File[2];
        files[0] = file1;
        files[1] = file2;
        return files;
    }

    private void validateNewLineExistsAndOldLineRemoved(String lineToRemove, String lineToAdd, List<String> allLines) {
        boolean lineRemoved = false;
        boolean lineAdded = false;

        for(String line : allLines) {
            if(line.equals(lineToAdd)) {
                lineAdded = true;
            }
            if(line.equals(lineToRemove)) {
                Assert.assertFalse(true);
            }
        }

        lineRemoved = true;

        Assert.assertTrue(lineAdded && lineRemoved);
    }

    private List<String> getAllLinesForTest() {
        List<String> lines = new ArrayList<>();
        lines.add("Hello first line");
        lines.add("Hello second line");
        lines.add("Hello third line");
        return lines;
    }
}
