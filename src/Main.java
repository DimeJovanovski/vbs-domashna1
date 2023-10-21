import org.apache.jena.rdf.model.*;

public class Main {
    public static void main(String[] args) {
        /////////////////////////////////////////////////////////////////////////////////////
        // I. CREATING A SIMPLE RDF GRAPH
        /////////////////////////////////////////////////////////////////////////////////////
        Model model = new RdfModelCreator().createModel();


        /////////////////////////////////////////////////////////////////////////////////////
        // II. PRINTING RDF GRAPH
        /////////////////////////////////////////////////////////////////////////////////////
        RdfModelWriter modelWriter = new RdfModelWriter();
        modelWriter.printStatements(model);
        modelWriter.printWithXMLSyntax(model);
        modelWriter.printWithXMLAbbrevSyntax(model);
        modelWriter.printWithNTriplesSyntax(model);
        modelWriter.printWithTurtleSyntax(model);
        modelWriter.saveModelAsXmlFile(model);
        modelWriter.saveModelAsXmlAbbrevFile(model);
        modelWriter.saveModelAsNTriplesFile(model);
        modelWriter.saveModelAsTurtleFile(model);
        modelWriter.saveModelAsN3File(model);


        /////////////////////////////////////////////////////////////////////////////////////
        // III. READING RDF GRAPH
        /////////////////////////////////////////////////////////////////////////////////////
        RdfModelReader modelReader = new RdfModelReader();
        model = modelReader.readModelFromFile();
        modelWriter.printWithXMLSyntax(model);


        /////////////////////////////////////////////////////////////////////////////////////
        // IV. NAVIGATING RDF GRAPH
        /////////////////////////////////////////////////////////////////////////////////////
        String resourceURI = "instagram:dimitar.jov";
        RdfModelNavigator modelNavigator = new RdfModelNavigator();
        modelNavigator.printResourceAndItsProperties(resourceURI, model);


        /////////////////////////////////////////////////////////////////////////////////////
        // V. PULLING DATA FROM RDF GRAPH
        // file: hifm-dataset.ttl
        /////////////////////////////////////////////////////////////////////////////////////
        RdfModelDataPuller dataPuller = new RdfModelDataPuller();
        model = dataPuller.readModelFromFile();
        dataPuller.printDrugNames(model);
        dataPuller.printValuesOfDrug("http://purl.org/net/hifm/data#983543", model);
        dataPuller.printSimilarDrugs("http://purl.org/net/hifm/data#966134", model);
        dataPuller.compareDrugPrices("http://purl.org/net/hifm/data#988146", model);

    }


}
