package chatt.hapiprofiler.test;

import chatt.hapiprofiler.ResourceProfile;
import chatt.hapiprofiler.SourceGenerator;
import org.hl7.fhir.dstu3.model.Patient;
import org.hl7.fhir.dstu3.model.StringType;
import org.junit.Test;

public class SourceGeneratorTest {

    @Test
    public void generatePatientTest() {
        ResourceProfile patientProfile = new ResourceProfile(Patient.class);
        patientProfile.setName("CuraCitizen");
        patientProfile.addChild()
                .setName("pet")
                .getAllowedTypes().add(StringType.class);

        SourceGenerator generator = new SourceGenerator();
        generator.generate(patientProfile);
    }

}