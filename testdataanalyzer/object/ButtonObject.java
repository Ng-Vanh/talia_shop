package testdataanalyzer.object;

import testdataanalyzer.Property;

import java.util.List;

// click button XYZ "abc"
public class ButtonObject extends AbstractNode {
    public ButtonObject(String name, List<Property> pros) {
        super(name,pros);
    }
    @Override
    public String generateTestscript() {
        return null;
    }
}
