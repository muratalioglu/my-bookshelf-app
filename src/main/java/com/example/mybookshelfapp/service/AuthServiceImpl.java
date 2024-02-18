package com.example.mybookshelfapp.service;

import com.example.mybookshelfapp.dto.AuthInDTO;
import com.example.mybookshelfapp.dto.RegisterInDTO;
import com.example.mybookshelfapp.entity.Role;
import com.example.mybookshelfapp.entity.Member;
import com.example.mybookshelfapp.repository.MemberRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {

    final BCryptPasswordEncoder bCryptPasswordEncoder;
    final JwtEncoder jwtEncoder;

    final RoleRepository roleRepository;
    final MemberRepository memberRepository;

    final MemberService memberService;

    public AuthServiceImpl(BCryptPasswordEncoder bCryptPasswordEncoder,
                           JwtEncoder jwtEncoder,
                           RoleRepository roleRepository,
                           MemberRepository memberRepository,
                           MemberService memberService) {

        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jwtEncoder = jwtEncoder;
        this.roleRepository = roleRepository;
        this.memberRepository = memberRepository;
        this.memberService = memberService;
    }

    @Override
    public Integer register(RegisterInDTO dto) {

        Optional<Member> memberOptional = memberRepository.findFirstByEmail(dto.getEmail());
        if (memberOptional.isPresent())
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    String.format("Member with email %s already exists!", dto.getEmail())
            );

        Member member = new Member();
        member.setEmail(dto.getEmail());
        member.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
        member.setRegistrationTime(new Timestamp(System.currentTimeMillis()));

        memberRepository.save(member);

        Role role = new Role();
        role.setMemberId(member.getId());
        role.setRole("user");

        roleRepository.save(role);

        return member.getId();
    }

    @Override
    public String login(AuthInDTO dto) {

        String email = dto.getEmail();
        String password = dto.getPassword();

        Member member = memberService.getMember(email);
        if (member == null)
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format("Member with email %s not found", email)
            );

        if (!bCryptPasswordEncoder.matches(password, member.getPassword()))
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "Bad credentials"
            );

        Instant now = Instant.now();

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plus(10, ChronoUnit.MINUTES))
                .subject(member.getId().toString())
                .claim("roles",
                        member.getRoles().stream().map(Role::getRole).collect(Collectors.toSet()))
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}