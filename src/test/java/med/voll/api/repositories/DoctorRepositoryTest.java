package med.voll.api.repositories;

import med.voll.api.dto.AddressData;
import med.voll.api.dto.doctor.DoctorsData;
import med.voll.api.dto.doctor.Speciality;
import med.voll.api.dto.patient.PatientData;
import med.voll.api.models.Consultation;
import med.voll.api.models.Doctor;
import med.voll.api.models.Patient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class DoctorRepositoryTest {

    @Autowired
    private DoctorRepository repository;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("Asigna un medico con especialidad similar si el seleccionado ya tiene una consulta en ese horario")
    void selectDoctorSimilarSpecialityStage1()
    {
        var nextMonday10H = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 00);

        var doctor = doctorRegister("Jhon Doe","jhon.doe@gmail.com","024860",Speciality.CARDIOLOGY);

        var patient = patientRegister("Jannet Doe","jannet.doe@gmail.com","123456789");
        ConsultingRegister(doctor,patient,nextMonday10H);

        var doctorFree = repository.selectDoctorSimilarSpeciality(Speciality.CARDIOLOGY, nextMonday10H);
        assertNull(doctorFree);
    }

    @Test
    @DisplayName("Asigna un medico")
    void selectDoctorSimilarSpecialityStage2()
    {
        // given
        var nextMonday10H = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 00);

        // when
        var doctor = doctorRegister("Jhon Doe","jhon.doe@gmail.com","024860",Speciality.CARDIOLOGY);

        // then
        var doctorFree = repository.selectDoctorSimilarSpeciality(Speciality.CARDIOLOGY, nextMonday10H);
        assertEquals(doctorFree,doctor);
    }

    private void ConsultingRegister(Doctor doctor, Patient patient, LocalDateTime date)
    {
        em.persist(new Consultation(null, doctor, patient, date));
    }

    private Doctor doctorRegister(String name, String email, String document, Speciality speciality)
    {
        var doctor = new Doctor(doctorsDataTest(name, email, document, speciality));
        em.persist(doctor);
        return doctor;
    }

    private DoctorsData doctorsDataTest(String name, String email, String document, Speciality speciality)
    {
        return new DoctorsData(
                name,
                email,
                "5566778899",
                document,
                speciality,
                addressDataTest()
        );
    }

    private Patient patientRegister(String name, String email, String document)
    {
        var patient = new Patient(PatientDataTest(name, email, document));
        em.persist(patient);
        return patient;
    }

    private PatientData PatientDataTest(String name, String email, String document)
    {
        return new PatientData(
                name,
                email,
                "5566778899",
                document,
                addressDataTest()
        );
    }

    private AddressData addressDataTest(){
        return new AddressData(
            "calle falsa",
            "masachussets",
            "springfield",
            123,
            "a"
        );
    };

}