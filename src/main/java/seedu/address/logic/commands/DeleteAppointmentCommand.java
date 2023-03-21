package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.stream.Collectors;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.id.AppointmentId;

/**
 * Deletes the appointment identified by user.
 */
public class DeleteAppointmentCommand extends Command {
    public static final String COMMAND_WORD = "delete_appt";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the appointment identified by the ID.\n"
            + "Parameters: ID (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_APPOINTMENT_SUCCESS = "Deleted Appointment: %1$s";

    private final AppointmentId id;

    public DeleteAppointmentCommand(AppointmentId id) {
        this.id = id;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Appointment> lastShownList = model.getFilteredAppointmentList();
        List<Appointment> matchingAppointments =
                lastShownList.stream().filter(appt -> appt.getAppointmentId().equals(id)).collect(
                        Collectors.toList());

        if (matchingAppointments.size() != 1) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPOINTMENT_ID);
        }

        Appointment appointmentToDelete = matchingAppointments.get(0);
        model.deleteAppointment(appointmentToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_APPOINTMENT_SUCCESS, appointmentToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteAppointmentCommand // instanceof handles nulls
                && id.equals(((DeleteAppointmentCommand) other).id)); // state check
    }
}
