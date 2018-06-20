package cn.com.taiji.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the role database table.
 * 
 */
@Entity
@NamedQuery(name="Role.findAll", query="SELECT r FROM Role r")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="r_id")
	private int rId;

	@Column(name="r_name")
	private String rName;

	//bi-directional many-to-one association to RolePermission
	@OneToMany(mappedBy="role",cascade={CascadeType.PERSIST,CascadeType.REMOVE})
	@JsonIgnore
	private List<RolePermission> rolePermissions;

	//bi-directional many-to-one association to UserRole
	@OneToMany(mappedBy="role",cascade={CascadeType.REMOVE})
	@JsonIgnore
	private List<UserRole> userRoles;

	public Role() {
	}

	public int getRId() {
		return this.rId;
	}

	public void setRId(int rId) {
		this.rId = rId;
	}

	public String getRName() {
		return this.rName;
	}

	public void setRName(String rName) {
		this.rName = rName;
	}

	public List<RolePermission> getRolePermissions() {
		return this.rolePermissions;
	}

	public void setRolePermissions(List<RolePermission> rolePermissions) {
		this.rolePermissions = rolePermissions;
	}

	public RolePermission addRolePermission(RolePermission rolePermission) {
		getRolePermissions().add(rolePermission);
		rolePermission.setRole(this);

		return rolePermission;
	}

	public RolePermission removeRolePermission(RolePermission rolePermission) {
		getRolePermissions().remove(rolePermission);
		rolePermission.setRole(null);

		return rolePermission;
	}

	public List<UserRole> getUserRoles() {
		return this.userRoles;
	}

	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public UserRole addUserRole(UserRole userRole) {
		getUserRoles().add(userRole);
		userRole.setRole(this);

		return userRole;
	}

	public UserRole removeUserRole(UserRole userRole) {
		getUserRoles().remove(userRole);
		userRole.setRole(null);

		return userRole;
	}

//	@Override
//	public String toString() {
//		return "Role [rId=" + rId + ", rName=" + rName + ", rolePermissions=" + rolePermissions.get(0).getPermission().getPName()+ "]";
//	}

	
}