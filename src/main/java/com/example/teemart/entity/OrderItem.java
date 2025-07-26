/*
 * package com.example.teemart.entity;
 * 
 * import com.fasterxml.jackson.annotation.JsonBackReference;
 * 
 * import jakarta.persistence.Entity; import jakarta.persistence.GeneratedValue;
 * import jakarta.persistence.GenerationType; import jakarta.persistence.Id;
 * import jakarta.persistence.JoinColumn; import jakarta.persistence.ManyToOne;
 * 
 * //@Entity public class OrderItem {
 * 
 * @Id
 * 
 * @GeneratedValue(strategy=GenerationType.IDENTITY) private long id;
 * 
 * @JsonBackReference
 * 
 * @ManyToOne
 * 
 * @JoinColumn(name="orderid") private Order order;
 * 
 * @JsonBackReference
 * 
 * @ManyToOne
 * 
 * @JoinColumn(name = "tshirtid") private Tshirt tshirt;
 * 
 * 
 * private int quantity;
 * 
 * private double price;
 * 
 * 
 * public OrderItem() { super(); }
 * 
 * public OrderItem(long id, Order order, Tshirt tshirt, int quantity, double
 * price) { super(); this.id = id; this.order = order; this.tshirt = tshirt;
 * this.quantity = quantity; this.price = price; }
 * 
 * public long getId() { return id; }
 * 
 * public void setId(long id) { this.id = id; }
 * 
 * public Order getOrder() { return order; }
 * 
 * public void setOrder(Order order) { this.order = order; }
 * 
 * public Tshirt getTshirt() { return tshirt; }
 * 
 * public void setTshirt(Tshirt tshirt) { this.tshirt = tshirt; }
 * 
 * public int getQuantity() { return quantity; }
 * 
 * public void setQuantity(int quantity) { this.quantity = quantity; }
 * 
 * public double getPrice() { return price; }
 * 
 * public void setPrice(double price) { this.price = price; }
 * 
 * 
 * @Override public String toString() { return "OrderItem [id=" + id +
 * ", order=" + order + ", tshirt=" + tshirt + ", quantity=" + quantity +
 * ", price=" + price + "]"; }
 * 
 * }
 */