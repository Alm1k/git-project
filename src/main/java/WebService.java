import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class WebService {
    private final HashMap<String, String> requestData = new HashMap<>();

    public void put(String key, String value) {
        try {
            requestData.put(
                    URLEncoder.encode(key, Pastebin.ENCODING),
                    URLEncoder.encode(value, Pastebin.ENCODING)
            );
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void delete(String key) {
        requestData.remove(key);
    }

    public String getParsedRequestData() {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, String> entry : requestData.entrySet()) {
            builder.append(entry.getKey()).append('=').append(entry.getValue())
                    .append('&');
        }
        builder.deleteCharAt(builder.length() - 1);

        return new String(builder);
    }

    public String makeRequest() {
        StringBuilder builder = new StringBuilder();
        try {
            URL url = new URL(Pastebin.API_POST_LINK);
            URLConnection connection = url.openConnection();

            connection.setDoOutput(true);

            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(connection.getOutputStream());

            outputStreamWriter.write(getParsedRequestData());
            outputStreamWriter.flush();
            outputStreamWriter.close();


            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                if (builder.length() > 0) {
                    builder.append('\n');
                }
                builder.append(line);
            }
            reader.close();

            System.out.println(builder);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }
}
