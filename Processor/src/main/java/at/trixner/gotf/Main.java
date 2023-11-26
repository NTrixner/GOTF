package at.trixner.gotf;

import at.trixner.gotf.model.GotfType;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class Main {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final ArrayList<GotfType> convertedTypes = new ArrayList<>();

    static {
        OBJECT_MAPPER.enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS);
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static void main(String[] args) throws IOException {
        URL resFolder = Main.class.getResource("/input");
        URL outFolder = Main.class.getResource("/output");

        File inputFolderFile = new File(resFolder.getPath());
        File outputFolderFile = new File(outFolder.getPath());

        if (!FileUtils.isEmptyDirectory(outputFolderFile)) {
            FileUtils.cleanDirectory(outputFolderFile);
        }

        //Parse and copy all tex and json files
        copyAndParseFiles(inputFolderFile, outputFolderFile);


        //Print the structure
        System.out.println(inputFolderFile.getName());
        if (inputFolderFile.isDirectory()) {
            printChildren(outputFolderFile.listFiles(), 0);
        }

    }

    private static void copyAndParseFiles(File inputFolderFile, File outputFolderFile) throws IOException {
        for (File f : inputFolderFile.listFiles()) {
            if (f.isDirectory()) {
                File newFolder = Files.createDirectory(Path.of(outputFolderFile.getPath(), f.getName())).toFile();
                copyAndParseFiles(f, newFolder);
            } else {
                if (f.getName().endsWith(".tex")) {
                    FileUtils.copyFile(f, Path.of(outputFolderFile.getPath(), f.getName()).toFile());
                } else if (f.getName().endsWith(".json")) {
                    File processed = parseFile(f);
                }
            }
        }
    }

    private static File parseFile(File f) {
        try {
            GotfType gotf = OBJECT_MAPPER.readValue(f, GotfType.class);
            convertedTypes.add(gotf);
        } catch (Exception e) {
            System.out.println("Error while trying to parse file " + f.getName() + ": " + e.getMessage());
            e.printStackTrace();
        }
        return f;
    }

    private static void printChildren(File[] files, int depth) {
        int newDepth = depth + 1;
        String placeholder = "-".repeat(newDepth);
        for (File f : files) {
            System.out.println(placeholder + f.getName());
            if (f.isDirectory()) {
                printChildren(f.listFiles(), newDepth);
            }
        }
    }
}