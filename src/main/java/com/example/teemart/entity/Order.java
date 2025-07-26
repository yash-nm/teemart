/*
 * package com.example.teemart.entity;
 * 
 * import java.time.LocalDateTime; import java.util.List;
 * 
 * import com.fasterxml.jackson.annotation.JsonBackReference; import
 * com.fasterxml.jackson.annotation.JsonManagedReference;
 * 
 * import jakarta.persistence.Entity; import jakarta.persistence.GeneratedValue;
 * import jakarta.persistence.GenerationType; import jakarta.persistence.Id;
 * import jakarta.persistence.JoinColumn; import jakarta.persistence.ManyToOne;
 * import jakarta.persistence.OneToMany;
 * 
 * //@Entity public class Order {
 * 
 * @Id
 * 
 * @GeneratedValue(strategy = GenerationType.IDENTITY) private long orderid;
 * 
 * private LocalDateTime orderdate;
 * 
 * private String status; // PLACED , SHIPPED , CANCELLED
 * 
 * @JsonBackReference
 * 
 * @ManyToOne
 * 
 * @JoinColumn(name="userid") private User user;
 * 
 * 
 * @JsonManagedReference
 * 
 * @OneToMany(mappedBy = "order") private List<OrderItem> orderitems;
 * 
 * private double totalamount;
 * 
 * 
 * public Order() { super(); }
 * 
 * public Order(long orderid, LocalDateTime orderdate, String status, User user,
 * List<OrderItem> orderitems, double totalamount) { super(); this.orderid =
 * orderid; this.orderdate = orderdate; this.status = status; this.user = user;
 * this.orderitems = orderitems; this.totalamount = totalamount; }
 * 
 * public long getOrderid() { return orderid; }
 * 
 * public void setOrderid(long orderid) { this.orderid = orderid; }
 * 
 * public LocalDateTime getOrderdate() { return orderdate; }
 * 
 * public void setOrderdate(LocalDateTime orderdate) { this.orderdate =
 * orderdate; }
 * 
 * public String getStatus() { return status; }
 * 
 * public void setStatus(String status) { this.status = status; }
 * 
 * public User getUser() { return user; }
 * 
 * public void setUser(User user) { this.user = user; }
 * 
 * public List<OrderItem> getOrderitems() { return orderitems; }
 * 
 * public void setOrderitems(List<OrderItem> orderitems) { this.orderitems =
 * orderitems; }
 * 
 * public double getTotalamount() { return totalamount; }
 * 
 * public void setTotalamount(double totalamount) { this.totalamount =
 * totalamount; }
 * 
 * @Override public String toString() { return "Order [orderid=" + orderid +
 * ", orderdate=" + orderdate + ", status=" + status + ", user=" + user +
 * ", orderitems=" + orderitems + ", totalamount=" + totalamount + "]"; }
 * 
 * }
 * 
 */