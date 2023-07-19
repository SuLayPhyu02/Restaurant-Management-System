package restaurant_project_ui;

import java.net.URL;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import uiTest.PersonUI;
import uiTest.WorkForm;

public class AdminWork extends Application{

	private Stage st,st1;
	
	private Label lstaff,lheader,loption0,loption1,loption2,report,register;
	private FlowPane staffPane;
	private VBox leftPane;
	private BorderPane mainPane;
	public void createTopPane()
	{
		lstaff=new Label(LoginUI_re.getStaffEmail());
		staffPane=new FlowPane(lstaff);
		staffPane.setAlignment(Pos.CENTER_RIGHT);
		staffPane.setPadding(new Insets(20));
		staffPane.setId("toppane");
		lstaff.setId("UserName");
	}
	public void createLeftPane()
	{
		lheader=new Label("Admin DashBoard");
		lheader.setId("dashboard");
		
		loption0=new Label("Staffs");
		loption0.getStyleClass().add("option");
		loption0.setMaxWidth(200);
		loption0.setOnMouseClicked(e->{
			mainPane.setCenter(null);
			mainPane.setCenter(new StaffUI().getMainPane());
		});
		register=new Label("Register");
		register.getStyleClass().add("option");
		register.setMaxWidth(200);
		register.setOnMouseClicked(e->{
			mainPane.setCenter(null);
			mainPane.setCenter(new Register_UI().getMainPane());
		});
		loption1=new Label("Dishes");
		loption1.getStyleClass().add("option");
		loption1.setMaxWidth(200);
		loption1.setOnMouseClicked(e->{
			mainPane.setCenter(null);
			mainPane.setCenter(new DishUI().getMainPane());

		});
		report=new Label("Report");
		report.getStyleClass().add("option");
		report.setMaxWidth(200);
		report.setOnMouseClicked(e->{
			mainPane.setCenter(null);
			mainPane.setCenter(new Report_UI().getMainPane());
		});
		
		loption2=new Label("Log out");
		loption2.getStyleClass().add("option");
		loption2.setMaxWidth(200);
		loption2.setOnMouseClicked(e->{
			st.hide();
			st1=new Stage(StageStyle.DECORATED);
			st1.initModality(Modality.APPLICATION_MODAL);
			try {
				new LoginUI_re().start(st1);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	
		});
		leftPane=new VBox(20,lheader,loption0,register,loption1,report,loption2);
		leftPane.setPadding(new Insets(20));
		leftPane.getStyleClass().add("leftpane");
	}
	
	public void createMainPane()
	{
		mainPane=new BorderPane();
		mainPane.setTop(staffPane);
		mainPane.setMargin(staffPane, new Insets(0,0,20,0));
		mainPane.setMargin(leftPane, new Insets(5,5,5,5));
		mainPane.setLeft(leftPane);
		mainPane.setCenter(new StaffUI().getMainPane());
	}
	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		st=arg0;
		createTopPane();
		createLeftPane();
		createMainPane();
		Scene sc=new Scene(mainPane,1500,800);
		URL url=this.getClass().getResource("myRestaurant.css");
		sc.getStylesheets().add(url.toExternalForm());
		st.setScene(sc);
		st.setTitle("DashBoard");
		st.show();
		
	}
	public static void main(String args[])
	{
		Application.launch(args);
	}

}
