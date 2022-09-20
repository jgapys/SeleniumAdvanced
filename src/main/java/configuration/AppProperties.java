package configuration;

import models.Browser;
import models.EnvironmentModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class AppProperties {
    private static final Logger logger = LoggerFactory.getLogger(AppProperties.class);
    YamlReader yamlReader = new YamlReader();
    private Browser browser;
    private List<EnvironmentModel> listOfEnvironments;

    public AppProperties() {
        setBrowserProperties();
        setEnvironmentProperties();
    }

    public static AppProperties getInstance() {
        return AppProperties.AppPropertiesSingleton.INSTANCE;
    }

    private void setBrowserProperties() {
        browser = YamlReader.getConfig().getBrowser();
        Map<String, Object> browserProperties = browser.getBrowserProperties();
        for (Map.Entry entry : browserProperties.entrySet()) {
            System.setProperty(entry.getKey().toString(), entry.getValue().toString());
            logger.info("Loaded browser properties: {} = {}", entry.getKey(), entry.getValue());
        }
    }

    private void setEnvironmentProperties() {
        listOfEnvironments = yamlReader.getConfig().getEnvironment().getListOfEnvironments();
        boolean foundActiveEnv = false;
        for (EnvironmentModel environmentModel : listOfEnvironments) {
            if (environmentModel.isActive()) {
                foundActiveEnv = true;
                Map<String, Object> environmentProperties = environmentModel.getEnvironmentProperties();
                for (Map.Entry entry : environmentProperties.entrySet()) {
                    System.setProperty(entry.getKey().toString(), entry.getValue().toString());
                    logger.info("Loaded environment properties: {} = {}", entry.getKey(), entry.getValue());
                }
                break;
            }
        }
    }

    private static class AppPropertiesSingleton {
        private static final AppProperties INSTANCE = new AppProperties();
    }
}
