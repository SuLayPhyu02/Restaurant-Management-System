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
import restaurant_project.*;

public class Manager_WorkForm_UI extends Application{
private Stage st,st1;
	
	private Label lstaff,lheader,loption0,loption1,loption2,loption3,register,shoplist,lsci;
	private BorderPane mainPane;
	private FlowPane staffPane;
	private VBox leftPane;
	public void createTopPane()
	{
		lstaff=new Label(LoginUI_re.getStaffEmail());
		staffPane=new FlowPane(lstaff);
		staffPane.setAlignment(Pos.CENTER_RIGHT);
		staffPane.setPadding(new Insets(20));
		staffPane.setId("Managertoppane");
		lstaff.setId("UserName");
	}
	public void createLeftPane()
	{
		lheader=new Label("Manager DashBoard");
		lheader.setId("dashboard");
		
		loption0=new Label("His Info:");
		loption0.getStyleClass().add("option");
		loption0.setMaxWidth(200);
		loption0.setOnMouseClicked(e->{
			mainPane.setCenter(null);
			mainPane.setCenter(new Manager_View_Info().getMainPane());
		});
		register=new Label("Other's Info");
		register.getStyleClass().add("option");
		register.setMaxWidth(200);
		register.setOnMouseClicked(e->{
			mainPane.setCenter(null);
			mainPane.setCenter(new StaffUI().getMainPane());
		});
		loption1=new Label("Order Table View");
		loption1.getStyleClass().add("option");
		loption1.setMaxWidth(200);
		loption1.setOnMouseClicked(e->{
			mainPane.setCenter(null);
			mainPane.setCenter(new OrderTable_UI().getMainPane());
		});
		shoplist=new Label("Shopping");
		shoplist.getStyleClass().add("option");
		shoplist.setMaxWidth(200);
		shoplist.setOnMouseClicked(e->{
			mainPane.setCenter(null);
			mainPane.setCenter(new ShoppingList().getMainPane());
		});
		
		lsci=new Label("SI Categories");
		lsci.getStyleClass().add("option");
		lsci.setMaxWidth(200);
		lsci.setOnMouseClicked(e->{
			mainPane.setCenter(null);
			mainPane.setCenter(new SICategories_UI().getMainPane());
		});
		loption2=new Label("Dishes");
		loption2.getStyleClass().add("option");
		loption2.setMaxWidth(200);
		loption2.setOnMouseClicked(e->{
			mainPane.setCenter(null);
			mainPane.setCenter(new DishUI().getMainPane());
		});
		loption3=new Label("Log out");
		loption3.getStyleClass().add("option");
		loption3.setMaxWidth(200);
		loption3.setOnMouseClicked(e->{
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
		leftPane=new VBox(20,lheader,loption0,register,shoplist,lsci,loption1,loption2,loption3);
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
		mainPane.setCenter(new Manager_View_Info().getMainPane());
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
		st.setTitle("Manager WorkForm");
		st.show();
		
	}
	
	

}