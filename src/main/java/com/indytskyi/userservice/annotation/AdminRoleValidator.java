package com.indytskyi.userservice.annotation;

import static com.indytskyi.userservice.model.enums.Role.ADMIN;

import com.indytskyi.userservice.exception.LimitedPermissionException;
import com.indytskyi.userservice.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class AdminRoleValidator {

    private final AuthenticationService authenticationService;

    @Around("@annotation(com.indytskyi.userservice.annotation.IsAdmin)")
    @Transactional
    public Object validateIfUserIsAdmin(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("check if role of user is ADMIN");
        var bearerToken = (String) joinPoint.getArgs()[joinPoint.getArgs().length - 1];
        var user = authenticationService.validateToken(bearerToken);
        if (ADMIN != user.getRole()) {
            log.error("User don`t have access to this request");
            throw new LimitedPermissionException("User don`t have access to this request");
        }
        return joinPoint.proceed();
    }
}
