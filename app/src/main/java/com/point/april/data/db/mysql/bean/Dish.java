package com.point.april.data.db.mysql.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Collection;

@DatabaseTable(tableName = "dish")
public class Dish {

	@DatabaseField(generatedId = true)
	private int dish_id;
	@DatabaseField
	private String name;
	@DatabaseField
	private String style;// 菜系
	@DatabaseField
	private String type;// 类型
	@DatabaseField
	private String avoid;
	@DatabaseField
	private String summary;
	@DatabaseField
	private double price;
	@ForeignCollectionField
	private Collection<Material> materials;
	@ForeignCollectionField
	private Collection<Move> moves;
	@DatabaseField(canBeNull = true, foreign = true, columnName = "menu_id")
	private Menu menu;
	@DatabaseField
	private String image;
	//@DatabaseField
	//private String material;
	private boolean state;

	/*public String getMaterial() {
		return material;
	}*/

/*
	public void setMaterial(String material) {
		this.material = material;
	}
*/

	public boolean getState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public Dish() {
	}

	public Dish(String name) {
		this.name = name;
	}

	public int getDish_id() {
		return dish_id;
	}

	public void setDish_id(int dish_id) {
		this.dish_id = dish_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAvoid() {
		return avoid;
	}

	public void setAvoid(String avoid) {
		this.avoid = avoid;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Collection<Material> getMaterials() {
		return materials;
	}

	public void setMaterials(Collection<Material> materials) {
		this.materials = materials;
	}

	public Collection<Move> getMoves() {
		return moves;
	}

	public void setMoves(Collection<Move> moves) {
		this.moves = moves;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "Dish [dish_id=" + dish_id + ", name=" + name + ", style="
				+ style + ", type=" + type + ", avoid=" + avoid + ", summary="
				+ summary + ", price=" + price + ", materials=" + materials
				+ ", moves=" + moves + ", menu=" + menu + ", image=" + image
				+ "]";
	}

}
