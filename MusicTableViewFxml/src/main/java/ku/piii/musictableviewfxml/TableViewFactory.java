/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ku.piii.musictableviewfxml;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import ku.piii.model.MusicMedia;
import ku.piii.model.MusicMediaColumnInfo;

/**
 *
 * @author James
 */
@SuppressWarnings("restriction")
public class TableViewFactory {

    
    static public void processInput(MusicMedia editItem, String newValue, String editProperty)
    {
        System.out.println("New value is " + newValue + " for property " + editProperty);
    }
    public static List<MusicMediaColumnInfo> makeColumnInfoList() {
        List<MusicMediaColumnInfo> myColumnInfoList = new ArrayList<MusicMediaColumnInfo>();
        myColumnInfoList.add(new MusicMediaColumnInfo().setHeading("Path")
                                             .setMinWidth(200)
                                             .setProperty("path")
        );
        myColumnInfoList.add(new MusicMediaColumnInfo().setHeading("Length (secs)")
                                             .setMinWidth(20)
                                             .setProperty("lengthInSeconds")
        );
        myColumnInfoList.add(new MusicMediaColumnInfo().setHeading("Track Title")
                                             .setMinWidth(100)
                                             .setProperty("title")
        );
        myColumnInfoList.add(new MusicMediaColumnInfo().setHeading("Year")
                                             .setMinWidth(10)
                                             .setProperty("year")
        );
        myColumnInfoList.add(new MusicMediaColumnInfo().setHeading("Genre")
                                             .setMinWidth(100)
                                             .setProperty("genre")
        );
       
        return myColumnInfoList;
        
    }
//    private String path;
//    private Integer lengthInSeconds;
    
//    private Id3Version id3Version;
    // retrieves from the ID tag:
//    private String title;
//    private String year;
//    private String genre;
    
    
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	static public void  makeTable(TableView<MusicMedia>      tableView, 
                                      List<MusicMediaColumnInfo> myColumnInfoList )
    {
        

        for(final MusicMediaColumnInfo myColumnInfo : myColumnInfoList)
        {
            @SuppressWarnings("rawtypes")
			TableColumn thisColumn = new TableColumn(myColumnInfo.getHeading());
            thisColumn.setMinWidth(myColumnInfo.getMinWidth());
               
            thisColumn.setCellValueFactory(
                new PropertyValueFactory<MusicMedia, String>(myColumnInfo.getProperty())
            );
            thisColumn.setCellFactory(TextFieldTableCell.forTableColumn());
            
            thisColumn.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<MusicMedia, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<MusicMedia, 
                                       String> editEvent) {
                    
                        int editRow = editEvent.getTablePosition().getRow();
                        MusicMedia editItem = editEvent.getTableView()
                                                        .getItems()
                                                        .get(editRow);                    
                        processInput(editItem, 
                                     editEvent.getNewValue(), 
                                     myColumnInfo.getProperty());
                    }
                }
            );
            tableView.getColumns().add(thisColumn);
        }
    }
     
}
