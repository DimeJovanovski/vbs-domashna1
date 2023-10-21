import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.sparql.vocabulary.FOAF;
import org.apache.jena.vocabulary.VCARD;

public class RdfModelCreator {
    public Model createModel() {
        /////////////////////////////////////////////////////////////////////////////////////
        //    DEFINING THE MAIN RESOURCE CLASSES OF PEOPLE/ORGANIZATIONS
        //    & THEIR BASIC PROPERTIES
        /////////////////////////////////////////////////////////////////////////////////////

        Model model = ModelFactory.createDefaultModel();
        model.setNsPrefix("instagram", "https://www.instagram.com#");
        model.setNsPrefix("vcard", "http://www.w3.org/2001/vcard-rdf/3.0#");
        model.setNsPrefix("foaf", "http://xmlns.com/foaf/0.1/");
        model.setNsPrefix("rdf", "http://www.w3.org/1999/02/22-rdf-syntax-ns#");
        model.setNsPrefix("rdfs", "http://www.w3.org/2000/01/rdf-schema#");


        /////////////////////////////////////////////////////////////////////////////////////
        //    DEFINING THE MAIN RESOURCE CLASSES OF PEOPLE/ORGANIZATIONS
        //    & THEIR BASIC PROPERTIES
        /////////////////////////////////////////////////////////////////////////////////////

        Resource dimitarjovanovski
                = model.createResource("instagram:dimitar.jov")
                .addProperty(VCARD.FN, "Dimitar Jovanovski")
                .addProperty(VCARD.EMAIL, "dimitar.jovanovski@students.finki.ukim.mk")
                .addProperty(VCARD.N,
                        model.createResource()
                                .addProperty(VCARD.Given, "Dimitar")
                                .addProperty(VCARD.Family, "Jovanovski"));

        Resource anastasijakrstanoska
                = model.createResource("instagram:akrstanoska")
                .addProperty(VCARD.FN, "Anastasija Krstanoska")
                .addProperty(VCARD.EMAIL, "anastasija.krstanoska@students.finki.ukim.mk")
                .addProperty(VCARD.N,
                        model.createResource()
                                .addProperty(VCARD.Given, "Anastasija")
                                .addProperty(VCARD.Family, "Krstanoska"));

        Resource fssfinki
                = model.createResource("instagram:fssfinki")
                .addProperty(VCARD.Orgname, "Student Faculty Assembly of FCSE (FINKI)")
                .addProperty(VCARD.EMAIL, "fss@finki.ukim.mk")
                .addProperty(FOAF.based_near, "ul. Ruger Boskovik 16 1000")
                .addProperty(VCARD.Country, "Macedonia");


        /////////////////////////////////////////////////////////////////////////////////////
        //    CONNECTING THE MAIN RESOURCE CLASSES WITH EACH OTHER
        /////////////////////////////////////////////////////////////////////////////////////

        dimitarjovanovski
                .addProperty(FOAF.knows, anastasijakrstanoska);

        anastasijakrstanoska
                .addProperty(FOAF.member, fssfinki);


        return model;
    }
}
