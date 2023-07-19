package restaurant_project_ui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;

public class Meals_UI {
	private Button MainDishes,SideDishes;
	private Button b1,b2,b3,b4,b5;
	private Label l1,l2,l3,l4,l5,l6,l7,l8,l9,l10;
	private Button b6,b7,b8,b9,b10;
	
	private BorderPane mainPane,both;
	private VBox v1,v2,v3,v4,v5,v6,v7,v8,v9,v10;
	private FlowPane Mflow,Sflow,tb;
	public void createMain()
	{
		FileInputStream fis;
		try {
			fis=new FileInputStream("images/fr.jpg");
			Image img=new Image(fis);
			ImageView imgView=new ImageView(img);
			b1=new Button("",imgView);
		}catch(FileNotFoundException e)
		{
			e.getStackTrace();
			b1=new Button("Error");
		}
		try {
			fis=new FileInputStream("images/cr.jpg");
			Image img=new Image(fis);
			ImageView imgView=new ImageView(img);
			b2=new Button("",imgView);
		}catch(FileNotFoundException e)
		{
			e.getStackTrace();
			b2=new Button("Error");
		}
		try {
			fis=new FileInputStream("images/pork.jpg");
			Image img=new Image(fis);
			ImageView imgView=new ImageView(img);
			b3=new Button("",imgView);
		}catch(FileNotFoundException e)
		{
			e.getStackTrace();
			b3=new Button("Error");
		}
		try {
			fis=new FileInputStream("images/mala.jpg");
			Image img=new Image(fis);
			ImageView imgView=new ImageView(img);
			b4=new Button("",imgView);
		}catch(FileNotFoundException e)
		{
			e.getStackTrace();
			b4=new Button("Error");
		}
		try {
			fis=new FileInputStream("images/shrimp.jpg");
			Image img=new Image(fis);
			ImageView imgView=new ImageView(img);
			b5=new Button("",imgView);
		}catch(FileNotFoundException e)
		{
			e.getStackTrace();
			b5=new Button("Error");
		}
		
		l1=new Label("Fried Rice");
		l2=new Label("Chicken Curry");
		l3=new Label("Pork Curry");
		l4=new Label("Mala Xiang Guo");
		l5=new Label("Shrimp Fried");
		
		v1=new VBox();
		v1.getChildren().addAll(b1,l1);
		v2=new VBox();
		v2.getChildren().addAll(b2,l2);
		v3=new VBox();
		v3.getChildren().addAll(b3,l3);
		v4=new VBox();
		v4.getChildren().addAll(b4,l4);
		v5=new VBox();
		v5.getChildren().addAll(b5,l5);
		
		Mflow=new FlowPane(v1,v2,v3,v4,v5);
		Mflow.setHgap(20);
		mainPane=new BorderPane();
		mainPane.setCenter(Mflow);
	}
	public void createSide()
	{
		FileInputStream fis;
		try {
			fis=new FileInputStream("images/salad.jpg");
			Image img=new Image(fis);
			ImageView imgView=new ImageView(img);
			b6=new Button("",imgView);
		}catch(FileNotFoundException e)
		{
			e.getStackTrace();
			b6=new Button("Error");
		}
		try {
			fis=new FileInputStream("images/bean.jpg");
			Image img=new Image(fis);
			ImageView imgView=new ImageView(img);
			b7=new Button("",imgView);
		}catch(FileNotFoundException e)
		{
			e.getStackTrace();
			b7=new Button("Error");
		}
		try {
			fis=new FileInputStream("images/corn.jpg");
			Image img=new Image(fis);
			ImageView imgView=new ImageView(img);
			b8=new Button("",imgView);
		}catch(FileNotFoundException e)
		{
			e.getStackTrace();
			b8=new Button("Error");
		}
		try {
			fis=new FileInputStream("images/potato.jpg");
			Image img=new Image(fis);
			ImageView imgView=new ImageView(img);
			b9=new Button("",imgView);
		}catch(FileNotFoundException e)
		{
			e.getStackTrace();
			b9=new Button("Error");
		}
		try {
			fis=new FileInputStream("images/tomato.jpg");
			Image img=new Image(fis);
			ImageView imgView=new ImageView(img);
			b10=new Button("",imgView);
		}catch(FileNotFoundException e)
		{
			e.getStackTrace();
			b10=new Button("Error");
		}

		
		l6=new Label("Salad");
		l7=new Label("Bean Curry");
		l8=new Label("Corn Curry");
		l9=new Label("Roasted Curry");
		l10=new Label("Tomato Soup");
		
		v6=new VBox();
		v6.getChildren().addAll(b6,l6);
		v7=new VBox();
		v7.getChildren().addAll(b7,l7);
		v8=new VBox();
		v8.getChildren().addAll(b8,l8);
		v9=new VBox();
		v9.getChildren().addAll(b9,l9);
		v10=new VBox();
		v10.getChildren().addAll(b10,l10);
		
		Sflow=new FlowPane(v6,v7,v8,v9,v10);
		Sflow.setHgap(20);
		
		mainPane=new BorderPane();
		mainPane.setCenter(Sflow);
		
	}
	public void setStyle()
	{
		l1.getStyleClass().add("fText");
		l2.getStyleClass().add("fText");
		l3.getStyleClass().add("fText");
		l4.getStyleClass().add("fText");
		l5.getStyleClass().add("fText");
		l6.getStyleClass().add("fText");
		l7.getStyleClass().add("fText");
		l8.getStyleClass().add("fText");
		l9.getStyleClass().add("fText");
		l10.getStyleClass().add("fText");
		
		b1.getStyleClass().add("photo");
		b2.getStyleClass().add("photo");
		b3.getStyleClass().add("photo");
		b4.getStyleClass().add("photo");
		b5.getStyleClass().add("photo");
		b6.getStyleClass().add("photo");
		b7.getStyleClass().add("photo");
		b8.getStyleClass().add("photo");
		b9.getStyleClass().add("photo");
		b10.getStyleClass().add("photo");
	}
	private void createButton()
	{
		MainDishes=new Button("Main Dishes");
		MainDishes.setOnAction(e->{
			mainPane.setCenter(null);
			mainPane.setCenter(Mflow);
		});
		SideDishes=new Button("Side Dishes");
		SideDishes.setOnAction(e->{
			mainPane.setCenter(null);
			mainPane.setCenter(Sflow);
		});
		MainDishes.getStyleClass().add("fbtn");
		SideDishes.getStyleClass().add("fbtn");
		tb=new FlowPane(MainDishes,SideDishes);
		tb.setHgap(60);
		tb.setVgap(20);
		//tb.getStyleClass().add("tb");
		//tb.setPadding(new Insets(50));
	}
	public BorderPane getMainPane()
	{
		createMain();
		createSide();
		setStyle();
		createButton();
		both=new BorderPane();
		both.setTop(Mflow);
		both.setCenter(Sflow);
		mainPane.setTop(tb);
		mainPane.setMargin(tb, new Insets(0,0,10,0));
		mainPane.setCenter(both);
		//mainPane.getStyleClass().add("reportmain");
		return mainPane;
	}
	public void setMainPane(BorderPane mainPane) {
		this.mainPane = mainPane;
	}
	
	
}
