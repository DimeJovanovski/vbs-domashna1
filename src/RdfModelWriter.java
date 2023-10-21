import org.apache.jena.rdf.model.*;

import java.io.FileOutputStream;
import java.io.IOException;

public class RdfModelWriter {
    /////////////////////////////////////////////////////////////////////////////////////
    // PRINT ALL THE MODELS STATEMENTS TO CONSOLE
    // IN FORMAT: SUBJECT - PREDICATE - OBJECT
    /////////////////////////////////////////////////////////////////////////////////////
    public void printStatements(Model model) {
        StmtIterator iter = model.listStatements();

        System.out.println("Printing with model.listStatements():");
        while (iter.hasNext()) {
            Statement stmt      = iter.nextStatement();   // get next statement
            Resource subject    = stmt.getSubject();      // get the subject
            Property predicate  = stmt.getPredicate();    // get the predicate
            RDFNode object      = stmt.getObject();       // get the object

            System.out.print(subject.toString());
            System.out.print(" - " + predicate.toString() + " - ");
            if (object instanceof Resource) {             // object is a resource
                System.out.print(object.toString());
            } else {                                      // object is a literal
                System.out.print(" \"" + object.toString() + "\"");
            }
            System.out.println(" .");
        }
    }


    /////////////////////////////////////////////////////////////////////////////////////
    // PRINT GIVEN RDF GRAPH TO CONSOLE WITH XML SYNTAX
    /////////////////////////////////////////////////////////////////////////////////////
    public void printWithXMLSyntax(Model model) {
        System.out.println("Printing with model.print(), in RDF/XML.");
        model.write(System.out);
        System.out.println();
    }


    /////////////////////////////////////////////////////////////////////////////////////
    // PRINT GIVEN RDF GRAPH TO CONSOLE WITH XML-ABBREV SYNTAX
    /////////////////////////////////////////////////////////////////////////////////////
    public void printWithXMLAbbrevSyntax(Model model) {
        System.out.println("Printing with model.print(), in RDF/XML-ABBREV.");
        model.write(System.out, "RDF/XML-ABBREV");
        System.out.println();
    }


    /////////////////////////////////////////////////////////////////////////////////////
    // PRINT GIVEN RDF GRAPH TO CONSOLE WITH N-TRIPLES SYNTAX
    /////////////////////////////////////////////////////////////////////////////////////
    public void printWithNTriplesSyntax(Model model) {
        System.out.println("Printing with model.print(), in N-TRIPLES.");
        model.write(System.out, "N-TRIPLES");
        System.out.println();
    }


    /////////////////////////////////////////////////////////////////////////////////////
    // PRINT GIVEN RDF GRAPH TO CONSOLE WITH TURTLE SYNTAX
    /////////////////////////////////////////////////////////////////////////////////////
    public void printWithTurtleSyntax(Model model) {
        System.out.println("Printing with model.print(), in TURTLE.");
        model.write(System.out, "TURTLE");
        System.out.println();
    }


    /////////////////////////////////////////////////////////////////////////////////////
    // SAVE RDF GRAPH LOCALLY WITH XML SYNTAX
    /////////////////////////////////////////////////////////////////////////////////////
    public void saveModelAsXmlFile(Model model) {
        String filePath = "C:\\Users\\dimitar\\Downloads\\graphs\\model.xml";
        try {
            FileOutputStream outputStream = new FileOutputStream(filePath);
            model.write(outputStream);
            outputStream.close();
            System.out.println("Model saved successfully to " + filePath);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    /////////////////////////////////////////////////////////////////////////////////////
    // SAVE RDF GRAPH LOCALLY WITH XML-ABBREV SYNTAX
    /////////////////////////////////////////////////////////////////////////////////////
    public void saveModelAsXmlAbbrevFile(Model model) {
        String filePath = "C:\\Users\\dimitar\\Downloads\\graphs\\model-abbrev.xml";
        try {
            FileOutputStream outputStream = new FileOutputStream(filePath);
            model.write(outputStream, "RDF/XML-ABBREV");
            outputStream.close();
            System.out.println("Model saved successfully to " + filePath);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    /////////////////////////////////////////////////////////////////////////////////////
    // SAVE RDF GRAPH LOCALLY WITH N-TRIPLES SYNTAX
    /////////////////////////////////////////////////////////////////////////////////////
    public void saveModelAsNTriplesFile(Model model) {
        String filePath = "C:\\Users\\dimitar\\Downloads\\graphs\\model.nt";
        try {
            FileOutputStream outputStream = new FileOutputStream(filePath);
            model.write(outputStream, "N-TRIPLES");
            outputStream.close();
            System.out.println("Model saved successfully to " + filePath);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    /////////////////////////////////////////////////////////////////////////////////////
    // SAVE RDF GRAPH LOCALLY WITH TURTLE SYNTAX
    /////////////////////////////////////////////////////////////////////////////////////
    public void saveModelAsTurtleFile(Model model) {
        String filePath = "C:\\Users\\dimitar\\Downloads\\graphs\\model.ttl";
        try {
            FileOutputStream outputStream = new FileOutputStream(filePath);
            model.write(outputStream, "TURTLE");
            outputStream.close();
            System.out.println("Model saved successfully to " + filePath);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    /////////////////////////////////////////////////////////////////////////////////////
    // SAVE RDF GRAPH LOCALLY WITH N3 SYNTAX
    /////////////////////////////////////////////////////////////////////////////////////
    public void saveModelAsN3File(Model model) {
        String filePath = "C:\\Users\\dimitar\\Downloads\\graphs\\model.n3";
        try {
            FileOutputStream outputStream = new FileOutputStream(filePath);
            model.write(outputStream, "N3");
            outputStream.close();
            System.out.println("Model saved successfully to " + filePath);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
