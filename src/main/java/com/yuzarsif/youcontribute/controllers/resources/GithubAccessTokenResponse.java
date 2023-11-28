package com.yuzarsif.youcontribute.controllers.resources;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GithubAccessTokenResponse {

    private String accessToken;
}
