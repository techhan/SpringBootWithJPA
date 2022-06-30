package hellojpa;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;

@Entity // 이 애노테이션이 있어야 JPA가 관리할 객체임을 인식한다.
@SequenceGenerator(
        name = "MEMBER_SEQ_GENERATOR",
        sequenceName = "MEMBER_SEQ",
        initialValue = 1, allocationSize = 50)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "MEMBER_SEQ_GENERATOR")
    private Long id;

    @Column(name = "name")
    private String username;

//    private Integer age;
//
//    @Enumerated(EnumType.STRING) // @Enumerated DB에는 EnumType이 존재하지 않는 문제를 해결한다.
//    private RoleType roleType;
//
//    @Temporal(TemporalType.TIMESTAMP) // TemporalType : 날짜 타입.
//    private Date createdDate;
//
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date lastModifiedDate;
//
//    private LocalDate testLocalDate;
//    private LocalDateTime testLocalDateTime;
//
//    @Transient
//    private Integer temp;
//
//    @Lob
//    private String description; // Lob : varchar를 넘어서는 데이터가 있을 경우 이 어노테이션을 사용하면 된다.

    public Member() { } // 기본 생성자 필수

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
}
