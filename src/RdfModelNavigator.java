import org.apache.jena.rdf.model.*;
import org.apache.jena.vocabulary.VCARD;

import java.beans.PropertyEditor;
import java.util.List;
import java.util.Optional;

public class RdfModelNavigator {
    /////////////////////////////////////////////////////////////////////////////////////
    // PRINT RESOURCE & IT'S PROPERTIES TO CONSOLE
    // just for demonstration, this is specific to a resource obj.
    /////////////////////////////////////////////////////////////////////////////////////
    public static void printResourceAndItsProperties(String resourceURI, Model model) {
        Resource selectedResource = model.getResource(resourceURI);


        System.out.println( selectedResource.toString() );
        printResourceAndItsPropertiesRecursively(selectedResource);
    }

    /////////////////////////////////////////////////////////////////////////////////////
    // HELPING FUNCTION TO RECURSIVELY PRINT THE RESOURCES PROPERTIES TO CONSOLE
    /////////////////////////////////////////////////////////////////////////////////////
    public static void printResourceAndItsPropertiesRecursively(Resource resource) {
        resource.listProperties().forEach(statement -> {
            if (statement.getObject() instanceof Resource &&
                    !statement.getPredicate().toString().equals("http://xmlns.com/foaf/0.1/knows")
            ) {         // it's a resource & so it doesn't go beyond itself
                System.out.printf("* %s %s \n", statement.getPredicate().toString(), statement.getObject().toString());
                printResourceAndItsPropertiesRecursively((Resource) statement.getObject());
            }           // it's a literal
            System.out.printf("* %s %s \n", statement.getPredicate().toString(), statement.getObject().toString());
            return;

        });
    }



}
