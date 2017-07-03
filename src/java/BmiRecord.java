/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author katharina
 */
@Entity
@Table(name = "BMI_RECORD")
public class BmiRecord implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String patient;
    private Timestamp create_time;
    private Double height;

    private Double weight;

    private Double bmi;

    public BmiRecord() {
    }

    public BmiRecord(String name, double height, double weight, double bmi) {
        this.patient = name;
        this.height = height;
        this.weight = weight;
        this.bmi = bmi;
        this.create_time = new Timestamp(System.currentTimeMillis());
    }

    public BmiRecord(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public Timestamp getCreateTime() {
        return create_time;
    }

    public void setCreateTime(Timestamp createTime) {
        this.create_time = createTime;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getBmi() {
        return bmi;
    }

    public void setBmi(Double bmi) {
        this.bmi = bmi;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BmiRecord)) {
            return false;
        }
        BmiRecord other = (BmiRecord) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "src.BmiRecord[ id=" + id + " ]";
    }
    
}
