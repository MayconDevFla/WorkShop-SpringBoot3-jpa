package com.teste.demo.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.teste.demo.entities.enums.OrderStatus;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_order")
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @JsonFormat FORMATANDO DATA E HORA PARA CRUD NO BANCO DE DADOS
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant moment;

    private Integer orderStatus;

    // @ManyToOne SIGNIFICA DE UM PARA MUITOS, OU SEJA, UM CLIENTE PODE TER MUITOS PEDIDOS E ESSES MUITOS PEDIDOS ESTÃO RELACIONADO A APENAS UM CLIENTE.
    // @JoinColumn AQUI ESTAMOS DIZENDO QUAL COLUNA DA TABELA DE PEDIDOS É A CHAVE ESTRANGEIRA
    @ManyToOne
    @JoinColumn(name = "client_id")
    private User client;

    //@OneToMany UM PARA MUITOS. UM PEDIDO PODE TER MUITOS OrderItem. EM mappedBy ESTAMOS FAZENDO AMARRAÇÃO COM A CLASSE DE ORDER E ORDERITEM PASSANDO O ID DA CLASSE PK COMPOSTA...
    // E ESPECIFICANDO QUE O ID QUE ESTAMOS PASSANDO É O DO PEDIDO.
    @OneToMany(mappedBy = "id.order")
    private Set<OrderItem> items = new HashSet<>();

    // NOS CASOS QUE TEMOS A ANOTATION @OneToOne COLOCAMOS mappedBy PASSANDO O ATRIBUTO QUE ESTÁ LÁ DO OUTRO LADO QUE SERIA O "order"...
    // LOGO PASSAMOS cascade = CascadeType.ALL POR QUE? PORQUE ASSIM ESTAMOS DIZENDO QUE O ID DO ORDER SERÁ O MESMO ID PARA O PAGAMENTO TAMBÉM.
    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private Payment payment;

    public Order (){
    }

    public Order(Long id, Instant moment, OrderStatus orderStatus,User client) {
        this.id = id;
        this.moment = moment;
        setOrderStatus(orderStatus);
        this.client = client;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    public OrderStatus getOrderStatus() {
        return OrderStatus.valueOf(orderStatus);
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        if (orderStatus != null) {
            this.orderStatus = orderStatus.getCode();
        }
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Set<OrderItem> getItems(){
        return items;
    }

    public Double getTotal(){
        double sum = 0.0;
        for (OrderItem x : items){
            sum = sum + x.getSubTotal();
            // OU - sum += x.getSubTotal();
        }
        return sum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
