package com.smoothstack.gcfashion.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "transaction_inventory")
public class TransactionProducts implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4179099953060096491L;

	@EmbeddedId
	@JsonBackReference(value="keyTransactionProducts")
	TransactionProductsKey id;
	
	@Column(name = "sku")
	private Long sku;

	public TransactionProducts() {
		
	}
	
	public TransactionProducts(TransactionProductsKey id, long sku) {
		super();
		this.id = id;
		this.sku = sku;
	}
	
	public TransactionProductsKey getId() {
		return id;
	}

	public void setId(TransactionProductsKey id) {
		this.id = id;
	}

	public Long getSku() {
		return sku;
	}

	public void setSku(Long sku) {
		this.sku = sku;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof TransactionProducts)) {
			return false;
		}
		TransactionProducts other = (TransactionProducts) obj;
		return Objects.equals(id, other.id);
	}
	
}
