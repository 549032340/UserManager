package cn.com.taiji.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the permission database table.
 * 
 */
@Entity
@NamedQuery(name="Permission.findAll", query="SELECT p FROM Permission p")
public class Permission implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="p_id")
	private int pId;

	@Column(name="p_name")
	private String pName;

	//bi-directional many-to-one association to RolePermission
	@OneToMany(mappedBy="permission",cascade={CascadeType.PERSIST,CascadeType.REMOVE})
	//级联删除的时候如果只是删除one的一方，而不删除one所对应的mang的一方，那么就只在oneonetomany的一方设置CascadeType.REMOVE就好
	@JsonIgnore
	private List<RolePermission> rolePermissions;

	public Permission() {
	}

	public int getPId() {
		return this.pId;
	}

	public void setPId(int pId) {
		this.pId = pId;
	}

	public String getPName() {
		return this.pName;
	}

	public void setPName(String pName) {
		this.pName = pName;
	}

	public List<RolePermission> getRolePermissions() {
		return this.rolePermissions;
	}

	public void setRolePermissions(List<RolePermission> rolePermissions) {
		this.rolePermissions = rolePermissions;
	}

	public RolePermission addRolePermission(RolePermission rolePermission) {
		getRolePermissions().add(rolePermission);
		rolePermission.setPermission(this);

		return rolePermission;
	}

	public RolePermission removeRolePermission(RolePermission rolePermission) {
		getRolePermissions().remove(rolePermission);
		rolePermission.setPermission(null);

		return rolePermission;
	}

//	@Override
//	public String toString() {
//		return "Permission [pId=" + pId + ", pName=" + pName + "]";
//	}

	
}