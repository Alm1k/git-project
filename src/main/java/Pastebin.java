import com.intellij.openapi.vfs.VirtualFile;

import java.util.Locale;

public final class Pastebin {
    public static String API_KEY = "PASTE API KEY";
    public static final String API_POST_LINK = "https://pastebin.com/api/api_post.php";
    public static final String ENCODING = "UTF-8";

    public static String getTypeFromVirtualFile(VirtualFile virtualFile) {
        return virtualFile == null
                ? null
                : virtualFile.getFileType().getName().toLowerCase(Locale.ROOT);
    }
}
