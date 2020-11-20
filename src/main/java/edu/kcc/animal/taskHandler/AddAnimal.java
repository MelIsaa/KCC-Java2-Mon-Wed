package edu.kcc.animal.taskHandler;

import edu.kcc.animal.Animal;
import edu.kcc.animal.data.AnimalDAO;
import edu.kcc.animal.data.AnimalDataHandler;
import edu.kcc.ui.UIUtility;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Melissa Isaacson
 */
public class AddAnimal implements AnimalDataHandler {
    @Override
    public void handleTask(AnimalDAO dao) {
        UIUtility.showSectionTitle("Add an Animal");
        Animal animal = new Animal();
        boolean needed = true;
        
        while(needed) {
            try {
                animal.setId(UIUtility.getUserString("Enter Id Number: "));
            } catch(IllegalArgumentException ex) {
                UIUtility.showErrorMessage(ex.getMessage(), true);
            }
        }
        
        needed = true;
        while(needed) {
            try {
                animal.setName(UIUtility.getUserString("Enter Name: "));                
            } catch(IllegalArgumentException ex) {
                UIUtility.showErrorMessage(ex.getMessage(), true);
            }
        }
        
        needed = true;
        while(needed) {
            try {
                animal.setSpecies(UIUtility.getUserString("Enter Species (cat or "
                    + "dog): "));                
            } catch(IllegalArgumentException ex) {
                UIUtility.showErrorMessage(ex.getMessage(), true);
            }
        }
        
        needed = true;
        while(needed) {
            try {
                animal.setGender(UIUtility.getUserString("Enter Gender (male or "
                    + "female): "));                
            } catch(IllegalArgumentException ex) {
                UIUtility.showErrorMessage(ex.getMessage(), true);
            }
        }
        
        needed = true;
        while(needed) {
            try {
                animal.setAge(UIUtility.getUserInt("Enter Age: ", "Age can only be "
                    + "an integer"));                
            } catch(NumberFormatException ex) {
                UIUtility.showErrorMessage(ex.getMessage(), true);
            } catch (IllegalArgumentException ex) {
                UIUtility.showErrorMessage(ex.getMessage(), true);                
            }
        }
        
        needed = true;
        while(needed) {
            try {
                animal.setFixed(UIUtility.getUserBoolean("Is the animal fixed? (true or"
                    + " false) "));                
            } catch (IllegalArgumentException ex) {
                UIUtility.showErrorMessage(ex.getMessage(), true);                
            }
        }
        
        needed = true;
        while(needed) {
            try {
                animal.setLegs(UIUtility.getUserInt("Enter the number of legs: ", 
                    "Legs can only be an integer"));                
            } catch(NumberFormatException ex) {
                UIUtility.showErrorMessage(ex.getMessage(), true);
            } catch (IllegalArgumentException ex) {
                UIUtility.showErrorMessage(ex.getMessage(), true);                
            }
        }
        
        needed = true;
        while(needed) {
            try {
                animal.setLegs(UIUtility.getUserInt("Enter the number of legs: ", 
                    "Legs can only be an integer"));                
            } catch(NumberFormatException ex) {
                UIUtility.showErrorMessage(ex.getMessage(), true);
            } catch (IllegalArgumentException ex) {
                UIUtility.showErrorMessage(ex.getMessage(), true);                
            }
        }
        
        needed = true;
        while(needed) {
            try {
                animal.setWeight(UIUtility.getUserBigDecimal("Enter the number of legs: ", 
                    "Legs can only be an integer"));                
            } catch(NumberFormatException ex) {
                UIUtility.showErrorMessage(ex.getMessage(), true);
            } catch (IllegalArgumentException ex) {
                UIUtility.showErrorMessage(ex.getMessage(), true);                
            }
        }
        
        needed = true;
        while(needed) {
            
            try {
                animal.setLastFeedingTime(UIUtility.getUserLocalDateTime("Enter "
                        + "the time of last feeding"));
            } catch (IllegalArgumentException ex) {
                UIUtility.showErrorMessage(ex.getMessage(), true);                
            }
        }
        
        // Adding current time
        animal.setDateAdded(LocalDate.now());

    }
}
