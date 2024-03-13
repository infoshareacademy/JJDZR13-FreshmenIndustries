package pl.isa.freshmenindustries.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {
    private String message;
    private Boolean isSuccess;
    private List<?> data;

    public Response() {
    }

    public Response(String message, Boolean isSuccess) {
        this.message = message;
        this.isSuccess = isSuccess;
    }

    public Response(String message, Boolean isSuccess, List<?> data) {
        this.message = message;
        this.isSuccess = isSuccess;
        this.data = data;
    }

    public Response(Boolean isSuccess, List<?> data) {
        this.isSuccess = isSuccess;
        this.data = data;
    }
}
