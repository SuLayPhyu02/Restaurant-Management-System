package restaurant_project_ui;

import java.util.Optional;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import restaurant_project.Checker_re;
import restaurant_project.DBHandler;

public class Register_UI {
	private Label lrname,lreposition,lreemail,lpassword,lrepassword,lreenroll,lepasswordError,lerr,lemailerr;
	private TextField trename,treemail,tdate;
	private RadioButton radmin,rmanager,rreceptionist;
	private PasswordField pass,repass;
	private Button btnRegister,btnreClear;
	private ToggleGroup tgPosition;
	private static int staffid=1;
	private static String staffName;
	private BorderPane mainPane;
	private GridPane gp;
	private VBox vb,evb,pvb;
	private TilePane tp;
	private void createRegisterPane()
	{

		lrname=new Label("Name");
		//lrname.setMaxWidth(200);
		lreposition=new Label("Position");
		//lreposition.setMaxWidth(200);
		lreemail=new Label("Email");	
		//lreemail.setMaxWidth(200);
		lpassword=new Label("Password");
		//lpassword.setMaxWidth(200);
		lrepassword=new Label("Repass");
		lreenroll=new Label("Date");
		lepasswordError=new Label();
		lerr=new Label();
		lemailerr=new Label();
		
		
		trename=new TextField();
		trename.setMaxWidth(200);
		
		tgPosition=new ToggleGroup();
		radmin=new RadioButton("Admin");
		radmin.setSelected(true);
		radmin.setToggleGroup(tgPosition);
		rmanager=new RadioButton("Manager");
		rmanager.setToggleGroup(tgPosition);
		rreceptionist=new RadioButton("Receptionist");
		rreceptionist.setToggleGroup(tgPosition);
		
		
		treemail=new TextField();
		treemail.setMaxWidth(200);
		
		pass=new PasswordField();
		pass.setMaxWidth(200);
		
		repass=new PasswordField();
		repass.setMaxWidth(200);
		
		tdate=new TextField();
		tdate.setMaxWidth(200);

		btnRegister=new Button("Register");
		btnRegister.setOnAction(e->{
			
			staff_register();
		});
		btnreClear=new Button("CLear");
		btnreClear.setOnAction(e->{
			RegClear();
		});
		//lepasswordError=new Label();
		//lerr=new Label();
		//lemailerr=new Label();
		gp=new GridPane();
		gp.add(lrname, 0, 0);
		vb=new VBox(trename,lerr);
		gp.add(vb, 1, 0);
		
		gp.add(lreposition, 0, 1);
		
		tp=new TilePane(4,4,radmin,rmanager,rreceptionist);
		gp.add(tp, 1, 1);

		
		gp.add(lreemail, 0, 2);
		evb=new VBox(treemail,lemailerr);
		gp.add(evb, 1, 2);
		
		gp.add(lpassword, 0, 3);
		gp.add(pass, 1, 3);
		
		gp.add(lrepassword, 0, 4);
		pvb=new VBox(repass,lepasswordError);
		gp.add(pvb, 1, 4);
		
		gp.add(lreenroll, 0, 5);
		gp.add(tdate, 1, 5);
		
		gp.add(btnRegister, 0, 6);
		gp.add(btnreClear, 1, 6);
		
		gp.setAlignment(Pos.CENTER);
		gp.setHgap(20);
		gp.setVgap(20);
		
		
		lrname.getStyleClass().add("reText");
		lreposition.getStyleClass().add("reText");
		radmin.getStyleClass().add("reText");
		rmanager.getStyleClass().add("reText");
		rreceptionist.getStyleClass().add("reText");
		lreemail.getStyleClass().add("reText");
		lpassword.getStyleClass().add("reText");
		lrepassword.getStyleClass().add("reText");
		lreenroll.getStyleClass().add("reText");
		
		lepasswordError.getStyleClass().add("err");
		lerr.getStyleClass().add("err");
		lemailerr.getStyleClass().add("err");
		
		btnRegister.getStyleClass().add("btn");
		btnRegister.getStyleClass().add("btnLogin");
		btnreClear.getStyleClass().add("btn");
		btnreClear.getStyleClass().add("btnClear");
		//gp.setId("salePane");
	}
	private void staff_register()
	{
		String name,email,password,repassword,sposition,sdate;
		name=trename.getText();
		if(radmin.isSelected())
			sposition="Admin";
		else if(rmanager.isSelected())
			sposition="Manager";
		else
			sposition="Receptionist";
		sdate=tdate.getText();
		email=treemail.getText();
		password=pass.getText();
		repassword=repass.getText();
		if(!password.equals(repassword))
		{
			lepasswordError.setText("Password and Repassword aren't match");
		}
		else if(!Checker_re.isValidName(name))
		{
			lepasswordError.setText("");
			lerr.setText("Wrong Name Format!!");
		}
		else if(!Checker_re.valEmail(email))
		{
			lepasswordError.setText("");
			lerr.setText("");
			lemailerr.setText("Your email is unvalid pls type again.(eg @gmail.com)!!");
		}
		else if(!Checker_re.isvalPass(password) && !Checker_re.isvalPass(repassword))
		{
			lepasswordError.setText("");
			lerr.setText("");
			lemailerr.setText("");
			lepasswordError.setText("password should be it's format.(eg 8 lengths,1 uppercase,numer,1 special character)!!");
		}
		else if(DBHandler.addStaff(name,sposition,email,password,sdate))
		{
			lepasswordError.setText("");
			lerr.setText("");
			lemailerr.setText("");
			lepasswordError.setText("");
			Alert  alt=new Alert(AlertType.CONFIRMATION);
			alt.setHeaderText("Confirmation");
			alt.setContentText("Are you sure to add ?");
			Optional<ButtonType>ans=alt.showAndWait();
			if(ans.isPresent() && ans.get()==ButtonType.OK)
			{
				Alert con=new Alert(AlertType.INFORMATION);
				con.setHeaderText("Successful");
				con.setContentText("you have been added new staff successfully");
				con.show();
			}
		}
		else
		{
			Alert sorry=new Alert(AlertType.INFORMATION);
			sorry.setHeaderText("unsuccessfully");
			sorry.setContentText("Sorry try again!!");
			sorry.show();
		}
		
	}
	public void RegClear()
	{
		trename.setText("");
		radmin.setSelected(true);
		treemail.setText(" ");
		pass.setText("");
		repass.setText("");
		repass.setText("");
		lepasswordError.setText("");
		tdate.setText("");
		lerr.setText("");
		lemailerr.setText("");
	}
	public BorderPane getMainPane()
	{
		//autoCompleteName();
		createRegisterPane();
		mainPane=new BorderPane();
		mainPane.setCenter(gp);
		return mainPane;
	}
	public void setMainPane(BorderPane mainPane) {
		this.mainPane = mainPane;
	}
	public static int getStaffid() {
		return staffid;
	}
	public static void setStaffid(int staffid) {
		Register_UI.staffid = staffid;
	}
	public static String getStaffName() {
		return staffName;
	}
	public static void setStaffName(String staffName) {
		Register_UI.staffName = staffName;
	}
	

}
