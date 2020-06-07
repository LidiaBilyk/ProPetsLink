package propets.link.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import propets.link.model.Link;

public interface LinkRepository extends MongoRepository<Link, String> {

}
