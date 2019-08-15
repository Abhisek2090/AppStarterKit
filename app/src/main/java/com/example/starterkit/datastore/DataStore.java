package com.example.starterkit.datastore;

import android.content.Context;

import com.example.starterkit.model.StarterDataModel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;



public class DataStore {

    //file name can be anything
    private static final String USER_FILE = "user_file";

    private Context context;

    public DataStore(Context context) {
        this.context = context;
    }


    //save data in file
    public void writeUserToFile(StarterDataModel dataModel) {
        try {
            FileOutputStream fos = context.openFileOutput(USER_FILE, Context.MODE_PRIVATE);
            ObjectOutputStream ous = new ObjectOutputStream(fos);
            ous.writeObject(dataModel);
            ous.flush();
            ous.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //read data from file
    public StarterDataModel getUserFromFile() {
        try {
            FileInputStream fis = context.openFileInput(USER_FILE);
            ObjectInputStream ois = new ObjectInputStream(fis);
            return (StarterDataModel) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteUserFromFile() {
        context.deleteFile(USER_FILE);
    }


}
