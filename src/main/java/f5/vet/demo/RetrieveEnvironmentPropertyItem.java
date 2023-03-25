package f5.vet.demo;

public record RetrieveEnvironmentPropertyItem(String propertySource, String propertyName, String propertyCurrentValue,
                                              String propertyPreviousValue, String propertyOriginalValue) {
}