package home.learning.voting.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.istack.NotNull;

import lombok.Data;

@Data
@Entity
@Table(name = "\"Votes\"", schema = "public", catalog = "postgres")
public class VoteEntity implements Serializable {
	private static final long serialVersionUID = 5193308124687509804L;
	@NotNull
	@Column(name = "\"Name\"")
	private String name;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "\"Id\"")
	private int id;

}
