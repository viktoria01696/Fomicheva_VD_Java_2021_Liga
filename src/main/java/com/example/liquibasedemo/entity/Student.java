package com.example.liquibasedemo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "student")
@Getter
@Setter
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long id;

    @Size(max = 255)
    @NotNull
    @Column(name = "firstname")
    private String firstname;

    @Size(max = 255)
    @NotNull
    @Column(name = "lastname")
    private String lastname;

    @Size(max = 255)
    @Column(name = "patronymic")
    private String patronymic;

    @NotNull
    @Column(name = "birthday")
    @Temporal(TemporalType.DATE)
    private Date birthday;

    @NotNull
    @Column(name = "age")
    private Integer age;

    @NotNull
    @Column(name = "sex")
    private Integer sex;

    @ManyToOne
    @JoinColumn(name = "school_id")
    @NotNull
    private School school;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Post> postList;

    @OneToMany(mappedBy = "student")
    private List<Message> messageList;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "student_chat",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "chat_id"))
    private List<Chat> chatList;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "friendship",
            joinColumns = @JoinColumn(name = "first_member_id", referencedColumnName = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "second_member_id", referencedColumnName = "student_id"))
    private List<Student> friendList;

    @OneToMany(mappedBy = "student")
    private List<Chat> chatListAsAdmin;

    @Size(max = 255)
    @Email
    @Column(name = "email")
    private String email;

    @NotNull
    @Column(name = "create_date")
    @Temporal(TemporalType.DATE)
    private Date createDate;

    @Column(name = "is_active")
    private Boolean isActive;

    public Student(String firstname, String lastname,
                   Date birthday, Integer age, Integer sex, School school) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthday = birthday;
        this.age = age;
        this.sex = sex;
        this.school = school;
        this.createDate = Calendar.getInstance().getTime();
        this.isActive = false;
    }
}
