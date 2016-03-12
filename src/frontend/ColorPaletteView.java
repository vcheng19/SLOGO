package frontend;

import javafx.scene.control.TableColumn;
import java.util.*;
import javafx.scene.control.TableView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ColorPaletteView extends ModuleView{
    private List<Entry> myDefaults = new ArrayList<Entry>();
    public ColorPaletteView (EntryManager manager, String title, String[] colTitles, DisplayPreferences display) {
        super(manager, title, colTitles);        
        initDefaults(manager);
        setCellDisplay();
        getMyTableView().setMaxWidth(120);//TODO magic value
        defineListener(display);
    }
    
    private void defineListener(DisplayPreferences display){
        getMyTableView().getSelectionModel().selectedItemProperty().addListener((observableValue,oldValue,newValue)->updateColor(display,newValue));
    }
    
    private void updateColor (DisplayPreferences display, Entry newValue) {
        if(getMyTableView().getSelectionModel().getSelectedItem()!=null){
            Color col = (Color) newValue.getSecondValue();
            display.setDisplayColor(col);
        }

    }

    private void initDefaults (EntryManager manager) {//TODO fix this
        manager.addEntry(new StringObjectEntry((""+(manager.getEntryList().size()+1)),Color.WHITE), true);
        manager.addEntry(new StringObjectEntry((""+(manager.getEntryList().size()+1)),Color.RED), true);
        manager.addEntry(new StringObjectEntry((""+(manager.getEntryList().size()+1)),Color.ORANGE), true);
        manager.addEntry(new StringObjectEntry((""+(manager.getEntryList().size()+1)),Color.YELLOW), true);
        manager.addEntry(new StringObjectEntry((""+(manager.getEntryList().size()+1)),Color.GREEN), true);
        manager.addEntry(new StringObjectEntry((""+(manager.getEntryList().size()+1)),Color.BLUE), true);
        manager.addEntry(new StringObjectEntry((""+(manager.getEntryList().size()+1)),Color.MAGENTA), true);
        manager.addEntry(new StringObjectEntry((""+(manager.getEntryList().size()+1)),Color.BLACK), true);
    }
    
    @SuppressWarnings("unchecked")  //TODO figure out how to check
    private void setCellDisplay(){    
        ((TableColumn<Entry,Color>) getMyTableView().getColumns().get(1)).setCellFactory(c-> new ColorDisplayCell());        
    }

    @Override
    protected void setSizing (TableView<Entry> table) {
        table.setPrefHeight(200);//TODO magic number
    }

}
