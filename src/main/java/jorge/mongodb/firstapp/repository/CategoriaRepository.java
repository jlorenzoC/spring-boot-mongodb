package jorge.mongodb.firstapp.repository;

import jorge.mongodb.firstapp.domain.Categoria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "categoria", path = "categorias")
public interface CategoriaRepository extends MongoRepository<Categoria, String> {
}
