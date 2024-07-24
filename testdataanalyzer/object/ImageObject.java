package testdataanalyzer.object;

import testdataanalyzer.Property;

import java.util.List;

// Click vao anh [id1224], trong do [id1234] nay duoc luu o mot tep png o ngoai
public class ImageObject extends AbstractNode {
    public ImageObject(String name, List<Property> pros) {
        super(name,pros);
    }
    @Override
    public String generateTestscript() {
        return null;
    }
}
