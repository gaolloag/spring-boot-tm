package com.ym.ms.utils;

import java.math.BigDecimal;

public class MonetaryUnit {

    private final static BigDecimal hundred = new BigDecimal(100);
    /**
     * 货币单位元转分
     * @param yuan 货币单元
     * */
    public static BigDecimal cent(BigDecimal yuan){
        if(yuan == null){
            return yuan;
        }
        return yuan.multiply(hundred).setScale(2,BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 货币单位分转元
     * @param cent 货币单元
     * */
    public static BigDecimal yuan(BigDecimal cent){
        if(cent == null){
            return null;
        }
        return cent.divide(hundred, 2, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 货币单位分转元
     * @param cent 货币单元
     * */
    public static BigDecimal yuan(Integer cent){
        if(cent == null){
            return null;
        }
        BigDecimal centNew = new BigDecimal(String.valueOf(cent));
        return centNew.divide(hundred, 2, BigDecimal.ROUND_HALF_UP);
    }

    public static void main(String[] args) {
        BigDecimal hundred = new BigDecimal("1045.465");
        BigDecimal cent = MonetaryUnit.cent(hundred);
        System.out.println("分：" + cent);
        BigDecimal yuan = MonetaryUnit.yuan(cent);
        System.out.println("元：" + yuan);
        yuan = MonetaryUnit.yuan(123456);
        System.out.println("元：" + yuan);
        yuan = MonetaryUnit.yuan(0);
        System.out.println("元：" + yuan);
    }
}
