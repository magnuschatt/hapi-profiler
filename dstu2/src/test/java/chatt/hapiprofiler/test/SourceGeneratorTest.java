package chatt.hapiprofiler.test;

import ca.uhn.fhir.model.dstu2.resource.Patient;
import ca.uhn.fhir.model.dstu2.resource.ProcedureRequest;
import ca.uhn.fhir.model.primitive.BooleanDt;
import ca.uhn.fhir.model.primitive.CodeDt;
import ca.uhn.fhir.model.primitive.IntegerDt;
import ca.uhn.fhir.model.primitive.StringDt;
import chatt.hapiprofiler.ResourceProfile;
import chatt.hapiprofiler.SourceGenerator;
import org.junit.Test;

public class SourceGeneratorTest {

    @Test
    public void generatePatientTest() {
        ResourceProfile patientProfile = new ResourceProfile(Patient.class);
        patientProfile.setName("MyPatient");
        patientProfile.addChild("pet")
                .setAllowedTypes(StringDt.class, IntegerDt.class);

        SourceGenerator generator = new SourceGenerator();
        generator.generate(patientProfile);
    }

    @Test
    public void generateCuraCitizen() {
        ResourceProfile citizen = new ResourceProfile(Patient.class);
        citizen.setUrl("http://fhir.columna.dk/cura/StructureDefinition/cura-procedure-request");
        citizen.setId("cura-procedure-request");
        citizen.setName("CuraProcedureRequest");

        citizen.getChild("identifier").setCardinality(0, 1);
        citizen.getChild("active").deprecate();
        citizen.getChild("name").setCardinality(1, 2);
        citizen.getChild("deceased").setAllowedTypes(BooleanDt.class);
        citizen.getChild("maritalStatus").deprecate();
        citizen.getChild("multipleBirth").deprecate();
        citizen.getChild("photo").deprecate();
        citizen.getChild("contact").deprecate();
        citizen.getChild("animal").deprecate();
        citizen.getChild("communication").deprecate();
        citizen.getChild("careProvider").setCardinality(0, 1);
        citizen.getChild("managingOrganization").deprecate();
        citizen.getChild("link").deprecate();
        citizen.addChild("identifierStatus").setAllowedTypes(CodeDt.class);
        citizen.addChild("insuranceGroup").setAllowedTypes(StringDt.class);

        SourceGenerator generator = new SourceGenerator();
        generator.generate(citizen);
    }

    @Test
    public void generateCuraProcedureRequest() {
        ResourceProfile citizen = new ResourceProfile(ProcedureRequest.class);
        citizen.setUrl("http://fhir.columna.dk/cura/StructureDefinition/cura-citizen");
        citizen.setId("cura-citizen");
        citizen.setName("CuraCitizen");

        citizen.getChild("identifier").deprecate();
        citizen.getChild("active").deprecate();
        citizen.getChild("name").setCardinality(1, 2);
        citizen.getChild("deceased").setAllowedTypes(BooleanDt.class);
        citizen.getChild("maritalStatus").deprecate();
        citizen.getChild("multipleBirth").deprecate();
        citizen.getChild("photo").deprecate();
        citizen.getChild("contact").deprecate();
        citizen.getChild("animal").deprecate();
        citizen.getChild("communication").deprecate();
        citizen.getChild("careProvider").setCardinality(0, 1);
        citizen.getChild("managingOrganization").deprecate();
        citizen.getChild("link").deprecate();
        citizen.addChild("identifierStatus").setAllowedTypes(CodeDt.class);
        citizen.addChild("insuranceGroup").setAllowedTypes(StringDt.class);

        SourceGenerator generator = new SourceGenerator();
        generator.generate(citizen);
    }

}