package restaurant_project_ui;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;

public class Drinks_UI {
	private Button HotD,ColdD;
	private Button b1,b2,b3,b4,b5,b6,b7,b8,b9,b10;
	private Label l1,l2,l3,l4,l5,l6,l7,l8,l9,l10;
	private BorderPane mainPane,both;
	private VBox v1,v2,v3,v4,v5,v6,v7,v8,v9,v10;
	private FlowPane Hflow,Cflow,tb;
	public void createHot()
	{
		
		FileInputStream fis;
		try {
			fis=new FileInputStream("images/coffee.jpg");
			Image img=new Image(fis);
			ImageView imgView=new ImageView(img);
			b1=new Button("",imgView);
		}catch(FileNotFoundException e)
		{
			e.getStackTrace();
			b1=new Button("Error");
		}
		try {
			fis=new FileInputStream("images/tea.jpg");
			Image img=new Image(fis);
			ImageView imgView=new ImageView(img);
			b2=new Button("",imgView);
		}catch(FileNotFoundException e)
		{
			e.getStackTrace();
			b2=new Button("Error");
		}
		try {
			fis=new FileInputStream("images/lemon.jpg");
			Image img=new Image(fis);
			ImageView imgView=new ImageView(img);
			b3=new Button("",imgView);
		}catch(FileNotFoundException e)
		{
			e.getStackTrace();
			b3=new Button("Error");
		}
		try {
			fis=new FileInputStream("images/milk.jpg");
			Image img=new Image(fis);
			ImageView imgView=new ImageView(img);
			b4=new Button("",imgView);
		}catch(FileNotFoundException e)
		{
			e.getStackTrace();
			b4=new Button("Error");
		}
		try {
			fis=new FileInputStream("images/hotcho.jpg");
			Image img=new Image(fis);
			ImageView imgView=new ImageView(img);
			b5=new Button("",imgView);
		}catch(FileNotFoundException e)
		{
			e.getStackTrace();
			b5=new Button("Error");
		}

		
		l1=new Label("Coffee");
		l2=new Label("Tea");
		l3=new Label("Hot Lemon");
		l4=new Label("Milk");
		l5=new Label("Hot Chocolate");
		
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
		
		Hflow=new FlowPane(v1,v2,v3,v4,v5);
		Hflow.setHgap(20);
		mainPane=new BorderPane();
		mainPane.setCenter(Hflow);
		
	}
	public void createCold()
	{
		FileInputStream fis;
		try {
			fis=new FileInputStream("images/stcold.jpg");
			Image img=new Image(fis);
			ImageView imgView=new ImageView(img);
			b6=new Button("",imgView);
		}catch(FileNotFoundException e)
		{
			e.getStackTrace();
			b6=new Button("Error");
		}
		try {
			fis=new FileInputStream("images/mt.jpg");
			Image img=new Image(fis);
			ImageView imgView=new ImageView(img);
			b7=new Button("",imgView);
		}catch(FileNotFoundException e)
		{
			e.getStackTrace();
			b7=new Button("Error");
		}
		try {
			fis=new FileInputStream("images/lime.jpg");
			Image img=new Image(fis);
			ImageView imgView=new ImageView(img);
			b8=new Button("",imgView);
		}catch(FileNotFoundException e)
		{
			e.getStackTrace();
			b8=new Button("Error");
		}
		try {
			fis=new FileInputStream("images/yogurt.jpg");
			Image img=new Image(fis);
			ImageView imgView=new ImageView(img);
			b9=new Button("",imgView);
		}catch(FileNotFoundException e)
		{
			e.getStackTrace();
			b9=new Button("Error");
		}
		try {
			fis=new FileInputStream("images/kiwi.jpg");
			Image img=new Image(fis);
			ImageView imgView=new ImageView(img);
			b10=new Button("",imgView);
		}catch(FileNotFoundException e)
		{
			e.getStackTrace();
			b10=new Button("Error");
		}

		l6=new Label("Strawberry Milkshake");
		l7=new Label("Bubble Milktea");
		l8=new Label("Lime Juice");
		l9=new Label("Yogurt");
		l10=new Label("Kiwi Juice");
		
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
		
		Cflow=new FlowPane(v6,v7,v8,v9,v10);
		Cflow.setHgap(20);
	
		mainPane=new BorderPane();
		mainPane.setCenter(Cflow);
		
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
		HotD=new Button("Hot Drinks");
		HotD.setOnAction(e->{
			mainPane.setCenter(null);
			mainPane.setCenter(Hflow);
		});
		ColdD=new Button("Cold Drinks");
		ColdD.setOnAction(e->{
			mainPane.setCenter(null);
			mainPane.setCenter(Cflow);
		});
		tb=new FlowPane(HotD,ColdD);
		tb.setHgap(60);
		tb.setVgap(20);
		HotD.getStyleClass().add("fbtn");
		ColdD.getStyleClass().add("fbtn");
	}
	public BorderPane getMainPane()
	{
		createHot();
		createCold();
		setStyle();
		createButton();
		both=new BorderPane();
		both.setTop(Hflow);
		both.setCenter(Cflow);
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