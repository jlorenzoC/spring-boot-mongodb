package jorge.mongodb.firstapp.repository;

import jorge.mongodb.firstapp.domain.Direccion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "direccion", path = "direcciones")
public interface DireccionRepository extends MongoRepository<Direccion, String> {
}
