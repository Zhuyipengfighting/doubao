package com.doubao.common.exception.user;

/**
 * 黑名单IP异常类
 *
 * @author doubao
 */
public class BlackListException extends UserException
{
    private static final long serialVersionUID = 1L;

    public BlackListException()
    {
        super("login.blocked", null);
    }
}
