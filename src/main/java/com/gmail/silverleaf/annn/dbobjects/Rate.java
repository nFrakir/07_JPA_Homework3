package com.gmail.silverleaf.annn.dbobjects;

import javax.persistence.*;

@Entity
@Table(name = "rates")
public class Rate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "currency_from")
    private Currency currencyFrom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "currency_to")
    private Currency currencyTo;

    private Double rate;

    public Rate(Currency currencyFrom, Currency currencyTo, Double rate) {
        super();
        this.currencyFrom = currencyFrom;
        this.currencyTo = currencyTo;
        this.rate = rate;
    }

    public Rate() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Currency getCurrencyFrom() {
        return currencyFrom;
    }

    public void setCurrencyFrom(Currency currencyFrom) {
        this.currencyFrom = currencyFrom;
    }

    public Currency getCurrencyTo() {
        return currencyTo;
    }

    public void setCurrencyTo(Currency currencyTo) {
        this.currencyTo = currencyTo;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "Rate{" +
                "id=" + id +
                ", currency_from=" + currencyFrom +
                ", currency_to=" + currencyTo +
                ", rate=" + rate +
                '}';
    }
}
