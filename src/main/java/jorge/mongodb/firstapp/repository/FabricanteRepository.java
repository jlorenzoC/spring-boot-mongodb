package jorge.mongodb.firstapp.repository;

import jorge.mongodb.firstapp.domain.Fabricante;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "fabricante", path = "fabricantes")
public interface FabricanteRepository extends MongoRepository<Fabricante, String> {
}
