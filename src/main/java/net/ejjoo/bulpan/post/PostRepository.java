package net.ejjoo.bulpan.post;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


/**
 * @see <a href="https://www.baeldung.com/spring-data-repositories"></a>
 */
@Repository
public interface PostRepository extends PagingAndSortingRepository<Post, Integer> {
}
