package gdsc.hello.data.repository;

import gdsc.hello.data.entity.Producer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProducerRepository extends JpaRepository<Producer, Long> {

}
