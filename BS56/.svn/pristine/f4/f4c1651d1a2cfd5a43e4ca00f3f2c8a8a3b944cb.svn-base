/*
 * Copyright (c) 2016, All rights reserved.
 */
package com.ztel.framework.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Number utility
 * @author Zeal,dandan.bai,lzy,jiyu.hou
 * @since 2016年4月26日
 */
public class NumberUtils extends org.apache.commons.lang3.math.NumberUtils {
	
	/**
     * 汉语中数字大写
     */
    private static final String[] CN_UPPER_NUMBER = { "零", "壹", "贰", "叁", "肆",
            "伍", "陆", "柒", "捌", "玖" };
    /**
     * 汉语中货币单位大写，这样的设计类似于占位符
     */
    private static final String[] CN_UPPER_MONETRAY_UNIT = { "分", "角", "元",
            "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿", "拾", "佰", "仟", "兆", "拾",
            "佰", "仟" };
    /**
     * 特殊字符：整
     */
    private static final String CN_FULL = "整";
    /**
     * 特殊字符：负
     */
    private static final String CN_NEGATIVE = "负";
    /**
     * 金额的精度，默认值为2
     */
    private static final int MONEY_PRECISION = 2;
    /**
     * 特殊字符：零元整
     */
    private static final String CN_ZEOR_FULL = "零元" + CN_FULL;

    /**
     * 把输入的金额转换为汉语中人民币的大写
     * 
     * @param numberOfMoney
     *            输入的金额
     * @return 对应的汉语大写
     */
    public static String number2CNMontrayUnit(BigDecimal numberOfMoney) {
        StringBuffer sb = new StringBuffer();
        // -1, 0, or 1 as the value of this BigDecimal is negative, zero, or
        // positive.
        int signum = numberOfMoney.signum();
        // 零元整的情况
        if (signum == 0) {
            return CN_ZEOR_FULL;
        }
        //这里会进行金额的四舍五入
        long number = numberOfMoney.movePointRight(MONEY_PRECISION)
                .setScale(0, 4).abs().longValue();
        // 得到小数点后两位值
        long scale = number % 100;
        int numUnit = 0;
        int numIndex = 0;
        boolean getZero = false;
        // 判断最后两位数，一共有四中情况：00 = 0, 01 = 1, 10, 11
        if (!(scale > 0)) {
            numIndex = 2;
            number = number / 100;
            getZero = true;
        }
        if ((scale > 0) && (!(scale % 10 > 0))) {
            numIndex = 1;
            number = number / 10;
            getZero = true;
        }
        int zeroSize = 0;
        while (true) {
            if (number <= 0) {
                break;
            }
            // 每次获取到最后一个数
            numUnit = (int) (number % 10);
            if (numUnit > 0) {
                if ((numIndex == 9) && (zeroSize >= 3)) {
                    sb.insert(0, CN_UPPER_MONETRAY_UNIT[6]);
                }
                if ((numIndex == 13) && (zeroSize >= 3)) {
                    sb.insert(0, CN_UPPER_MONETRAY_UNIT[10]);
                }
                sb.insert(0, CN_UPPER_MONETRAY_UNIT[numIndex]);
                sb.insert(0, CN_UPPER_NUMBER[numUnit]);
                getZero = false;
                zeroSize = 0;
            } else {
                ++zeroSize;
                if (!(getZero)) {
                    sb.insert(0, CN_UPPER_NUMBER[numUnit]);
                }
                if (numIndex == 2) {
                    if (number > 0) {
                        sb.insert(0, CN_UPPER_MONETRAY_UNIT[numIndex]);
                    }
                } else if (((numIndex - 2) % 4 == 0) && (number % 1000 > 0)) {
                    sb.insert(0, CN_UPPER_MONETRAY_UNIT[numIndex]);
                }
                getZero = true;
            }
            // 让number每次都去掉最后一个数
            number = number / 10;
            ++numIndex;
        }
        // 如果signum == -1，则说明输入的数字为负数，就在最前面追加特殊字符：负
        if (signum == -1) {
            sb.insert(0, CN_NEGATIVE);
        }
        // 输入的数字小数点后两位为"00"的情况，则要在最后追加特殊字符：整
        if (!(scale > 0)) {
            sb.append(CN_FULL);
        }
        return sb.toString();
    }
	
	
	/**
	 * Covert string array to int array, will throw NumberFormatException
	 * @param strArray
	 * @return
	 */
	public static int[] toIntArray(String[] strArray) {
		if (strArray == null || strArray.length <= 0) {
			return new int[] {};
		}
		int[] intArray = new int[strArray.length];
		for (int i = 0; i < strArray.length; ++i) {
			intArray[i] = Integer.parseInt(strArray[i]);
		}
		return intArray;
	}
	
	/**
	 * Covert string array to integer list, will throw NumberFormatException
	 * @param strArray
	 * @return
	 */
	public static List<Integer> toIntList(String[] strArray) {
		if (strArray == null || strArray.length <= 0) {
			return new ArrayList<>(0);
		}
		List<Integer> intArray = new ArrayList<>(strArray.length);
		for (int i = 0; i < strArray.length; ++i) {
			intArray.add(Integer.parseInt(strArray[i]));
		}
		return intArray;
	}
	
	/**
	 * @param strArray
	 * @return
	 */
	public static double[] toDoubleArray(String[] strArray) {
		if (strArray == null || strArray.length <= 0) {
			return new double[]{};
		}
		double[] doubleArray = new double[strArray.length];
		for (int i = 0; i < strArray.length; ++i) {
			doubleArray[i] = Double.parseDouble(strArray[i]);
		}
		return doubleArray;
	}
	
	/**
	 * @param strArray
	 * @return
	 */
	public static double[] toDoubleArray(String[] strArray, double defaultValue) {
		if (strArray == null || strArray.length <= 0) {
			return new double[]{};
		}
		double[] doubleArray = new double[strArray.length];
		for (int i = 0; i < strArray.length; ++i) {
			doubleArray[i] = toDouble(strArray[i], defaultValue);
		}
		return doubleArray;
	}
	
	/**
	 * Check whether two numbers are equal
	 * @param num1
	 * @param num2
	 * @return
	 */
	public static boolean equals(Number num1, Number num2) {
		if (num1 == null || num2 == null) {
			return num1 == num2;
		}
		if (num1.getClass() != num2.getClass()) {
			return false;
		}
		else {
		    return num1.equals(num2);
		}
	}
	
//	/**
//	 * 两个Integer类型相等比较
//	 * @return
//	 * @deprecated Use equals(Number,Number) instead
//	 */
//	public static boolean cmpIntegers(Integer obj1, Integer obj2){
//		
//		if(obj1 == null)
//		{
//			if(obj2 == null)
//				return true;
//			else
//				return false;
//		}
//		
//		return obj1.equals(obj2);	
//	}
//	
//	/**
//	 * 两个Long类型相等比较
//	 * @return
//	 *  @deprecated Use equals(Number,Number) instead
//	 */
//	public static boolean cmpLongs(Long obj1, Long obj2){
//		
//		if(obj1 == null)
//		{
//			if(obj2 == null)
//				return true;
//			else
//				return false;
//		}
//		return obj1.equals(obj2);
//	}
	
	//---------------------------------------------------------------------
	//Merged from DoubleConvertUtil remove some methods with logics of weight,price and etc
	
	
	/**
	 * 对double数据进行取精度.
	 * 
	 * @param value
	 *            double数据.
	 * @param scale
	 *            精度位数(保留的小数位数).
	 * @param roundingMode
	 *            精度取值方式.
	 * @return 精度计算后的数据.
	 */
	public static double round(double value, int scale, int roundingMode) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(scale, roundingMode);
		return bd.doubleValue();
	}
	
	public static double round(BigDecimal value, int scale, int roundingMode) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		value = value.setScale(scale, roundingMode);
		return value.doubleValue();
	}
	
	public static double round(BigDecimal value, int scale) {
		return round(value, scale, BigDecimal.ROUND_HALF_UP);
	}
	
	

	/**
	 * @Title: convertToString
	 * @Description: 按指定格式转换成字符串
	 * @param d
	 * @param format
	 * @return String
	 * @author jiyu.hou
	 * @date 2015年8月6日 下午11:20:15
	 */
	public static String convertToString(double d, String format) {
		DecimalFormat df = new DecimalFormat(format);
		String s = df.format(d);
		return s;
	}

	/**
	 * 提供精确的小数位四舍五入处理。
	 * 
	 * @param v
	 *            需要四舍五入的数字
	 * @param scale
	 *            小数点后保留几位
	 * @return 四舍五入后的结果
	 */
	public static double round(double value, int scale) {
		return round(value, scale, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * 提供精确的乘法运算。
	 * 
	 * @param v1
	 * @param v2
	 * @param scale 小数点后保留几位  1和0时均会保留一位小数,如果需要整数，调用完后自己再强转
	 * @return 四舍五入后的结果
	 */
	public static double multiply(double v1, double v2, int scale) {
		return multiply(v1, v2, scale, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * 
	 * @description 带RoundingMode的乘法计算
	 * @author linwei.yu
	 * @date 2015年8月18日
	 * @param v1
	 * @param v2
	 * @param scale
	 *            精度
	 * @param roundingMode
	 * @return
	 */
	public static double multiply(double v1, double v2, int scale, int roundingMode) {
		return round(new BigDecimal(v1).multiply(new BigDecimal(v2)), scale, roundingMode);
	}

	/**
	 * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。舍入模式采用ROUND_HALF_EVEN
	 * 
	 * @param v1
	 * @param v2
	 * @param scale
	 *            表示需要精确到小数点以后几位。
	 * @return 两个参数的商
	 */

	public static double divide(double v1, double v2, int scale) {
		return divide(v1, v2, scale, BigDecimal.ROUND_HALF_EVEN);
	}

	/**
	 * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度。舍入模式采用用户指定舍入模式
	 * 
	 * @param v1
	 * @param v2
	 * @param scale
	 *            表示需要精确到小数点以后几位
	 * @param round_mode
	 *            表示用户指定的舍入模式
	 * @return 两个参数的商
	 */

	public static double divide(double v1, double v2, int scale, int round_mode) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		return new BigDecimal(v1).divide(new BigDecimal(v2), scale, round_mode).doubleValue();
	}

	/**
	 * 两个Double数相加
	 * 
	 * @param v1
	 * @param v2
	 * @return Double
	 */
	public static double add(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return b1.add(b2).doubleValue();
	}

	/**
	 * 两个Double数相减
	 * 
	 * @param v1
	 * @param v2
	 * @return Double
	 */
	public static double sub(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return b1.subtract(b2).doubleValue();
	}	
	
	public static void main(String[] args) throws Exception {
//		Double d1 = new Double(2.0);
//		Double d2 = new Double(1.999d);
//		System.out.println(equals(d1, d2));
//		System.out.println(String.valueOf(round(1.9999999, 5, BigDecimal.ROUND_HALF_UP)));
		double abc = 3200.32d;
		double cd = round(abc*100, 0);
		//Long f = new Long(cd + "");
		long f = (long) cd;
		
		System.out.println(f);
		System.out.println(cd);
		

		
	}
	
}
