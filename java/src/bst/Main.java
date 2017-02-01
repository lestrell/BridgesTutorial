package bst;

import bridges.connect.Bridges;
import bst.shaffer.BST;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import model.earthquake.Earthquake;
import util.Auth;
import util.DownloadJSONHelper;
import util.YandexStaticMap;

import java.io.FileNotFoundException;

/**
 * Created by Lucas Estrella on 1/31/2017.
 */
public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        JsonObject credentialsObject = Auth.getAuth();
        Bridges<String, Earthquake> bridges = new Bridges<String, Earthquake>(1, credentialsObject.get("applicationID").getAsString(), credentialsObject.get("username").getAsString());

        JsonObject jsonData = DownloadJSONHelper.downloadJSONHelper("http://earthquakes-uncc.herokuapp.com/eq/latest/100");

        Earthquake[] earthquakes;

        BST<Double,Earthquake> bst = new BST<Double, Earthquake>();


        if(jsonData != null){
            earthquakes = new Gson().fromJson(jsonData.getAsJsonArray("Earthquakes"), Earthquake[].class);

            for(int i = 0; i < earthquakes.length; i++){
                String url = new YandexStaticMap()
                                .reset()
                                .setLatitude(earthquakes[i].getGeometry().getCoordinates()[0])
                                .setLongitude(earthquakes[i].getGeometry().getCoordinates()[1])
                                .setSize(null)
                                .setZoom("5 ")
                                .build();

                earthquakes[i].setMapURl(url.replace("https","http"));
                bst.insert(Double.valueOf(earthquakes[i].getProperties().getMag()),earthquakes[i]);

            }
        }


        if(bst != null && bst.getTreeRoot() != null){
            bst.setLabels(bst.getTreeRoot());
            bst.setElementColors(bst.getTreeRoot());
            bridges.setDataStructure(bst.getTreeRoot());
            bridges.visualize();
        }

    }

}
