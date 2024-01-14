package hello.gdsc.data.dto;

public class ChangeProductNameDto {
    private Long number;
    private String name;

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ChangeProductNameDto(Long number, String name){
        this.name = name;
        this.number = number;
    }
}
