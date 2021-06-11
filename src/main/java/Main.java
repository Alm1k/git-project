public class Main {
    public static void main(String[] args) {
        WebService service = new WebService();

        // required parameters
        service.put("api_dev_key", Pastebin.API_KEY);
        service.put("api_option", "paste");
        service.put("api_paste_code", "test"); // TODO: add code parser
        service.put("api_paste_private", Integer.toString(0));
//        service.makeRequest();
    }
}
