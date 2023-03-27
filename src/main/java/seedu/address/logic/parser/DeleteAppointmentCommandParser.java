package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.DeleteAppointmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.patient.Name;

/**
 * Parses input command and deletes Appointment object.
 */
public class DeleteAppointmentCommandParser implements Parser<DeleteAppointmentCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the DeleteAppointmentCommand
     * and returns an DeleteAppointmentCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteAppointmentCommand parse(String args) throws ParseException {
        try {
            Name name = ParserUtil.parseName(args);
            return new DeleteAppointmentCommand(name);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteAppointmentCommand.MESSAGE_USAGE), pe);
        }
    }
}
