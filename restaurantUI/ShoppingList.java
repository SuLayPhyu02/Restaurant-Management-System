package restaurant_project_ui;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Predicate;

import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableSelectionModel;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import restaurant_project.DBHandler;
import restaurant_project.ShoppingItems;

public class ShoppingList {
	private TableView tvShopping;
	private ArrayList<ShoppingItems> al;
	private FilteredList<ShoppingItems>fl;
	private TableColumn<ShoppingItems,Integer> idCol;
	private TableColumn<ShoppingItems,String> nameCol;
	private TableColumn<ShoppingItems,Integer> qtyCol;
	private TableColumn<ShoppingItems,String> qtyUnitCol;
	private TableColumn<ShoppingItems,Integer> priceCol;
	private TableColumn<ShoppingItems,String> DateCol;
	private TableColumn<ShoppingItems, Integer>sidCol;
	private TableColumn<ShoppingItems,String> staffCol;
	private TableColumn<ShoppingItems,Integer> totalAm;
	private TableSelectionModel<ShoppingItems> selectionModel;
	private BorderPane mainPane;
	
	private Label lKeyword;
	private TextField tSearch;
	private FlowPane searchPane;
	public void createSearchBar()
	{

		lKeyword=new Label("Search Here:");
		tSearch=new TextField();
		tSearch.setOnKeyPressed(e->{
			if(e.getCode()==KeyCode.ENTER)
			{
				filtering();
			}
		});
		
		searchPane=new FlowPane(20,20,lKeyword,tSearch);
		searchPane.setAlignment(Pos.CENTER_RIGHT);
		searchPane.setPadding(new Insets(15));
		searchPane.getStyleClass().add("searchD");
		lKeyword.getStyleClass().add("se");
		tSearch.getStyleClass().add("Search");
		
	}
	public void filtering()
	{
		fl=new FilteredList<ShoppingItems>(FXCollections.observableArrayList(al));
		fl.setPredicate(new Predicate<ShoppingItems >() 
		{
			public boolean test(ShoppingItems s)
			{
				String value=tSearch.getText();
				String sicname=s.getSicname().toLowerCase();
				String sname=s.getSname().toLowerCase();
				String buydate=s.getBuydate();
				if(value.length()==0)
				{
					return true;
				}
				if(value.equals(sicname) || value.equals(buydate) || value.equals(sname))
				{
					return true;
				}
				else
				{
					return false;
				}
				
			}
		});
		tvShopping.getItems().clear();
		tvShopping.getItems().addAll(fl);
	}
	public void createShopTable()
	{
		tvShopping=new TableView<ShoppingItems>();
		
		idCol=new TableColumn<ShoppingItems, Integer>("Id");
		idCol.setCellValueFactory(new PropertyValueFactory<ShoppingItems,Integer>("siid"));
		
		nameCol=new TableColumn<ShoppingItems, String>("Name");
		nameCol.setCellValueFactory(new PropertyValueFactory<ShoppingItems,String>("sicname"));
		
		qtyCol=new TableColumn<ShoppingItems, Integer>("Qty");
		qtyCol.setCellValueFactory(new PropertyValueFactory<ShoppingItems,Integer>("iqty"));
		
		qtyUnitCol=new TableColumn<ShoppingItems, String>("Unit");
		qtyUnitCol.setCellValueFactory(new PropertyValueFactory<ShoppingItems,String>("qtyunit"));
		
		priceCol=new TableColumn<ShoppingItems, Integer>("Price");
		priceCol.setCellValueFactory(new PropertyValueFactory<ShoppingItems,Integer>("sicprice"));
		
		DateCol=new TableColumn<ShoppingItems, String>("Date");
		DateCol.setCellValueFactory(new PropertyValueFactory<ShoppingItems,String>("buydate"));
		DateCol.setSortable(true);
		
		totalAm=new TableColumn<ShoppingItems,Integer>("Total");
		totalAm.setCellValueFactory(new PropertyValueFactory<ShoppingItems,Integer>("total"));
		
		sidCol=new TableColumn<ShoppingItems,Integer>("Staff id");
		sidCol.setCellValueFactory(new PropertyValueFactory<ShoppingItems,Integer>("staffid"));
		
		staffCol=new TableColumn<ShoppingItems,String>("Staff Name");
		staffCol.setCellValueFactory(new PropertyValueFactory<ShoppingItems,String>("sname"));
		
		tvShopping.getColumns().add(idCol);
		tvShopping.getColumns().add(nameCol);
		tvShopping.getColumns().add(qtyCol);
		tvShopping.getColumns().add(qtyUnitCol);
		tvShopping.getColumns().add(priceCol);
		tvShopping.getColumns().add(DateCol);
		tvShopping.getColumns().add(sidCol);
		tvShopping.getColumns().add(staffCol);
		tvShopping.getColumns().add(totalAm);
		idCol.getStyleClass().add("tableText");
		nameCol.getStyleClass().add("tableText");
		qtyCol.getStyleClass().add("tableText");
		qtyUnitCol.getStyleClass().add("tableText");
		priceCol.getStyleClass().add("tableText");
		DateCol.getStyleClass().add("tableText");
		sidCol.getStyleClass().add("tableText");
		staffCol.getStyleClass().add("tableText");
		totalAm.getStyleClass().add("tableText");
		tvShopping.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		selectionModel=tvShopping.getSelectionModel();
		selectionModel.setSelectionMode(SelectionMode.SINGLE);
		tvShopping.setOnMouseClicked(e->{
			if(e.getButton()==MouseButton.PRIMARY)
			{
				if(e.getClickCount()==2)
				{
					delete();
				}
			}
		});
		setData();	
	}
	public void setData()
	{
		al=DBHandler.getAllShopIView();
		tvShopping.getItems().addAll(al);
	}
	public void delete()
	{
		Alert alt=new Alert(AlertType.CONFIRMATION);
		alt.setHeaderText("Confirmation");
		alt.setContentText("Do you want to delete?");
		Optional<ButtonType>ans=alt.showAndWait();
		if(ans.isPresent() && ans.get()==ButtonType.OK)
		{
			ShoppingItems si=selectionModel.getSelectedItem();
			tvShopping.getItems().remove(si);
			boolean delete=DBHandler.deleteD(si.getSiid());
			System.out.println(delete);
		}
		else
		{
			Alert sorry=new Alert(AlertType.INFORMATION);
			sorry.setHeaderText("unsuccessfully");
			sorry.setContentText("Sorry try again!!");
			sorry.show();
		}
	}
	public BorderPane getMainPane()
	{
		createShopTable();
		createSearchBar();
		mainPane=new BorderPane();
		mainPane.setTop(searchPane);
		mainPane.setCenter(tvShopping);
		mainPane.setPadding(new Insets(20));
		return mainPane;
	}
	public void setMainPane(BorderPane mainPane) {
		this.mainPane = mainPane;
	}

}
