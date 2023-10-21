import org.apache.jena.rdf.model.*;
import org.apache.jena.vocabulary.RDFS;
import org.apache.jena.ontology.OntModel;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class RdfModelDataPuller {
    static final String filePath = "C:\\Users\\dimitar\\FINKI\\semestar_7\\VBS\\domashni\\domashna1\\hifm-dataset.ttl";

    /////////////////////////////////////////////////////////////////////////////////////
    //    PULLING DATA FROM RDF GRAPH
    /////////////////////////////////////////////////////////////////////////////////////
    public Model readModelFromFile() {
        Model model = ModelFactory.createDefaultModel();
        try {
            FileInputStream inputStream = new FileInputStream(filePath);
            model.read(inputStream, null, "Turtle");
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return model;
    }


    /////////////////////////////////////////////////////////////////////////////////////
    //    LISTING THE NAMES OF ALL THE DRUGS IN hifm-dataset.ttl FILE ALPHABETICALLY
    /////////////////////////////////////////////////////////////////////////////////////
    public void printDrugNames(Model model) {
        System.out.println("Printing drug names from file: hifm-dataset.ttl");
        NodeIterator iter = model.listObjectsOfProperty(RDFS.label);

        List<String> drugNames = new ArrayList<>();
        while (iter.hasNext()) {
            drugNames.add(iter.next().toString());
        }

        Collections.sort(drugNames);
        drugNames.forEach(System.out::println);
    }


    /////////////////////////////////////////////////////////////////////////////////////
    //    PRINTING ALL THE PROPERTIES(AND THEIR VALUES) OF SPECIFIC DRUG(URI SPECIFIED)
    /////////////////////////////////////////////////////////////////////////////////////
    public void printValuesOfDrug(String drugURI, Model model) {
        Resource drugResource = model.getResource(drugURI);

        String drugName = model.listStatements(
                new SimpleSelector(drugResource, RDFS.label, (RDFNode) null)).next().getObject().toString();

        StmtIterator iter = model.listStatements(
                new SimpleSelector(drugResource, null, (RDFNode) null));

        System.out.println("Printing values of drug " + drugName + " " + drugURI);
        while (iter.hasNext()) {
            Statement stmt = iter.next();            // get next statement
            Resource subject = stmt.getSubject();      // get the subject
            Property predicate = stmt.getPredicate();    // get the predicate
            RDFNode object = stmt.getObject();       // get the object

            System.out.print("* " + predicate.toString() + " - ");
            if (object instanceof Resource) {             // object is a resource
                System.out.print(object.toString());
            } else {                                      // object is a literal
                System.out.print(" \"" + object.toString() + "\"");
            }
            System.out.println(" .");
        }

    }


    /////////////////////////////////////////////////////////////////////////////////////
    //    PRINT THE NAMES OF THE DRUGS THAT HAVE SIMILAR FUNC. TO THE ONE AS URI ARGUMENT
    /////////////////////////////////////////////////////////////////////////////////////
    public void printSimilarDrugs(String drugURI, Model model) {
        Resource drugResource = model.getResource(drugURI);
        Property similarTo = model.createProperty("http://purl.org/net/hifm/ontology#similarTo");
        String drugName = model.listStatements(
                new SimpleSelector(drugResource, RDFS.label, (RDFNode) null)).next().getObject().toString();
        StmtIterator iter = model.listStatements(
                new SimpleSelector(drugResource, similarTo, (RDFNode) null));
        System.out.println("Drugs simmilar to " + drugName + " " + drugResource.toString());
        while(iter.hasNext()) {
            Statement stmt = iter.next();
            RDFNode object = stmt.getObject();

            String simmilarDrugName = model.listStatements(
                    new SimpleSelector((Resource) object, RDFS.label, (RDFNode) null)).next().getObject().toString();

            System.out.println("* " + simmilarDrugName + " " + object.toString());
        }

    }

    /////////////////////////////////////////////////////////////////////////////////////
    //    COMPARE PRICES OF DRUGS SIMILAR TO THE ONE AS URI ARGUMENT
    /////////////////////////////////////////////////////////////////////////////////////
    public void compareDrugPrices(String drugURI, Model model) {
        Resource drugResource = model.getResource(drugURI);
        Property refPrice = model.createProperty("http://purl.org/net/hifm/ontology#refPriceWithVAT");
        Property similarTo = model.createProperty("http://purl.org/net/hifm/ontology#similarTo");
        String drugName = model.listStatements(
                new SimpleSelector(drugResource, RDFS.label, (RDFNode) null)).next().getObject().toString();
        String drugPrice = model.listStatements(
                new SimpleSelector(drugResource, refPrice, (RDFNode) null)).next().getObject().toString();

        StmtIterator iter = model.listStatements(
                new SimpleSelector(drugResource, similarTo, (RDFNode) null));

        System.out.println("Comparing prices of simmilar drugs to " + drugName + " " + drugResource.toString() + " price " + drugPrice);
        while(iter.hasNext()) {
            Statement stmt = iter.next();
            RDFNode object = stmt.getObject();

            String simmilarDrugName = model.listStatements(
                    new SimpleSelector((Resource) object, RDFS.label, (RDFNode) null)).next().getObject().toString();
            String simmilarDrugPrice = model.listStatements(
                    new SimpleSelector((Resource) object, refPrice, (RDFNode) null)).next().getObject().toString();

            System.out.println("* " + simmilarDrugName + " " + object.toString() + " price " + simmilarDrugPrice);
        }
    }
}
