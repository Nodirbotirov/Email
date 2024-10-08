package nodbotirov.production.email.repository;

import nodbotirov.production.email.entity.Cat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatRepo extends JpaRepository<Cat, Integer> {
}
