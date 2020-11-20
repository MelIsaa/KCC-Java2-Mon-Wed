/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.kcc;

import edu.kcc.animal.data.AnimalDAO;
import edu.kcc.animal.data.AnimalDAOFactory;
import edu.kcc.animal.taskHandler.AddAnimal;
import edu.kcc.animal.taskHandler.FindAnAnimal;
import edu.kcc.taskHandler.ShowAllAnimals;
import edu.kcc.ui.UIUtility;
import java.util.function.Function;

/**
 *
 * @author k0519415
 */
public class Main {

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) {
        UIUtility.showMessage("Program starting...");
        
        String menuTitle = "Main Menu";
        String[] menuOptions = {
            "1) Add an Animal",
            "2) Find an Animal",
            "3) Show All Animals",
            "4) Update an Animal",
            "5) Delete an Animal",
            "6) Exit"
        };
        String prompt = "Your choice: ";
        String errorMessage = "Invalid option. Please try again.";
        String userChoice;
        AnimalDAO dao = AnimalDAOFactory.getAnimalDAO();
        
        // Primary Program Logic
        boolean working = true;
        while(working) {
            userChoice = UIUtility.showMenuOptions(menuTitle, prompt,
                    menuOptions);
            
            switch(userChoice) {
                case "1":
                    new AddAnimal().handleTask(dao);
                    break;
                case "2":
                    new FindAnAnimal().handleTask(dao);
                    break;
                case "3":
                    new ShowAllAnimals().handleTask(dao);
                    break;
                case "4":
                    // code
                    break;
                case "5":
                    // code
                    break;
                case "6":
                    working = false;
                    break;
                default:
                    UIUtility.showErrorMessage(errorMessage, true);
            }
        }
        UIUtility.showMessage("\nProgram Complete.");
    }
    
}
