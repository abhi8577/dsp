package com.dsp.dsp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CONTRACTOR")
public class Contractor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="SR_NO")
	private Long sr_No;

}
