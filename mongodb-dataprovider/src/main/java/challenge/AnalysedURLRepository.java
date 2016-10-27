package challenge;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import challenge.models.AnalysedURL;

@Repository
public interface AnalysedURLRepository extends MongoRepository<AnalysedURL, String> {
	@SuppressWarnings("unchecked")
	AnalysedURL save(AnalysedURL url);

	AnalysedURL findOne(String id);

	List<AnalysedURL> findAll();

	Page<AnalysedURL> findAll(Pageable pageable);

}
