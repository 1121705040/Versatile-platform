package com.lxzn.userservce.utils;

/**
 * 使用规则校验的工具栏
 */
public class RuleValidationUtils {
    /**
     * 必须是6-10位字母、数字、下划线（这里字母、数字、下划线是指任意组合，没有必须三类均包含）
     * 不能以数字开头
     * @param name
     * @return
     */
    public static boolean checkAccount(String name) {
        String regExp = "^[^0-9][\\w_]{6,18}$";
        if(name.matches(regExp)) {
            return true;
        }else {
            return false;
        }
    }

    /**
     * 必须是6-20位的字母、数字、下划线（这里字母、数字、下划线是指任意组合，没有必须三类均包含）
     * @param pwd
     * @return
     */
    public static boolean checkPwd(String pwd) {
        String regExp = "^[\\w_]{6,18}$";
        if(pwd.matches(regExp)) {
            return true;
        }
        return false;
    }
}
