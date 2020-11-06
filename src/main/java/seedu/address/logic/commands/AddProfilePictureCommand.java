
package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FILE_PATH;

import java.io.File;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.Patient;
import seedu.address.model.patient.ProfilePicture;
import seedu.address.storage.StorageManager;

public class AddProfilePictureCommand extends Command {

    public static final String COMMAND_WORD = "addpicture";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a profile picture to the patient specified by "
            + "the index. \n"
            + "Parameters: INDEX (must be a positive number) "
            + PREFIX_FILE_PATH + "File path to the image \n\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_FILE_PATH + "downloads/profile_picture";

    public static final String MESSAGE_ADD_PROFILE_PICTURE_SUCCESS = "Profile picture added for %1$s";

    private final File profilePicture;
    private final Index patientIndex;

    /**
     * Adds a profile picture to the patient.
     */
    public AddProfilePictureCommand(File profilePicture, Index patientIndex) {
        requireNonNull(profilePicture);
        requireNonNull(patientIndex);
        this.profilePicture = profilePicture;
        this.patientIndex = patientIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        assert model != null : "Model cannot be null";
        List<Patient> lastShownList = model.getFilteredPatientList();

        int index = patientIndex.getZeroBased();
        assert index > -1 : "Index cannot be negative";
        if (patientIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
        }

        Patient patientToEdit = lastShownList.get(index);

        Name patientNameObject = patientToEdit.getName();
        String patientName = patientNameObject.toString();
        String filePath = StorageManager.addPicture(patientName, profilePicture);
        ProfilePicture profilePicture = new ProfilePicture(filePath);

        Patient patientEdited = new Patient(patientToEdit.getName(), patientToEdit.getPhone(),
                patientToEdit.getIcNumber(), patientToEdit.getVisitHistory(), patientToEdit.getAddress(),
                patientToEdit.getEmail(), profilePicture, patientToEdit.getSex(), patientToEdit.getBloodType(),
                patientToEdit.getAllergies(), patientToEdit.getColorTag());

        model.setPatient(patientToEdit, patientEdited);

        Name editedPersonNameObject = patientEdited.getName();
        String editedPersonName = editedPersonNameObject.toString();
        CommandResult commandResult = new CommandResult(String.format(MESSAGE_ADD_PROFILE_PICTURE_SUCCESS,
                editedPersonName));
        return commandResult;
    }
}
