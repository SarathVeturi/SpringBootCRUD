package com.app.spb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PRODUCT_TBL")
public class Product {

	@Id
	@GeneratedValue
	private int id;
	@Column(name = "NAME")
	private String name;
	@Column(name = "QUANTITY")
	private int quantity;
	@Column(name = "PRICE")
	private double price;
	@Column(name = "TOTAL")
	private double total;
	
	/*If we specify variable as Date type but wanted to pass the input as String 
	 * @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	 * private Date travelDate;
	 */
}
