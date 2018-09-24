package figus.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
interface UserRepository extends JpaRepository<User, Long> {

	@Query("SELECT t FROM User t where t.email = :email")
	User findByEmail(String email);
}
