package configuration;

import models.Browser;
import models.Environment;

public class Config {
    public Environment environment;
    public Browser browser;

    public Environment getEnvironment() {
        return environment;
    }

    public Browser getBrowser() {
        return browser;
    }
}
