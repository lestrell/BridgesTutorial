package graph;

import bridges.base.GraphAdjList;
import bridges.connect.Bridges;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import model.VideoGame;
import model.earthquake.Earthquake;
import util.Auth;
import util.DownloadJSONHelper;

import java.io.FileNotFoundException;
import java.util.HashMap;

/**
 * Created by Lucas Estrella on 2/1/2017.
 */
public class Main {

    private static HashMap<String, String> gamePlatformColors = new HashMap<String, String>(){{
        put("Xbox 360", "green");
        put("Xbox", "green");
        put("PC", "black");

        put("Wireless","yellow");

        put("Game Boy Advance", "cyan");
        put("Nintendo DS","cyan");
        put("Genesis","cyan");
        put("Wii","cyan");
        put("Super NES","cyan");
        put("GameCube","cyan");
        put("Game Boy Advance","cyan");

        put("PlayStation Portable","magenta");
        put("PlayStation 2","magenta");
        put("PlayStation 3","magenta");
    }};

    public static void main(String[] args) throws FileNotFoundException {

        JsonObject credentialsObject = Auth.getAuth();
        Bridges<String, VideoGame> bridges = new Bridges<>(3, credentialsObject.get("applicationID").getAsString(), credentialsObject.get("username").getAsString());

        JsonObject jsonData = DownloadJSONHelper.downloadJSONHelper("http://bridgesdata.herokuapp.com/api/games");

        VideoGame[] videoGames;


        GraphAdjList<String, VideoGame> graph = new GraphAdjList<>();

        if(jsonData != null) {
            videoGames = new Gson().fromJson(jsonData.getAsJsonArray("data"), VideoGame[].class);
            for(int i = 0; i < videoGames.length && i < 2500; i++){
//                System.out.println(videoGames[i].getGame());
                graph.addVertex(videoGames[i].getGame(),videoGames[i]);
                graph.addVertex(videoGames[i].getPlatform(),videoGames[i]);

                graph.addEdge(videoGames[i].getGame(), videoGames[i].getPlatform(), (int)videoGames[i].getRating() * 3);
                graph.addEdge(videoGames[i].getPlatform(), videoGames[i].getGame(), (int)videoGames[i].getRating() * 3);
            }


            for(String key : graph.getVertices().keySet()){
                if(gamePlatformColors.containsKey(graph.getVertices().get(key).getValue().getPlatform())){
                    graph.getVertices().get(key).getVisualizer().setColor(gamePlatformColors.get(graph.getVertices().get(key).getValue().getPlatform()));
                    graph.getVertices().get(key).setLabel(graph.getVertices().get(key).getValue().getLabel());

                }
            }


            //pass graph
            bridges.setDataStructure(graph);

            //visualize data structure
            bridges.visualize();


        }



    }
}
