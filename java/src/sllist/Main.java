package sllist;
/**
 * Created by Lucas Estrella on 1/31/2017.
 * lestrell@uncc.edu
 */
import bridges.connect.Bridges;
import bridges.base.SLelement;
import com.google.gson.JsonObject;
import model.Student;
import org.json.simple.parser.ParseException;
import provider.StudentProvider;
import util.Auth;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException, ParseException {


        JsonObject credentialsObject = Auth.getAuth();
        Bridges<String, Student> bridge = new Bridges<String, Student>(2, credentialsObject.get("applicationID").getAsString(), credentialsObject.get("username").getAsString());


        /**
         * new SLelement<>(label, genericData)
         */
        //initializing all elements with empty labels, and with the student data. See Object model.Student.java
        SLelement<Student> el0 = new SLelement<>("", StudentProvider.getStudentAt(0));
        SLelement<Student> el1 = new SLelement<>("", StudentProvider.getStudentAt(1));
        SLelement<Student> el2 = new SLelement<>("", StudentProvider.getStudentAt(2));
        SLelement<Student> el3 = new SLelement<>("", StudentProvider.getStudentAt(3));
        SLelement<Student> el4 = new SLelement<>("", StudentProvider.getStudentAt(4));

        //Linking the Singly LinkedList Element1 -> Element2 -> Element3 -> Element4 -> NULL
        el0.setNext(el1);
        el1.setNext(el2);
        el2.setNext(el3);
        el3.setNext(el4);

        SLelement<Student> currentElement = el0;

        while(currentElement != null){
            currentElement.getVisualizer().setColor(currentElement.getValue().getFavoriteColor());

            if(currentElement.getNext() != null){

                currentElement
                        .getLinkVisualizer(currentElement.getNext())
                        .setColor(currentElement.getValue().getDislikeColor());

                currentElement
                        .getLinkVisualizer(currentElement.getNext())
                        .setThickness(currentElement.getValue().getStudentCreditHours() * 0.75);//75 percent thinner

            }

            currentElement.setLabel(currentElement.getValue().getStudentLabel());

            currentElement = currentElement.getNext();
        }

        bridge.setDataStructure(el0);
        bridge.visualize();


    }





}
