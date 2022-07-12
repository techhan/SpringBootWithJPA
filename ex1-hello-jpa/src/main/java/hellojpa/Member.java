package hellojpa;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity // 이 애노테이션이 있어야 JPA가 관리할 객체임을 인식한다.
//@SequenceGenerator(
//        name = "MEMBER_SEQ_GENERATOR",
//        sequenceName = "MEMBER_SEQ",
//        initialValue = 1, allocationSize = 50)
public class Member  {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;


    // 기간
    @Embedded
    private Period workPeriod;

    // 주소
    @Embedded
    private Address homeAddress;

//    @Embedded
//    @AttributeOverrides({@AttributeOverride(name = "city",
//                        column = @Column(name = "work_city")),
//                        @AttributeOverride(name = "street",
//                        column = @Column(name = "work_street")),
//                        @AttributeOverride(name = "zipcode",
//                        column = @Column(name = "work_zipcode"))})
//    private Address workAddress;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Period getWorkPeriod() {
        return workPeriod;
    }

    public void setWorkPeriod(Period workPeriod) {
        this.workPeriod = workPeriod;
    }

    public Address getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }
}
