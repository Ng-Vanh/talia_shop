package testdataanalyzer.object;

import testdataanalyzer.Property;

import java.util.List;

public class LinkObject extends AbstractNode {
    public LinkObject(String name, List<Property> pros) {
        super(name,pros);
    }
    @Override
    public String generateTestscript() {
        return null;
    }
}
