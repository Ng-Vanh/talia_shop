package testdataanalyzer;

import edu.stanford.nlp.ling.*;
import edu.stanford.nlp.pipeline.*;
import testdataanalyzer.object.*;

import java.util.*;

public class POSTagging {

    public static String text;

    public void setText(String text) {
        this.text = text;
    }
    public POSTagging(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
    public static AbstractNode tagging() {
        // set up pipeline properties
        Properties props = new Properties();
        // set the list of annotators to run
        props.setProperty("annotators", "tokenize,pos");
        // build pipeline
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
        // create a document object
        CoreDocument document = pipeline.processToCoreDocument(text);
        // display tokens
        /*
        for (CoreLabel tok : document.tokens()) {
            System.out.println(String.format("%s\t%s", tok.word(), tok.tag()));
        }
         */
        Boolean isProperty = false;
        Boolean isImage = false;
        List<String> inputOrNot = new ArrayList<>();
        List<Property> propertyList = new ArrayList<>();
        List<String> images = new ArrayList<>();
        String input = "";
        int type = 0;
        for (CoreLabel tok : document.tokens()) {
          //  System.out.println(String.format("%s\t%s", tok.word(), tok.tag()));
            if(tok.tag().equals("VB")) {
                Property action = new Property(tok.word());
                propertyList.add(action);
                //System.out.println("verb ne : " +propertyList.get(0).getKey());
            }
            if(tok.tag().equals("ADD")) {
               // link object
              //  System.out.println("link ne");
                type =1;
                propertyList.get(0).setValue(tok.word());
            }
            if(tok.tag().equals("-LRB-")) {
                // [
               // System.out.println("anhhh");
                isImage = true;
                continue;
            }
            if(isImage == true) {
                isImage = false;
                type =2;
                images.add(tok.word());
            }
            if(tok.tag().equals("``")) {
                // mo ngoac kep
                isProperty = true;
                continue;
            }
            if(tok.tag().equals("''")) {
                isProperty = false;
                inputOrNot.add(input);
                input = "";
            }
            if(isProperty == true) {
                // textfield hoac la ten rieng mot GUI element
                input += tok.word();
               // System.out.println(inputOrNot);
            }

        }
        if(inputOrNot.size() >=2) {
            // inputField
            type = 3;
            propertyList.get(0).setKey(inputOrNot.get(1));
            propertyList.get(0).setValue(inputOrNot.get(0));
            AbstractNode node = new InputFieldObject("InputField",propertyList);
          //  System.out.println(node.toString());
            return node;
        } else if(inputOrNot.size() == 1){
            // button object
            type = 4;
            propertyList.get(0).setValue(inputOrNot.get(0));
            AbstractNode node = new ButtonObject("Button",propertyList);
          //  System.out.println(node.toString());
            return node;

        }
        if(type == 1) {
            AbstractNode node = new LinkObject("Url",propertyList);
          //  System.out.println(node.toString());
            return node;
        }
        if(type ==2) {
            // image object
            for(int i = 0; i < images.size();i++) {

                Property prop = new Property(propertyList.get(0).getKey(),images.get(i));
                if(i != 0) {
                    propertyList.add(prop);
                    continue;
                }
                propertyList.get(i).setValue(images.get(i));
            }
            AbstractNode node = new ImageObject("Image",propertyList);
            //  System.out.println(node.toString());
            return node;

        }

        return null;
    }

    public static void main(String[] args) {
            // set up pipeline properties
            Properties props = new Properties();
            // set the list of annotators to run
            props.setProperty("annotators", "tokenize,pos");
            // build pipeline
            StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
            // create a document object
            CoreDocument document = pipeline.processToCoreDocument("Click on English button on the bar.");
            // display tokens

            for (CoreLabel tok : document.tokens()) {
                System.out.println(String.format("%s\t%s", tok.word(), tok.tag()));
            }
    }
}
