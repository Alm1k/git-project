import com.intellij.ide.BrowserUtil;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.project.Project;

import javax.annotation.Nullable;
import java.util.Locale;

public class PasteService {
    WebService service = new WebService();

    public void finishPaste(String paste, String pasteFormat) {
        System.out.println("paste" + paste);
        System.out.println(pasteFormat);
        service.put("api_dev_key", Pastebin.API_KEY);
        service.put("api_option", "paste");
        service.put("api_paste_code", paste);

        if (pasteFormat != null) {
            service.put("api_paste_format", pasteFormat);
        }

        String result = service.makeRequest();

        BrowserUtil.browse(result);
    }

    public String validatePaste(Editor editor, Project project) {
        String paste = editor != null ? editor.getSelectionModel().getSelectedText() : null;
        return validatePaste(paste, project, false);
    }

    public String validatePaste(Editor editor, Project project, boolean finish) {
        String paste = editor != null ? editor.getSelectionModel().getSelectedText() : null;
        return validatePaste(paste, project, finish);
    }

    public String validatePaste(String paste, Project project) {
        return validatePaste(paste, project, false);
    }

    public String validatePaste(String paste, Project project, boolean finish) {
        if (paste == null) {
            Messages.showErrorDialog(
                    project,
                    "You didn't select any text. \nMake sure you select the text that you want to paste.",
                    "No Data Selected"
            );
            return null;
        }

        if (finish && confirmFinishDialog(project) == 0) {
            return paste;
        }

        return finish
                ? confirmFinishDialog(project) == 0
                    ? paste
                    : null
                : paste;
    }

    public int confirmFinishDialog(Project project) {
        return Messages.showOkCancelDialog(
                project,
                "Do you want to create a new paste on Pastebin?",
                "Select an Option",
                "Ok",
                "Cancel",
                Messages.getQuestionIcon()
        );
    }
}
