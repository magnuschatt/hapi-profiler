
import ca.uhn.fhir.model.dstu2.resource.BaseResource;
import ca.uhn.fhir.context.FhirVersionEnum;
import java.util.List;
import ca.uhn.fhir.model.api.annotation.ResourceDef;
import ca.uhn.fhir.model.dstu2.composite.IdentifierDt;
import ca.uhn.fhir.model.dstu2.composite.HumanNameDt;
import ca.uhn.fhir.model.dstu2.composite.ContactPointDt;
import ca.uhn.fhir.model.primitive.CodeDt;
import ca.uhn.fhir.model.primitive.DateDt;
import ca.uhn.fhir.model.primitive.BooleanDt;
import ca.uhn.fhir.model.dstu2.composite.AddressDt;
import ca.uhn.fhir.model.dstu2.resource.Organization;
import ca.uhn.fhir.model.dstu2.resource.Practitioner;
import ca.uhn.fhir.model.dstu2.composite.ResourceReferenceDt;
import ca.uhn.fhir.model.primitive.StringDt;

@ResourceDef(name = "Patient", id = "cura-citizen", profile = "http://fhir.columna.dk/cura/StructureDefinition/cura-citizen")
public class CuraCitizen extends BaseResource {

	protected IdentifierDt identifier;
	protected List<HumanNameDt> name;
	protected List<ContactPointDt> telecom;
	protected CodeDt gender;
	protected DateDt birthDate;
	protected BooleanDt deceased;
	protected List<AddressDt> address;
	protected ResourceReferenceDt careProvider;
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
}