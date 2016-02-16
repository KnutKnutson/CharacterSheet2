package com.boredombabies.charactersheet.io;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.boredombabies.charactersheet.R;
import com.boredombabies.charactersheet.model.PlayerCharacter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 * Created on 2/15/16.
 */
public class CharacterSerializer {
    public static final String CHARACTER_JSON_FILE = "characterSheetExportedCharacter.json";
    Context context;

    public CharacterSerializer(Context context) {
        this.context = context;
    }

    public boolean exportCharacter(PlayerCharacter playerCharacter) {
        String json = getGson().toJson(playerCharacter);
        saveToExternalStorage(json);
        return true;
    }

    private Gson getGson() {
        Gson gson = new GsonBuilder().create();
        return gson;
    }

    /** GET RID OF ALL THE FILE SAVING **/

    private boolean saveToExternalStorage(String characterToExportJson) {
        if (!isExternalStorageWritable()) {
            return false;
        }
        File file = new File(context.getExternalFilesDir(null), CHARACTER_JSON_FILE);
        file.setReadable(true, false); // world readable necessary for NFC file transfer
        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));

            bufferedWriter.write(characterToExportJson);

            bufferedWriter.close();
            outputStream.close();
        } catch (IOException e) {
            Log.w("ExternalStorage", "Error writing " + file, e);
        }
        return true;
    }

    private String readFromExternalStorage() {
        if (!isExternalStorageReadable()) {
            return null;
        }
        File file = new File(context.getExternalFilesDir(null), CHARACTER_JSON_FILE);
        String fileContents = "";
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            String fileRow;
            while ((fileRow = bufferedReader.readLine()) != null) {
                fileContents += fileRow;
            }
            bufferedReader.close();
            fileInputStream.close();
        } catch (IOException e) {
            Log.w("ExternalStorage", "Error reading " + file, e);
        }
        return fileContents;
    }

    public boolean deleteExternalStoragePrivateFile() {
        if (!isExternalStorageWritable()) {
            return false;
        }
        File file = new File(context.getExternalFilesDir(null), CHARACTER_JSON_FILE);
        if (file != null) {
            file.delete();
        }
        return true;
    }

    boolean hasExternalStoragePrivateFile() {
        if (!isExternalStorageReadable()) {
            return false;
        }
        File file = new File(context.getExternalFilesDir(null), "DemoFile.jpg");
        if (file != null) {
            return file.exists();
        }
        return false;
    }

    /* Checks if external storage is available for read and write */
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /* Checks if external storage is available to at least read */
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }
}
