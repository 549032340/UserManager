package cn.com.taiji.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the user_role database table.
 * 
 */
@Entity
@Table(name="user_role")
@NamedQuery(name="UserRole.findAll", query="SELECT u FROM UserRole u")
public class UserRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	/**
	 * @GeneratedValue 注解存在的意义主要就是为一个实体生成一个唯一标识的主键
	 * (JPA要求每一个实体Entity,必须有且只有一个主键),@GeneratedValue提供了主键的生成策略。
	 */
	@Column(name="ur_id")
	private int urId;

	//bi-directional many-to-one association to User
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="u_id")
	private User user;

	//bi-directional many-to-one association to Role
	@ManyToOne
	@JoinColumn(name="r_id")
	private Role role;

	public UserRole() {
	}

	public int getUrId() {
		return this.urId;
	}

	public void setUrId(int urId) {
		this.urId = urId;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "UserRole [urId=" + urId + ", user=" + user.getUName() + ", role=" + role.getRName() + "]";
	}
	
	
}