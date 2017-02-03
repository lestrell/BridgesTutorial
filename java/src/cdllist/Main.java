package cdllist; /**
 * Created by Lucas Estrella on 1/31/2017.
 * lestrell@uncc.edu
 */
import bridges.base.CircDLelement;
import bridges.base.CircSLelement;
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
        Bridges<String, Student> bridge = new Bridges<String, Student>(9, credentialsObject.get("applicationID").getAsString(), credentialsObject.get("username").getAsString());


        /**
         * new SLelement<>(label, genericData)
         */
        //initializing all elements with empty labels, and with the student data. See Object model.Student.java
        CircDLelement<Student> el0 = null;
        for(int i = 0; i < StudentProvider.getStudents().length; i++) {
            if(i > 0) {
                el0 = insertFront(el0, new CircDLelement("",StudentProvider.getStudentAt(i)));
            }else{
                el0 = new CircDLelement("",StudentProvider.getStudentAt(i));
            }
        }

        CircDLelement<Student> current = el0;
        do{
            current.setLabel(current.getValue().getStudentLabel());
            current.getVisualizer().setColor(current.getValue().getFavoriteColor());

            current.getLinkVisualizer(current.getNext()).setColor(current.getValue().getDislikeColor());
            current.getLinkVisualizer(current.getNext()).setThickness(current.getValue().getStudentCreditHours()*.75);

            current.getLinkVisualizer(current.getPrev()).setColor(current.getValue().getDislikeColor());
            current.getLinkVisualizer(current.getPrev()).setThickness(current.getValue().getStudentCreditHours()*.75);

            current = current.getNext();
        }while(current.getIdentifier() != el0.getIdentifier());

        bridge.setDataStructure(el0);
        bridge.visualize();


    }


    public static CircDLelement insertFront(CircDLelement tailElement,
                                            CircDLelement newElement){
        CircDLelement tailNextElement = tailElement.getNext();

        newElement.setNext(tailNextElement);
        newElement.setPrev(tailElement);

        tailNextElement.setPrev(newElement);
        tailElement.setNext(newElement);
        return tailElement;
    }





}
