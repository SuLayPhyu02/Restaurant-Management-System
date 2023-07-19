package restaurant_project_ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;

import org.controlsfx.control.textfield.TextFields;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import restaurant_project.*;

public class LoginUI_re extends Application{

	private Label lemail,lpassword,lposition,lerr;
	private RadioButton radmin,rmanager,rreceptionist;
	private ToggleGroup tgPosition;
	private TextField temail;
	private PasswordField tpassword;
	private Button btnLogin,btnCancel;
	
	private GridPane gp;
	private FlowPane fp;
	private BorderPane mainpane;
	private TilePane tp;
	private VBox vb;
	
	private static int staffId=-1;
	private static String staffEmail;
	private static String staffName;

	private Stage stage,st1;
	public void createNode()
	{
		lemail=new Label("Email");
		lpassword=new Label("Password");
		lposition=new Label("Position");
		lerr=new Label();
		
		temail=new TextField();
		temail.setMaxWidth(200);
		tpassword=new PasswordField();
		tpassword.setMaxWidth(200);
		
		tgPosition=new ToggleGroup();
		radmin=new RadioButton("Admin");
		radmin.setSelected(true);
		radmin.setToggleGroup(tgPosition);
		rmanager=new RadioButton("Manager");
		rmanager.setToggleGroup(tgPosition);
		rreceptionist=new RadioButton("Receptionist");
		rreceptionist.setToggleGroup(tgPosition);
		
		btnLogin=new Button("Login");
		btnLogin.setOnAction(e->{
			staffLogin();
		});
		btnCancel=new Button("Cancel");
		btnCancel.setOnAction(e->{
			clear();
		});
		
	}
	public void staffLogin()
	{
		String n,p,sposition;
		n=temail.getText();
		p=tpassword.getText();
		if(radmin.isSelected())
			sposition="Admin";
		else if(rmanager.isSelected())
			sposition="Manager";
		else
			sposition="Receptionist";
		
		staffId=DBHandler.staff_login(n, p,sposition);
		staffEmail=n;
		if(staffId!=-1)
		{
			stage.hide();
			if(sposition.equals("Admin"))
			{
				st1=new Stage(StageStyle.DECORATED);
				st1.initModality(Modality.APPLICATION_MODAL);
				try {
					new AdminWork().start(st1);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if(sposition.equals("Manager"))
			{
				st1=new Stage(StageStyle.DECORATED);
				st1.initModality(Modality.APPLICATION_MODAL);
				try {
					new Manager_WorkForm_UI().start(st1);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if(sposition.equals("Receptionist"))
			{
				st1=new Stage(StageStyle.DECORATED);
				st1.initModality(Modality.APPLICATION_MODAL);
				try {
					new Receptionist_Work_UI().start(st1);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		else {
			lerr.setText("Try Again!");
		}
	}
	public void clear()
	{
		temail.setText("");
		tpassword.setText("");
		lerr.setText("");
	}
	public void setLayout()
	{
		mainpane=new BorderPane();
		
		gp=new GridPane();
		gp.add(lemail, 0, 0);
		gp.add(temail, 1, 0);;
		
		gp.add(lpassword, 0, 1);
		vb=new VBox(tpassword,lerr);
		gp.add(vb, 1, 1);
		
		gp.add(lposition, 0, 2);
		tp=new TilePane(20,20,radmin,rmanager,rreceptionist);
		gp.add(tp, 1, 2); 
		
		
		gp.add(btnLogin, 0, 3);
		gp.add(btnCancel, 1, 3);
		gp.setAlignment(Pos.CENTER_RIGHT);
		gp.setPadding(new Insets(20));
		gp.setVgap(50);
		gp.setHgap(30);
		mainpane.setCenter(gp);		
	}
	public void autocomplete()
	{
		ArrayList<Staff>al=DBHandler.getAllSEmail();
		TextFields.bindAutoCompletion(temail,al);
	}
	public void setStyle()
	{
		lemail.getStyleClass().add("ltext");
		lpassword.getStyleClass().add("ltext");
		lposition.getStyleClass().add("ltext");
		radmin.getStyleClass().add("ltext");
		rmanager.getStyleClass().add("ltext");
		rreceptionist.getStyleClass().add("ltext");
		
		btnLogin.getStyleClass().add("btn");
		btnCancel.getStyleClass().add("btn");
		btnLogin.getStyleClass().add("btnLogin");
		btnCancel.getStyleClass().add("btnClear");
		
		lerr.getStyleClass().add("err");
		mainpane.setId("lborderPane");
	}
	
	
	@Override
	public void start(Stage st1) throws Exception {
		stage=st1;
		createNode();
		setLayout();
		autocomplete();
		Scene sc=new Scene(mainpane,1500,700);
		URL url=this.getClass().getResource("myRestaurant.css");
		sc.getStylesheets().add(url.toExternalForm());
		setStyle();
		st1.setScene(sc);
		st1.setTitle("Login");
		st1.show();
	}
	public static void main(String args)
	{
		Application.launch(args);
	}
	public static int getStaffId() {
		return staffId;
	}
	public static void setStaffId(int staffId) {
		LoginUI_re.staffId = staffId;
	}
	public static String getStaffEmail() {
		return staffEmail;
	}
	public static void setStaffEmail(String staffEmail) {
		LoginUI_re.staffEmail = staffEmail;
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}
	

}
