package common;

import org.yaml.snakeyaml.Yaml;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

public class FileUtils {

    public static HashMap<String, Object> readYamlFile(String fileName, String resourceFolder) {
        String filePath = System.getProperty(Constants.USER_DIR_CONST) + File.separator + "src" + File.separator + "test"
                + File.separator + "resources" + File.separator + resourceFolder + File.separator + fileName;
        Path path = Path.of(filePath);
        if(!Files.exists(path)) {
            throw new RuntimeException(filePath + " unavailable at specified path");
        }
        try (InputStream inputStream = Files.newInputStream(path)) {
            Yaml yaml = new Yaml();
            return yaml.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("Error reading the yaml file: " + filePath);
        }
    }
}
