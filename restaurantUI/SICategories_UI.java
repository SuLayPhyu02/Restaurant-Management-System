package restaurant_project_ui;

import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Predicate;

import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import restaurant_project.*;

public class SICategories_UI {
	private Label lid,lsicname,lsicprice;
	private TextField tid,tsicname,tsicprice;
	private Button btnAdd,btnUpdate;
	private BorderPane mainPane;
	private GridPane gp;
	
	private ArrayList<SItemCategories>al;
	private TableView<SItemCategories>tvSI;
	private TableSelectionModel<SItemCategories> selectionModel;
	private TableColumn<SItemCategories, Integer> idCol;
	private TableColumn<SItemCategories, String> nameCol;
	private TableColumn<SItemCategories, Integer> priceCol;
	
	private Label lKeyword;
	private TextField tSearch;
	private FlowPane searchPane;
	private FilteredList<SItemCategories>fl;
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
		fl=new FilteredList<SItemCategories>(FXCollections.observableArrayList(al));
		fl.setPredicate(new Predicate<SItemCategories>() 
		{
			public boolean test(SItemCategories s)
			{
				String value=tSearch.getText();
				value=value.toLowerCase();
				
				String sicname=s.getSicname().toLowerCase();
				if(value.length()==0)
				{
					return true;
				}
				try {
					int price=Integer.parseInt(value);
					return price==s.getSicprice();
				}catch(Exception e)
				{
					return sicname.equals(value);
				}
			}
		});
		tvSI.getItems().clear();
		tvSI.getItems().addAll(fl);
	}
	public void createAddPane()
	{
		lid=new Label("ID");
		lsicname=new Label("Item Name");
		lsicprice=new Label("Item Price");
		
		tid=new TextField();
		tid.setMaxWidth(200);
		tsicname=new TextField();
		tsicname.setMaxWidth(200);
		tsicprice=new TextField();
		tsicprice.setMaxWidth(200);
		
		btnAdd=new Button("Add");
		btnAdd.setOnAction(e->{
			Add();
		});
		btnUpdate=new Button("Update");
		btnUpdate.setOnAction(e->{
			Update();
		});
		gp=new GridPane();
		gp.add(lid, 0, 0);
		gp.add(tid, 1, 0);
		
		gp.add(lsicname, 0, 1);
		gp.add(tsicname, 1, 1);
		
		gp.add(lsicprice, 0, 2);
		gp.add(tsicprice, 1, 2);
		
		gp.add(btnAdd, 0, 3);
		gp.add(btnUpdate, 1, 3);
		gp.setHgap(20);
		gp.setVgap(20);
		gp.setPadding(new Insets(20));
		gp.setAlignment(Pos.CENTER);
		gp.setMaxWidth(350);
		lid.getStyleClass().add("SI");
		lsicname.getStyleClass().add("SI");
		lsicprice.getStyleClass().add("SI");
		btnAdd.getStyleClass().add("btn");
		btnUpdate.getStyleClass().add("btn");
		btnAdd.getStyleClass().add("AddB");
		btnUpdate.getStyleClass().add("UpdateB");
		gp.setId("updatePane");
		
	}
	public void createTableView()
	{
		tvSI=new TableView<SItemCategories>();
		
		idCol=new TableColumn<SItemCategories, Integer>("ID");
		idCol.setCellValueFactory(new PropertyValueFactory<SItemCategories,Integer>("sicid"));
		
		nameCol=new TableColumn<SItemCategories, String>("Name");
		nameCol.setCellValueFactory(new PropertyValueFactory<SItemCategories,String>("sicname"));
		
		priceCol=new TableColumn<SItemCategories, Integer>("Price");
		priceCol.setCellValueFactory(new PropertyValueFactory<SItemCategories,Integer>("sicprice"));
		
		idCol.getStyleClass().add("tableText");
		nameCol.getStyleClass().add("tableText");
		priceCol.getStyleClass().add("tableText");
		
		tvSI.getColumns().add(idCol);
		tvSI.getColumns().add(nameCol);
		tvSI.getColumns().add(priceCol);
		tvSI.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		selectionModel=tvSI.getSelectionModel();
		selectionModel.setSelectionMode(SelectionMode.SINGLE);
		tvSI.setEditable(true);
		tvSI.getSortOrder().add(idCol);
		tvSI.setOnMouseClicked(e->{
			if(e.getButton()==MouseButton.PRIMARY)
			{
				setUpdateInfo();
			}
		});
		setData();
		
	}
	public void setUpdateInfo()
	{
		SItemCategories si=selectionModel.getSelectedItem();
		tid.setText(""+si.getSicid());
		tid.setEditable(false);
		tsicname.setText(""+si.getSicname());
		tsicprice.setText(""+si.getSicprice());
	}
	public void setData()
	{
		al=DBHandler.getAllSICategories();
		tvSI.getItems().addAll(al);
	}
	public void Add()
	{
		String name;
		int id,price;
		id=DBHandler.getSCIMax();
		id++;
		tid.setText(""+id);
		name=tsicname.getText().trim();
		price=Integer.parseInt(tsicprice.getText().trim());
		Alert  alt=new Alert(AlertType.CONFIRMATION);
		alt.setHeaderText("Confirmation");
		alt.setContentText("Are you sure to add ?");
		Optional<ButtonType>ans=alt.showAndWait();
		if(ans.isPresent() && ans.get()==ButtonType.OK)
		{
			boolean add=DBHandler.insertSICategories(id,name,price);
			if(add=true)
			{
				Alert con=new Alert(AlertType.INFORMATION);
				con.setHeaderText("Successful");
				con.setContentText("you insert successfully");
				con.show();
			}
		}
		else
		{
			System.out.println("siid"+id);
			Alert sorry=new Alert(AlertType.INFORMATION);
			sorry.setHeaderText("unsuccessfully");
			sorry.setContentText("Sorry try again!!");
			sorry.show();
		}
		
	}
	public void Update()
	{
		String name;
		int id,price;
		id=Integer.parseInt(tid.getText().trim());
		name=tsicname.getText().trim();
		price=Integer.parseInt(tsicprice.getText().trim());
		Alert  alt=new Alert(AlertType.CONFIRMATION);
		alt.setHeaderText("Confirmation");
		alt.setContentText("Are you sure to update ?");
		Optional<ButtonType>ans=alt.showAndWait();
		if(ans.isPresent() && ans.get()==ButtonType.OK)
		{
			boolean update=DBHandler.updateSICategories(id, name, price);
			if(update==true)
			{
				Alert con=new Alert(AlertType.INFORMATION);
				con.setHeaderText("Successful");
				con.setContentText("you update successfully");
				con.show();
			}
			else 
			{
				Alert sorry=new Alert(AlertType.INFORMATION);
				sorry.setHeaderText("unsuccessfully");
				sorry.setContentText("Sorry try again!!");
				sorry.show();
			}
		}
		
	}
	public BorderPane getMainPane()
	{
		createSearchBar();
		createTableView();
		createAddPane();
		mainPane=new BorderPane();
		mainPane.setTop(searchPane);
		mainPane.setCenter(tvSI);
		mainPane.setRight(gp);
		mainPane.setMargin(searchPane,new Insets(0,0,20,0));
		return mainPane;
	}
	public void setMainPane(BorderPane mainPane) {
		this.mainPane = mainPane;
	}
	
	

}
