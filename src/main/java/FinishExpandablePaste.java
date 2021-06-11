import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;

public class FinishExpandablePaste extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        Project project = e.getData(PlatformDataKeys.PROJECT);
        VirtualFile vFile = e.getData(PlatformDataKeys.VIRTUAL_FILE);
        PasteService service = new PasteService();

        if (
                service.validatePaste(ExpandableDataService.getInstance().get(), project) != null
                && service.confirmFinishDialog(project) == 0
        ) {
            service.finishPaste(
                    ExpandableDataService.getInstance().get(),
                    Pastebin.getTypeFromVirtualFile(vFile)
            );
        }
    }
}
