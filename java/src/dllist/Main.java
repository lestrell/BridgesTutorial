package dllist;
/**
 * Created by Lucas Estrella on 1/31/2017.
 * lestrell@uncc.edu
 */
import bridges.base.DLelement;
import bridges.base.Element;
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
        Bridges<String, Student> bridge = new Bridges<String, Student>(7, credentialsObject.get("applicationID").getAsString(), credentialsObject.get("username").getAsString());


        /**
         * new SLelement<>(label, genericData)
         */

        //initializing all elements with empty labels, and with the student data. See Object model.Student.java
        DLelement<Student> el0 = null;

          for(int i = 0; i < StudentProvider.getStudents().length; i++){
              if(i > 1){
                  el0 = insertFront(el0, new DLelement<>("",StudentProvider.getStudentAt(i)));
              }else if(i == 0){
                  el0 = new DLelement<>("",StudentProvider.getStudentAt(0));
                  el0.setNext(new DLelement<>("",StudentProvider.getStudentAt(1)));
              }
          }

          DLelement<Student> current = el0;
          while(current != null){
              current.setLabel(current.getValue().getStudentLabel());
              current.getVisualizer().setColor(current.getValue().getFavoriteColor());

              if(current.getNext() != null)current.getLinkVisualizer(current.getNext()).setColor(current.getValue().getDislikeColor());
              if(current.getPrev() != null)current.getLinkVisualizer(current.getPrev()).setColor(current.getValue().getDislikeColor());

              current = current.getNext();
          }

//        el0.setNext(el1);
//
//        el1.setPrev(el0);
//        el1.setNext(el2);
//
//        el2.setPrev(el1);
//        el2.setNext(el3);
//
//        el3.setPrev(el2);
//        el3.setNext(el4);
//
//        el4.setPrev(el3);
//

        bridge.setDataStructure(el0);
        bridge.visualize();


    }

    public static DLelement insertFront(DLelement tail,
                                        DLelement newElement){
        DLelement tailNext = tail.getNext();

        newElement.setNext(tailNext);
        newElement.setPrev(tail);

        tailNext.setPrev(newElement);
        tail.setNext(newElement);
        return tail;
    }





}
