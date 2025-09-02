package com.drones.util;

import com.drones.errors.AboveMaxWeightLimitException;
import com.drones.errors.InvalidInputParameterException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.security.SecureRandom;
import java.util.stream.Collectors;

import static com.drones.util.AppConstants.*;
import static java.lang.String.format;


@Slf4j
public class ValidationUtil {
    public static void validateCode(String code) {
        log.info("Validating code = {}", code);
        String pattern = ("[A-Z0-9_]*$");
        final boolean isValid = !StringUtils.isEmpty(code) && code.matches(pattern);
        if (!isValid){
            throw new InvalidInputParameterException(
                    format(IN_VALID_CODE, code)
            );
        }
    }
    public static void validateName(String name) {
        log.info("Validating name = {}", name);
        String pattern = ("[-a-zA-Z0-9_]*$");
        final boolean isValid = !StringUtils.isEmpty(name) && name.matches(pattern);
        if (!isValid){
            throw new InvalidInputParameterException(
                    format(IN_VALID_NAME, name)
            );
        }
    }
    public static void validateWeightLimit(Integer weightLimit){
        log.info("Validating weightLimit = {}", weightLimit);
        if (weightLimit > 500){
            throw new AboveMaxWeightLimitException(
                    format(ABOVE_MAX_WEIGHT_LIMIT, weightLimit)
            );
        }
    }
    public static String generateSerialNumber(){
        return new SecureRandom()
                .ints(0, 100)
                .mapToObj(value -> Integer.toString(value, 100))
                .distinct().limit(100)
                .collect(Collectors.joining())
                .replaceAll("([a-zA-Z0-9]{4})", "$1-")
                .substring(0, 100);
    }
}
