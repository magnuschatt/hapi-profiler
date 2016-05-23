
import org.hl7.fhir.dstu3.model.DomainResource;
import org.hl7.fhir.dstu3.model.ResourceType;
import ca.uhn.fhir.model.api.annotation.ResourceDef;
import ca.uhn.fhir.model.api.annotation.Block;
import org.hl7.fhir.dstu3.model.Identifier;
import java.util.List;
import org.hl7.fhir.dstu3.model.BooleanType;
import org.hl7.fhir.dstu3.model.HumanName;
import org.hl7.fhir.dstu3.model.ContactPoint;
import org.hl7.fhir.dstu3.model.CodeType;
import org.hl7.fhir.dstu3.model.DateType;
import org.hl7.fhir.dstu3.model.DateTimeType;
import org.hl7.fhir.dstu3.model.Type;
import org.hl7.fhir.dstu3.model.Address;
import org.hl7.fhir.dstu3.model.CodeableConcept;
import org.hl7.fhir.dstu3.model.IntegerType;
import org.hl7.fhir.dstu3.model.Attachment;
import org.hl7.fhir.dstu3.model.Organization;
import org.hl7.fhir.dstu3.model.Practitioner;
import org.hl7.fhir.dstu3.model.Reference;
import org.hl7.fhir.dstu3.model.StringType;
import org.hl7.fhir.dstu3.model.Period;
import org.hl7.fhir.dstu3.model.Patient;

@ResourceDef(name = "Patient", id = "", profile = "http://hl7.org/fhir/Profile/Patient")
public class CuraCitizen extends DomainResource {

	protected List<Identifier> identifier;
	protected BooleanType active;
	protected List<HumanName> name;
	protected List<ContactPoint> telecom;
	protected CodeType gender;
	protected DateType birthDate;
	protected Type deceased;
	protected List<Address> address;
	protected CodeableConcept maritalStatus;
	protected Type multipleBirth;
	protected List<Attachment> photo;
	protected List<Contact> contact;
	protected Animal animal;
	protected List<Communication> communication;
	protected List<Reference> careProvider;
	protected Reference managingOrganization;
	protected List<Link> link;
	protected StringType pet;

	@Override
	public DomainResource copy() {
		return null;
	}

	@Override
	public ResourceType getResourceType() {
		return null;
	}

	@Block
	public static class Contact {
		protected List<CodeableConcept> relationship;
		protected HumanName name;
		protected List<ContactPoint> telecom;
		protected Address address;
		protected CodeType gender;
		protected Reference organization;
		protected Period period;
	}

	@Block
	public static class Animal {
		protected CodeableConcept species;
		protected CodeableConcept breed;
		protected CodeableConcept genderStatus;
	}

	@Block
	public static class Communication {
		protected CodeableConcept language;
		protected BooleanType preferred;
	}

	@Block
	public static class Link {
		protected Reference other;
		protected CodeType type;
	}
}