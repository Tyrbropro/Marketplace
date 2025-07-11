package com.tyrbropro.user_service.service;

import com.tyrbropro.user_service.dto.security.JwtAuthenticationDto;
import com.tyrbropro.user_service.dto.security.RefreshTokenDto;
import com.tyrbropro.user_service.dto.security.UserCredentialsDto;
import com.tyrbropro.user_service.dto.user.UserRequestDto;
import com.tyrbropro.user_service.dto.user.UserResponseDto;
import com.tyrbropro.user_service.entity.User;
import com.tyrbropro.user_service.exception.UserNotFoundException;
import com.tyrbropro.user_service.mapper.UserMapper;
import com.tyrbropro.user_service.mapper.UserRedisMapper;
import com.tyrbropro.user_service.repository.redis.UserRedisRepository;
import com.tyrbropro.user_service.repository.jdbc.UserRepository;
import com.tyrbropro.user_service.security.jwt.JwtService;
import javax.security.sasl.AuthenticationException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    UserRedisRepository userRedisRepository;
    UserRedisMapper userRedisMapper;
    UserMapper userMapper;
    JwtService jwtService;
    PasswordEncoder passwordEncoder;

    @Override
    public JwtAuthenticationDto signIn(UserCredentialsDto userCredentialsDto) throws AuthenticationException {
        var user = findByCredentials(userCredentialsDto);
        return this.jwtService.generateAuthToken(user.getEmail());
    }

    @Override
    public JwtAuthenticationDto refreshToken(RefreshTokenDto refreshTokenDto) throws Exception {
        var refreshToken = refreshTokenDto.getRefreshToken();

        if (refreshToken != null && jwtService.validateJwtToken(refreshToken)) {
            var user = findByEmail(this.jwtService.getEmailFromToken(refreshToken));
            return this.jwtService.refreshBaseToken(user.getEmail(), refreshToken);
        }

        throw new AuthenticationException("Invalid refresh token");
    }

    @Override
    public UserResponseDto addUser(UserRequestDto dto) {
        var user = this.userMapper.toEntity(dto);
        var saved = this.userRepository.save(user);

        this.userRedisRepository.save(userRedisMapper.toEntity(saved));
        return userMapper.toDto(saved);
    }

    @Override
    public UserResponseDto getUserById(Long id) {
        return this.userRedisRepository.findById(id).map(userRedisMapper::toDto)
                .orElseGet(() -> this.userRepository.findById(id)
                        .map(entity -> {
                            var userResponseDto = userMapper.toDto(entity);
                            this.userRedisRepository.save(userRedisMapper.toEntity(userResponseDto));
                            return userResponseDto;
                        }).orElseThrow(() -> new UserNotFoundException("User not found")));
    }

    @Override
    public void updateUser(Long id, UserRequestDto details) {
        var user = this.userRedisRepository
                .findById(id).map(userMapper::toEntity)
                .orElseGet(() -> this.userRepository.findById(id)
                        .orElseThrow(() -> new UserNotFoundException("User not found")));

        this.userMapper.updateEntity(user, details);

        this.userRepository.save(user);
        this.userRedisRepository.save(userRedisMapper.toEntity(user));
    }

    private User findByCredentials(UserCredentialsDto userCredentialsDTO) throws AuthenticationException {
        var optionalUser = this.userRepository.findByEmail(userCredentialsDTO.getEmail());
        if (optionalUser.isPresent()) {
            var user = optionalUser.get();
            if (passwordEncoder.matches(userCredentialsDTO.getPassword(), user.getPassword())) {
                return user;
            }
        }
        throw new AuthenticationException("Email or password is not correct");
    }

    private User findByEmail(String email) {
        return this.userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException(
                        String.format("User with email %s not found", email)
                ));
    }
}
