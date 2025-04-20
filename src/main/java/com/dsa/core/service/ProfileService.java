package com.dsa.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsa.core.dto.ProfileRequest;
import com.dsa.core.model.Profile;
import com.dsa.core.model.User;
import com.dsa.core.repository.ProfileRepository;
import com.dsa.core.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProfileService implements ProfileServiceInterface {
    
    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;
	
	@Autowired
    private UserServiceInterface userService;

    public void saveProfile(Profile profile) {
        profileRepository.save(profile);
    }

    public void createProfile(Long userId, ProfileRequest request) {
        User user = userService.getUserById(userId);
        if (user == null) {
            throw new IllegalArgumentException("User not found with ID: " + userId);
        }
        if (profileRepository.findByUserId(userId).isPresent()) {
            throw new IllegalStateException("Profile already exists for user: " + userId);
        }
        Profile newProfile = new Profile(userId); // Use the constructor that sets userId
        updateProfileFromRequest(newProfile, request);
        profileRepository.save(newProfile);
    }

    public Profile getProfileByUserId(Long userId) {
        return profileRepository.findByUserId(userId).orElse(null);
    }

    @Transactional
    public void updateProfile(Long userId, ProfileRequest request) {
        if (!userRepository.existsById(userId)) {
            throw new IllegalArgumentException("User not found with ID: " + userId);
        }
        Profile profile = profileRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalStateException("Profile not found for user: " + userId));
        updateProfileFromRequest(profile, request);
        profileRepository.save(profile);
    }

    private void updateProfileFromRequest(Profile profile, ProfileRequest request) {
        profile.setFirstName(request.getFirstName());
        profile.setLastName(request.getLastName());
        profile.setDateOfBirth(request.getDateOfBirth());
        profile.setBio(request.getBio());
        // The updatedAt field will be automatically handled by the @PreUpdate annotation
    }
}
