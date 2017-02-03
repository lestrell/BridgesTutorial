package graph;

import bridges.base.GraphAdjList;
import bridges.connect.Bridges;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import model.VideoGame;
import util.Auth;
import util.DownloadJSONHelper;

import java.io.FileNotFoundException;
import java.util.HashMap;

/**
 * Created by Lucas Estrella on 2/1/2017.
 */
public class GamePlatformRatingRelation {

    private static HashMap<Integer, String> ratingColors = new HashMap<Integer, String>(){{
        put(1, "green");
        put(2, "green");
        put(3, "black");
        put(4, "black");
        put(5, "yellow");
        put(6, "yellow");
        put(7, "cyan");
        put(8, "cyan");
        put(9, "magenta");
        put(10,"magenta");
    }};

    public static void main(String[] args) throws FileNotFoundException {

        JsonObject credentialsObject = Auth.getAuth();
        Bridges<String, VideoGame> bridges = new Bridges<>(4, credentialsObject.get("applicationID").getAsString(), credentialsObject.get("username").getAsString());

        JsonObject jsonData = DownloadJSONHelper.downloadJSONHelper("http://bridgesdata.herokuapp.com/api/games");

        VideoGame[] videoGames;


        GraphAdjList<String, VideoGame> graph = new GraphAdjList<>();

        if(jsonData != null) {
            videoGames = new Gson().fromJson(jsonData.getAsJsonArray("data"), VideoGame[].class);

            for(int i = 0; i < videoGames.length && i < 2500; i++){
                graph.addVertex(String.valueOf(videoGames[i].getRating()),videoGames[i]);
                graph.addVertex(videoGames[i].getPlatform(),videoGames[i]);

                graph.addEdge(String.valueOf(videoGames[i].getRating()), videoGames[i].getPlatform(), (int)videoGames[i].getRating() * 3);
                graph.addEdge(videoGames[i].getPlatform(), String.valueOf(videoGames[i].getRating()), (int)videoGames[i].getRating() * 3);
            }

            for(String key : graph.getVertices().keySet()){
                if(ratingColors.containsKey((int)graph.getVertices().get(key).getValue().getRating())){
                    graph
                        .getVertices()
                        .get(key)
                        .getVisualizer()
                        .setColor(
                                ratingColors
                                        .get(
                                                (int)graph.getVertices()
                                                        .get(key)
                                                        .getValue()
                                                        .getRating())
                                             );

                    graph
                        .getVertices()
                        .get(key)
                        .setLabel(graph
                                    .getVertices()
                                    .get(key)
                                    .getValue().getLabel()
                                  );

                }
            }


            //pass graph
            bridges.setDataStructure(graph);

            //visualize data structure
            bridges.visualize();


        }

    }

}
