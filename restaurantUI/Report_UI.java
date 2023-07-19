package restaurant_project_ui;

import java.time.LocalDate;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.*;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import restaurant_project.DBHandler;
import restaurant_project.Sale_Dishes_Joint;
import restaurant_project.Sales;
import restaurant_project.ShoppingItems;

public class Report_UI {
	private TableView tvPopular,tvPoor,tvProfit;
	private ArrayList<Sale_Dishes_Joint>popularal;
	private ArrayList<Sale_Dishes_Joint> pooral;
	private TableColumn<Sale_Dishes_Joint, String> dname1Col;
	private TableColumn<Sale_Dishes_Joint, String> dname2Col;
	
	private TableColumn<ShoppingItems,String> dateCol;
	private TableColumn<ShoppingItems, Integer>incomeCol;
	private TableColumn<ShoppingItems, Integer> expenseCol;
	private TableColumn<ShoppingItems, Integer> profitCol;
	
	
	private Button pieB,barB,lineB,btnShow;
	private FlowPane btnFlow,dateFlow;
	private PieChart pc;
	private BarChart bc;
	private LineChart lc;
	private Label lstart,lend,lpopular,lpoor,date,manager,rec,popular,poor;
	private TextField start,end;
	private BorderPane mainPane,piePane,barPane,linePane,firstPane;
	private VBox v1,v2,v3,barBox,firstvb;
	public void createFirstView()
	{
		date=new Label("Today Date : "+DBHandler.getTodayDate());
		manager=new Label("NO of Manger : "+DBHandler.getMCount());
		rec=new Label("NO of Receptionist : "+DBHandler.getRCount());
		popular=new Label("Popular Dish : "+DBHandler.getFpopular());
		poor=new Label("Poor Dish : "+DBHandler.getFpoor());
		date.setMaxWidth(800);
		manager.setMaxWidth(800);
		rec.setMaxWidth(800);
		popular.setMaxWidth(800);
		poor.setMaxWidth(800);
		date.getStyleClass().add("firstL");
		date.getStyleClass().add("date");
		manager.getStyleClass().add("firstL");
		manager.getStyleClass().add("manager");
		rec.getStyleClass().add("firstL");
		rec.getStyleClass().add("receptionist");
		popular.getStyleClass().add("firstL");
		popular.getStyleClass().add("popular");
		poor.getStyleClass().add("firstL");
		poor.getStyleClass().add("poor");
		firstvb=new VBox(20,date,manager,rec,popular,poor);
		tvProfit=new TableView<ShoppingItems>();
		dateCol=new TableColumn<ShoppingItems, String>("Date");
		dateCol.setCellValueFactory(new PropertyValueFactory<ShoppingItems,String>("buydate"));
		
		incomeCol=new TableColumn<ShoppingItems, Integer>("Income");
		incomeCol.setCellValueFactory(new PropertyValueFactory<ShoppingItems,Integer>("income"));
		
		expenseCol=new TableColumn<ShoppingItems, Integer>("Expense");
		expenseCol.setCellValueFactory(new PropertyValueFactory<ShoppingItems,Integer>("expense"));
		
		profitCol=new TableColumn<ShoppingItems, Integer>("Profit");
		profitCol.setCellValueFactory(new PropertyValueFactory<ShoppingItems,Integer>("profit"));
		
		
		tvProfit.getColumns().add(dateCol);
		tvProfit.getColumns().add(incomeCol);
		tvProfit.getColumns().add(expenseCol);
		tvProfit.getColumns().add(profitCol);
		tvProfit.setPadding(new Insets(10));
		dateCol.getStyleClass().add("tableText");
		incomeCol.getStyleClass().add("tableText");
		expenseCol.getStyleClass().add("tableText");
		profitCol.getStyleClass().add("tableText");
		tvProfit.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		ArrayList<ShoppingItems>profit=DBHandler.FProfit();
		tvProfit.getItems().addAll(profit);
		firstPane=new BorderPane();
		firstPane.setCenter(firstvb);
		firstPane.setBottom(tvProfit);
	}
	public void showProfit(String sdate,String edate)
	{
		tvProfit=new TableView<ShoppingItems>();
		dateCol=new TableColumn<ShoppingItems, String>("Date");
		dateCol.setCellValueFactory(new PropertyValueFactory<ShoppingItems,String>("buydate"));
		
		incomeCol=new TableColumn<ShoppingItems, Integer>("Income");
		incomeCol.setCellValueFactory(new PropertyValueFactory<ShoppingItems,Integer>("income"));
		
		expenseCol=new TableColumn<ShoppingItems, Integer>("Expense");
		expenseCol.setCellValueFactory(new PropertyValueFactory<ShoppingItems,Integer>("expense"));
		
		profitCol=new TableColumn<ShoppingItems, Integer>("Profit");
		profitCol.setCellValueFactory(new PropertyValueFactory<ShoppingItems,Integer>("profit"));
		
		
		tvProfit.getColumns().add(dateCol);
		tvProfit.getColumns().add(incomeCol);
		tvProfit.getColumns().add(expenseCol);
		tvProfit.getColumns().add(profitCol);
		dateCol.getStyleClass().add("tableText");
		incomeCol.getStyleClass().add("tableText");
		expenseCol.getStyleClass().add("tableText");
		profitCol.getStyleClass().add("tableText");
		tvProfit.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		ArrayList<ShoppingItems>profit=DBHandler.getProfit(sdate,edate);
		tvProfit.getItems().addAll(profit);
		tvProfit.setMaxHeight(150);
	}
	public void createTopPane()
	{
		pieB=new Button("Dish Report");
		pieB.setOnAction(e->{
			showpieChart();
		});
		barB=new Button("Income/Expense");
		barB.setOnAction(e->{
			showBarChart();
		});
		lineB=new Button("Our Profit");
		lineB.setOnAction(e->{
			showLineChart();
		});
		btnFlow=new FlowPane(pieB,barB,lineB);
		btnFlow.setHgap(60);
		btnFlow.setVgap(20);
		btnFlow.setId("reptop");
		pieB.getStyleClass().add("pie");
		barB.getStyleClass().add("pie");
		lineB.getStyleClass().add("pie");
	}
	public void showpieChart()
	{
		mainPane.setCenter(null);
		lstart=new Label("Start Date :");
		lend=new Label("End Date :");
		start=new TextField();
		end=new TextField();
		start.setPromptText(String.valueOf(LocalDate.now().getYear()+"-01-01"));
		end.setPromptText(String.valueOf(LocalDate.now().getYear()+"-01-10"));
		btnShow=new Button("Show");
		dateFlow=new FlowPane(20,20,lstart,start,lend,end,btnShow);
		dateFlow.setAlignment(Pos.CENTER_RIGHT);
		//setting style
		btnShow.getStyleClass().add("UpdateB");
		btnShow.getStyleClass().add("showbtn");
		lstart.getStyleClass().add("SearchWord");
		lend.getStyleClass().add("SearchWord");
		start.getStyleClass().add("Search");
		end.getStyleClass().add("Search");
		dateFlow.getStyleClass().add("searchP");
		piePane=new BorderPane();
		piePane.setTop(dateFlow);
		piePane.setCenter(tvProfit);
		btnShow.setOnAction(e->{
			String sdate=start.getText().trim();
			String edate=end.getText().trim();
			if(sdate.equals(""))
			{
				sdate=String.valueOf(LocalDate.now().getYear()+"-01-01");
			}
			if(edate.equals(""))
			{
				edate=String.valueOf(LocalDate.now().getYear()+"-01-10");
			}
			piePane.setCenter(null);
			lpopular=new Label("Popular Dishes");
			lpoor=new Label("Poor Dishes");
			tvPopular=new TableView<Sale_Dishes_Joint>();
			dname1Col=new TableColumn<Sale_Dishes_Joint, String>("Top 5 Popular Dishes");
			dname1Col.setCellValueFactory(new PropertyValueFactory<Sale_Dishes_Joint,String>("dname"));
			tvPopular.getColumns().add(dname1Col);
			dname1Col.getStyleClass().add("tableText");
			popularal=DBHandler.getPopular(sdate,edate);
			tvPopular.getItems().addAll(popularal);
			tvPopular.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
			tvPopular.setMaxHeight(200);
			
			tvPoor=new TableView<Sale_Dishes_Joint>();
			dname2Col=new TableColumn<Sale_Dishes_Joint, String>("5 Poor Sale Dishes");
			dname2Col.setCellValueFactory(new PropertyValueFactory<Sale_Dishes_Joint,String>("dname"));
			tvPoor.getColumns().add(dname2Col);
			dname2Col.getStyleClass().add("tableText");
			pooral=DBHandler.getPoor(sdate,edate);
			tvPoor.getItems().addAll(pooral);
			tvPoor.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
			tvPoor.setMaxHeight(200);
			v1=new VBox(lpopular,tvPopular);
			v2=new VBox(lpoor,tvPoor);
			
			v3=new VBox(30,v1,v2);
			v3.setMaxWidth(500);
			v3.setPadding(new Insets(30));
			
			lpopular.getStyleClass().add("reportText");
			lpoor.getStyleClass().add("reportText");
			
			ArrayList<Sale_Dishes_Joint> al=DBHandler.getPoPShowDishes(sdate,edate);
			pc=new PieChart();
			pc.setTitle("Popular Dishes and Poor Dishes");
			for(int i=0;i<al.size();i++)
			{
				PieChart.Data d1=new Data(al.get(i).getDname(),al.get(i).getCount());
				pc.getData().add(d1);
			}
			pc.getStyleClass().add("chart-pie-label");
			pc.setMaxWidth(700);
			pc.setLabelLineLength(50);
			pc.setLabelsVisible(true);
			pc.setMaxHeight(600);
			pc.setStartAngle(180);
			//pc.getStyleClass().add("chartfont");
			piePane.setRight(v3);
			piePane.setCenter(pc);
		});
		mainPane.setCenter(piePane);
	}
	public void showBarChart()
	{
		mainPane.setCenter(null);
		lstart=new Label("Start Date :");
		lend=new Label("End Date :");
		start=new TextField();
		end=new TextField();
		start.setPromptText(String.valueOf(LocalDate.now().getYear()+"-01-01"));
		end.setPromptText(String.valueOf(LocalDate.now().getYear()+"-01-10"));
		btnShow=new Button("Show");
		dateFlow=new FlowPane(20,20,lstart,start,lend,end,btnShow);
		dateFlow.setAlignment(Pos.CENTER_RIGHT);
		//setting style
		btnShow.getStyleClass().add("UpdateB");
		btnShow.getStyleClass().add("showbtn");
		lstart.getStyleClass().add("SearchWord");
		lend.getStyleClass().add("SearchWord");
		start.getStyleClass().add("Search");
		end.getStyleClass().add("Search");
		dateFlow.getStyleClass().add("searchB");
		barPane=new BorderPane();
		barPane.setTop(dateFlow);
		barPane.setCenter(tvProfit);
		btnShow.setOnAction(e->{
			String sdate=start.getText().trim();
			String edate=end.getText().trim();
			if(sdate.equals(""))
			{
				sdate=String.valueOf(LocalDate.now().getYear()+"-01-01");
				
			}
			if(edate.equals(""))
			{
				edate=String.valueOf(LocalDate.now().getYear()+"-01-10");
			}
			barPane.setCenter(null);
			CategoryAxis exa=new CategoryAxis();
			exa.setLabel("Day");
			NumberAxis eya=new NumberAxis();
			eya.setLabel("Income & Expense");
			bc=new BarChart<>(exa,eya);
			bc.setTitle("Income & Expense");
			
			XYChart.Series<String,Integer> income=new Series<>();
			ArrayList<Sales>sal=DBHandler.getincome(sdate,edate);
			for(int i=0;i<sal.size();i++)
			{
				XYChart.Data<String, Integer> sdata=new XYChart.Data<String, Integer>(sal.get(i).getSaleDate(),sal.get(i).getIncome());
				income.getData().add(sdata);
			}
			income.setName("Income");
			XYChart.Series<String,Integer> expense=new Series<>();
			ArrayList<ShoppingItems>al=DBHandler.getexpense(sdate,edate);
			for(int i=0;i<al.size();i++)
			{
				XYChart.Data<String, Integer> data=new XYChart.Data<String, Integer>(al.get(i).getBuydate(),al.get(i).getExpense());
				expense.getData().add(data);
			}
			expense.setName("Expense");
			bc.getData().addAll(income,expense);
			bc.setMaxHeight(350);
			bc.getStyleClass().add("chartfont");
			showProfit(sdate,edate);
			barBox=new VBox(10,bc,tvProfit);
			barBox.setMaxHeight(500);
			barPane.setCenter(barBox);
		});
		mainPane.setCenter(barPane);
	}
	public void showLineChart()
	{
		mainPane.setCenter(null);
		lstart=new Label("Start Date :");
		lend=new Label("End Date :");
		start=new TextField();
		end=new TextField();
		start.setPromptText(String.valueOf(LocalDate.now().getYear()+"-01-01"));
		end.setPromptText(String.valueOf(LocalDate.now().getYear()+"-01-10"));
		btnShow=new Button("Show");
		dateFlow=new FlowPane(20,20,lstart,start,lend,end,btnShow);
		dateFlow.setAlignment(Pos.CENTER_RIGHT);
		//setting style
		btnShow.getStyleClass().add("UpdateB");
		btnShow.getStyleClass().add("showbtn");
		lstart.getStyleClass().add("SearchWord");
		lend.getStyleClass().add("SearchWord");
		start.getStyleClass().add("Search");
		end.getStyleClass().add("Search");
		dateFlow.getStyleClass().add("searchL");
		linePane=new BorderPane();
		linePane.setTop(dateFlow);
		linePane.setCenter(tvProfit);
		btnShow.setOnAction(e->{
			String sdate=start.getText().trim();
			String edate=end.getText().trim();
			if(sdate.equals(""))
			{
				sdate=String.valueOf(LocalDate.now().getYear()+"-01-01");
			}
			if(edate.equals(""))
			{
				edate=String.valueOf(LocalDate.now().getYear()+"-01-10");
			}
			linePane.setCenter(null);
			CategoryAxis exa=new CategoryAxis();
			exa.setLabel("Day");
			
			NumberAxis eya=new NumberAxis();
			eya.setLabel("Expense & Income");
			
			lc=new LineChart<>(exa, eya);
			lc.setTitle("Expense & Income");
			
			XYChart.Series<String,Integer> income=new Series<>();
			ArrayList<Sales>sal=DBHandler.getincome(sdate,edate);
			for(int i=0;i<sal.size();i++)
			{
				XYChart.Data<String, Integer> sdata=new XYChart.Data<String, Integer>(sal.get(i).getSaleDate(),sal.get(i).getIncome());
				income.getData().add(sdata);
			}
			income.setName("Income");
			
			XYChart.Series<String,Integer> expense=new Series<>();
			ArrayList<ShoppingItems>al=DBHandler.getexpense(sdate,edate);
			for(int i=0;i<al.size();i++)
			{
				XYChart.Data<String, Integer> data=new XYChart.Data<String, Integer>(al.get(i).getBuydate(),al.get(i).getExpense());
				expense.getData().add(data);
			}
			expense.setName("Expense");
			lc.getData().addAll(income,expense);
			lc.getStyleClass().add("chartfont");
			linePane.setCenter(lc);
		});
		mainPane.setCenter(linePane);
	}
	public BorderPane getMainPane()
	{
		createTopPane();
		createFirstView();
		mainPane=new BorderPane();
		mainPane.setTop(btnFlow);
		mainPane.setCenter(firstPane);
		mainPane.setMargin(btnFlow, new Insets(0,0,20,0));
		return mainPane;
	}
	public void setMainPane(BorderPane mainPane) {
		this.mainPane = mainPane;
	}
	

}
