package relation_test_code;

import javax.persistence.Embeddable;

@Embeddable
//기본생성자가 필수임.( 생성자를 별도로 만들경우 기본생성자 추가해야함.)
public class Address {

    private String city;
    private String Street;
    private String zipcode;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String street) {
        Street = street;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
}
