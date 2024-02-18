package com.example.mybookshelfapp;

import com.example.mybookshelfapp.config.RsaKeyProperties;
import com.example.mybookshelfapp.entity.Role;
import com.example.mybookshelfapp.entity.Member;
import com.example.mybookshelfapp.repository.MemberRepository;
import com.example.mybookshelfapp.service.RoleRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Set;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
public class MyBookshelfAppApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(MyBookshelfAppApplication.class, args);

		RoleRepository roleRepository = context.getBean(RoleRepository.class);
		MemberRepository memberRepository = context.getBean(MemberRepository.class);
		BCryptPasswordEncoder bCryptPasswordEncoder = context.getBean(BCryptPasswordEncoder.class);

		Member member = new Member();
		member.setEmail("admin@mybookshelf");
		member.setFirstName("Murat Deniz");
		member.setLastName("Alioglu");
		member.setRegistrationTime(Timestamp.valueOf(LocalDateTime.of(2023, Month.JANUARY, 1, 0, 0)));
		member.setPassword(bCryptPasswordEncoder.encode("P4ssw0rd"));
		memberRepository.save(member);

		roleRepository.saveAll(
				Set.of(
						new Role(member.getId(), "user"),
						new Role(member.getId(), "editor")
				)
		);
	}
}