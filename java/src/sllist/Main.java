package sllist; /**
 * Created by Lucas Estrella on 1/31/2017.
 */
import bridges.connect.Bridges;
import bridges.base.SLelement;
import com.google.gson.JsonObject;
import model.StudentInfo;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import util.Auth;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException, ParseException {


        JsonObject credentialsObject = Auth.getAuth();
        Bridges<String, StudentInfo> bridge = new Bridges<String, StudentInfo>(2, credentialsObject.get("applicationID").getAsString(), credentialsObject.get("username").getAsString());

        final String lineBreak = "\n";

        /**
         * new SLelement<model.StudentInfo>(label, genericData)
         */
        //initializing all elements with empty labels, and with the student data. See Object model.StudentInfo.java
        SLelement<StudentInfo> el0 =
                new SLelement<StudentInfo>(
                        "",
                        new StudentInfo(
                                "00000000000",
                                "Gretel Chaney",
                                "CS",
                                "g.chaney@generated.com",
                                "magenta",
                                "blue",
                                9.0,
                                "https://randomuser.me/api/portraits/med/women/45.jpg"
                        )
                );

        SLelement<StudentInfo> el1 =
                new SLelement<StudentInfo>(
                        "",
                        new StudentInfo(
                                "00000000001",
                                "Karol Soderman",
                                "SIS",
                                "k.soderman@generated.com",
                                "magenta",
                                "red",
                                11.0,
                                "https://randomuser.me/api/portraits/med/women/46.jpg"
                        )
                );
        SLelement<StudentInfo> el2 =
                new SLelement<StudentInfo>(
                        "",
                        new StudentInfo(
                                "00000000002",
                                "Lamont Kyler",
                                "BIO",
                                "l.kyler@generated.com",
                                "yellow",
                                "green",
                                12.0,
                                "https://randomuser.me/api/portraits/med/men/80.jpg"));
        SLelement<StudentInfo> el3 =
                new SLelement<StudentInfo>(
                        "",
                        new StudentInfo(
                                "00000000003",
                                "Gladys Serino",
                                "CS","g.serino@generated.com",
                                "blue",
                                "magenta",
                                9.0,
                                "https://randomuser.me/api/portraits/med/women/2.jpg"));
        SLelement<StudentInfo> el4 =
                new SLelement<StudentInfo>(
                        "",
                        new StudentInfo("00000000004",
                                "Starr Mcginn",
                                "CS",
                                "s.mcginn@generated.com",
                                "red",
                                "yellow",
                                15.0,
                                "https://randomuser.me/api/portraits/med/men/87.jpg"));

        //Linking the Singly LinkedList Element1 -> Element2 -> Element3 -> Element4 -> NULL
        el0.setNext(el1);
        el1.setNext(el2);
        el2.setNext(el3);
        el3.setNext(el4);

        SLelement<StudentInfo> currentElement = el0;

        while(currentElement != null){
            System.out.println(currentElement.getValue().getFullName());

            currentElement.getVisualizer().setColor(currentElement.getValue().getFavoriteColor());

            if(currentElement.getNext() != null){

                currentElement
                        .getLinkVisualizer(currentElement.getNext())
                        .setColor(currentElement.getValue().getDislikeColor());
                currentElement
                        .getLinkVisualizer(currentElement.getNext())
                        .setThickness(currentElement.getValue().getStudentCreditHours() * 0.75);//75 percent thinner

            }


            currentElement.setLabel(
                                                           currentElement.getValue().getFullName() + lineBreak +
                                               "Email: " + currentElement.getValue().getEmail() + lineBreak   +
                                             "Program: " + currentElement.getValue().getProgram() + lineBreak   +
                                          "Student ID: " + currentElement.getValue().getStudentID() + lineBreak   +
                                      "Favorite Color: " + currentElement.getValue().getFavoriteColor() + lineBreak +
                                "<img width='100' src='" + currentElement.getValue().getAvatar()+"'/>"
                                    );

            currentElement = currentElement.getNext();
        }



        bridge.setDataStructure(el0);
        bridge.visualize();


    }





}
