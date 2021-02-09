package demo.hibernate.jdbc;

@Entity
@Table(name="student")
public class Student {
	
	@Id //For  primary key column
	@Column(name="id")
	private int id;
	
	@Column(name="first_name")
	private String firstName;
	
}
