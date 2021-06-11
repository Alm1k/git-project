import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import org.jetbrains.annotations.NotNull;

public class EditExpandablePaste extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        Editor editor = e.getData(PlatformDataKeys.EDITOR);
        Project project = e.getData(PlatformDataKeys.PROJECT);

        String textToPaste = ExpandableDataService.getInstance().get();

        textToPaste = Messages.showMultilineInputDialog(project, "You can edit your paste", "Edit Your Paste", textToPaste, Messages.getInformationIcon(), null);
        ExpandableDataService.getInstance().set(textToPaste);
    }
}
