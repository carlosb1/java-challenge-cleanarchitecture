package challenge;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import challenge.entities.AnalysedURL;
import challenge.usecases.GetURL;
import challenge.usecases.ModifyURL;

@Repository
public interface AnalysedURLRepository extends MongoRepository<AnalysedURL, String>, ModifyURL, GetURL {
	@SuppressWarnings("unchecked")
	AnalysedURL save(AnalysedURL url);

	List<AnalysedURL> save(List<AnalysedURL> url);

	AnalysedURL findOne(String id);

	List<AnalysedURL> findAll();

	Page<AnalysedURL> findAll(Pageable pageable);

}
