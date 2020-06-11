package propets.link.model;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode(of = {"id"})
@Document(collection = "posts")
public class Post {
	String id;
	boolean typePost;
	String userLogin;
    String username; 
    String avatar;
	LocalDateTime datePost;
	String type;
	String sex;
	String breed;	
	List<String> tags;	
	List<String> photos;
	Address address;	
    Location location;

}
