package at.trixner.gotf.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

public class JsonPreProcessor {
    public static String getResolvedJson(Path inputPath, int maxReferenceDepth) {
        try {
            HashMap<Path, String> fileMap = getJsonFileList(inputPath);
            String baseString = readFile(inputPath.toString());
            return getResolvedJson(baseString, inputPath, maxReferenceDepth, fileMap);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String readFile(String filePath) throws IOException {
        return Files.readString(Path.of(filePath));
    }

    private static String getResolvedJson(String input, Path currentPath, int maxReferenceDepth, HashMap<Path, String> fileMap) throws IOException {
        if(maxReferenceDepth == 0 || input == null || input.isEmpty()) {
            return "{}";
        }
        int index = -1;
        String currentFolder = currentPath.getParent().toString();
        while((index = input.indexOf("\"$ref(")) != -1) {
            int endIndex = input.indexOf(")\"", index);
            if(endIndex == -1) {
                throw new RuntimeException("You didn't close the reference bingus! The file was " + currentPath);
            }
            String pathToRef = input.substring(index+6, endIndex);
            String beforeString = input.substring(0, index);
            String afterString = input.substring(endIndex + 2);
            Path refFile = Path.of(currentFolder, pathToRef);
            String readFile = fileMap.get(refFile.toRealPath());
            String resolvedRef = getResolvedJson(readFile, refFile, maxReferenceDepth - 1, fileMap);
            input = beforeString + resolvedRef + afterString;
        }
        return input;
    }

    private static HashMap<Path, String> getJsonFileList(Path path) throws IOException {
        HashMap<Path, String> fileMap = new HashMap<>();
        File file = new File(String.valueOf(path));
        File dir = file.getParentFile();
        addJsonsToHashmap(path.getParent(), dir, fileMap);

        return fileMap;
    }

    private static void addJsonsToHashmap(Path path, File dir, HashMap<Path, String> fileMap) throws IOException {
        for(File file : dir.listFiles()) {
            if(file.getName().endsWith(".json")) {
                fileMap.put(Path.of(path.toString(), file.getName()).toRealPath(), readFile(file.getPath()));
            } else if(file.isDirectory()) {
                addJsonsToHashmap(Path.of(path.toString(), file.getName()), file, fileMap);
            }
        }
    }
}
