
import ca.uhn.fhir.model.dstu2.resource.BaseResource;
import ca.uhn.fhir.context.FhirVersionEnum;
import java.util.List;
import ca.uhn.fhir.model.api.annotation.ResourceDef;
import ca.uhn.fhir.model.api.annotation.Block;
import ca.uhn.fhir.model.dstu2.composite.IdentifierDt;
import ca.uhn.fhir.model.primitive.BooleanDt;
import ca.uhn.fhir.model.dstu2.composite.HumanNameDt;
import ca.uhn.fhir.model.dstu2.composite.ContactPointDt;
import ca.uhn.fhir.model.primitive.CodeDt;
import ca.uhn.fhir.model.primitive.DateDt;
import ca.uhn.fhir.model.dstu2.composite.AddressDt;
import ca.uhn.fhir.model.dstu2.composite.CodeableConceptDt;
import ca.uhn.fhir.model.primitive.IntegerDt;
import ca.uhn.fhir.model.api.IDatatype;
import ca.uhn.fhir.model.dstu2.composite.AttachmentDt;
import ca.uhn.fhir.model.dstu2.resource.Organization;
import ca.uhn.fhir.model.dstu2.resource.Practitioner;
import ca.uhn.fhir.model.dstu2.composite.ResourceReferenceDt;
import ca.uhn.fhir.model.primitive.StringDt;
import ca.uhn.fhir.model.dstu2.composite.PeriodDt;
import ca.uhn.fhir.model.dstu2.resource.Patient;

@ResourceDef(name = "Patient", id = "cura-procedure-request", profile = "http://fhir.columna.dk/cura/StructureDefinition/cura-procedure-request")
public class CuraProcedureRequest extends BaseResource {

	protected IdentifierDt identifier;
	protected List<BooleanDt> active;
	protected List<HumanNameDt> name;
	protected List<ContactPointDt> telecom;
	protected CodeDt gender;
	protected DateDt birthDate;
	protected BooleanDt deceased;
	protected List<AddressDt> address;
	protected List<CodeableConceptDt> maritalStatus;
	protected List<IDatatype> multipleBirth;
	protected List<AttachmentDt> photo;
	protected List<Contact> contact;
	protected List<Animal> animal;
	protected List<Communication> communication;
	protected ResourceReferenceDt careProvider;
	protected List<ResourceReferenceDt> managingOrganization;
	protected List<Link> link;
	protected CodeDt identifierStatus;
	protected StringDt insuranceGroup;

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public String getResourceName() {
		return null;
	}

	@Override
	public FhirVersionEnum getStructureFhirVersionEnum() {
		return null;
	}

	@Override
	public List getAllPopulatedChildElementsOfType(Class c) {
		return null;
	}

	@Block
	public static class Contact {
		protected List<CodeableConceptDt> relationship;
		protected HumanNameDt name;
		protected List<ContactPointDt> telecom;
		protected AddressDt address;
		protected CodeDt gender;
		protected ResourceReferenceDt organization;
		protected PeriodDt period;
	}

	@Block
	public static class Animal {
		protected CodeableConceptDt species;
		protected CodeableConceptDt breed;
		protected CodeableConceptDt genderStatus;
	}

	@Block
	public static class Communication {
		protected CodeableConceptDt language;
		protected BooleanDt preferred;
	}

	@Block
	public static class Link {
		protected ResourceReferenceDt other;
		protected CodeDt type;
	}
}