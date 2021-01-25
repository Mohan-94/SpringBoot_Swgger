package com.app.emp.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "EMPLOYEE")
@Setter
@Getter
@NoArgsConstructor
@ToString
@ApiModel(description = "All details about the Employee. ")
public class Employee extends AuditModel {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1783909179296500213L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@ApiModelProperty(notes = "The database generated employee ID")
	private Integer id;
	@Column(name = "emp_id")
	@ApiModelProperty(notes = "The employee first name")
	private String empId;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	@ApiModelProperty(notes = "The employee last name")
	private String lastName;
	@Column(name = "email_id")
	@ApiModelProperty(notes = "The employee email id")
	private String emailId;
	@Column(name = "mobile_no")
	@ApiModelProperty(notes = "The employee mobile number")
	private String mobileNo;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(foreignKey = @ForeignKey(name = "dept_id"), name = "dept_id")
    private Department department;

}
