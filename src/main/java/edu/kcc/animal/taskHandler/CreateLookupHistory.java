package edu.kcc.animal.taskHandler;

import edu.kcc.animal.Animal;
import edu.kcc.animal.data.AnimalDAO;
import edu.kcc.animal.data.AnimalDAOXML;
import edu.kcc.animal.data.AnimalDataException;
import edu.kcc.animal.data.AnimalDataHandler;
import edu.kcc.ui.UIUtility;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Melissa Isaacson
 */
public class CreateLookupHistory {
    private HashMap<String, Animal> animals = new HashMap<>();
    
    public void handleTask(AnimalDAO dao) {
        try {
            
            animals = dao.getAllAnimals();
            
            UIUtility.showSectionTitle("Animal Lookup History");
            
            System.out.println(animals.size());
            
            animals.forEach((key, value) -> {
                System.out.println(key + ": " + value.toString());
            });
            
//            for(HashMap.Entry<String, Animal> entry: animals.entrySet()) {
//                System.out.println(entry.getKey() + ": " + entry.getValue().toString());
//            }
        } catch (AnimalDataException ex) {
            Logger.getLogger(CreateLookupHistory.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
