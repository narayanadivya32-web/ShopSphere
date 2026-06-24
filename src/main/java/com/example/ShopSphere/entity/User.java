package com.example.ShopSphere.entity;


import com.example.ShopSphere.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.AnyDiscriminatorImplicitValues;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="users")
public class User {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(name="first_name",nullable = false)
   private String firstName;

   @Column(name="last_name",nullable = false)
   private String lastName;

   @Column(name="email",nullable = false)
   private String email;

   @Column(name="password",nullable = false)
   private String password;

   @Enumerated(EnumType.STRING)
   @Column(name="role",nullable = false)
   private Role role;
}
