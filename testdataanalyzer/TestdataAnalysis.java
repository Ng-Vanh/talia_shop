package testdataanalyzer;

import testdataanalyzer.object.AbstractNode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.security.cert.PolicyNode;
import java.util.ArrayList;
import java.util.List;

public class TestdataAnalysis {
    private TestdataNLP testdataNLP;
    private POSTagging tagging;
    public TestdataTree analyzeTestdata() {
        TestdataTree testdataTree = new TestdataTree();
        List<String> steps = new ArrayList<>();

        try {
            FileReader reader = new FileReader(this.testdataNLP.getSourcePath());
            BufferedReader read = new BufferedReader(reader);
            String line;
            while((line = read.readLine()) != null) {
                steps.add(line);
            }
            this.testdataNLP.setSteps(steps); // them cac step vao list
            List<AbstractNode> vertices = new ArrayList<>();
            for(String step : this.testdataNLP.getSteps()) {
                System.out.println("các bước: " + step);
                tagging = new POSTagging(step);
                AbstractNode node = tagging.tagging();
                if(node != null) {
                    vertices.add(node);
                }
            }
            testdataTree.setVertices(vertices);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return testdataTree;
    }

    public void setTestdataNLP(TestdataNLP testdataNLP) {
        this.testdataNLP = testdataNLP;
    }

    public TestdataNLP getTestdataNLP() {
        return testdataNLP;
    }
    public static void main(String[] args) {

    }
}
