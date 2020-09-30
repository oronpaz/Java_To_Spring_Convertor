package ProjectScanning;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ClassScanner {
    private ScanChecker scanChecker;
    private ScannerUtils scannerUtils;

    public ClassScanner() {
        scanChecker = new ScanChecker();
        scannerUtils = new ScannerUtils();
    }

    private boolean isLineISBlackListOnMethod(String line, List<String> allLines) {
        return line.contains("@BlackList") && scanChecker.isLineUnderIsMethod(line, allLines);
    }

    public void handleInternalClassScan(String createsClass, String classPath, Map<String, InnerClassDetails> classNameToClassDetails, Map<String, BeanDetails> instanceNameToBeanDetails, Set<String> classesToAddAppCox) throws IOException {
        boolean ignore = false;
        Map<String, String> replaceLineWithNew = new HashMap<>();
        File cFile = new File(classPath);
        List<String> allLines = Files.readAllLines(cFile.toPath(), StandardCharsets.UTF_8);
        int index = 0;
        List<Integer> linesToRemove = new ArrayList<>();

        for(String line : allLines) {
            if(isLineISBlackListOnMethod(line, allLines)) {
                linesToRemove.add(index);
                ignore = true;
            }
            else {
                if(ignore) {
                    if (line.contains("}")) {
                        ignore = false;
                        continue;
                    }
                }
                else {
                    if(line.contains("new") && !line.contains("Exception")) {
                        if(scanChecker.checkIFNewInstanceIsBlackList(allLines, index)) {
                            linesToRemove.add(index - 1);
                            index++;
                            continue;
                        }
                        BeanDetails beanDetails = findAllInstanceElements(line, allLines);

                        if(scanChecker.isInternalClass(beanDetails.getClassName(), classNameToClassDetails)) {
                            beanDetails.setDataStructure(scanChecker.isDataStructureInstance(line));
                            scanChecker.checkIfBeanIsInsideTryCatchOrMethodWithExceptionThrowing(allLines, beanDetails, index);
                            scannerUtils.setIfBeanIsLazy(beanDetails, classNameToClassDetails);
                            beanDetails.setConfigurationFilePath(classNameToClassDetails.get("MainConfiguration").getPath());
                            beanDetails.setCreatedUnderClass(createsClass);
                            classesToAddAppCox.add(createsClass);
                            instanceNameToBeanDetails.put(beanDetails.getInstanceName(), beanDetails);
                            replaceLineWithNew.put(line, scannerUtils.getBeanAccessStrFromBeanDetails(beanDetails));
                            beanDetails.setPrototypeInst(scanChecker.checkIfInstancePrototype(allLines, index));
                            if(beanDetails.isPrototypeInst()) {
                                linesToRemove.add(index - 1);
                            }
                        }
                    }
                }
                index++;
            }
        }

        int counter = 0;
        //remove lines class don't need any more
        for(Integer indexBlack : linesToRemove) {
            allLines.remove(indexBlack - counter);
            counter++;
        }
        //replace lines with new bean access
        for(Map.Entry<String, String> item : replaceLineWithNew.entrySet()) {
            scannerUtils.replaceLines(item.getKey(), item.getValue(), allLines);
        }
        //Update class file
        scannerUtils.writeNewContentToFile(cFile, allLines);
    }

    private BeanDetails findAllInstanceElements(String line, List<String> allLines) {
        String temp;
        String className;
        String instanceName;
        String implName;
        String constructorArgs;

        temp = line.trim();
        temp = temp.replace(", ", ",");
        String[] elements = temp.split(" ");

        if(elements.length == 6) {
            if(temp.contains("private") || temp.contains("protected")) {
                if(elements[1].contains("<")) {
                    className = elements[1].substring(elements[1].indexOf("<") + 1, elements[1].indexOf(">"));
                }
                else {
                    className = elements[1];
                }
                instanceName = elements[2];

                return new BeanDetails(className, instanceName, elements[5].split("\\(")[0], line.substring(line.indexOf("(") + 1, line.indexOf(")")), line, allLines);
            }
            else {
                className = elements[0];
                instanceName = elements[1];
                return new BeanDetails(className, instanceName, className, temp.substring(temp.indexOf("(") + 1, temp.indexOf(")")), line, allLines);
            }

        }
        else if(elements.length == 5) {
            if(elements[0].contains("<")) {
                className = elements[0].substring(elements[0].indexOf("<") + 1, elements[0].indexOf(">"));
            }
            else {
                className = elements[0];
            }
            instanceName = elements[1];
            return new BeanDetails(className, instanceName, elements[4].split("\\(")[0], line.substring(line.indexOf("(") + 1, line.indexOf(")")), line, allLines);
        }
        else if(elements.length == 3) {
            if(elements[2].contains("<")) {
                className = elements[2].substring(elements[2].indexOf("<") + 1, elements[2].indexOf(">"));
            }
            else {
                className = elements[2].substring(0, elements[2].indexOf("("));
            }
            instanceName = scannerUtils.changeFirstLetterToLower(className);
            return new BeanDetails(className, instanceName, elements[2].split("\\(")[0], line.substring(line.indexOf("(") + 1, line.indexOf(")")), line, allLines);
        }
        //handle data structure
        className = elements[0];
        instanceName = elements[1];
        try {
            if(elements.length <=4) {
                implName = elements[3].split("\\(")[0];
            }
            else {
                implName = elements[4].split("\\(")[0];
            }
            constructorArgs = line.substring(line.indexOf("(") + 1, line.indexOf(")"));

            return new BeanDetails(className, instanceName, implName, constructorArgs, line, allLines);
        }
        catch (Exception ex) {
            return new BeanDetails(className, instanceName, null, null, line, allLines);
        }
    }

    public boolean isInternalJavaClass(File currFile) throws IOException {
        if(!currFile.getName().endsWith(".java")) {
            return false;
        }
        else {
            List<String> allLines = Files.readAllLines(currFile.toPath(), StandardCharsets.UTF_8);
            for(String line : allLines) {
                if(line.contains("public class")) {
                    return true;
                }

            }
            return false;
        }
    }

}
