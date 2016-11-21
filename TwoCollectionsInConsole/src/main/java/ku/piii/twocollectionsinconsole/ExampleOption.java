/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ku.piii.twocollectionsinconsole;

/**
 *
 * @author ku14009
 */
class ExampleOption {

    
    // enumeration would be better?
    public enum OptionID{OPTION_EXIT, 
        OPTION_LOAD_FIRST_COLLECTION,
        OPTION_SAVE_FIRST_COLLECTION,
        OPTION_CLEAR_FIRST_COLLECTION,
        OPTION_LIST_FIRST_COLLECTION,
        OPTION_LOAD_SECOND_COLLECTION,
        OPTION_SAVE_SECOND_COLLECTION,
        OPTION_CLEAR_SECOND_COLLECTION,
        OPTION_LIST_SECOND_COLLECTION};
    
    
    
    
    private OptionID myOptionID;
    private int optionNumber;
    private String optionText;

    ExampleOption(int optionNumber, OptionID code, String optionText) {
        //this.code = code;
        setOptionID(code);
        setOptionNumber(optionNumber);
        setOptionText(optionText);
        
        
        
    }
    
    public OptionID getOptionID() {
        return myOptionID;
    }

    public void setOptionID(OptionID o) {
        this.myOptionID = o;
    }

    public int getOptionNumber() {
        return optionNumber;
    }

    public void setOptionNumber(int optionNumber) {
        this.optionNumber = optionNumber;
    }

    public String getOptionText() {
        return optionText;
    }

    public void setOptionText(String optionText) {
        this.optionText = optionText;
    }

    
}
