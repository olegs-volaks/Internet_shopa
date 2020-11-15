package application.responses;
import java.util.List;

public class ShowProductByIDResponse  extends CoreResponse{

    private final Long getById;

    public ShowProductByIDResponse(Long getById) {
        this.getById = getById;
    }

    public ShowProductByIDResponse(List<CoreError> errors, Long getById) {
        super(errors);
        this.getById = getById;
    }

    public Long getById() {
        return getById;
    }
}
