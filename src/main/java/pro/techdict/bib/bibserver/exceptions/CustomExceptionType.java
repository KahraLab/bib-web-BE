package pro.techdict.bib.bibserver.exceptions;

public enum CustomExceptionType {
    USER_SIDE_ERROR(400, "您的操作有误，客户端运行异常！"),
    SERVER_SIDE_ERROR(500, "系统出现异常，请稍后再试或联系开发团队！"),
    OTHER_ERROR(999, "系统出现未知异常，请联系开发团队！"),
    AUTHENTICATE_FAILED_ERROR(400, "登录验证执行失败！"),
    USER_NOT_FOUND_ERROR(400, "您所登录的用户不存在！"),
    UNAUTHORIZED_ERROR(401, "您还没有登录，无法访问该接口！"),
    TOKEN_EXPIRED_ERROR(401, "您的令牌已经过期，请重新登录！"),
    FORBIDDEN_ERROR(403, "您没有足够的权限进行该操作！"),
    TENCENT_CLOUD_SDK_ERROR(909, "后端腾讯云服务出错，请联系开发团队！")
    ;

    CustomExceptionType(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private final String desc; // 异常类型描述
    private final int code;    // 异常状态代码

    public String getDesc() {
        return desc;
    }

    public int getCode() {
        return code;
    }
}
