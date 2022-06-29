package hellojpa;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity // 이 애노테이션이 있어야 JPA가 관리할 객체임을 인식한다.
@Table//(name = "MBR")
public class Member {

    @Id // PK
    private Long id;
    private String name;

    public Member() { } // 기본 생성자 필수

    public Member(Long id, String name) {
        this.id = id;
        this.name = name;
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
