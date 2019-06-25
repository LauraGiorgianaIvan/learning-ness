package com.lri.learningness.tools;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lri.learningness.base.GlobalApp;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;

public class JsonFileParser<T> {
    public T constructUsingGson(Class<T> type, String jsonTextInput) {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(jsonTextInput, type);
    }

    public String getJsonFromLocalFile(int file) {
        InputStream resourceReader = GlobalApp.appContext().getResources().openRawResource(file);

        StringWriter writer = new StringWriter();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(resourceReader, "UTF-8"));
            String line = reader.readLine();
            while (line != null) {
                writer.write(line);
                line = reader.readLine();
            }
        } catch (Exception e) {
            Log.e("LocalParse", "Unhandled exception while using JSONResourceReader", e);
        } finally {
            try {
                resourceReader.close();
            } catch (Exception e) {
                Log.e("LocalParse", "Unhandled exception while using JSONResourceReader", e);
            }

        }

        return writer.toString();
    }
}
