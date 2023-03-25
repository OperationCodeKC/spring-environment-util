package f5.vet.demo;

public class RetrieveEnvironmentPropertyItem {
    private String propertySource;
    private String propertyName;
    private String propertyCurrentValue;
    private String propertyPreviousValue;
    private String propertyOriginalValue;

    public RetrieveEnvironmentPropertyItem(String propertySource, String propertyName, String propertyCurrentValue, String propertyPreviousValue, String propertyOriginalValue) {
        this.propertySource = propertySource;
        this.propertyName = propertyName;
        this.propertyCurrentValue = propertyCurrentValue;
        this.propertyPreviousValue = propertyPreviousValue;
        this.propertyOriginalValue = propertyOriginalValue;
    }

    public String getPropertySource() {
        return propertySource;
    }

    public void setPropertySource(String propertySource) {
        this.propertySource = propertySource;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getPropertyCurrentValue() {
        return propertyCurrentValue;
    }

    public void setPropertyCurrentValue(String propertyCurrentValue) {
        this.propertyCurrentValue = propertyCurrentValue;
    }

    public String getPropertyPreviousValue() {
        return propertyPreviousValue;
    }

    public void setPropertyPreviousValue(String propertyPreviousValue) {
        this.propertyPreviousValue = propertyPreviousValue;
    }

    public String getPropertyOriginalValue() {
        return propertyOriginalValue;
    }

    public void setPropertyOriginalValue(String propertyOriginalValue) {
        this.propertyOriginalValue = propertyOriginalValue;
    }
}