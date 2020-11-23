/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.kcc;

import edu.kcc.animal.data.AnimalDAO;
import edu.kcc.animal.data.AnimalDAOXML;
import edu.kcc.animal.taskHandler.CreateLookupHistory;
import edu.kcc.animal.taskHandler.FindAnAnimal;
import edu.kcc.ui.UIUtility;

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
            "1) Lookup an Animal By Name",
            "2) View Lookup History",
            "3) Exit"
        };
        String prompt = "Your choice: ";
        String errorMessage = "Invalid option. Please try again.";
        String userChoice;
        // Primary Program Logic
        boolean working = true;
        while(working) {
            userChoice = UIUtility.showMenuOptions(menuTitle, prompt,
                    menuOptions);
            
            switch(userChoice) {
                case "1":
                    new FindAnAnimal().handleTask();
                    break;
                case "2":
                    AnimalDAO dao = new AnimalDAOXML();
                    new CreateLookupHistory().handleTask(dao);
                    break;
                case "3":
                    working = false;
                    break;
                default:
                    UIUtility.showErrorMessage(errorMessage, true);
            }
        }
        UIUtility.showMessage("\nProgram Complete.");
    }
    
}
