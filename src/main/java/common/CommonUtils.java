package common;

import org.testng.ITestContext;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CommonUtils {

    public static Map<String, String> environmentAttributes;
    public static String environment;

    @SuppressWarnings("unchecked")
    public static void readEnvironmentData(ITestContext context) {
        environment = context.getCurrentXmlTest().getParameter("environment");
        environment = (environment == null || environment.equals("$environment")) ? Constants.DEFAULT_ENVIRONMENT.toUpperCase()
                : environment.toUpperCase();
        System.out.println("Reading the environment details for: " + environment);
        HashMap<String, Object> envData = FileUtils.readYamlFile("Environment.yaml", "config");
        environmentAttributes = Optional.ofNullable((HashMap<String, String>) envData.get(environment)).
                orElseThrow(() -> new RuntimeException("Unable to read the environment details"));
    }
}