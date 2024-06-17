package med.voll.api.infra.exceptions;

public class IntegrityValidation extends RuntimeException
{

    public IntegrityValidation(String message)
    {
        super(message);
    }
}
