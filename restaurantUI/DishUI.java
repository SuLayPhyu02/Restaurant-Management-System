package restaurant_project_ui;
import java.util.*;
import restaurant_project.*;

import java.util.function.Predicate;


import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.*;
import javafx.scene.control.cell.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.text.Text;
import javafx.stage.Stage;



public class DishUI{
	private TableView tvDish,tvSub;
	private TableSelectionModel<Dish> selectionModel;
	private TableSelectionModel<Sub_Categories> selectionModelC;
	
	
	private ArrayList<Dish> dal;
	private ArrayList<Sub_Categories> subal;
	private FilteredList<Dish> fl;
	private FilteredList<Sub_Categories>cfl;
	
	private TableColumn<Dish, Integer> idCol;
	private TableColumn<Dish, String> naCol;
	private TableColumn<Dish, Integer> prCol;
	
	private TableColumn<Sub_Categories, Integer>subidCol;
	private TableColumn<Sub_Categories, String> subnaCol;
	
	private Label lDKeyword,lCKeyword,lid,ldname,ldprice,lsubcid,lsubcname,subcate,cate;
	private TextField tDSerach,tCSearch,tid,tname,tprice,tscid,tscname,subname,catname;
	private Button btnAdd,btnUpdate,btnDelete,btnSCAdd,btnSCUpdate,btnSCDelete;
	private FlowPane searchDPane,searchSCPane,btnFlow,btnSCFlow;
	private GridPane AddPane,AddSCPane;
	private HBox hb,hscb,dishbox;
	private HBox searchbox,updatebox;
	private BorderPane mainPane;
	
	public void createSearchBar()
	{
		lDKeyword=new Label("Search for dish ");
		tDSerach=new TextField();
		//work press enter 
		tDSerach.setOnKeyPressed(e->{
			if(e.getCode()==KeyCode.ENTER)
				filteringD();
		});
		searchDPane=new FlowPane(20,20,lDKeyword,tDSerach);
		searchDPane.setAlignment(Pos.TOP_LEFT);
		searchDPane.setPadding(new Insets(15));
		//setting styles
		lDKeyword.getStyleClass().add("SearchWord");
		tDSerach.getStyleClass().add("Search");
	}
	public void createCategorySearchBar()
	{
		lCKeyword=new Label("Search for category :");
		tCSearch=new TextField();
		//work press enter 
		tCSearch.setOnKeyPressed(e->{
			if(e.getCode()==KeyCode.ENTER)
				filteringSubC();
		});
		searchSCPane=new FlowPane(20,20,lCKeyword,tCSearch);
		searchSCPane.setAlignment(Pos.TOP_RIGHT);
		searchSCPane.setPadding(new Insets(15));
		lCKeyword.getStyleClass().add("SearchWord");
		tCSearch.getStyleClass().add("Search");
	}
	public void createAddPane()
	{
		lid=new Label("ID");
		lid.setPrefWidth(80);
		tid=new TextField();
		
		
		ldname=new Label("Name");
		ldname.setPrefWidth(80);
		tname=new TextField();
		
		ldprice=new Label("Price");
		ldname.setPrefWidth(80);
		tprice=new TextField();
		
		subcate=new Label("Sub Cate:");
		subcate.setPrefWidth(80);
		subname=new TextField();
				
		btnAdd=new Button("Add");
		btnDelete=new Button("Delete");
		btnUpdate=new Button("Update");
		
		AddPane=new GridPane();
		AddPane.add(lid, 0, 0);
		AddPane.add(tid, 1, 0);
		
		AddPane.add(ldname, 0, 1);
		AddPane.add(tname, 1, 1);
		
		AddPane.add(ldprice, 0, 2);
		AddPane.add(tprice, 1, 2);
		
		AddPane.add(subcate, 0, 3);
		AddPane.add(subname, 1, 3);
		
		AddPane.setHgap(20);
		AddPane.setVgap(20);
		
		btnFlow=new FlowPane(btnAdd,btnUpdate,btnDelete);
		btnFlow.setAlignment(Pos.BOTTOM_CENTER);
		btnFlow.setPadding(new Insets(10));
		btnFlow.setHgap(30);
		
		hb=new HBox(20,AddPane,btnFlow);
		hb.setAlignment(Pos.BOTTOM_LEFT);
		hb.setPadding(new Insets(20));
		
		//setting style
		lid.getStyleClass().add("dishT");
		ldname.getStyleClass().add("dishT");
		ldprice.getStyleClass().add("dishT");
		subcate.getStyleClass().add("dishT");
		
		
		btnAdd.getStyleClass().add("btn");
		btnAdd.setOnAction(e->{
			AddD();

		});
		btnUpdate.getStyleClass().add("btn");
		btnUpdate.setOnAction(e->{
			updateD();
		
		});
		btnDelete.getStyleClass().add("btn");
		btnDelete.setOnAction(e->{
			deleteD();
			
		});
		
		//setting styles
		btnAdd.getStyleClass().add("AddB");
		btnUpdate.getStyleClass().add("UpdateB");
		btnDelete.getStyleClass().add("DeleteB");
		
		hb.setId("DishHB");
		
	}
	public void createAddPaneC()
	{
		lsubcid=new Label("ID");
		lsubcid.setPrefWidth(80);
		tscid=new TextField();
		
		lsubcname=new Label("Name");
		lsubcname.setPrefWidth(80);
		tscname=new TextField();
		
		cate=new Label("Category");
		cate.setPrefWidth(80);
		catname=new TextField();
		
		btnSCAdd=new Button("Add");
		btnSCAdd.setOnAction(e->{
			AddSubC();

		});
		btnSCDelete=new Button("Delete");
		btnSCDelete.setOnAction(e->{
			deleteSubC();
			
		});
		btnSCUpdate=new Button("Update");
		btnSCUpdate.setOnAction(e->{
			updateSubC();
		
		});
		
		AddSCPane=new GridPane();
		AddSCPane.add(lsubcid, 0, 0);
		AddSCPane.add(tscid, 1, 0);
		
		AddSCPane.add(lsubcname, 0, 1);
		AddSCPane.add(tscname, 1, 1);
		
		AddSCPane.add(cate, 0, 2);
		AddSCPane.add(catname, 1, 2);
		
		AddSCPane.setHgap(20);
		AddSCPane.setVgap(20);
		
		btnSCFlow=new FlowPane(btnSCAdd,btnSCUpdate,btnSCDelete);
		btnSCFlow.setAlignment(Pos.BOTTOM_CENTER);
		btnSCFlow.setPadding(new Insets(10));
		btnSCFlow.setHgap(30);
		
		hscb=new HBox(20,AddSCPane,btnSCFlow);
		hscb.setAlignment(Pos.BOTTOM_RIGHT);
		hscb.setPadding(new Insets(20));
		
		//setting style
		lsubcid.getStyleClass().add("dishT");
		lsubcname.getStyleClass().add("dishT");
		cate.getStyleClass().add("dishT");
		btnSCAdd.getStyleClass().add("btn");
		btnSCUpdate.getStyleClass().add("btn");
		btnSCDelete.getStyleClass().add("btn");
		btnSCAdd.getStyleClass().add("AddB");
		btnSCUpdate.getStyleClass().add("UpdateB");
		btnSCDelete.getStyleClass().add("DeleteB");
		hscb.setId("DishHB");
		
	}
	public void filteringD()
	{
		fl=new FilteredList<Dish>(FXCollections.observableArrayList(dal));
		fl.setPredicate(new Predicate<Dish>() {
			@Override
			public boolean test(Dish d) {
				String value=tDSerach.getText();
				if(value.length()==0)
					return true;
				try {
					int price=Integer.parseInt(value);
					return price==d.getDprice();
					
				}catch(Exception e)
				{
					return value.equals(d.getDname());
				}
				
			}
			
		});
		tvDish.getItems().clear();
		tvDish.getItems().addAll(fl);
	}
	public void filteringSubC()
	{
		cfl=new FilteredList<Sub_Categories>(FXCollections.observableArrayList(subal));
		cfl.setPredicate(new Predicate<Sub_Categories>() {
			@Override
			public boolean test(Sub_Categories sc) {
				String value=tCSearch.getText();
				if(value.length()==0)
					return true;
				else
					return value.equals(sc.getScname());
				
			}
			
		});
		tvSub.getItems().clear();
		tvSub.getItems().addAll(cfl);
	}
	public void deleteD()
	{
		Alert alt=new Alert(AlertType.CONFIRMATION);
		alt.setHeaderText("Confirmation");
		alt.setContentText("Do you want to delete?");
		Optional<ButtonType>ans=alt.showAndWait();
		if(ans.isPresent() && ans.get()==ButtonType.OK)
		{
			Dish d=selectionModel.getSelectedItem();
			tvDish.getItems().remove(d);
			boolean delete=DBHandler.deleteD(d.getDid());
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
	public void deleteSubC()
	{
		Alert alt=new Alert(AlertType.CONFIRMATION);
		alt.setHeaderText("Confirmation");
		alt.setContentText("Do you want to delete?");
		Optional<ButtonType>ans=alt.showAndWait();
		if(ans.isPresent() && ans.get()==ButtonType.OK)
		{
			Sub_Categories sc=selectionModelC.getSelectedItem();
			tvSub.getItems().remove(sc);
			boolean delete=DBHandler.deleteSubC(sc.getScid());
			System.out.println(delete);
		}
		else {
			Alert sorry=new Alert(AlertType.INFORMATION);
			sorry.setHeaderText("unsuccessfully");
			sorry.setContentText("Sorry try again!!");
			sorry.show();	
		}
	}
	public void updateD()
	{
		int id=Integer.parseInt(tid.getText());
		tid.setEditable(false);
		String name=tname.getText();
		int dprice=Integer.parseInt(tprice.getText());
		String subn=subname.getText();
		int subcid=DBHandler.getSubCateid(subn);
		boolean b=DBHandler.UpdateDishes(id, name, dprice,subcid);
		
		Alert  alt=new Alert(AlertType.CONFIRMATION);
		alt.setHeaderText("Confirmation");
		alt.setContentText("Are you sure to update ?");
		Optional<ButtonType>ans=alt.showAndWait();
		if(ans.isPresent() && ans.get()==ButtonType.OK)
		{
			if(b==true)
			{
				Alert con=new Alert(AlertType.INFORMATION);
				con.setHeaderText("Successful");
				con.setContentText("you update successfully");
				con.show();
			}
			else
			{
				System.out.println("subcatename"+name);
				System.out.println("dprice"+dprice);
				System.out.println("subcid"+subcid);
				Alert sorry=new Alert(AlertType.INFORMATION);
				sorry.setHeaderText("unsuccessfully");
				sorry.setContentText("Sorry try again!!");
				sorry.show();
			}
		}

	}
	public void updateSubC()
	{
		int id=Integer.parseInt(tscid.getText());
		tscid.setEditable(false);
		String name=tscname.getText();
		String category=catname.getText();
		int cid=DBHandler.getCateid(category);
		boolean b=DBHandler.UpdateSubC(id, name,cid);
		
		Alert  alt=new Alert(AlertType.CONFIRMATION);
		alt.setHeaderText("Confirmation");
		alt.setContentText("Are you sure to update ?");
		Optional<ButtonType>ans=alt.showAndWait();
		if(ans.isPresent() && ans.get()==ButtonType.OK)
		{
			if(b==true)
			{
				Alert con=new Alert(AlertType.INFORMATION);
				con.setHeaderText("Successful");
				con.setContentText("you update successfully");
				con.show();
			}
			else
			{
				System.out.println("subcatename"+name);
				System.out.println("category"+category);
				System.out.println("cid"+cid);
				Alert sorry=new Alert(AlertType.INFORMATION);
				sorry.setHeaderText("unsuccessfully");
				sorry.setContentText("Sorry try again!!");
				sorry.show();
			}
		}
	}
	public void AddD()
	{
		String dname=tname.getText();
		int dprice=Integer.parseInt(tprice.getText());
		String subn=subname.getText();
		int subcid=DBHandler.getSubCateid(subn);
		boolean b=DBHandler.insertDishes(dname, dprice,subcid);
		Alert  alt=new Alert(AlertType.CONFIRMATION);
		alt.setHeaderText("Confirmation");
		alt.setContentText("Are you sure to add ?");
		Optional<ButtonType>ans=alt.showAndWait();
		if(ans.isPresent() && ans.get()==ButtonType.OK)
		{
			if(b==true)
			{
				Alert con=new Alert(AlertType.INFORMATION);
				con.setHeaderText("Successful");
				con.setContentText("you insert successfully");
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
	public void AddSubC()
	{
		String scname=tscname.getText();
		String category=catname.getText();
		int cid=DBHandler.getCateid(category);
		boolean b=DBHandler.insertSubC(scname, cid);
		
		Alert  alt=new Alert(AlertType.CONFIRMATION);
		alt.setHeaderText("Confirmation");
		alt.setContentText("Are you sure to add ?");
		Optional<ButtonType>ans=alt.showAndWait();
		if(ans.isPresent() && ans.get()==ButtonType.OK)
		{
			if(b==true)
			{
				Alert con=new Alert(AlertType.INFORMATION);
				con.setHeaderText("Successful");
				con.setContentText("you insert successfully");
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
	public void createSubCTable()
	{
		tvSub=new TableView<Sub_Categories>();
		subidCol=new TableColumn<Sub_Categories, Integer>("Id");
		subidCol.setCellValueFactory(new PropertyValueFactory<Sub_Categories, Integer>("scid"));
		
		subnaCol=new TableColumn<Sub_Categories, String>("Name");
		subnaCol.setCellValueFactory(new PropertyValueFactory<Sub_Categories, String>("scname"));
		
		tvSub.getColumns().add(subidCol);
		tvSub.getColumns().add(subnaCol);
		tvSub.setMaxWidth(600);
		tvSub.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		selectionModelC=tvSub.getSelectionModel();
		selectionModelC.setSelectionMode(SelectionMode.SINGLE);
		tvSub.setEditable(true);
		tvSub.setOnMouseClicked(e->{
			if(e.getButton()==MouseButton.PRIMARY)
			{
				setUpdateInfoSubC();
			}
		});
		setSubCatData();
		subidCol.getStyleClass().add("tableText");
		subnaCol.getStyleClass().add("tableText");
		
	}
	public void createDishTable()
	{
		tvDish=new TableView<Dish>();
		idCol=new TableColumn<Dish, Integer>("Did");
		idCol.setCellValueFactory(new PropertyValueFactory<Dish	, Integer>("did"));
		
		
		naCol=new TableColumn<Dish, String>("Name");
		naCol.setCellValueFactory(new PropertyValueFactory<Dish, String>("dname"));
		naCol.setSortable(true);
		
		prCol=new TableColumn<Dish, Integer>("Price");
		prCol.setCellValueFactory(new PropertyValueFactory<Dish, Integer>("dprice"));
		
		idCol.getStyleClass().add("tableText");
		naCol.getStyleClass().add("tableText");
		prCol.getStyleClass().add("tableText");
		
		tvDish.getColumns().add(idCol);
		tvDish.getColumns().add(naCol);
		tvDish.getColumns().add(prCol);
		tvDish.setMaxWidth(1800);
		tvDish.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		selectionModel=tvDish.getSelectionModel();
		selectionModel.setSelectionMode(SelectionMode.SINGLE);
		tvDish.setEditable(true);
		tvDish.setOnMouseClicked(e->{
			if(e.getButton()==MouseButton.PRIMARY)
			{
				setUpdateInfo();
			}
		});
		setDish_Data();
	}
	public void setDish_Data()
	{
		dal=DBHandler.getAllDishes();
		fl=new FilteredList<Dish>(FXCollections.observableArrayList(dal));
		tvDish.getItems().addAll(fl);
	}
	public void setSubCatData()
	{
		subal=DBHandler.getAllSubC();
		cfl=new FilteredList<Sub_Categories>(FXCollections.observableArrayList(subal));
		tvSub.getItems().addAll(subal);
	}
	public void setUpdateInfo()
	{
		Dish d=selectionModel.getSelectedItem();
		tid.setText(""+d.getDid());
		tid.setEditable(false);
		tname.setText(""+d.getDname());
		tprice.setText(""+d.getDprice());
		subname.setText(""+d.getSubcid());
	}
	public void setUpdateInfoSubC()
	{
		Sub_Categories sc=selectionModelC.getSelectedItem();
		tscid.setText(""+sc.getScid());
		tscid.setEditable(false);
		tscname.setText(""+sc.getScname());
		catname.setText(""+sc.getCid());
	}
	public BorderPane getMainPane() 
	{
		createDishTable();
		createSubCTable();
		createSearchBar();
		createCategorySearchBar();
		createAddPane();
		createAddPaneC();
		mainPane=new BorderPane();
		searchbox=new HBox(searchDPane,searchSCPane);
		searchbox.setAlignment(Pos.CENTER);
		searchbox.setPadding(new Insets(70));
		searchbox.getStyleClass().add("searchD");
		mainPane.setTop(searchbox);
		mainPane.setMargin(searchbox, new Insets(0,0,20,0));
		dishbox=new HBox(150,tvDish,tvSub);
		dishbox.setAlignment(Pos.CENTER);
		dishbox.setPadding(new Insets(10));
		mainPane.setCenter(dishbox);
		updatebox=new HBox(hb,hscb);
		updatebox.setAlignment(Pos.CENTER);
		mainPane.setBottom(updatebox);
		
		return mainPane;
	}
	public void setMainPane(BorderPane mainPane) {
		this.mainPane = mainPane;
	}


}
