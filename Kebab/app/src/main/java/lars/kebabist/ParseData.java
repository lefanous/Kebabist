package lars.kebabist;

/**
 * Created by lars on 2/23/17.
 */

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.xml.parsers.SAXParserFactory;

class ParseData extends AsyncTask<String, Void, JSONObject> {
    private Exception exception;

    protected JSONObject doInBackground(String... urls) {
        try {
            HttpURLConnection urlConnection = null;

            URL url = new URL(urls[0]);

            urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);

            urlConnection.setDoOutput(true);

            urlConnection.connect();

            BufferedReader br=new BufferedReader(new InputStreamReader(url.openStream()));

            char[] buffer = new char[1024];

            String jsonString = new String();

            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line+"\n");
            }
            br.close();

            jsonString = sb.toString();

            System.out.println("JSON: " + jsonString);
            return new JSONObject(jsonString);
        } catch (Exception e) {
            this.exception = e;

            return null;
        }
    }

    protected void onPostExecute(JSONObject feed) {
        // TODO: check this.exception
        // TODO: do something with the feed
    }
}