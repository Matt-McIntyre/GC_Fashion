package com.smoothstack.gcfashion.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "subcategory")
public class Subcategory implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5025050942490678068L;

	@Id
	@Column(name = "subcat_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long subcatId;
	
	@Column(name = "subcat_name")
	private String subcatName;
	
	@Column(name = "subcat_desc")
	private String catDesc;
	
	@Column(name = "cat_id")
	private Long catId;

	public Long getSubcatId() {
		return subcatId;
	}

	public void setSubcatId(Long subcatId) {
		this.subcatId = subcatId;
	}

	public String getSubcatName() {
		return subcatName;
	}

	public void setSubcatName(String subcatName) {
		this.subcatName = subcatName;
	}

	public String getCatDesc() {
		return catDesc;
	}

	public void setCatDesc(String catDesc) {
		this.catDesc = catDesc;
	}

	public Long getCatId() {
		return catId;
	}

	public void setCatId(Long catId) {
		this.catId = catId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((catDesc == null) ? 0 : catDesc.hashCode());
		result = prime * result + ((catId == null) ? 0 : catId.hashCode());
		result = prime * result + ((subcatId == null) ? 0 : subcatId.hashCode());
		result = prime * result + ((subcatName == null) ? 0 : subcatName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Subcategory other = (Subcategory) obj;
		if (catDesc == null) {
			if (other.catDesc != null)
				return false;
		} else if (!catDesc.equals(other.catDesc))
			return false;
		if (catId == null) {
			if (other.catId != null)
				return false;
		} else if (!catId.equals(other.catId))
			return false;
		if (subcatId == null) {
			if (other.subcatId != null)
				return false;
		} else if (!subcatId.equals(other.subcatId))
			return false;
		if (subcatName == null) {
			if (other.subcatName != null)
				return false;
		} else if (!subcatName.equals(other.subcatName))
			return false;
		return true;
	}
	


}
