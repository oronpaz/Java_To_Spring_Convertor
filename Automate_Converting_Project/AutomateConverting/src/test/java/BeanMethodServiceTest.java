import ProjectScanning.BeanMethodService;
import ProjectScanning.ScannerUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BeanMethodServiceTest {
    private BeanMethodService beanMethodService = new BeanMethodService();
    private ScannerUtils scannerUtils = new ScannerUtils();

    @Test
    public void addListImportsToConfFileTestPositive() throws IOException {
        File file = getConfFile();
        beanMethodService.addListImportsToConfFile(file);
        List<String> allLines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
        boolean isListImportsExists = checkIfListImportsExists(allLines);
        Assert.assertTrue(isListImportsExists);
        file.delete();
    }

    @Test
    public void addScopeImportToConfFileTestPositive() throws IOException {
        File file = getConfFile();
        beanMethodService.addScopeImportToConfFile(file);
        List<String> alLines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
        boolean isScopeImportExists = checkIfScopeImportExists(alLines);
        Assert.assertTrue(isScopeImportExists);
        file.delete();
    }

    private File getConfFile() throws IOException {
        Path filePath = Paths.get("src/test/java/testFiles" + "/ConfigurationFile.java");
        File file = new File(filePath.toString());
        List<String> lines = new ArrayList<>();
        lines.add("import org.springframework.context.annotation.Configuration;\n");
        lines.add("@Configuration\n");
        lines.add("public class MainConfiguration {" +
                "" +
                "}\n");

        scannerUtils.writeNewContentToFile(file, lines);
        return file;
    }

    @Test
    public void addNewConfPackageFile() {
        String element1 = "MainConfiguration";
        String element2 = "weatherReportsPackageConfiguration";
        beanMethodService.addNewConfFile(element1);
        beanMethodService.addNewConfFile(element2);
        Map<String, Boolean> result = beanMethodService.getConfFileNameToIsBeanAdded();

        Boolean result1 = result.get(element1);
        Assert.assertFalse(result1);

        Boolean result2 = result.get(element2);
        Assert.assertFalse(result2);
    }

    private boolean checkIfListImportsExists(List<String> allLines) {
        boolean listFound = false;
        boolean arrayListFound = false;

        for(String line : allLines) {
            if(line.contains("import java.util.List;")) {
                listFound = true;
            }
            if(line.contains("import java.util.ArrayList;")) {
                arrayListFound = true;
            }
        }

        return listFound && arrayListFound;
    }

    private boolean checkIfScopeImportExists(List<String> alLines) {
        for(String line : alLines) {
            if(line.contains("import org.springframework.context.annotation.Scope;")) {
                return true;
            }
        }
        return false;
    }
}
