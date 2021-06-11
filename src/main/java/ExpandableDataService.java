public class ExpandableDataService {
    private String storedData;
    private static ExpandableDataService instance;
    public String value;

    private ExpandableDataService() {
        storedData = "";
    }

    public static ExpandableDataService getInstance() {
        if (instance == null) {
            instance = new ExpandableDataService();
        }
        return instance;
    }

    public void store(String data) {
        storedData += "\n" + data;
    }

    public void clear() {
        storedData = "";
    }

    public String get() {
        return storedData;
    }

    public void set(String data) {
        storedData = data;
    }
}
