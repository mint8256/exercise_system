package com.five.util;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TokenInfo {

    private Long userId;

    private Integer role;

    private String realName;
    private String userIdentifier;

    public static class Builder {

        TokenInfo tokenInfo = new TokenInfo();

        public Builder role(Integer role) {
            tokenInfo.setRole(role);
            return this;
        }

        public Builder userId(Long userId) {
            tokenInfo.setUserId(userId);
            return this;
        }

        public Builder realName(String realName) {
            tokenInfo.setRealName(realName);
            return this;
        }

        public Builder userIdentifier(String userIdentifier) {
            tokenInfo.setUserIdentifier(userIdentifier);
            return this;
        }

        public TokenInfo build() {
            return tokenInfo;
        }
    }

}
