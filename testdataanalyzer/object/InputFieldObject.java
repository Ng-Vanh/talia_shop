package testdataanalyzer.object;

import testdataanalyzer.Property;

import java.util.List;

public class InputFieldObject extends AbstractNode {
    public InputFieldObject(String name, List<Property> pros) {
        super(name,pros);
    }
    @Override
    public String generateTestscript() {
        return null;
    }
}
