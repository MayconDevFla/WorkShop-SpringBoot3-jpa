package com.teste.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.teste.demo.entities.pk.OrderItemPK;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tb_order_item")
public class OrderItem implements Serializable {
    private static final long serialVersionUID = 1L;

    // UTILIZAMOS A ANOTAÇÃO @EmbeddedId PARA RECONHECER OS ID'S COMPOSTOS VINDO DE OUTRA CLASSE
    @EmbeddedId
    private OrderItemPK id = new OrderItemPK();
    private Integer quantity;
    private Double price;

    public OrderItem(){
    }

    // AQUI O CONSTRUTOR IRÁ PEGAR APENAS OS ID'S DE PRODUTO E PEDIDO PARA CRIAR MEIO QUE UMA TABELA AMARRAÇÃO.
    public OrderItem(Order order, Product product, Integer quantity, Double price) {
        super();
        id.setOrder(order);
        id.setProduct(product);
        this.quantity = quantity;
        this.price = price;
    }

    // POR TERMOS ID COMPOSTO NESSA CLASSE, COLOCAMOS A ANOTAÇÃO @JsonIgnore NO getOrder, POIS ELA QUE É RESPONSÁVEL DE CHAMAR O PEDIDO.
    @JsonIgnore
    public Order getOrder(){
        return id.getOrder();
    }

    public void setOrder(Order order){
        this.id.setOrder(order);
    }

    public Product getProduct(){
        return id.getProduct();
    }

    public void setProduct(Product product){
        this.id.setProduct(product);
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getSubTotal(){
        return price * quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return id.equals(orderItem.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
