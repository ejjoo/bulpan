package net.ejjoo.bulpan.Repository;

import net.ejjoo.bulpan.Model.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Integer> {
}
