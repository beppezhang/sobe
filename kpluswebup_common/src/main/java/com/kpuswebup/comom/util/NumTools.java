package com.kpuswebup.comom.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;


public final class NumTools
{
    private static DecimalFormat defaultFormat = new DecimalFormat();
    private static DecimalFormat decimalFormatComma = new DecimalFormat("#,##0.00");
    private static DecimalFormat decimalFormat = new DecimalFormat("#0.00");
    private static DecimalFormat currencyFormat = (DecimalFormat) NumberFormat.getCurrencyInstance();
    private static DecimalFormat percentFormat = (DecimalFormat) NumberFormat.getPercentInstance();
    private static String percentSuffix = String.valueOf(percentFormat.getDecimalFormatSymbols().getPercent());
    private static String perMillSuffix = String.valueOf(percentFormat.getDecimalFormatSymbols().getPerMill());

    private NumTools()
    {
    }

    static
    {
        // PercentInstance 下述值 默认为 0 例 0.57655 ->58% 现设置小数位数为 2, 例 0.57656 -> 57.66%
        setMaxMinFractionDigits(percentFormat, 2, 2);
    }

    // 设置小数位最大位数与最小位数
    private static void setMaxMinFractionDigits(NumberFormat nf, int max, int min)
    {
        nf.setMaximumFractionDigits(max);
        nf.setMinimumFractionDigits(min);
    }

    // #0.00 格式化数字，并保留两位小数
    public static String formatTwo(Number number)
    {
        return format(number, 2);
    }

    // #0.0000 格式化数字，并保留四位小数
    public static String formatFour(Number number)
    {
        return format(number, 4);
    }

    public static Double formatFour(Double val)
    {
        return format(val, 4);
    }

    public static Double formatTwo(Double val)
    {
        return format(val, 2);
    }

    public static Float formatFour(Float val)
    {
        return format(val, 4);
    }

    public static Float formatTwo(Float val)
    {
        return format(val, 2);
    }

    // #0.00 格式化Number 并保留指定小数位数
    public static String format(Number number, int digit)
    {
        setMaxMinFractionDigits(decimalFormat, digit, digit);
        return decimalFormat.format(number);
    }

    private static Double format(Double val, int digit)
    {
        // new BigDecimal(val).setScale(newScale, BigDecimal.ROUND_HALF_UP).doubleValue()
        return new Double(format((Number) val, digit));
    }

    private static Float format(Float val, int digit)
    {
        // new BigDecimal(val).setScale(newScale, BigDecimal.ROUND_HALF_UP).doubleValue()
        return new Float(format((Number) val, digit));
    }

    // 返回指定格式化 Number e.g #,##0.00
    public static String format(Number number, String pattern)
    {
        defaultFormat.applyPattern(pattern);
        return defaultFormat.format(number);
    }

    // #,##0.00 保留两位小数
    public static String formatCommaTwo(Number number)
    {
        return formatComma(number, 2);
    }

    // #,##0.0000 保留四位小数
    public static String formatCommaFour(Number number)
    {
        return formatComma(number, 4);
    }

    // #,##0.00 保留指定小数位数
    private static String formatComma(Number number, int digit)
    {
        setMaxMinFractionDigits(decimalFormatComma, digit, digit);
        return decimalFormatComma.format(number);
    }

    // ￥99,798,464,668.99 返回金额类型的字符串
    public static String formatCurrency(Number number)
    {
        return currencyFormat.format(number);
    }

    // 解析 金额 字符串为 Number对象
    public static Number parseCurrency(String source) throws Exception
    {
        try
        {
            return currencyFormat.parse(source);
        }
        catch(ParseException e)
        {
            throw new Exception("parseCurrency 报错", e);
        }
    }

    // 0.8828 -> 88.28% 百分比
    public static String formatPercentage(Number number)
    {
        return formatPercent(number, 100, percentSuffix);
    }

    // 0.8828 -> 882.8% 千分比
    public static String formatPermillage(Number number)
    {
        return formatPercent(number, 1000, perMillSuffix);
    }

    // 返回指定比率 100/1000/10000 suffix %/‰
    private static String formatPercent(Number number, int newValue, String suffix)
    {
        setPositiveNegativeSuffix(percentFormat, newValue, suffix);
        return percentFormat.format(number);
    }

    // 解析百分比为 Number 88.88%-->0.8888
    public static Number parsePercentPercentage(String source)
    {
        return parsePercent(source, 100, percentSuffix);
    }

    // 解析千分比为 Number
    public static Number parsePercentPermillage(String source)
    {
        return parsePercent(source, 1000, perMillSuffix);
    }

    // 解析指定 比率 为 Number 100/1000/10000 suffix %/‰
    private static Number parsePercent(String source, int newValue, String suffix)
    {
        try
        {
            setPositiveNegativeSuffix(percentFormat, newValue, suffix);
            return percentFormat.parse(source);
        }
        catch(ParseException e)
        {
            //throw new Exception("parsePercent 报错", e);
            e.printStackTrace();
        }
		return null;
    }

    // 设置 DecimalFormat 的 后缀名 df DecimalFormat newValue 100/1000 suffix %/‰
    private static void setPositiveNegativeSuffix(DecimalFormat df, int newValue, String suffix)
    {
        df.setMultiplier(newValue);
        df.setPositiveSuffix(suffix);
        df.setNegativeSuffix(suffix);
    }

    public static void printDigits(NumberFormat nf)
    {
        System.out.println(nf.getClass().getName() + "-MaximumFractionDigits=" + nf.getMaximumFractionDigits());
        System.out.println(nf.getClass().getName() + "-MinimumFractionDigits=" + nf.getMinimumFractionDigits());
        System.out.println(nf.getClass().getName() + "-MaximumIntegerDigits=" + nf.getMaximumIntegerDigits());
        System.out.println(nf.getClass().getName() + "-MinimumIntegerDigits=" + nf.getMinimumIntegerDigits());
    }

    public static void main(String[] args) throws Exception
    {
        NumTools.printDigits(NumTools.decimalFormat);
        System.out.println(NumTools.formatCurrency(123456.87583));
        System.out.println(NumTools.parseCurrency("￥123,456.88"));
        System.out.println(NumTools.formatPercentage(0.87655));
        System.out.println(NumTools.formatTwo(123456.87583));
        System.out.println(NumTools.formatFour(123456.87583));
        System.out.println(NumTools.percentFormat.getPositiveSuffix());
        System.out.println(NumTools.parsePercentPercentage("87.86%"));
        System.out.println(NumTools.percentFormat.getPositiveSuffix());
        System.out.println(NumTools.parsePercentPermillage("87.86‰"));
        System.out.println(NumTools.percentFormat.getPositiveSuffix());
        System.out.println(NumTools.percentFormat.getDecimalFormatSymbols().getPercent());
        System.out.println(NumTools.percentFormat.getDecimalFormatSymbols().getPerMill());

        if(NumTools.defaultFormat.toPattern() != null)
        {
            System.out.println("pattern is not null = " + NumTools.defaultFormat.toPattern());
        }
        else
        {
            System.out.println("pattern is null");
        }
    }

    public static void test() throws Exception
    {
        // Print out a number using the localized number, integer, currency,
        // and percent format for each locale
        Locale[] locales = NumberFormat.getAvailableLocales();
        double myNumber = -1234.56;
        NumberFormat form;
        for(int j = 0; j < 4; ++j)
        {
            System.out.println("FORMAT");
            for(int i = 0; i < locales.length; ++i)
            {
                if(locales[i].getCountry().length() == 0)
                {
                    continue; // Skip language-only locales
                }
                System.out.print(locales[i].getDisplayName());
                switch(j)
                {
                    case 0:
                        form = NumberFormat.getInstance(locales[i]);
                        break;
                    case 1:
                        form = NumberFormat.getIntegerInstance(locales[i]);
                        break;
                    case 2:
                        form = NumberFormat.getCurrencyInstance(locales[i]);
                        break;
                    default:
                        form = NumberFormat.getPercentInstance(locales[i]);
                        break;
                }
                if(form instanceof DecimalFormat)
                {
                    System.out.print(": " + ((DecimalFormat) form).toPattern());
                }
                System.out.print(" -> " + form.format(myNumber));
                System.out.println(" -> " + form.parse(form.format(myNumber)));
            }
        }
    }
}
