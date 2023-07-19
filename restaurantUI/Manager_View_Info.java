package restaurant_project_ui;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import restaurant_project.DBHandler;
import restaurant_project.Staff;

public class Manager_View_Info {
	private ArrayList<Staff> al;
	private TableViewSelectionModel<Staff>selectionModel;
	private TableView tvStaffs;
	private TableColumn<Staff,Integer> idCol;
	private TableColumn<Staff, String> nCol;
	private TableColumn<Staff, String> poCol;
	private TableColumn<Staff, String> eCol;
	private TableColumn<Staff, String> enrollCol;
	
	//update pane
	private Label lid,lname,lposition,lemail,lenroll,lstaff;
	private TextField tid,tname,temail,tedate;
	private ComboBox< String> Sposition;
	private Button btnUpdate,btnClear;
	private TilePane tp;
	private HBox hb;
	private GridPane updatePane,gp;
	private BorderPane mainPane;
	private FlowPane staffPane;
	public void createTopPane()
	{
		lstaff=new Label(DBHandler.getStaffName(LoginUI_re.getStaffId()));
		staffPane=new FlowPane(lstaff);
		staffPane.setAlignment(Pos.CENTER_RIGHT);
		staffPane.setPadding(new Insets(20));
		staffPane.getStyleClass().add("staffpane");
		lstaff.setId("UserName");
	}
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
		idCol.getStyleClass().add("tableText");
		nCol.getStyleClass().add("tableText");
		poCol.getStyleClass().add("tableText");
		eCol.getStyleClass().add("tableText");
		enrollCol.getStyleClass().add("tableText");
		
		tvStaffs.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		selectionModel=tvStaffs.getSelectionModel();
		selectionModel.setSelectionMode(SelectionMode.SINGLE);
		tvStaffs.setEditable(true);
		
		tvStaffs.setOnMouseClicked(e->{
			if(e.getButton()==MouseButton.PRIMARY)
			{
				setUpdateInfo();
			}
		});
		setData();
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
	public void setData()
	{
		al=DBHandler.getownMan();
		tvStaffs.getItems().addAll(al);
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
	public void updateStaff()
	{ 
		Staff s=selectionModel.getSelectedItem();
		int id=Integer.parseInt(tid.getText());
		String name=tname.getText();
		String position=Sposition.getValue();
		String email=temail.getText();
		String Edate=tedate.getText();
		boolean b=DBHandler.updateStaff(id, name, position, email, Edate);
	}
	public BorderPane getMainPane()
	{
		createStaffTable();
		createUpdatePane();
		createTopPane();
		mainPane=new BorderPane();
		mainPane.setTop(staffPane);
		mainPane.setCenter(tvStaffs);
		mainPane.setRight(updatePane);
		mainPane.setMargin(staffPane,new Insets(0,0,20,0));
		return mainPane;
	}
	public void setMainPane(BorderPane mainPane) {
		this.mainPane = mainPane;
	}



}
