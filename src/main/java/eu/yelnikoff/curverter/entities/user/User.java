package eu.yelnikoff.curverter.entities.user;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Getter
@Setter
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String firstName;

    @Column(nullable=false)
    private String lastName;

    @Column(nullable=false, unique=true)
    private String email;

    @Column(nullable=false)
    private String passwordHash;

    private String companyName;

    private Date lastSignInAt;

    @CreationTimestamp
    @Column(nullable=false)
    private Date createdAt;

}
