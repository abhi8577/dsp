package com.dsp.dsp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="SUPPLY_VOLTAGE")
public class SupplyVoltage {
	
	    @Id
	    @Column(name="SUPPLY_VOLTAGE_ID")
		private Long supplyVoltageId;
	    
	    @Column(name="SUPPLY_VOLTAGE_NAME")
		private String supplyVoltageName;

		public Long getSupplyVoltageId() {
			return supplyVoltageId;
		}

		public void setSupplyVoltageId(Long supplyVoltageId) {
			this.supplyVoltageId = supplyVoltageId;
		}

		public String getSupplyVoltageName() {
			return supplyVoltageName;
		}

		public void setSupplyVoltageName(String supplyVoltageName) {
			this.supplyVoltageName = supplyVoltageName;
		}
	    
	    

}
