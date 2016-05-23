package chatt.hapiprofiler.test;

import ca.uhn.fhir.model.dstu2.resource.Patient;
import ca.uhn.fhir.model.dstu2.valueset.AdministrativeGenderEnum;
import ca.uhn.fhir.model.primitive.BooleanDt;
import chatt.hapiprofiler.ResourceProfile;
import org.junit.Test;

import java.util.GregorianCalendar;

import static org.junit.Assert.*;

public class ResourceProfileTest {

    @Test
    public void testSettersGetters() {
        ResourceProfile p = new ResourceProfile(Patient.class);

        assertFalse(p.getChildren().isEmpty());

        Patient patient = new Patient();
        patient.addIdentifier().setValue("2403902507");
        patient.setActive(true);
        patient.addName().addGiven("Magnus").addFamily("Vinther");
        patient.addTelecom().setValue("#####");
        patient.setGender(AdministrativeGenderEnum.FEMALE);
        patient.getBirthDateElement().setValue(new GregorianCalendar(1990, 3, 24).getTime());
        patient.setDeceased(new BooleanDt(true));
        patient.getMaritalStatus().addCoding().setCode("awdawd").setSystem("aw");

        String name = "CuraCitizen";
        p.setName(name);
        assertEquals(name, p.getName());

        String id = "cura-citizen";
        p.setId(id);
        assertEquals(id, p.getId());

        String url = "http://www.cura.com/";
        p.setUrl(url);
        assertEquals(url, p.getUrl());
    }

    @Test(expected = NullPointerException.class)
    public void testNullConstructor() {
        new ResourceProfile(null);
    }

}