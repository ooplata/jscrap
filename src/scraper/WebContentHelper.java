package scraper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class WebContentHelper {
    /**
     * Gets the web contents from the provided URL.
     *
     * @param source A URL in string form.
     * @return The contents of the URL if possible, an empty string otherwise.
     */
    public static String GetWebContent(String source) {
        try {
            var url = new URL(source);
            var conn = url.openConnection();

            var reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            var output = new StringBuilder();
            var curr = reader.readLine();

            while (curr != null) {
                output.append(curr);
                output.append('\n');

                curr = reader.readLine();
            }

            reader.close();
            return output.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void main(String[] args) {
        System.out.println(GetWebContent("https://docs.microsoft.com/en-us/uwp/api/windows.ui.xaml.markup.ixamltype"));
    }
}
