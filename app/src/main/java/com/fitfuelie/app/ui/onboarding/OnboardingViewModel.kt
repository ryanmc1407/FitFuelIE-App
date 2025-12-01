package com.fitfuelie.app.ui.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fitfuelie.app.data.model.FitnessGoal
import com.fitfuelie.app.data.model.TrainingFrequency
import com.fitfuelie.app.data.model.DietaryPreference
import com.fitfuelie.app.data.model.UserProfile
import com.fitfuelie.app.data.repository.UserProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val userProfileRepository: UserProfileRepository
) : ViewModel() {

    // Onboarding data state
    var selectedGoal: FitnessGoal? = null
    var selectedTrainingFrequency: TrainingFrequency? = null
    var selectedDietaryPreferences: Set<DietaryPreference> = emptySet()

    fun saveOnboardingData() {
        viewModelScope.launch {
            val profile = UserProfile(
                goal = selectedGoal,
                trainingFrequency = selectedTrainingFrequency,
                dietaryPreferences = selectedDietaryPreferences,
                onboardingCompleted = true
            )
            userProfileRepository.saveUserProfile(profile)
        }
    }

    fun isOnboardingComplete(): Flow<Boolean> {
        return userProfileRepository.getUserProfile().map { profile ->
            profile?.onboardingCompleted == true
        }
    }
}
