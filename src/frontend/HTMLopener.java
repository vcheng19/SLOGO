package frontend;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class HTMLopener 
{
	private Stage stage;
	private Group root;
	private Button openButton;
	private boolean isOpen;
	private ResourceBundle sceneResources =
	            ResourceBundle.getBundle(UserInterface.DEFAULT_RESOURCE_PACKAGE + UserInterface.SCENE);   
	
	public HTMLopener()
	{
		openButton = new Button(sceneResources.getString("HELPBUTTON")); 
		openButton.getStyleClass().add(sceneResources.getString("BUTTONID"));
		openButton.setOnAction(e -> openHTML());
		stage = new Stage();
		stage.setOnCloseRequest(e -> helpClosed());
		isOpen = false;
	}

	public Button getButton()
	{
		return openButton;
	}

	public void openHTML()
	{
		if ( isOpen )
			return;
		
		isOpen = true;
		URL url = getClass().getResource("/"+ UserInterface.DEFAULT_RESOURCE_PACKAGE+ "help.html");
		WebView webview = new WebView();
		webview.getEngine().load(url
			    .toExternalForm()); 	// TODO: resource file!
		stage.setScene(new Scene(webview));
		stage.show();
	}

	private void helpClosed()
	{
		isOpen = false;
	}	

}
