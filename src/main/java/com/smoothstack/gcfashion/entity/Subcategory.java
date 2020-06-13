package com.smoothstack.gcfashion.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
	private String subcatDesc;
	
	@Column(name = "cat_id")
	private Long catId;
	
	@ManyToOne
	@MapsId("catId")
	@JoinColumn(name = "cat_id")
	@JsonBackReference(value="subcategoryCategory")
	private Category category;

	/**
	 * @return the subcatId
	 */
	public Long getSubcatId() {
		return subcatId;
	}

	/**
	 * @param subcatId the subcatId to set
	 */
	public void setSubcatId(Long subcatId) {
		this.subcatId = subcatId;
	}

	/**
	 * @return the subcatName
	 */
	public String getSubcatName() {
		return subcatName;
	}

	/**
	 * @param subcatName the subcatName to set
	 */
	public void setSubcatName(String subcatName) {
		this.subcatName = subcatName;
	}

	/**
	 * @return the subcatDesc
	 */
	public String getSubcatDesc() {
		return subcatDesc;
	}

	/**
	 * @param subcatDesc the subcatDesc to set
	 */
	public void setSubcatDesc(String subcatDesc) {
		this.subcatDesc = subcatDesc;
	}

	/**
	 * @return the catId
	 */
	public Long getCatId() {
		return catId;
	}

	/**
	 * @param catId the catId to set
	 */
	public void setCatId(Long catId) {
		this.catId = catId;
	}

	/**
	 * @return the category
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((catId == null) ? 0 : catId.hashCode());
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((subcatDesc == null) ? 0 : subcatDesc.hashCode());
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
		if (catId == null) {
			if (other.catId != null)
				return false;
		} else if (!catId.equals(other.catId))
			return false;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (subcatDesc == null) {
			if (other.subcatDesc != null)
				return false;
		} else if (!subcatDesc.equals(other.subcatDesc))
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
