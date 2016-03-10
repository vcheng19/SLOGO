package frontend;

import javafx.scene.control.TextInputDialog;

public class TextPrompt{
    private TextInputDialog myPrompt;
    public TextPrompt(String defaultVal, String message){
        myPrompt = new TextInputDialog(defaultVal);
        myPrompt.setHeaderText(message);
    }
    public void show(){
        myPrompt.showAndWait();
    }
    
    public String getText(){
        return myPrompt.getResult();
    }
    
}