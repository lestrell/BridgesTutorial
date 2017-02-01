package util;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Lucas Estrella on 1/31/2017.
 */
public class Auth {

    public static JsonObject getAuth() throws FileNotFoundException {
        Scanner in = new Scanner(new File("bridges_auth.json"));
        JsonObject credentialsObject = null;
        if(in.hasNext()) {
            credentialsObject = new Gson().fromJson(in.nextLine(), JsonObject.class);
        }
        in.close();

        if(credentialsObject.get("applicationID") == null && credentialsObject.get("username") == null){
            System.err.println("Terminating application, check credentials in bridges_auth.json");
            throw new NullPointerException();
        }

        return credentialsObject;
    }
}
