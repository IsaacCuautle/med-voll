package med.voll.api.repositories;

import med.voll.api.models.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.print.Doc;
import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, Long>
{

    Page<Doctor> findByActiveTrue(Pageable pageable);
}
