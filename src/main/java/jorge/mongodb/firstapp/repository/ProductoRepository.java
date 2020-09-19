package jorge.mongodb.firstapp.repository;

import jorge.mongodb.firstapp.domain.Producto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "producto", path = "productos")
public interface ProductoRepository extends MongoRepository<Producto, String> {
}
