import org.apache.jena.rdf.model.*;
import org.apache.jena.riot.RDFDataMgr;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class RdfModelReader {
    static final String filePath = "C:\\Users\\dimitar\\Downloads\\graphs\\model.xml";

    /////////////////////////////////////////////////////////////////////////////////////
    // READING RDF MODEL FROM LOCAL FILE
    // & PRINTING IT WITH THE RdfModelWriter CLASS
    /////////////////////////////////////////////////////////////////////////////////////
    public Model readModelFromFile() {
        Model model = ModelFactory.createDefaultModel();
        try {
            FileInputStream inputStream = new FileInputStream(filePath);
            model.read(inputStream, null);
            inputStream.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return model;
    }


}
