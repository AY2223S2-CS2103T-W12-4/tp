package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;

/**
 * Finds and lists all appointments whose timeslots contain the specified time.
 * Keyword matching is case insensitive.
 */
public class FindAppointmentCommand extends Command {

    public static final String COMMAND_WORD = "find_appt";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all appointments whose timeslots cover the "
        + "specified time and displays them as a list with index"
        + "numbers.\n If a second time is specified, all appointments that occur during that time period are "
        + "displayed.\n"
        + "Parameters: ddMMyyyy HH:mm [ddMMyyyy HH:mm]\n"
        + "Example: " + COMMAND_WORD + " 19032023 08:30";

    private final Predicate<Appointment> predicate;

    public FindAppointmentCommand(Predicate<Appointment> predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredAppointmentList(predicate);
        return new CommandResult(
            String.format(Messages.MESSAGE_APPOINTMENTS_LISTED_OVERVIEW, model.getFilteredAppointmentList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof FindAppointmentCommand // instanceof handles nulls
            && predicate.equals(((FindAppointmentCommand) other).predicate)); // state check
    }
}
