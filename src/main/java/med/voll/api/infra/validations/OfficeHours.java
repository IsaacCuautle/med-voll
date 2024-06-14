package med.voll.api.infra.validations;

import jakarta.validation.ValidationException;
import med.voll.api.dto.consultations.DataScheduleConsultation;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class OfficeHours implements ConsultationValidation
{
    // Validar el dia de la cita
    public void validate(DataScheduleConsultation data){
        var sunday = DayOfWeek.SUNDAY.equals(data.date());
        var beforeOpening = data.date().getHour() < 7;
        var afterClosing = data.date().getHour() > 19;

        if (sunday || beforeOpening || afterClosing)
        {
            throw new ValidationException("El horario de atencion es de lun a sabado de 07:00 a 19:00hrs");
        }
    }


}
