import ProjectScanning.ClassScanner;
import ProjectScanning.ScannerUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ClassScannerTest {
    private ClassScanner classScanner = new ClassScanner();
    private ScannerUtils scannerUtils = new ScannerUtils();

    @Test
    public void isInternalJavaClassTestPositive() throws IOException {
        File file = getFilePositive();
        boolean actualResult = classScanner.isInternalJavaClass(file);
        Assert.assertTrue(actualResult);
        file.delete();
    }

    @Test
    public void isInternalJavaClassTestNegativeNotClass() throws IOException {
        File file = getFileNegativeTestNotClass();
        boolean actualResult = classScanner.isInternalJavaClass(file);
        Assert.assertFalse(actualResult);
        file.delete();
    }

    @Test
    public void isInternalJavaClassTestNegativeNotJavaClass() throws IOException {
        File file = getFileNegativeNotJavaFile();
        boolean actualResult = classScanner.isInternalJavaClass(file);
        Assert.assertFalse(actualResult);
        file.delete();
    }

    private File getFilePositive() throws IOException {
        Path filePath = Paths.get("src/test/java/testFiles" + "/fileTestPositive.java");
        File file = new File(filePath.toString());
        List<String> lines = new ArrayList<>();
        lines.add("public class A {}");
        scannerUtils.writeNewContentToFile(file, lines);
        return file;
    }

    private File getFileNegativeTestNotClass() throws IOException {
        Path filePath = Paths.get("src/test/java/testFiles" + "/fileNegativeNotClass.java");
        File file = new File(filePath.toString());
        List<String> lines = new ArrayList<>();
        lines.add("public interface B {}");
        scannerUtils.writeNewContentToFile(file, lines);
        return file;
    }

    private File getFileNegativeNotJavaFile() throws IOException {
        Path filePath = Paths.get("src/test/java/testFiles" + "/fileNegativeNotJavaFile");
        File file = new File(filePath.toString());
        List<String> lines = new ArrayList<>();
        lines.add("public class A {}");
        scannerUtils.writeNewContentToFile(file, lines);
        return file;
    }
}
