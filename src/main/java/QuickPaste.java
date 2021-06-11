import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public class QuickPaste extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        Editor editor = e.getData(PlatformDataKeys.EDITOR);
        Project project = e.getData(PlatformDataKeys.PROJECT);
        VirtualFile vFile = e.getData(PlatformDataKeys.VIRTUAL_FILE);
        PasteService service = new PasteService();
        String textToPaste = service.validatePaste(editor, project, true);

        if (textToPaste != null) {
            service.finishPaste(
                    textToPaste,
                    Pastebin.getTypeFromVirtualFile(vFile)
            );
        }
    }
}
