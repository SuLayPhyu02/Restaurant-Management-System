package restaurant_project_ui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class Snacks_UI {
	private Button Noodle,FastFood,Dessert;
	private Button b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,b12,b13,b14,b15;
	private Label l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12,l13,l14,l15;
	private BorderPane mainPane,both;
	private VBox v1,v2,v3,v4,v5,v6,v7,v8,v9,v10,v11,v12,v13,v14,v15;
	private FlowPane Nflow,Fflow,Dflow,tb;
	public void createNoodle()
	{
		
		FileInputStream fis;
		try {
			fis=new FileInputStream("images/rice.jpg");
			Image img=new Image(fis);
			ImageView imgView=new ImageView(img);
			b1=new Button("",imgView);
		}catch(FileNotFoundException e)
		{
			e.getStackTrace();
			b1=new Button("Error");
		}
		try {
			fis=new FileInputStream("images/flour.jpg");
			Image img=new Image(fis);
			ImageView imgView=new ImageView(img);
			b2=new Button("",imgView);
		}catch(FileNotFoundException e)
		{
			e.getStackTrace();
			b2=new Button("Error");
		}
		try {
			fis=new FileInputStream("images/egg.jpg");
			Image img=new Image(fis);
			ImageView imgView=new ImageView(img);
			b3=new Button("",imgView);
		}catch(FileNotFoundException e)
		{
			e.getStackTrace();
			b3=new Button("Error");
		}
		try {
			fis=new FileInputStream("images/mhg.jpg");
			Image img=new Image(fis);
			ImageView imgView=new ImageView(img);
			b4=new Button("",imgView);
		}catch(FileNotFoundException e)
		{
			e.getStackTrace();
			b4=new Button("Error");
		}
		try {
			fis=new FileInputStream("images/montti.jpg");
			Image img=new Image(fis);
			ImageView imgView=new ImageView(img);
			b5=new Button("",imgView);
		}catch(FileNotFoundException e)
		{
			e.getStackTrace();
			b5=new Button("Error");
		}
		
		l1=new Label("Rice Noodle");
		l2=new Label("Flour Noodle");
		l3=new Label("Egg Noodle");
		l4=new Label("Mont Hin Gar");
		l5=new Label("Mont Ti");
		
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
		
		Nflow=new FlowPane(v1,v2,v3,v4,v5);
		Nflow.setHgap(20);
		mainPane=new BorderPane();
		mainPane.setCenter(Nflow);
	}
	public void createFastFood()
	{
		FileInputStream fis;
		try {
			fis=new FileInputStream("images/hamburger.jpg");
			Image img=new Image(fis);
			ImageView imgView=new ImageView(img);
			b6=new Button("",imgView);
		}catch(FileNotFoundException e)
		{
			e.getStackTrace();
			b6=new Button("Error");
		}
		try {
			fis=new FileInputStream("images/sandwich.jpg");
			Image img=new Image(fis);
			ImageView imgView=new ImageView(img);
			b7=new Button("",imgView);
		}catch(FileNotFoundException e)
		{
			e.getStackTrace();
			b7=new Button("Error");
		}
		try {
			fis=new FileInputStream("images/hd.jpg");
			Image img=new Image(fis);
			ImageView imgView=new ImageView(img);
			b8=new Button("",imgView);
		}catch(FileNotFoundException e)
		{
			e.getStackTrace();
			b8=new Button("Error");
		}
		try {
			fis=new FileInputStream("images/kfc.jpg");
			Image img=new Image(fis);
			ImageView imgView=new ImageView(img);
			b9=new Button("",imgView);
		}catch(FileNotFoundException e)
		{
			e.getStackTrace();
			b9=new Button("Error");
		}
		try {
			fis=new FileInputStream("images/taco.jpg");
			Image img=new Image(fis);
			ImageView imgView=new ImageView(img);
			b10=new Button("",imgView);
		}catch(FileNotFoundException e)
		{
			e.getStackTrace();
			b10=new Button("Error");
		}

		l6=new Label("Hamburger");
		l7=new Label("Sandwich");
		l8=new Label("Hotdog");
		l9=new Label("Fires and Chicken");
		l10=new Label("Tacos");
		
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
		
		Fflow=new FlowPane(v6,v7,v8,v9,v10);
		Fflow.setHgap(20);
		mainPane=new BorderPane();
		mainPane.setCenter(Fflow);
		
	}
	public void createDessert()
	{
		FileInputStream fis;
		try {
			fis=new FileInputStream("images/strawberrycake.jpg");
			Image img=new Image(fis);
			ImageView imgView=new ImageView(img);
			b11=new Button("",imgView);
		}catch(FileNotFoundException e)
		{
			e.getStackTrace();
			b11=new Button("Error");
		}
		try {
			fis=new FileInputStream("images/brownie.jpg");
			Image img=new Image(fis);
			ImageView imgView=new ImageView(img);
			b12=new Button("",imgView);
		}catch(FileNotFoundException e)
		{
			e.getStackTrace();
			b12=new Button("Error");
		}
		try {
			fis=new FileInputStream("images/ice.jpg");
			Image img=new Image(fis);
			ImageView imgView=new ImageView(img);
			b13=new Button("",imgView);
		}catch(FileNotFoundException e)
		{
			e.getStackTrace();
			b13=new Button("Error");
		}
		try {
			fis=new FileInputStream("images/ccc.jpg");
			Image img=new Image(fis);
			ImageView imgView=new ImageView(img);
			b14=new Button("",imgView);
		}catch(FileNotFoundException e)
		{
			e.getStackTrace();
			b14=new Button("Error");
		}
		try {
			fis=new FileInputStream("images/donut.jpg");
			Image img=new Image(fis);
			ImageView imgView=new ImageView(img);
			b15=new Button("",imgView);
		}catch(FileNotFoundException e)
		{
			e.getStackTrace();
			b15=new Button("Error");
		}

		l11=new Label("Strawberry Cake");
		l12=new Label("Brownie Cake");
		l13=new Label("Ice-cream");
		l14=new Label("Chocolate Chip Cookie");
		l15=new Label("Donuts");
		
		v11=new VBox();
		v11.getChildren().addAll(b11,l11);
		v12=new VBox();
		v12.getChildren().addAll(b12,l12);
		v13=new VBox();
		v13.getChildren().addAll(b13,l13);
		v14=new VBox();
		v14.getChildren().addAll(b14,l14);
		v15=new VBox();
		v15.getChildren().addAll(b15,l15);
		
		Dflow=new FlowPane(v11,v12,v13,v14,v15);
		Dflow.setHgap(20);
		mainPane=new BorderPane();
		mainPane.setCenter(Dflow);
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
		l11.getStyleClass().add("fText");
		l12.getStyleClass().add("fText");
		l13.getStyleClass().add("fText");
		l14.getStyleClass().add("fText");
		l15.getStyleClass().add("fText");
		
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
		b11.getStyleClass().add("photo");
		b12.getStyleClass().add("photo");
		b13.getStyleClass().add("photo");
		b14.getStyleClass().add("photo");
		b15.getStyleClass().add("photo");
	}
	private void createButton()
	{
		Noodle=new Button("Noodles");
		Noodle.setOnAction(e->{
			mainPane.setCenter(null);
			mainPane.setCenter(Nflow);
		});
		FastFood=new Button("Fast Foods");
		FastFood.setOnAction(e->{
			mainPane.setCenter(null);
			mainPane.setCenter(Fflow);
		});
		Dessert=new Button("Desserts");
		Dessert.setOnAction(e->{
			mainPane.setCenter(null);
			mainPane.setCenter(Dflow);
		});
		Noodle.getStyleClass().add("fbtn");
		FastFood.getStyleClass().add("fbtn");
		Dessert.getStyleClass().add("fbtn");
		tb=new FlowPane(Noodle,FastFood,Dessert);
		tb.setHgap(60);
		tb.setVgap(20);
	}
	public BorderPane getMainPane()
	{
		createNoodle();
		createFastFood();
		createDessert();
		setStyle();
		createButton();
		both=new BorderPane();
		both.setTop(Nflow);
		both.setCenter(Fflow);
		both.setBottom(Dflow);
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
