package at.trixner.gotf;

import at.trixner.gotf.mapper.MultiPerkToTemplate;
import at.trixner.gotf.mapper.PerkToTemplate;
import at.trixner.gotf.model.structure.GotfType;
import at.trixner.gotf.model.perks.MultiPerk;
import at.trixner.gotf.model.perks.Perk;
import at.trixner.gotf.texModel.TemplatePerk;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class Main {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final ArrayList<GotfType> convertedTypes = new ArrayList<>();

    private static final Configuration cfg = new Configuration();

    static {
        OBJECT_MAPPER.enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS);
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        cfg.setClassForTemplateLoading(Main.class, "/templates");
        cfg.setIncompatibleImprovements(new Version(2, 3, 20));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setLocale(Locale.of("en", "AT"));
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
                    String targetFileName = f.getName();
                    targetFileName = targetFileName.substring(0, targetFileName.lastIndexOf(".json")) + ".tex";
                    File targetFile = Path.of(outputFolderFile.getPath(), targetFileName).toFile();
                    File processed = parseFile(f, targetFile);
                }
            }
        }
    }

    private static File parseFile(File f, File targetFile) {
        try {
            GotfType gotf = OBJECT_MAPPER.readValue(f, GotfType.class);
            if(gotf instanceof MultiPerk perk)
            {
                List<TemplatePerk> templatePerks = MultiPerkToTemplate.map(perk, new HashMap<>());
                Writer fileWriter = new FileWriter(targetFile);
                for(TemplatePerk templatePerk : templatePerks)
                {
                    Template template = cfg.getTemplate("perk.fmk");
                    template.process(templatePerk, fileWriter);
                }
                return targetFile;
            }
            if(gotf instanceof Perk perk)
            {
                TemplatePerk templatePerk = PerkToTemplate.map(perk, new HashMap<>());
                Template template = cfg.getTemplate("perk.fmk");
                Writer fileWriter = new FileWriter(targetFile);
                template.process(templatePerk, fileWriter);
                return targetFile;
            }
            convertedTypes.add(gotf);
        } catch (Exception e) {
            System.out.println("Error while trying to parse file " + f.getName() + ": " + e.getMessage());
            e.printStackTrace();
        }
        return null;
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