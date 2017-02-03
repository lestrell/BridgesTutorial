package csllist;
/**
 * Created by Lucas Estrella on 1/31/2017.
 * lestrell@uncc.edu
 */
import bridges.base.CircDLelement;
import bridges.base.CircSLelement;
import bridges.base.DLelement;
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
        Bridges<String, Student> bridge = new Bridges<String, Student>(8, credentialsObject.get("applicationID").getAsString(), credentialsObject.get("username").getAsString());


        /**
         * new SLelement<>(label, genericData)
         */
        //initializing all elements with empty labels, and with the student data. See Object model.Student.java
        //initializing all elements with empty labels, and with the student data. See Object model.Student.java
//        CircSLelement<Student> el0 = new CircSLelement("",StudentProvider.getStudentAt(0));
//        CircSLelement<Student> el1 = new CircSLelement("",StudentProvider.getStudentAt(1));
//        CircSLelement<Student> el2 = new CircSLelement("",StudentProvider.getStudentAt(2));
//        CircSLelement<Student> el3 = new CircSLelement("",StudentProvider.getStudentAt(3));
//        CircSLelement<Student> el4 = new CircSLelement("",StudentProvider.getStudentAt(4));
//
//        el0.setNext(el1);
//        el0.getVisualizer().setColor(el0.getValue().getFavoriteColor());
//        el0.setLabel(el0.getValue().getStudentLabel());
//
//        el1.setNext(el2);
//        el2.setNext(el3);
//        el3.setNext(el4);
//        el4.setNext(el0);


        CircSLelement<Student> el0 = new CircSLelement("",StudentProvider.getStudentAt(0));
//        el0.setLabel(el0.getValue().getStudentLabel());

        CircSLelement<Student> current = el0;

        for(int i = 1; i < StudentProvider.getStudents().length; i++){

            //if it's not the last student
            if(i != StudentProvider.getStudents().length-1){
                  current.setNext(new CircSLelement("",StudentProvider.getStudentAt(i)));
            }else{

                //set the last element
                current.setNext(new CircSLelement("",StudentProvider.getStudentAt(i)));

//                //set the label for the last element
//                current.setLabel(current.getValue().getStudentLabel());

                //getting the last element
                current = current.getNext();

                //point the last element to the first element, so the list becomes circular.
                current.getNext().setNext(el0);
            }

            //set the label for the current element, excluding the last element
//            current.setLabel(current.getValue().getStudentLabel());

            //set the current element to be the next element
            current = current.getNext();

        }

        current = el0;

        do{
            current.setLabel(current.getValue().getStudentLabel());
            current.getVisualizer().setColor(current.getValue().getFavoriteColor());

            current.getLinkVisualizer(current.getNext()).setColor(current.getValue().getDislikeColor());
            current.getLinkVisualizer(current.getNext()).setThickness(current.getValue().getStudentCreditHours()*0.75);

            current = current.getNext();
        }while(current.getIdentifier() != el0.getIdentifier());



        bridge.setDataStructure(el0);
        bridge.visualize();

    }

}
