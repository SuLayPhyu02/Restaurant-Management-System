package restaurant_project_ui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.*;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.*;
import javafx.scene.transform.Scale;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import restaurant_project.*;

public class Order_UI extends Application implements Printable{
	private Label lorderno,ldish,lqty,ldname,ldprice,ltotal;
	private TextField ordno,total,dname,dprice,qty;
	private ComboBox<Dish> dcom;
	private GridPane gp;
	private BorderPane mainPane,dishPane;
	
	private Label lid,ltableNO,lremark,lsaletype,lpaymenttype,lalltotal,lstaff;
	private TextField tid,note,allamount;
	private Button btnAdd,btnClear,btnOrder,btnDishClear,btnShowRec,btnPrint;
	private ComboBox<Integer>TableNo;
	private RadioButton takeOut,DineIn,Cash,Kpay;
	private ToggleGroup tgOT,tgPT;
	private TilePane OTpane,PTpane,AC;
	private GridPane spane;
	private FlowPane staffPane;
	private VBox vb;
	private Button Photo;
	
	private Label lbillRestaurant,lbillAddress,lbillTB,lbillNo,lbillDate,lbillDish,lbillQty,lbillPrice,lthank,lplease,lline,nextline;
	private Label bdish,bqty,bprice,btotal,line;
	private GridPane receiptPane,billdishpane;
	private FlowPane ad,tbill;
	private Stage st;
	
	private TableView<Sale_Dishes_Joint>tvSaleDish;
	private ArrayList<Sale_Dishes_Joint>sdal;
	
	private TableColumn<Sale_Dishes_Joint, String>dnameCol;
	private TableColumn<Sale_Dishes_Joint, Integer>pricecCol;
	private TableColumn<Sale_Dishes_Joint, Integer>qtyCol;
	public void createDTable()
	{
		tvSaleDish=new TableView<Sale_Dishes_Joint>();
		
		dnameCol=new TableColumn<Sale_Dishes_Joint, String>("Dish Name");
		dnameCol.setCellValueFactory(new PropertyValueFactory<Sale_Dishes_Joint,String>("dname"));
		
		pricecCol=new TableColumn<Sale_Dishes_Joint, Integer>("Dish Price");
		pricecCol.setCellValueFactory(new PropertyValueFactory<Sale_Dishes_Joint, Integer>("dprice"));
		
		qtyCol=new TableColumn<Sale_Dishes_Joint, Integer>("Qty");
		qtyCol.setCellValueFactory(new PropertyValueFactory<Sale_Dishes_Joint, Integer>("qty"));
		
		
		dnameCol.getStyleClass().add("tableText");
		pricecCol.getStyleClass().add("tableText");
		qtyCol.getStyleClass().add("tableText");
		
		tvSaleDish.getColumns().add(dnameCol);
		tvSaleDish.getColumns().add(pricecCol);
		tvSaleDish.getColumns().add(qtyCol);
		tvSaleDish.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		tvSaleDish.setMaxWidth(500);
		tvSaleDish.setMaxHeight(200);
	}
	public void createTopPane()
	{
		lstaff=new Label(DBHandler.getStaffName(LoginUI_re.getStaffId())+" is working now ..");
		staffPane=new FlowPane(lstaff);
		staffPane.setAlignment(Pos.CENTER_RIGHT);
		staffPane.setPadding(new Insets(20));
		staffPane.getStyleClass().add("orderstaff");
		lstaff.setId("userorder");
	}
	public void createOrderPane()
	{
		lid=new Label("Sale ID");
		lid.setDisable(true);
		ltableNO=new Label("TableNo");
		lremark=new Label("Note");
		lsaletype=new Label("Order Type");
		lpaymenttype=new Label("Pay Type");
		
	
		tid=new TextField();
		tid.setDisable(true);
		tid.setMaxWidth(150);
		ArrayList<Integer>tn=new ArrayList<Integer>();
		for(int i=0;i<=6;i++)
		{
			tn.add(i);
		}
		TableNo=new ComboBox<Integer>(FXCollections.observableArrayList(tn));
		TableNo.setMaxWidth(150);
		
		note=new TextField("");
		note.setMaxWidth(150);
		
		
		tgOT=new ToggleGroup();
		takeOut=new RadioButton("Take Out");
		DineIn=new RadioButton("Dine In");
		takeOut.setToggleGroup(tgOT);
		DineIn.setToggleGroup(tgOT);
		OTpane=new TilePane(10,10,takeOut,DineIn);
		
		tgPT=new ToggleGroup();
		Cash=new RadioButton("With Cash");
		Cash.setToggleGroup(tgPT);
		Kpay=new RadioButton("Kpay");
		Kpay.setToggleGroup(tgPT);
		PTpane=new TilePane(10,10,Cash,Kpay);
				
		spane=new GridPane();

		spane.add(lid, 0, 0);
		spane.add(tid, 1, 0);
		
		
		spane.add(ltableNO, 0, 1);
		spane.add(TableNo, 1, 1);
		
		spane.add(lsaletype, 0, 2);
		spane.add(OTpane, 1, 2);
		
		spane.add(lremark, 0, 3);
		spane.add(note, 1, 3);
		
		spane.add(lpaymenttype,0,4);
		spane.add(PTpane, 1, 4);
			
		spane.setAlignment(Pos.CENTER_RIGHT);
		spane.setVgap(20);
		spane.setHgap(20);
		spane.setPadding(new Insets(20));
		
		//setting styles
		lid.getStyleClass().add("Order");
		ltableNO.getStyleClass().add("Order");
		lsaletype.getStyleClass().add("Order");
		lremark.getStyleClass().add("Order");
		lpaymenttype.getStyleClass().add("Order");
		
		takeOut.getStyleClass().add("Order");
		DineIn.getStyleClass().add("Order");
		Cash.getStyleClass().add("Order");
		Kpay.getStyleClass().add("Order");
	}
	public void createSaleDishPane()
	{
		ldish=new Label("Dish Name and price :");
		lqty=new Label("Qty");
		
		ldname=new Label("Dish Name");
		ldprice=new Label("Dish Price");
		ltotal=new Label("Total");
		lalltotal=new Label("All Amount");
		lorderno=new Label("Order No :");
		
		ArrayList<Dish>dish=DBHandler.getAllDishes();
		dcom=new ComboBox<Dish>(FXCollections.observableArrayList(dish));
		qty=new TextField();
		
		dname=new TextField();
		dprice=new TextField();
		total=new TextField();
		allamount=new TextField();
		ordno=new TextField();
		
		//setdisable
		lorderno.setDisable(true);
		ordno.setDisable(true);		
		ldname.setDisable(true);
		dname.setDisable(true);
		ldprice.setDisable(true);
		dprice.setDisable(true);
		ltotal.setDisable(true);
		total.setDisable(true);
		lalltotal.setDisable(true);
		allamount.setDisable(true);
		
		btnAdd=new Button("Add Item");
		btnAdd.setOnAction(e->{
			AddSale();
		});
		btnDishClear=new Button("Clear");
		btnDishClear.setOnAction(e->{
			dishClear();
		});
		btnShowRec=new Button("Receipt");
		btnShowRec.setOnAction(e->{
			createReceipt(DBHandler.getSaleMax());
		});
		btnPrint=new Button("Print");
		btnPrint.setOnAction(e->{
			printMIAction();
		});
		btnShowRec.setDisable(true);
		btnPrint.setDisable(true);
		gp=new GridPane();
		
		gp.add(ldish, 0, 0);
		gp.add(dcom, 1, 0);
		
		gp.add(lqty, 0, 1);
		gp.add(qty, 1, 1);
		
		gp.add(ldname, 0, 2);
		gp.add(dname, 1, 2);
		
		gp.add(ldprice, 0, 3);
		gp.add(dprice, 1, 3);
		
		gp.add(ltotal, 0, 4);
		gp.add(total, 1, 4);
		
		gp.add(lalltotal, 0, 5);
		gp.add(allamount, 1, 5);
		
		gp.add(lorderno, 0, 6);
		gp.add(ordno, 1, 6);
		
		gp.add(btnAdd, 0, 7);
		gp.add(btnDishClear, 1, 7);
		
		gp.add(btnShowRec, 0, 8);
		gp.add(btnPrint, 1, 8);
		
		gp.setAlignment(Pos.CENTER);
		gp.setVgap(20);
		gp.setHgap(20);
		gp.setPadding(new Insets(20));
		gp.setMaxWidth(700);
		dishPane=new BorderPane();
		dishPane.setLeft(gp);
		dishPane.setPadding(new Insets(20));
		
		lorderno.getStyleClass().add("oText");
		ldish.getStyleClass().add("oText");
		lqty.getStyleClass().add("oText");
		ldname.getStyleClass().add("oText");
		ldprice.getStyleClass().add("oText");
		ltotal.getStyleClass().add("oText");
		lalltotal.getStyleClass().add("oText");
		dishPane.getStyleClass().add("searchD");
		
		btnAdd.getStyleClass().add("btn");
		btnDishClear.getStyleClass().add("btn");
		btnShowRec.getStyleClass().add("btn");
		btnPrint.getStyleClass().add("btn");
		
		btnAdd.getStyleClass().add("UpdateB");
		btnDishClear.getStyleClass().add("btnC");
		btnShowRec.getStyleClass().add("btnRec");
		btnPrint.getStyleClass().add("btnPrint");
	}

	public void printMIAction()
	{	
		 javafx.print.PrinterJob job = javafx.print.PrinterJob.createPrinterJob();
		 final WritableImage screenshot = receiptPane.snapshot(null, null);
		 final Paper paper = job.getJobSettings().getPageLayout().getPaper();
		 final javafx.print.Printer printer = job.getPrinter();
	     final PageLayout pageLayout = printer.createPageLayout(paper,
	                                                            PageOrientation.LANDSCAPE,
	                                                            javafx.print.Printer.MarginType.DEFAULT);
	     final double scaleX = pageLayout.getPrintableWidth() / screenshot.getWidth();
	     final double scaleY = pageLayout.getPrintableHeight() / screenshot.getHeight();
	     final double scale = Math.min(scaleX, scaleY);
	     final ImageView print_node = new ImageView(screenshot);
	     print_node.getTransforms().add(new Scale(scale, scale));
//		 job.setPrintable(this);
	     boolean ok = job.showPrintDialog(st);
	     if (ok) {
	         job.printPage(print_node);
	             job.endJob();
	        
	}

	}
	public int print(Graphics g, PageFormat pf, int page) throws
	PrinterException {

	if (page > 0) { /* We have only one page, and 'page' is zero-based */
	return NO_SUCH_PAGE;
	}

	/* User (0,0) is typically outside the imageable area, so we must
	* translate by the X and Y values in the PageFormat to avoid clipping
	*/
	Graphics2D g2d = (Graphics2D)g;
	g2d.translate(pf.getImageableX(), pf.getImageableY());

	/* Now we perform our rendering */
	g.drawString("Hello world!", 100, 100);


	/* tell the caller that this page is part of the printed document */
	return PAGE_EXISTS;
	}
	
	public void createButton()
	{
		btnOrder=new Button("Order ");
		btnOrder.setOnAction(e->{
			OrderSale();
		});
		btnClear=new Button("Clear");
		btnClear.setOnAction(e->{
			ClearSale();
		});
		AC=new TilePane(30,30,btnOrder,btnClear);
		AC.setAlignment(Pos.CENTER_LEFT);
		AC.setPadding(new Insets(20));
		AC.setVgap(30);
	
		btnClear.getStyleClass().add("btn");
		btnOrder.getStyleClass().add("btn");
		btnOrder.getStyleClass().add("OrderB");
		btnClear.getStyleClass().add("btnClear");
	}
	public void OrderSale()
	{
		String mark,st,pt;
		int id,tno;
		id=DBHandler.getSaleMax();
		id++;
		tid.setText(""+id);
		tno=TableNo.getValue();
		
		mark=note.getText();
		if(mark.equals(""))
		{
			mark=null;
		}
		if(takeOut.isSelected())
			st="Take Out";
		else
			st="Dine In";
		if(Cash.isSelected())
			pt="Cash";
		else
			pt="Kpay";
		int stid=DBHandler.getSaleTypeId(st);
		int ptid=DBHandler.getPayTid(pt);
		int sid=LoginUI_re.getStaffId();
		Alert  alt=new Alert(AlertType.CONFIRMATION);
		alt.setHeaderText("Confirmation");
		alt.setContentText("Are you sure to add ?");
		Optional<ButtonType>ans=alt.showAndWait();
		if(ans.isPresent() && ans.get()==ButtonType.OK)
		{
			boolean sale=DBHandler.insertSales(id,tno, mark,sid,stid);
			boolean payment=DBHandler.insertPayment(ptid, id);
			if(sale==true && payment==true)
			{
				boolean date=DBHandler.getSDate(id);
				boolean pdate=DBHandler.getPayDate(id);
				if(!date && !pdate)
				{
					System.out.println("date is "+date);
					System.out.println("pay date is "+pdate);
					Alert sorry=new Alert(AlertType.INFORMATION);
					sorry.setHeaderText("unsuccessfully");
					sorry.setContentText("Sorry try again!!");
					sorry.show();
				}
				else
				{
					Alert con=new Alert(AlertType.INFORMATION);
					con.setHeaderText("Successful");
					con.setContentText("you have been insert successfully");
					Optional<ButtonType>ans1=con.showAndWait();
					if(ans1.isPresent() && ans1.get()==ButtonType.OK)
					{
						
						mainPane.setLeft(null);
						mainPane.setCenter(null);
						mainPane.setBottom(null);
						mainPane.setCenter(dishPane);
						mainPane.setRight(receiptPane);
						mainPane.setMargin(dishPane, new Insets(10,5,5,5));
					}
				}
			}
			else
			{
				System.out.println(" db handler said id"+id);
				System.out.println("stid"+stid);
				System.out.println("ptid"+ptid);
				System.out.println("staffid"+sid);
				System.out.println("insert sales"+sale);
				System.out.println("insert payment"+payment);
				Alert sorry=new Alert(AlertType.INFORMATION);
				sorry.setHeaderText("unsuccessfully");
				sorry.setContentText("Sorry try again!!");
				sorry.show();
			}
		}
	}
	public void AddSale()
	{
		int iqty;
		int orderno=DBHandler.getSaleMax();
		ordno.setText(""+orderno);
		iqty=Integer.parseInt(""+qty.getText());
		String[] disharr=(dcom.getValue().toString()).split(",");
		int dishid=DBHandler.getDishid(disharr[1].trim());
		String dn=DBHandler.getDname(dishid);
		int dp=DBHandler.getDprice(dishid);
		int stot=dp*(iqty);
		
		dname.setText(dn);
		dprice.setText(""+dp);
		total.setText(""+stot);
		int subtotal=Integer.parseInt(total.getText());
		int saleid=DBHandler.getSaleMax();
		Alert  alt=new Alert(AlertType.CONFIRMATION);
		alt.setHeaderText("Confirmation");
		alt.setContentText("Are you sure to add ?");
		Optional<ButtonType>ans=alt.showAndWait();
		if(ans.isPresent() && ans.get()==ButtonType.OK)
		{
			boolean saledish=DBHandler.insertSaleDish(orderno,iqty,subtotal,saleid,dishid);
			if(saledish==true)
			{
				int total=DBHandler.getTotal(orderno);
				allamount.setText(""+total);
				int saletotal=Integer.parseInt(allamount.getText());
				boolean	b=DBHandler.insertAllToatal(saletotal,saleid);
				if(!b)
				{
					System.out.println("orderno "+orderno);
					System.out.println("sale id "+saleid);
					System.out.println("your insert is "+b);
					System.out.println("sales of total get text "+saletotal);
					System.out.println("sale total "+total);
					Alert sorry=new Alert(AlertType.INFORMATION);
					sorry.setHeaderText("unsuccessfully");
					sorry.setContentText("Sorry try again!!");
					sorry.show();
				}
				else {
				Alert con=new Alert(AlertType.INFORMATION);
				con.setHeaderText("Successful");
				con.setContentText("you have been insert successfully");
				con.show();
				}
			}
			else
			{
				System.out.println("sales of total "+total);
				System.out.println("sale id of sale dishes"+saleid);
				System.out.println("total of sale dishes"+subtotal);
				System.out.println("saledish"+stot);
				Alert sorry=new Alert(AlertType.INFORMATION);
				sorry.setHeaderText("unsuccessfully");
				sorry.setContentText("Sorry try again!!");
				sorry.show();
			}
		}
		btnShowRec.setDisable(false);
		int sdid=DBHandler.getSaleDishMax();
		sdal=DBHandler.getViewDish(sdid);
		tvSaleDish.getItems().addAll(sdal);
		dishPane.setCenter(tvSaleDish);
	}
	public void createReceipt(int id)
	{
		dishPane.setCenter(null);
		lbillRestaurant=new Label("\t\t\t"+"Seventeen Restaurant");
		lbillAddress=new Label("62 x 66 st Mandalay"+"\t\t");
		lbillTB=new Label("Table NO : "+DBHandler.gettbNo(id));
		lbillNo=new Label("Bill NO : "+id+"\t\t\t\t");
		lbillDate=new Label("Date : "+DBHandler.getTodayDate());
		line=new Label("-----------------------------------------------------------");
		lbillDish=new Label("Dish Name");
		lbillQty=new Label("Qty");
		lbillPrice=new Label("Price");
		billdishpane=new GridPane();
		billdishpane.add(lbillDish, 0, 0);
		billdishpane.add(lbillQty, 1, 0);
		billdishpane.add(lbillPrice, 3, 0);
		ArrayList<Sale_Dishes_Joint> al=DBHandler.getDish(id);
		for(int i=0;i<al.size();i++)
		{
			bdish=new Label(""+al.get(i).getDname()+"  ");
			bqty=new Label(""+al.get(i).getQty());
			bprice=new Label(""+al.get(i).getDprice());
			billdishpane.add(bdish, 0, (i+1));
			billdishpane.add(bqty, 1,(i+1));
			billdishpane.add(bprice,3,(i+1));
			bdish.getStyleClass().add("Order");
			bqty.getStyleClass().add("Order");
			bprice.getStyleClass().add("Order");
		}
		billdishpane.setPadding(new Insets(10)); 
		billdishpane.setVgap(10);
		billdishpane.setHgap(30);
		billdishpane.setAlignment(Pos.CENTER_LEFT);
		lline=new Label("-----------------------------------------------------------");
		btotal=new Label("Total:\t\t\t\t\t\t\t"+DBHandler.getTotal(id)+"Ks");
		nextline=new Label("-----------------------------------------------------------");
		lthank=new Label("Thank you for dining with us!!");
		lplease=new Label("Please Come Again <3 ");
		receiptPane=new GridPane();
		
		receiptPane.add(lbillRestaurant, 0, 0);
		
		ad=new FlowPane(lbillAddress,lbillDate);
		ad.setHgap(20);
		receiptPane.add(ad, 0, 1);
		
		tbill=new FlowPane(lbillNo,lbillTB);
		tbill.setHgap(20);
		receiptPane.add(tbill, 0, 2);
		
		receiptPane.add(line, 0, 3);
		receiptPane.add(billdishpane, 0, 4);
		receiptPane.add(lline, 0, 5);
		receiptPane.add(btotal, 0, 6);
		receiptPane.add(nextline, 0, 7);
		receiptPane.add(lthank, 0, 8);
		receiptPane.add(lplease, 0, 9);
		receiptPane.setPadding(new Insets(10));
		receiptPane.setVgap(10);
		receiptPane.setHgap(20);
		receiptPane.setAlignment(Pos.CENTER);
		mainPane.setRight(receiptPane);
		btnPrint.setDisable(false);
		//settting style
		lbillRestaurant.getStyleClass().add("Order");
		lbillAddress.getStyleClass().add("Order");
		lbillDate.getStyleClass().add("Order");
		lbillTB.getStyleClass().add("Order");
		lbillNo.getStyleClass().add("Order");
		lbillDish.getStyleClass().add("Order");
		lbillQty.getStyleClass().add("Order");
		lbillPrice.getStyleClass().add("Order");
		lthank.getStyleClass().add("Order");
		lplease.getStyleClass().add("Order");
		btotal.getStyleClass().add("Order");
		lline.getStyleClass().add("Order");
		line.getStyleClass().add("Order");
		nextline.getStyleClass().add("Order");
	}
	public void createPhoto()
	{
		FileInputStream fis;
		try {
			fis=new FileInputStream("images/ord.png");
			Image img=new Image(fis);
			ImageView imgView=new ImageView(img);
			Photo=new Button("",imgView);
		}catch(FileNotFoundException e)
		{
			e.printStackTrace();
			Photo=new Button("Error");
		}
		vb=new VBox(30,Photo);
		Photo.getStyleClass().add("btnPhoto");
		vb.setAlignment(Pos.CENTER);
		
	}
	public void ClearSale()
	{
		tid.setText("");
		TableNo.getSelectionModel().select(0);
		note.setText("");
		takeOut.setSelected(true);
		Cash.setSelected(true);
	}
	public void dishClear()
	{
		total.setText("");
		dcom.getSelectionModel().select(0);;
		dname.setText("");
		dprice.setText("");
		allamount.setText("");
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public BorderPane getMainPane()
	{
		createDTable();
		createOrderPane();
		createSaleDishPane();
		createButton();
		createTopPane();
		createPhoto();
		mainPane=new BorderPane();
		mainPane.setTop(staffPane);
		mainPane.setLeft(spane);
		mainPane.setCenter(vb);
		mainPane.setBottom(AC);
		return mainPane;
	}
	public void setMainPane(BorderPane mainPane) {
		this.mainPane = mainPane;
	}
	@Override
	public void start(Stage arg0) throws Exception {
	}
	public static void main(String args)
	{
		Application.launch(args);
	}
	

}
