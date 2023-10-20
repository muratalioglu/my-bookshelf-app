package com.example.mybookshelfapi;

import com.example.mybookshelfapi.config.RsaKeyProperties;
import com.example.mybookshelfapi.entity.Authority;
import com.example.mybookshelfapi.entity.Member;
import com.example.mybookshelfapi.repository.MemberRepository;
import com.example.mybookshelfapi.service.AuthorityRepository;
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
public class MyBookshelfApiApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(MyBookshelfApiApplication.class, args);

		AuthorityRepository authorityRepository = context.getBean(AuthorityRepository.class);
		MemberRepository memberRepository = context.getBean(MemberRepository.class);
		BCryptPasswordEncoder bCryptPasswordEncoder = context.getBean(BCryptPasswordEncoder.class);

		Member member = new Member();
		member.setEmail("admin@mybookshelf");
		member.setFirstName("Murat Deniz");
		member.setLastName("Alioglu");
		member.setRegistrationTime(Timestamp.valueOf(LocalDateTime.of(2023, Month.JANUARY, 1, 0, 0)));
		member.setPassword(bCryptPasswordEncoder.encode("P4ssw0rd"));
		memberRepository.save(member);
		System.out.println("test");
		authorityRepository.saveAll(
				Set.of(
						new Authority(member.getId(), "user"),
						new Authority(member.getId(), "editor")
				)
		);
	}
}