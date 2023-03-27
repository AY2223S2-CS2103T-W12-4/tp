package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PATIENT_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIMESLOT;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;

/**
 * Adds an appointment to a patient in the address book.
 */
public class AddAppointmentCommand extends Command {
    public static final String COMMAND_WORD = "add_appt";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an appointment for a patient. "
            + "Parameters: "
            + PREFIX_PATIENT_ID + "PATIENT ID "
            + PREFIX_TIMESLOT + "TIMESLOT "
            + PREFIX_DESCRIPTION + "DESCRIPTION\n"
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_PATIENT_ID + "1 "
            + PREFIX_TIMESLOT + "01012023 00:00,01012023 01:00 "
            + PREFIX_DESCRIPTION + "Regular checkup "
            + PREFIX_TAG + "routine "
            + PREFIX_TAG + "noSymptoms";

    public static final String MESSAGE_SUCCESS = "New appointment added: %1$s";
    public static final String MESSAGE_DUPLICATE_APPOINTMENT = "This patient already exists";
    public static final String MESSAGE_PATIENT_NOT_EXIST = "This patient does not exist";


    private final Appointment toAdd;

    /**
     * Creates an AddAppointmentCommand to add the specified {@code Patient}
     */
    public AddAppointmentCommand(Appointment appointment) {
        requireNonNull(appointment);
        toAdd = appointment;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasName(toAdd.getName())) {
            throw new CommandException(MESSAGE_PATIENT_NOT_EXIST);
        }

        if (model.hasAppointment(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_APPOINTMENT);
        }

        model.addAppointment(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddAppointmentCommand // instanceof handles nulls
                && toAdd.equals(((AddAppointmentCommand) other).toAdd));
    }
}
