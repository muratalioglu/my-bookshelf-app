package com.example.mybookshelfapi.service;

import com.example.mybookshelfapi.dto.AuthInDTO;
import com.example.mybookshelfapi.dto.RegisterInDTO;
import com.example.mybookshelfapi.entity.Authority;
import com.example.mybookshelfapi.entity.Member;
import com.example.mybookshelfapi.repository.MemberRepository;
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

    final AuthorityRepository authorityRepository;
    final MemberRepository memberRepository;

    final MemberService memberService;

    public AuthServiceImpl(BCryptPasswordEncoder bCryptPasswordEncoder,
                           JwtEncoder jwtEncoder,
                           AuthorityRepository authorityRepository,
                           MemberRepository memberRepository,
                           MemberService memberService) {

        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jwtEncoder = jwtEncoder;
        this.authorityRepository = authorityRepository;
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

        Authority authority = new Authority();
        authority.setMemberId(member.getId());
        authority.setAuthority("user");

        authorityRepository.save(authority);

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
                    "Member with email %s not found"
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
                .claim("scope",
                        member.getAuthorities().stream().map(Authority::getAuthority).collect(Collectors.toSet()))
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}