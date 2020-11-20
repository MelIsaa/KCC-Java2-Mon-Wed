package edu.kcc.animal.taskHandler;

import edu.kcc.animal.Animal;
import edu.kcc.animal.data.AnimalDAO;
import edu.kcc.animal.data.AnimalDataException;
import edu.kcc.animal.data.AnimalDataHandler;
import edu.kcc.ui.UIUtility;

/**
 *
 * @author Melissa Isaacson
 */
public class FindAnAnimal implements AnimalDataHandler {
    
    @Override
    public void handleTask(AnimalDAO dao) {
        UIUtility.showSectionTitle("Find an Animal");
        
        String prompt = "Enter Animal Name: ";
        String animalName = UIUtility.getUserString(prompt);
        UIUtility.showMessage("Searching for animal name " + animalName +
                "...");
        Animal animal;
        
        try {
            animal = dao.getAnimalByAnimalName(animalName);
            
            if(animal == null) {
                UIUtility.showMessage("No animal found with the name: " +
                        animalName);
            } else {
                UIUtility.showMessage("Retrieved animal: " + animal);
            }
        } catch(AnimalDataException ex) {
            UIUtility.showErrorMessage(ex.getMessage(), true);
        }
        
        UIUtility.showMessage("Find an animal complete.");
        UIUtility.pressEnterToContinue();
    }
    
    public Animal getAnimalByAnimalName(AnimalDAO dao,
            String animalName) throws AnimalDataException{
        return dao.getAnimalByAnimalName(animalName);
    }
    
}
