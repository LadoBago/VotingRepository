package home.learning.voting.model;

import java.io.Serializable;
import lombok.Data;

@Data
public class Vote implements Serializable
{
	private static final long serialVersionUID = 5854618188670483112L;
	private String name;
}
