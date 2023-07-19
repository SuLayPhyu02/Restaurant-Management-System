package restaurant_project_ui;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;
import java.util.Locale.FilteringMode;
import java.util.function.Predicate;

import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import restaurant_project.*;
import javafx.stage.Stage;
import model.Checker;
import restaurant_project.*;
import javafx.scene.control.TableView.TableViewSelectionModel;

public class StaffUI{
	private TableViewSelectionModel<Staff>selectionModel;
	private Stage stage;
	private FilteredList<Staff> fl;
	private ArrayList<Staff> al;

	
	private TableView tvStaffs;
	private TableColumn<Staff,Integer> idCol;
	private TableColumn<Staff, String> nCol;
	private TableColumn<Staff, String> poCol;
	private TableColumn<Staff, String> eCol;
	private TableColumn<Staff, String> enrollCol;
	
	//update pane
	private Label lKeyword,lid,lname,lposition,lemail,lenroll;
	private TextField tSearch,tid,tname,temail,tedate;
	private ComboBox< String> Sposition;
	private Button btnUpdate,btnClear;
	
	private GridPane updatePane;
	private FlowPane searchPane;
	private BorderPane mainPane;
	
	
	//register pane
	
	public void createUpdatePane()
	{
		lid=new Label("ID");
		tid=new TextField();
		
		lname=new Label("SName");
		tname=new TextField();
		
		lposition=new Label("Position");
		ArrayList<String>sp=new ArrayList<String>();
		sp.add("Admin");
		sp.add("Manager");
		sp.add("Receptionist");
		Sposition=new ComboBox<String>(FXCollections.observableArrayList(sp));
		

		lemail=new Label("Email");
		temail=new TextField();
		
		lenroll=new Label("Enroll Date");
		tedate=new TextField();
		
				
		btnUpdate=new Button("Update");
		btnUpdate.setOnAction(e->{
			updateStaff();
		});
		btnClear=new Button("Clear");
		btnClear.setOnAction(e->{
			Upclear();
		});
		
		
		updatePane=new GridPane();
		updatePane.add(lid, 0, 0);
		updatePane.add(tid, 1, 0);
		
		updatePane.add(lname, 0, 1);
		updatePane.add(tname, 1, 1);
		
		updatePane.add(lposition, 0, 2);
		updatePane.add(Sposition, 1, 2);
		
		updatePane.add(lemail, 0, 3);
		updatePane.add(temail, 1, 3);
		
		updatePane.add(lenroll, 0, 4);
		updatePane.add(tedate, 1, 4);
		
		
		updatePane.add(btnUpdate, 0, 5);
		updatePane.add(btnClear, 1, 5);
		
		
		updatePane.setHgap(20);
		updatePane.setVgap(20);
		updatePane.setPadding(new Insets(20));
		//updatePane.setPrefWidth(300);
		
		//setting styles
		lid.getStyleClass().add("SU");
		lname.getStyleClass().add("SU");
		lemail.getStyleClass().add("SU");
		lposition.getStyleClass().add("SU");
		Sposition.getStyleClass().add("SU");
		lenroll.getStyleClass().add("SU");
		//lquit.getStyleClass().add("SU");
		
		btnUpdate.getStyleClass().add("btn");
		btnClear.getStyleClass().add("btn");
		btnUpdate.getStyleClass().add("UpdateB");
		btnClear.getStyleClass().add("btnClear");
		updatePane.setId("updatePane");
		

	}
	
	public void createStaffTable()
	{
		tvStaffs=new TableView<Staff>();
		
		idCol=new TableColumn<Staff, Integer>("SID");
		idCol.setCellValueFactory(new PropertyValueFactory<Staff, Integer>("sid"));
		
		nCol=new TableColumn<Staff, String>("Name");
		nCol.setCellValueFactory(new PropertyValueFactory<Staff, String>("sname"));
		
		poCol=new TableColumn<Staff, String>("Position");
		poCol.setCellValueFactory(new PropertyValueFactory<Staff, String>("sposition"));
		
		eCol=new TableColumn<Staff, String>("Email");
		eCol.setCellValueFactory(new PropertyValueFactory<Staff, String>("semail"));
		
		enrollCol=new TableColumn<Staff, String>("Enroll date");
		enrollCol.setCellValueFactory(new PropertyValueFactory<Staff, String>("enrolldate"));
		
		
		tvStaffs.getColumns().add(idCol);
		tvStaffs.getColumns().add(nCol);
		tvStaffs.getColumns().add(poCol);
		tvStaffs.getColumns().add(eCol);
		tvStaffs.getColumns().add(enrollCol);
		tvStaffs.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		idCol.getStyleClass().add("tableText");
		nCol.getStyleClass().add("tableText");
		poCol.getStyleClass().add("tableText");
		eCol.getStyleClass().add("tableText");
		enrollCol.getStyleClass().add("tableText");
		
		selectionModel=tvStaffs.getSelectionModel();
		selectionModel.setSelectionMode(SelectionMode.SINGLE);
		tvStaffs.setEditable(true);
		
		tvStaffs.setOnMouseClicked(e->{
			if(e.getButton()==MouseButton.PRIMARY)
			{
				if(e.getClickCount()==2)
				{
					delete();
				}
				else 
				{
					setUpdateInfo();
				}
			}
		});
		setData();
			
	}
	public void setData()
	{
		al=DBHandler.getAllStaffs();
		fl=new FilteredList<Staff>(FXCollections.observableArrayList(al));
		tvStaffs.getItems().addAll(fl);
	}
	public void setUpdateInfo()
	{
		Staff s=selectionModel.getSelectedItem();
		tid.setText(""+s.getSid());
		tid.setEditable(false);
		tname.setText(s.getSname());
		try {
			Sposition.setValue(s.getSposition());
		}
		catch(Exception e)
		{
			e.getStackTrace();
		}
		temail.setText(s.getSemail());
		tedate.setText(s.getEnrolldate());
	}
	public void Upclear()
	{
		tid.setText("");
		tname.setText("");
		Sposition.getSelectionModel().select("");
		tedate.setText("");
		//tqdate.setText("");
		temail.setText("");
	}
	public void delete()
	{
		Alert alt=new Alert(AlertType.CONFIRMATION);
		alt.setHeaderText("Confirmation");
		alt.setContentText("Do you want to delete?");
		Optional<ButtonType>ans=alt.showAndWait();
		if(ans.isPresent() && ans.get()==ButtonType.OK)
		{
			Staff s=selectionModel.getSelectedItem();
			tvStaffs.getItems().remove(s);
			boolean delete=DBHandler.delete(s.getSid());
		}
	}
	public void updateStaff()
	{ 
		Staff s=selectionModel.getSelectedItem();
		int id=Integer.parseInt(tid.getText());
		String name=tname.getText();
		String position=Sposition.getValue();
		String email=temail.getText();
		String Edate=tedate.getText();
		
		Alert alt=new Alert(AlertType.CONFIRMATION);
		alt.setHeaderText("Confirmation");
		alt.setContentText("Do you want to delete?");
		Optional<ButtonType>ans=alt.showAndWait();
		if(ans.isPresent() && ans.get()==ButtonType.OK)
		{
			boolean b=DBHandler.updateStaff(id, name, position, email, Edate);
			if(b==true)
			{
				Alert con=new Alert(AlertType.INFORMATION);
				con.setHeaderText("Successful");
				con.setContentText("you have been insert successfully");
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
	public void createSearchBar()
	{

		lKeyword=new Label("Search here:");
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
		fl=new FilteredList<Staff>(FXCollections.observableArrayList(al));
		fl.setPredicate(new Predicate<Staff>() 
		{
			public boolean test(Staff s)
			{
				String email=s.getSemail().toLowerCase();
				String position=s.getSposition().toLowerCase();
				String sname=s.getSname().toLowerCase();
				String value=tSearch.getText();
				if(value.length()==0)
				{
					return true;
				}
				if(value.equals(email) ||value.equals(position) || value.equals(sname) )
				{
					return true;
				}
				else 
				{
					return false;
				}
			}
		});
		tvStaffs.getItems().clear();
		tvStaffs.getItems().addAll(fl);
	}
//	public void autocomplete()
//	{
//		ArrayList<Staff>al=DBHandler.getStaffWP();
//		System.out.println("array list"+al);
//		TextFields.bindAutoCompletion(tSearch,al);
//		
//	}
	public BorderPane getMainPane()
	{
		createStaffTable();
		createSearchBar();
		createUpdatePane();
		//autocomplete();
		mainPane=new BorderPane();
		mainPane.setTop(searchPane);
		mainPane.setCenter(tvStaffs);
		mainPane.setMargin(searchPane,new Insets(0,0,20,0));
		mainPane.setRight(updatePane);
		return mainPane;
	}
	public void setMainPane(BorderPane mainPane) {
		this.mainPane = mainPane;
	}
//	public void start(Stage st1) throws Exception {
//		stage=st1;
//		autocomplete();
//		createStaffTable();
//		createSearchBar();
//		createUpdatePane();
//		autocomplete();
//		mainPane=new BorderPane();
//		mainPane.setTop(searchPane);
//		mainPane.setCenter(tvStaffs);
//		mainPane.setMargin(searchPane,new Insets(0,0,20,0));
//		mainPane.setRight(updatePane);
//		Scene sc=new Scene(mainPane,1500,700);
//		URL url=this.getClass().getResource("myRestaurant.css");
//		sc.getStylesheets().add(url.toExternalForm());
//		st1.setScene(sc);
//		st1.setTitle("Login");
//		st1.show();
//	}

	
	
	
	
}