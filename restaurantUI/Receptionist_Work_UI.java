package restaurant_project_ui;

import java.net.URL;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Receptionist_Work_UI extends Application{
private Stage st,st1;
	
	private Label lstaff,lheader,loption0,loption1,loption2,loption3,loption4,loption5,reinfo,tableView;
	private FlowPane staffPane;
	private VBox leftPane;
	private BorderPane mainPane;
	public void createTopPane()
	{
		lstaff=new Label(LoginUI_re.getStaffEmail());
		staffPane=new FlowPane(lstaff);
		staffPane.setAlignment(Pos.CENTER_RIGHT);
		staffPane.setPadding(new Insets(20));
		staffPane.setId("Receptoppane");
		lstaff.setId("UserName");
	}
	public void createLeftPane()
	{
		lheader=new Label("Receptionist DashBoard");
		lheader.setId("dashboard");
		
		reinfo=new Label("My info");
		reinfo.getStyleClass().add("option");
		reinfo.setMaxWidth(200);
		reinfo.setOnMouseClicked(e->{
			mainPane.setCenter(null);
			mainPane.setCenter(new Receptionist_info_View().getMainPane());
		});
		loption0=new Label("Sales");
		loption0.getStyleClass().add("option");
		loption0.setMaxWidth(200);
		loption0.setOnMouseClicked(e->{
			mainPane.setCenter(null);
			mainPane.setCenter(new Order_UI().getMainPane());
		});
		loption0.getStyleClass().add("option");
		
		tableView=new Label("Sale Tables");
		tableView.getStyleClass().add("option");
		tableView.setMaxWidth(200);
		tableView.setOnMouseClicked(e->{
			mainPane.setCenter(null);
			mainPane.setCenter(new OrderTable_UI().getMainPane());
		});
		tableView.getStyleClass().add("option");
		
		
		loption1=new Label("Meals");
		loption1.getStyleClass().add("option");
		loption1.setMaxWidth(200);	
		loption1.setOnMouseClicked(e->{
			mainPane.setCenter(null);
			mainPane.setCenter(new Meals_UI().getMainPane());
		});
		loption2=new Label("Drinks");
		loption2.getStyleClass().add("option");
		loption2.setMaxWidth(200);
		loption2.setOnMouseClicked(e->{
			mainPane.setCenter(null);
			mainPane.setCenter(new Drinks_UI().getMainPane());
		});
		loption3=new Label("Snacks");
		loption3.getStyleClass().add("option");
		loption3.setMaxWidth(200);
		loption3.setOnMouseClicked(e->{
			mainPane.setCenter(null);
			mainPane.setCenter(new Snacks_UI().getMainPane());
		});
		loption4=new Label("Shopping");
		loption4.getStyleClass().add("option");
		loption4.setMaxWidth(200);
		loption4.setOnMouseClicked(e->{
			mainPane.setCenter(null);
			mainPane.setCenter(new BuyingGoods_UI().getMainPane());
		});
		loption5=new Label("Log out");
		loption5.getStyleClass().add("option");
		loption5.setMaxWidth(200);
		loption5.setOnMouseClicked(e->{
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
		leftPane=new VBox(20,lheader,reinfo,loption0,tableView,loption1,loption2,loption3,loption4,loption5);
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
		mainPane.setCenter(new Receptionist_info_View().getMainPane());
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
		st.setTitle("Receptionist WorkForm");
		st.show();
		
	}
	

}
