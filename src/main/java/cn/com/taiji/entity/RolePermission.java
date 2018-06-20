package cn.com.taiji.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the role_permission database table.
 * 
 */
@Entity
@Table(name="role_permission")
@NamedQuery(name="RolePermission.findAll", query="SELECT r FROM RolePermission r")
public class RolePermission implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	/**
	 * @GeneratedValue 注解存在的意义主要就是为一个实体生成一个唯一标识的主键
	 * (JPA要求每一个实体Entity,必须有且只有一个主键),@GeneratedValue提供了主键的生成策略。
	 */
	@Column(name="rp_id")
	private int rpId;

	//bi-directional many-to-one association to Role
	@ManyToOne
	//级联删除的时候如果只是删除one的一方，而不删除one所对应的mang的一方，那么就只在oneonetomany的一方设置CascadeType.REMOVE就好,ManyToOne不要设置remove
	@JoinColumn(name="r_id")
	private Role role;

	//bi-directional many-to-one association to Permission
	@ManyToOne
	@JoinColumn(name="p_id")
	private Permission permission;

	public RolePermission() {
	}

	public int getRpId() {
		return this.rpId;
	}

	public void setRpId(int rpId) {
		this.rpId = rpId;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Permission getPermission() {
		return this.permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}

	@Override
	public String toString() {
		return "RolePermission [rpId=" + rpId + ", role=" + role.getRName() + ", permission=" + permission.getPName() + "]";
	}
	
}