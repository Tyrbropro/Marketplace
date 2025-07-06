package com.tyrbropro.User_service.service;

import com.tyrbropro.User_service.dto.security.JwtAuthenticationDTO;
import com.tyrbropro.User_service.dto.security.RefreshTokenDTO;
import com.tyrbropro.User_service.dto.security.UserCredentialsDTO;
import com.tyrbropro.User_service.dto.user.UserRequestDTO;
import com.tyrbropro.User_service.dto.user.UserResponseDTO;
import com.tyrbropro.User_service.entity.User;
import com.tyrbropro.User_service.mapper.UserMapper;
import com.tyrbropro.User_service.mapper.UserRedisMapper;
import com.tyrbropro.User_service.repository.UserRedisRepository;
import com.tyrbropro.User_service.repository.UserRepository;
import com.tyrbropro.User_service.security.jwt.JwtService;
import java.util.Optional;
import javax.security.sasl.AuthenticationException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserRedisRepository userRedisRepository;
    private final UserRedisMapper userRedisMapper;
    private final UserMapper userMapper;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, UserRedisRepository userRedisRepository, UserRedisMapper userRedisMapper, UserMapper userMapper, JwtService jwtService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userRedisRepository = userRedisRepository;
        this.userRedisMapper = userRedisMapper;
        this.userMapper = userMapper;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public JwtAuthenticationDTO signIn(UserCredentialsDTO userCredentialsDTO) throws AuthenticationException {
        User user = findByCredentials(userCredentialsDTO);
        return jwtService.generateAuthToken(user.getEmail());
    }

    @Override
    public JwtAuthenticationDTO refreshToken(RefreshTokenDTO refreshTokenDTO) throws Exception {
        String refreshToken = refreshTokenDTO.getRefreshToken();
        if(refreshToken != null && jwtService.validateJwtToken(refreshToken)) {
            User user = findByEmail(jwtService.getEmailFromToken(refreshToken));
            return jwtService.refreshBaseToken(user.getEmail(), refreshToken);
        }
        throw new AuthenticationException("Invalid refresh token");
    }

    @Override
    @Transactional
    public UserResponseDTO addUser(UserRequestDTO dto) {
        User user = userMapper.toEntity(dto);
        User saved = userRepository.save(user);
        userRedisRepository.save(userRedisMapper.toEntity(userMapper.toDto(saved)));
        return userMapper.toDto(saved);
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        return userRedisRepository.findById(id).map(userRedisMapper::toDto)
                .orElseGet(() -> userRepository.findById(id)
                        .map(entity -> {
                            UserResponseDTO dto = userMapper.toDto(entity);
                            userRedisRepository.save(userRedisMapper.toEntity(dto));
                            return dto;
                        }).orElseThrow(() -> new RuntimeException("User not found")));
    }

    @Override
    @Transactional
    public void updateUser(Long id, UserRequestDTO details) throws ChangeSetPersister.NotFoundException {
        User user = userRepository.findById(id).
                orElseThrow(ChangeSetPersister.NotFoundException::new);

        userMapper.updateEntity(user, details);
        userRepository.save(user);
        userRedisRepository.save(userRedisMapper.toEntity(userMapper.toDto(user)));
    }

    private User findByCredentials(UserCredentialsDTO userCredentialsDTO) throws AuthenticationException {
        Optional<User> optionalUser = userRepository.findByEmail(userCredentialsDTO.getEmail());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (passwordEncoder.matches(userCredentialsDTO.getPassword(), user.getPassword())) {
                return user;
            }
        }
        throw new AuthenticationException("Email or password is not correct");
    }

    private User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(()-> new RuntimeException(String.format("User with email %s not found", email)));
    }
}
