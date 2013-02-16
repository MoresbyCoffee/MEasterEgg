package org.moresbycoffee.measteregg.android.util;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.test.AndroidTestCase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

public class TestUtils extends AndroidTestCase {

    private final static String ASSET_DIR = "responses";
    
    public static String getStringFromAssets(Context context, String filename) throws IOException, JSONException {
        InputStream is = context.getResources().getAssets().open(ASSET_DIR + "/" + filename);
        String textfile = convertStreamToString(is);
        return textfile;
    }
    
    public static JSONObject getJson(Context context, String filename) throws IOException, JSONException {
        InputStream is = context.getResources().getAssets().open(ASSET_DIR + "/" + filename);
        String textfile = convertStreamToString(is);
        return new JSONObject(textfile);
    }

    private static String convertStreamToString(InputStream is)
            throws IOException {
            Writer writer = new StringWriter();

            char[] buffer = new char[2048];
            try {
                Reader reader = new BufferedReader(new InputStreamReader(is,
                        "UTF-8"));
                int n;
                while ((n = reader.read(buffer)) != -1) {
                    writer.write(buffer, 0, n);
                }
            } finally {
                is.close();
            }
            String text = writer.toString();
            return text;
    }
}
