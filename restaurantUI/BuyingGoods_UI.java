package restaurant_project_ui;

import java.util.ArrayList;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import restaurant_project.DBHandler;
import restaurant_project.Dish;
import restaurant_project.SItemCategories;
import restaurant_project.ShoppingItems;
import restaurant_project.Staff;
import restaurant_project.Sub_Categories;

public class BuyingGoods_UI {
	private TableView tvShopping;
	private ArrayList<ShoppingItems>al;
	private TableSelectionModel<ShoppingItems> selectionModel;
	private TableColumn<ShoppingItems,String> nameCol;
	private TableColumn<ShoppingItems,Integer> qtyCol;
	private TableColumn<ShoppingItems,String> qtyUnitCol;
	private TableColumn<ShoppingItems,Integer> priceCol;
	private TableColumn<ShoppingItems,String> DateCol;
	private TableColumn<ShoppingItems,String> staffCol;
	private TableColumn<ShoppingItems,Integer> totalAm;
	

	
	private ArrayList<SItemCategories> sial;
	private BorderPane mainPane;
	private Label lid,litem,lqty,ltotal,lqtyunit,lsname,lname,lprice;
	private TextField tid,tqty,total,tsname,tname,tprice;
	private Button btnAdd,btnUpdate;
	private ComboBox<SItemCategories> itemCom;
	private ComboBox<String> qtyunitcom;
	private GridPane gp;
	
	private void createAddPane()
	{
		lid=new Label("id");
		lid.setDisable(true);
		litem=new Label("Items");
		lqty=new Label("Qty");
		lqtyunit=new Label("Unit");
		ltotal=new Label("Total");
		ltotal.setDisable(true);
		lsname=new Label("Staff Name");
		lsname.setDisable(true);
		lname=new Label("Item Name");
		lname.setDisable(true);
		lprice=new Label("Item Price");
		lprice.setDisable(true);
		
		tid=new TextField();
		tid.setDisable(true);
		tid.setMaxWidth(200);
		sial=DBHandler.getAllSICategories();
		itemCom=new ComboBox<SItemCategories>(FXCollections.observableArrayList(sial));
		itemCom.setMaxWidth(200);
		tqty=new TextField();
		tqty.setMaxWidth(200);
		ArrayList<String>qal=new ArrayList<String>();
		qal.add("viss");
		qal.add("bag");
		qal.add("bottle");
		qal.add("basket");
		qal.add("can");
		qal.add("package");
		qal.add("bar");
		qal.add("loaf");
		qal.add("lime");
		qal.add("lettuce");
		qal.add("cabbage");
		qal.add("cauliflower");
		qtyunitcom=new ComboBox<String>(FXCollections.observableArrayList(qal));
		total=new TextField();
		total.setDisable(true);
		total.setMaxWidth(200);
		tsname=new TextField();
		tsname.setDisable(true);
		tsname.setMaxWidth(200);
		tname=new TextField();
		tname.setDisable(true);
		tname.setMaxWidth(200);
		tprice=new TextField();
		tprice.setDisable(true);
		tprice.setMaxWidth(200);
		
		btnAdd=new Button("Add");
		btnAdd.setOnAction(e->{
			AddSI();
		});
		btnUpdate=new Button("Update");
		btnUpdate.setOnAction(e->{
			UpdateSI();
		});
		gp=new GridPane();
		gp.add(lid, 0, 0);
		gp.add(tid, 1, 0);
		
		gp.add(litem, 0, 1);
		gp.add(itemCom, 1, 1);
		
		gp.add(lqty, 0, 2);
		gp.add(tqty, 1, 2);
		
		gp.add(lqtyunit, 0, 3);
		gp.add(qtyunitcom, 1, 3);
		
		gp.add(ltotal, 0, 4);
		gp.add(total, 1, 4);
		
		
		gp.add(lsname, 0, 5);
		gp.add(tsname, 1, 5);
		
		gp.add(lname, 0, 6);
		gp.add(tname, 1, 6);
		
		gp.add(lprice, 0, 7);
		gp.add(tprice, 1, 7);
		
		gp.add(btnAdd, 0, 8);
		gp.add(btnUpdate, 1, 8);
		
		gp.setAlignment(Pos.CENTER);
		gp.setPadding(new Insets(20));
		gp.setVgap(20);
		gp.setMaxWidth(500);
		
		lid.getStyleClass().add("SI");
		litem.getStyleClass().add("SI");
		lqty.getStyleClass().add("SI");
		lqtyunit.getStyleClass().add("SI");
		ltotal.getStyleClass().add("SI");
		lsname.getStyleClass().add("SI");
		lname.getStyleClass().add("SI");
		lprice.getStyleClass().add("SI");
		btnAdd.getStyleClass().add("btn");
		btnUpdate.getStyleClass().add("btn");
		btnAdd.getStyleClass().add("AddB");
		btnUpdate.getStyleClass().add("UpdateB");
		gp.setId("updatePane");
	}
	public void AddSI()
	{
		String name,sname,unit;
		int id,qty,price,sicid;
		id=DBHandler.getSIMax();
		id++;
		tid.setText(""+id);
		String itemarr[]=(itemCom.getValue().toString()).split(",");
		sicid=DBHandler.getSICid(itemarr[1].trim());
		name=DBHandler.getSICName(sicid);
		tname.setText(""+name);
		price=DBHandler.getSICprice(sicid);
		tprice.setText(""+price);
		qty=Integer.parseInt(tqty.getText().trim());
		unit=qtyunitcom.getValue();
		int to=(qty*price);
		total.setText(""+to);
		int staffid=LoginUI_re.getStaffId();
		sname=DBHandler.getStaffName(staffid);
		tsname.setText(""+sname);
		Alert  alt=new Alert(AlertType.CONFIRMATION);
		alt.setHeaderText("Confirmation");
		alt.setContentText("Are you sure to add ?");
		Optional<ButtonType>ans=alt.showAndWait();
		if(ans.isPresent() && ans.get()==ButtonType.OK)
		{
			boolean add=DBHandler.insertShopping(id,qty,unit,to,staffid,sicid);
			if(add==true)
			{
				boolean date=DBHandler.getSIDate(id);
				if(!date)
				{
					System.out.println();
					Alert sorry=new Alert(AlertType.INFORMATION);
					sorry.setHeaderText("unsuccessfully");
					sorry.setContentText("Sorry try again!!");
					sorry.show();
				}
				else 
				{
				Alert con=new Alert(AlertType.INFORMATION);
				con.setHeaderText("Successful");
				con.setContentText("you insert successfully");
				con.show();
				
				}
			}
			else
			{
				System.out.println("siid"+id);
				Alert sorry=new Alert(AlertType.INFORMATION);
				sorry.setHeaderText("unsuccessfully");
				sorry.setContentText("Sorry try again!!");
				sorry.show();
			}
		}
		
	}
	public void UpdateSI()
	{
		String name,unit,sname;
		int id,qty,price,sicid;
		id=Integer.parseInt(tid.getText());
		name=tname.getText();
		qty=Integer.parseInt(tqty.getText());
		unit=qtyunitcom.getValue();
		price=Integer.parseInt(tprice.getText());
		int to=(qty*price);
		total.setText(""+to);
		int staffid=LoginUI_re.getStaffId();
		boolean update=DBHandler.updateShopping(id,name,qty,unit,price,staffid,to);
		Alert  alt=new Alert(AlertType.CONFIRMATION);
		alt.setHeaderText("Confirmation");
		alt.setContentText("Are you sure to update ?");
		Optional<ButtonType>ans=alt.showAndWait();
		if(ans.isPresent() && ans.get()==ButtonType.OK)
		{
			if(update==true)
			{
				boolean date=DBHandler.getSIDate(id);
				if(!date)
				{
					System.out.println();
					Alert sorry=new Alert(AlertType.INFORMATION);
					sorry.setHeaderText("unsuccessfully");
					sorry.setContentText("Sorry try again!!");
					sorry.show();
				}
				else 
				{
				Alert con=new Alert(AlertType.INFORMATION);
				con.setHeaderText("Successful");
				con.setContentText("you update successfully");
				con.show();
				
				}	
			}
			else
			{
				System.out.println("id"+id);
				System.out.println("name"+name);
				System.out.println("qty"+qty);
				System.out.println("unit"+unit);
				System.out.println("price"+price);
				System.out.println("staffid"+staffid);
				System.out.println(update);
				Alert sorry=new Alert(AlertType.INFORMATION);
				sorry.setHeaderText("unsuccessfully");
				sorry.setContentText("Sorry try again!!");
				sorry.show();
			}
		}	
	}
	public void createShopTable()
	{
		tvShopping=new TableView<ShoppingItems>();
		
		nameCol=new TableColumn<ShoppingItems, String>("Name");
		nameCol.setCellValueFactory(new PropertyValueFactory<ShoppingItems,String>("sicname"));
		
		qtyCol=new TableColumn<ShoppingItems, Integer>("Qty");
		qtyCol.setCellValueFactory(new PropertyValueFactory<ShoppingItems,Integer>("iqty"));
		
		qtyUnitCol=new TableColumn<ShoppingItems, String>("Unit");
		qtyUnitCol.setCellValueFactory(new PropertyValueFactory<ShoppingItems,String>("qtyunit"));
		
		priceCol=new TableColumn<ShoppingItems, Integer>("Price");
		priceCol.setCellValueFactory(new PropertyValueFactory<ShoppingItems,Integer>("sicprice"));
		
		
		DateCol=new TableColumn<ShoppingItems, String>("Date");
		DateCol.setCellValueFactory(new PropertyValueFactory<ShoppingItems,String>("buydate"));
		
		totalAm=new TableColumn<ShoppingItems,Integer>("Total");
		totalAm.setCellValueFactory(new PropertyValueFactory<ShoppingItems,Integer>("total"));
		
		
		staffCol=new TableColumn<ShoppingItems,String>("Staff Name");
		staffCol.setCellValueFactory(new PropertyValueFactory<ShoppingItems,String>("sname"));
		
		
		nameCol.getStyleClass().add("tableText");
		qtyCol.getStyleClass().add("tableText");
		qtyUnitCol.getStyleClass().add("tableText");
		priceCol.getStyleClass().add("tableText");
		DateCol.getStyleClass().add("tableText");
		staffCol.getStyleClass().add("tableText");
		totalAm.getStyleClass().add("tableText");
		
		
		tvShopping.getColumns().add(nameCol);
		tvShopping.getColumns().add(qtyCol);
		tvShopping.getColumns().add(qtyUnitCol);
		tvShopping.getColumns().add(priceCol);
		tvShopping.getColumns().add(DateCol);
		tvShopping.getColumns().add(staffCol);
		tvShopping.getColumns().add(totalAm);
		tvShopping.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		selectionModel=tvShopping.getSelectionModel();
		selectionModel.setSelectionMode(SelectionMode.SINGLE);
		tvShopping.setEditable(true);
		tvShopping.getSortOrder().setAll(DateCol);
		tvShopping.setOnMouseClicked(e->{
			if(e.getButton()==MouseButton.PRIMARY)
			{
				setUpdateInfo();
			}
		});
		setData();	
	}
	public void setUpdateInfo()
	{
		ShoppingItems si=selectionModel.getSelectedItem();
		tid.setText(""+si.getSiid());
		tid.setEditable(false);
		tqty.setText(""+si.getIqty());
		qtyunitcom.setValue(si.getQtyunit());
		total.setText(""+si.getTotal());
		tsname.setText(""+si.getSname());
		tname.setText(""+si.getSicname());
		tprice.setText(""+si.getSicprice());
		
	}
	public void setData()
	{
		al=DBHandler.getAllShopIView();
		tvShopping.getItems().addAll(al);
	}
	public BorderPane getMainPane()
	{
		createShopTable();
		createAddPane();
		mainPane=new BorderPane();
		mainPane.setCenter(tvShopping);
		mainPane.setRight(gp);
		return mainPane;
	}
	public void setMainPane(BorderPane mainPane) {
		this.mainPane = mainPane;
	}
}
