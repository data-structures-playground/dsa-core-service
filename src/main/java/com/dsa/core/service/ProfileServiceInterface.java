package com.dsa.core.service;

import com.dsa.core.dto.ProfileRequest;
import com.dsa.core.model.Profile;

public interface ProfileServiceInterface {
    void saveProfile(Profile profile);
    void createProfile(Long userId, ProfileRequest request);
    Profile getProfileByUserId(Long userId);
    void updateProfile(Long userId, ProfileRequest request);
}
