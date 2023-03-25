package f5.vet.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

@Component
public class RetrieveEnvironmentProperties {

    private final ApplicationContext applicationContext;

    private ConfigurableEnvironment environment;
    private List<RetrieveEnvironmentPropertyItem> retrieveEnvironmentPropertyItems;

    public RetrieveEnvironmentProperties(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    private List<EnumerablePropertySource> findPropertiesPropertySources() {
        environment = (ConfigurableEnvironment) applicationContext.getEnvironment();
        List<EnumerablePropertySource> propertiesPropertySources = new LinkedList<>();
        for (PropertySource<?> propertySource : environment.getPropertySources()) {
            if (propertySource instanceof EnumerablePropertySource) {
                propertiesPropertySources.add((EnumerablePropertySource) propertySource);
            }
        }
        return propertiesPropertySources;
    }

    private void retrieveProperties() {
        Properties properties = new Properties();
        retrieveEnvironmentPropertyItems = new LinkedList<>();
        for (EnumerablePropertySource propertySource : findPropertiesPropertySources()) {
            String[] propertyNames = propertySource.getPropertyNames();
            Arrays.sort(propertyNames);
            for (String propertyName : propertyNames) {

                // retrieve the original property value
                String originalPropertyValue = environment.getProperty(propertyName);

                // retrieve the property value from this property source
                String sourcePropertyValue = propertySource.getProperty(propertyName).toString();

                // retrieve the latest known property value
                String latestProperty = properties.getProperty(propertyName);

                if (latestProperty == null) {
                    properties.setProperty(propertyName, sourcePropertyValue);
                    retrieveEnvironmentPropertyItems.add(new RetrieveEnvironmentPropertyItem(propertySource.getName(),
                            propertyName, sourcePropertyValue, "", ""));
                } else {
                    if (latestProperty.equals(sourcePropertyValue)) {
                        retrieveEnvironmentPropertyItems.add(new RetrieveEnvironmentPropertyItem(propertySource.getName(),
                                propertyName, sourcePropertyValue, latestProperty, originalPropertyValue));
                    } else {
                        properties.setProperty(propertyName, sourcePropertyValue);
                        retrieveEnvironmentPropertyItems.add(new RetrieveEnvironmentPropertyItem(propertySource.getName(),
                                propertyName, sourcePropertyValue, latestProperty, originalPropertyValue));
                    }
                }
            }
        }
    }

    public String toString() {
        retrieveProperties();
        StringBuilder retVal = new StringBuilder();
        String lastPropertySource = "";
        for (RetrieveEnvironmentPropertyItem retrieveEnvironmentPropertyItem : retrieveEnvironmentPropertyItems) {
            if (!retrieveEnvironmentPropertyItem.propertySource().equals(lastPropertySource)) {
                retVal.append("\n******* ").append(retrieveEnvironmentPropertyItem.propertySource()).append(" *******\n");
                lastPropertySource = retrieveEnvironmentPropertyItem.propertySource();
            }
            if (retrieveEnvironmentPropertyItem.propertyPreviousValue().equals("")) {
                retVal.append("        New Property: ");
                retVal.append(retrieveEnvironmentPropertyItem.propertyName());
                retVal.append(" = ");
                retVal.append(retrieveEnvironmentPropertyItem.propertyCurrentValue());
                retVal.append("\n");
            } else {
                if (retrieveEnvironmentPropertyItem.propertyCurrentValue().equals(retrieveEnvironmentPropertyItem.propertyPreviousValue())) {
                    retVal.append("  Duplicate Property: ");
                    retVal.append(retrieveEnvironmentPropertyItem.propertyName());
                    retVal.append(" = ");
                    retVal.append(retrieveEnvironmentPropertyItem.propertyCurrentValue());
                    retVal.append("\n");
                } else {
                    retVal.append("   Override Property: ");
                    retVal.append(retrieveEnvironmentPropertyItem.propertyName());
                    retVal.append(" = ");
                    retVal.append(retrieveEnvironmentPropertyItem.propertyCurrentValue());
                    retVal.append(" (Last Value = ");
                    retVal.append(retrieveEnvironmentPropertyItem.propertyPreviousValue());
                    retVal.append("; Orig Value = ");
                    retVal.append(retrieveEnvironmentPropertyItem.propertyOriginalValue());
                    retVal.append(")\n");
                }
            }
        }
        return retVal.toString();
    }
}