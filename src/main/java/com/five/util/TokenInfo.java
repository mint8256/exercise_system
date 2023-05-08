package com.five.util;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TokenInfo {

    private Integer userId;

    private Integer roleId;

    private String realName;


    enum RoleEnum{

        STUDENT(0),
        DOMITORY_ADMIN(1),
        ADMIN(2);

        private Integer roleId;

        RoleEnum(){}

        RoleEnum(Integer roleId){
            this.roleId = roleId;
        }

        public Integer getRoleId(){
            return this.roleId;
        }

    }

    public static class Builder{

        TokenInfo tokenInfo = new TokenInfo();

        public Builder role(Integer roleId){
            tokenInfo.setRoleId(roleId);
            return this;
        }

        public Builder userId(Integer userId){
            tokenInfo.setUserId(userId);
            return this;
        }

        public Builder username(String realName){
            tokenInfo.setRealName(realName);
            return this;
        }

        public TokenInfo build(){
            return tokenInfo;
        }
    }

}
