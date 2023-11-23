package com.yuzarsif.youcontribute.controllers.requests;

import com.yuzarsif.youcontribute.models.IssueChallengeStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateChallengeRequest {

    private IssueChallengeStatus status;
}
