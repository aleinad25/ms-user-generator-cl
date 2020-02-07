package com.globallogic.demo.app.msusergenerator.repository.entity;


import javax.persistence.*;

@Entity
@Table(name = "TB_PHONE")
public class PhoneEntity {

    private String idPhone;
    private UserEntity userEntity;
    private String number;
    private String cityCode;
    private String countryCode;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public String getIdPhone() {
        return idPhone;
    }

    public void setIdPhone(String idPhone) {
        this.idPhone = idPhone;
    }
    @ManyToOne
    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    @Column(name="number")
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
    @Column(name="cityCode")
    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }
    @Column(name="countryCode")
    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}
