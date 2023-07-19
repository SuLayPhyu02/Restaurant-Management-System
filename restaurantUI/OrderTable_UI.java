package restaurant_project_ui;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Predicate;

import com.mysql.cj.xdevapi.Table;

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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Person;
import model.dbHandler;
import restaurant_project.*;
public class OrderTable_UI extends Application{
	private TableView tvSales;
	private ArrayList<Sales> oal;
	private TableSelectionModel<Sales>selectionModel;
	private FilteredList<Sales>fl;
	private TableColumn<Sales, Integer>idCol;
	private TableColumn<Sales, String>DateCol;
	private TableColumn<Sales, Integer>tabCol;
	private TableColumn<Sales, String>NoteCol;
	private TableColumn<Sales, String>STcol;
	private TableColumn<Sales, String>PayTCol;
	private TableColumn<Sales, String> StaffNCol;
	private TableColumn<Sales, Integer>totCol;
	
	
	private TableView<Sale_Dishes_Joint>tvSaleDish;
	private ArrayList<Sale_Dishes_Joint>sdal;
	
	private TableColumn<Sale_Dishes_Joint, Integer>sdidCol;
	private TableColumn<Sale_Dishes_Joint, Integer>orderCol;
	private TableColumn<Sale_Dishes_Joint, String>dnameCol;
	private TableColumn<Sale_Dishes_Joint, Integer>pricecCol;
	private TableColumn<Sale_Dishes_Joint, Integer>qtyCol;
	private TableColumn<Sale_Dishes_Joint, Integer>totalCol;
	
	private BorderPane mainPane,viewpane;
	private Label lKeyword,lid;
	private TextField tSearch,tid;
	private FlowPane searchPane,fp;
	private Button btnShow;
	private HBox hb;
	private Stage st1,st2;
	public void createSearchBar()
	{
		lid=new Label("Sale ID :");
		tid=new TextField();
		fp=new FlowPane(20,20,lid,tid);
		fp.setAlignment(Pos.CENTER_LEFT);
		lid.getStyleClass().add("se");
		tid.getStyleClass().add("Search");
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
		lKeyword.getStyleClass().add("se");
		tSearch.getStyleClass().add("Search");
		hb=new HBox(fp,searchPane);
		hb.setPadding(new Insets(15));
		hb.getStyleClass().add("searchD");
	}
	public void filtering()
	{
		fl=new FilteredList<Sales>(FXCollections.observableArrayList(oal));
		fl.setPredicate(new Predicate<Sales>() 
		{
			public boolean test(Sales s)
			{
				String saleDate=s.getSaleDate();
				String saletype=s.getSaletname().toLowerCase();
				String paytype=s.getPtype();
				String sname=s.getSname().toLowerCase();
				String value=tSearch.getText();
				if(value.length()==0)
				{
					return true;
				}
				if(value.equals(saleDate) || value.equals(saletype) || value.equals(paytype)|| value.equals(sname))
				{
					return true;
				}
				else
				{
					return false;
				}
				
			}
		});
		tvSales.getItems().clear();
		tvSales.getItems().addAll(fl);
	}
	public void createDTable()
	{
		tvSaleDish=new TableView<Sale_Dishes_Joint>();
		
		sdidCol=new TableColumn<Sale_Dishes_Joint, Integer>("Sdid");
		sdidCol.setCellValueFactory(new PropertyValueFactory<Sale_Dishes_Joint, Integer>("sdid"));
		
		orderCol=new TableColumn<Sale_Dishes_Joint, Integer>("Order NO");
		orderCol.setCellValueFactory(new PropertyValueFactory<Sale_Dishes_Joint, Integer>("orderno"));
		
		dnameCol=new TableColumn<Sale_Dishes_Joint, String>("Dish Name");
		dnameCol.setCellValueFactory(new PropertyValueFactory<Sale_Dishes_Joint,String>("dname"));
		
		pricecCol=new TableColumn<Sale_Dishes_Joint, Integer>("Dish Price");
		pricecCol.setCellValueFactory(new PropertyValueFactory<Sale_Dishes_Joint, Integer>("dprice"));
		
		qtyCol=new TableColumn<Sale_Dishes_Joint, Integer>("Qty");
		qtyCol.setCellValueFactory(new PropertyValueFactory<Sale_Dishes_Joint, Integer>("qty"));
		
		totalCol=new TableColumn<Sale_Dishes_Joint, Integer>("Total");
		totalCol.setCellValueFactory(new PropertyValueFactory<Sale_Dishes_Joint, Integer>("total"));
		
		sdidCol.getStyleClass().add("tableText");
		orderCol.getStyleClass().add("tableText");
		dnameCol.getStyleClass().add("tableText");
		pricecCol.getStyleClass().add("tableText");
		qtyCol.getStyleClass().add("tableText");
		totalCol.getStyleClass().add("tableText");
		
		tvSaleDish.getColumns().add(sdidCol);
		tvSaleDish.getColumns().add(orderCol);
		tvSaleDish.getColumns().add(dnameCol);
		tvSaleDish.getColumns().add(pricecCol);
		tvSaleDish.getColumns().add(qtyCol);
		tvSaleDish.getColumns().add(totalCol);
		tvSaleDish.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		tvSaleDish.setMaxHeight(500);

	}
	public void createOrderTable()
	{
		tvSales=new TableView<Sales>();
		
		idCol=new TableColumn<Sales, Integer>("Sales ID");
		idCol.setCellValueFactory(new PropertyValueFactory<Sales, Integer>("said"));

		DateCol=new TableColumn<Sales, String>("Sale Date");
		DateCol.setCellValueFactory(new PropertyValueFactory<Sales, String>("saleDate"));
		DateCol.setSortable(true);
		
		tabCol=new TableColumn<Sales, Integer>("Table NO");
		tabCol.setCellValueFactory(new PropertyValueFactory<Sales, Integer>("tableNO"));
		
		NoteCol=new TableColumn<Sales, String>("Remark");
		NoteCol.setCellValueFactory(new PropertyValueFactory<Sales, String>("remark"));
		
		STcol=new TableColumn<Sales, String>("Sale Type");
		STcol.setCellValueFactory(new PropertyValueFactory<Sales, String>("saletname"));
		
		PayTCol=new TableColumn<Sales, String>("Payment Type");
		PayTCol.setCellValueFactory(new PropertyValueFactory<Sales, String>("ptype"));
		
		StaffNCol=new TableColumn<Sales, String>("Staff Name");
		StaffNCol.setCellValueFactory(new PropertyValueFactory<Sales, String>("sname"));
		
		totCol=new TableColumn<Sales, Integer>("Total");
		totCol.setCellValueFactory(new PropertyValueFactory<Sales, Integer>("alltotal"));
		
		idCol.getStyleClass().add("tableText");
		DateCol.getStyleClass().add("tableText");
		tabCol.getStyleClass().add("tableText");
		NoteCol.getStyleClass().add("tableText");
		STcol.getStyleClass().add("tableText");
		PayTCol.getStyleClass().add("tableText");
		StaffNCol.getStyleClass().add("tableText");
		totCol.getStyleClass().add("tableText");
		
		tvSales.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		tvSales.setMaxHeight(650);
		tvSales.getColumns().add(idCol);
		tvSales.getColumns().add(DateCol);
		tvSales.getColumns().add(tabCol);
		tvSales.getColumns().add(NoteCol);
		tvSales.getColumns().add(STcol);
		tvSales.getColumns().add(PayTCol);
		tvSales.getColumns().add(StaffNCol);
		tvSales.getColumns().add(totCol);
		tvSales.getSortOrder().add(DateCol);
		selectionModel=tvSales.getSelectionModel();
		selectionModel.setSelectionMode(SelectionMode.SINGLE);
		tvSales.setOnMouseClicked(e->{
			if(e.getClickCount()==2)
			{	
				setUpdateInfo();
			}
		});
		setOrderData();
	}
	public void setUpdateInfo()
	{
		
		Sales s=selectionModel.getSelectedItem();
		tid.setText(""+s.getSaid());
		int id=Integer.parseInt(tid.getText());
		sdal=DBHandler.getAllSalesDishes(id);
		createDTable();
		tvSaleDish.getItems().clear();
		tvSaleDish.getItems().addAll(sdal);
		newWindow();
	}
	public void newWindow()
	{
		try {
			st2=new Stage(StageStyle.DECORATED);
			st2.initModality(Modality.APPLICATION_MODAL);
			Scene sc1=new Scene(tvSaleDish,700,300);
			URL url=this.getClass().getResource("myRestaurant.css");
			sc1.getStylesheets().add(url.toExternalForm());
			st2.setScene(sc1);
			st2.setTitle("Order Details");
			new OrderTable_UI().start(st2);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public void setOrderData()
	{
		oal=DBHandler.getAllSales();
		tvSales.getItems().addAll(oal);
	}
	public BorderPane getMainPane()
	{
		createOrderTable();
		createSearchBar();
		createDTable();
		mainPane=new BorderPane();
		mainPane.setTop(hb);
		mainPane.setCenter(tvSales);
		mainPane.setMargin(hb,new Insets(0,0,20,0));
		return mainPane;
	}
	public void setMainPane(BorderPane mainPane) {
		this.mainPane = mainPane;
	}
	@Override
	public void start(Stage st1) throws Exception {
		st1.show();
	}
	public static void main(String args)
	{
		Application.launch(args);
	}
}
