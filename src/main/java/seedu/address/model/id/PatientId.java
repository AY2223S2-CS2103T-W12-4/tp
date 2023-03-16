package seedu.address.model.id;

/**
 * Represents the id of the patient
 * guarantees: details are present and not null, field values are validated, immutable.
 */
public class PatientId extends Id {
    private String id;

    public PatientId(String id) {
        this.id = id;
    }
}
