package hellojpa;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity // 이 애노테이션이 있어야 JPA가 관리할 객체임을 인식한다.
public class Member {

    @Id // PK
    private Long id;
    private String name;

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
