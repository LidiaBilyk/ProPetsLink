package propets.link.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import propets.link.model.Post;

public interface PostRepository extends MongoRepository<Post, String> {

}
