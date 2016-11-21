/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ku.piii.twocollectionsinconsole;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import ku.piii.model.MusicMedia;
import ku.piii.model.MusicMediaCollection;
import ku.piii.twocollectionsinconsole.ExampleOption.OptionID;


/**
 *
 * @author ku14009
 */
public class ExampleInterface {

    ExampleModel model;
    List<ExampleOption> options;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        ExampleModel myModel = new ExampleModel();
        ExampleInterface myInterface = new ExampleInterface();
        myInterface.setModel(myModel);
        while (true) {
            myInterface.setAvailableOptions();
            myInterface.printOptions();
            ExampleOption userSelectedOption
                    = myInterface.getOptionFromUser();
            if (userSelectedOption.getOptionID() == OptionID.OPTION_EXIT) {
                break;
            }
            myInterface.processSelectedOption(userSelectedOption);

        }
    }

    private void setModel(ExampleModel myModel) {
        model = myModel;
    }

    public void setAvailableOptions() {
        options = new ArrayList<ExampleOption>();

        int nextOptionIndex = 1;
        if (model.haveFirstCollection()) {
            options.add(new ExampleOption(
                            nextOptionIndex++, 
                            OptionID.OPTION_SAVE_FIRST_COLLECTION, 
                            "Save First Collection"));
            options.add(new ExampleOption(
                            nextOptionIndex++, 
                            OptionID.OPTION_CLEAR_FIRST_COLLECTION, 
                            "Clear First Collection"));
            options.add(new ExampleOption(
                            nextOptionIndex++, 
                            OptionID.OPTION_LIST_FIRST_COLLECTION, 
                            "List First Collection"));
        } else {
            options.add(new ExampleOption(
                            nextOptionIndex++, 
                            OptionID.OPTION_LOAD_FIRST_COLLECTION, 
                            "Load First Collection"));
        }

        if (model.haveSecondCollection()) {
            options.add(new ExampleOption(
                            nextOptionIndex++, 
                            OptionID.OPTION_SAVE_SECOND_COLLECTION, 
                            "Save Second Collection"));
            options.add(new ExampleOption(
                            nextOptionIndex++, 
                            OptionID.OPTION_CLEAR_SECOND_COLLECTION, 
                            "Clear Second Collection"));
            options.add(new ExampleOption(nextOptionIndex++, 
                            OptionID.OPTION_LIST_SECOND_COLLECTION, 
                            "List Second Collection"));
        } else {
            options.add(new ExampleOption(
                            nextOptionIndex++, 
                            OptionID.OPTION_LOAD_SECOND_COLLECTION, 
                            "Load Second Collection"));
        }
        ExampleOption exitOption = 
                new ExampleOption(
                    nextOptionIndex++, 
                    OptionID.OPTION_EXIT, 
                    "Exit");
        options.add(exitOption);
    }

    private void printOptions() {
        for (ExampleOption option : options) {
            System.out.println(option.getOptionNumber() 
                               + ": " + option.getOptionText());
        }
    }

    private void processSelectedOption(ExampleOption userSelectedOption) {
        OptionID userSelectedCode = userSelectedOption.getOptionID();
        if (userSelectedCode == OptionID.OPTION_CLEAR_FIRST_COLLECTION) {
            model.clearFirstCollection();
        } else if (userSelectedCode == OptionID.OPTION_LOAD_FIRST_COLLECTION) {
            String collectionFilename = "../test-json-files/Collection-A.json";
            model.loadFirstCollection(collectionFilename);
        } else if (userSelectedCode == OptionID.OPTION_SAVE_FIRST_COLLECTION) {
            String filename = "user needs to select this.m3u";
            model.saveFirstCollection(filename);
        } else if (userSelectedCode == OptionID.OPTION_LIST_FIRST_COLLECTION) {
            MusicMediaCollection p = model.getFirstCollection();
            printCollection(p);
        } else if (userSelectedCode == OptionID.OPTION_CLEAR_SECOND_COLLECTION) {
            model.clearSecondCollection();
        } else if (userSelectedCode == OptionID.OPTION_LOAD_SECOND_COLLECTION) {
            String collectionFilename = "../test-json-files/Collection-B.json";
            model.loadSecondCollection(collectionFilename);
        } else if (userSelectedCode == OptionID.OPTION_SAVE_SECOND_COLLECTION) {
            String filename = "user needs to select this.m3u";
            model.saveSecondCollection(filename);
        } else if (userSelectedCode == OptionID.OPTION_LIST_SECOND_COLLECTION) {
            MusicMediaCollection p = model.getSecondCollection();
            printCollection(p);
        } else {
            System.out.println("eek - unsupported option!");
        }
    }

    private void printCollection(MusicMediaCollection p) {
        for (MusicMedia t : p.getMusic()) {
            System.out.println(t.getPath() + ": " + t.getTitle());
        }
    }

    private ExampleOption getOptionFromUser() {
        Scanner in = new Scanner(System.in);
        while (true) {
            int num = in.nextInt();
            for (ExampleOption option : options) {
                if (option.getOptionNumber() == num) {
                    return option;
                }
            }
        }
    }
}
