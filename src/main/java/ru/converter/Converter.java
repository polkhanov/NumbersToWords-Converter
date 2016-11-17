package ru.converter;

import java.math.BigInteger;
import java.text.NumberFormat;
import java.util.Locale;

public class Converter extends Dictionary {

    public String getFullNumber(String _sourceNumber) {

        StringBuilder fullNumberName = new StringBuilder();
        try {
            BigInteger convertibleNumber = new BigInteger(_sourceNumber);
            Integer triadCount;
            Boolean isNegative;
            if (getTriadCount(convertibleNumber) > EXPONENT_MAP.size() - 1)
                throw new IndexOutOfBoundsException("К сожалению, введенное число слишком большое и не удовлетворяет условию задания!");
            else {
                isNegative = convertibleNumber.compareTo(BigInteger.ZERO) < 0;
                convertibleNumber = convertibleNumber.abs();
                triadCount = getTriadCount(convertibleNumber);
            }
            String[] strings = getFormattedNumber(convertibleNumber);

            if (isNegative)
                fullNumberName.append(MINUS + " ");
            for (int i = 0; i <= triadCount; i++) {
                fullNumberName.append(getTriadName(triadCount - i, strings[i])).append(" ");
            }
            if ("".equals(fullNumberName.toString().trim()))
                fullNumberName.append(ZERO);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
      }  catch (NumberFormatException e) {
        System.out.println("Введенные данные не корректны!");
    }
        return fullNumberName.toString().trim();
    }

    private int getTriadCount(BigInteger value) {
        return getFormattedNumber(value).length - 1;
    }

    private String getTriadName(int position, String triad) {

        StringBuilder curTriad = new StringBuilder();
        int numb = Integer.parseInt(triad);
        int firstNumber,
                secondNumber,
                thirdNumber;

        firstNumber = (numb / 100) % 10;
        secondNumber = (numb / 10) % 10;

        if (numb % 100 < 20)
            thirdNumber = numb % 100;
        else
            thirdNumber = numb % 10;

        if (firstNumber == 0 && secondNumber == 0 && thirdNumber == 0 && position != 0)
            return "";

        curTriad.append((firstNumber == 0) ? "" : getHundredthNumber(firstNumber) + " ")
                .append((secondNumber < 2) ? "" : getDecimalNumber(secondNumber) + " ");

        if (position == 1) {
            StringBuilder key = new StringBuilder();
            switch (thirdNumber) {
                case 1:
                    key.append(getThousandOperator(1));
                    break;
                case 2:
                    key.append(getThousandOperator(2));
                    break;
                default:
                    key.append(getSimpleNumber(thirdNumber));
                    break;
            }
            curTriad.append((thirdNumber == 0) ? "" : key + " ");
        } else
            curTriad.append((thirdNumber == 0) ? "" : getSimpleNumber(thirdNumber) + " ");

        curTriad.append(getExponent(position));
        if (position != 0)
            switch (getGroupNumbers(thirdNumber)) {
                case FIRST:
                    curTriad = (position == 1 ? curTriad.append("a") : curTriad.append(""));
                    break;
                case SECOND:
                    curTriad = (position == 1 ? curTriad.append("и") : curTriad.append("а"));
                    break;
                case THIRD:
                    curTriad = (position == 1 ? curTriad.append("") : curTriad.append("ов"));
                    break;
                default:
                    break;
            }
        return curTriad.toString();
    }

    private String[] getFormattedNumber(BigInteger value) {
        Locale loc = new Locale("ru");
        NumberFormat formatter = NumberFormat.getInstance(loc);
        return formatter.format(value).split(" ");
    }

    private numbersMap getGroupNumbers(int group) {
        if (group == 1)
            return numbersMap.FIRST;
        else {
            if (group > 1 && group < 5)
                return numbersMap.SECOND;
            else if (group > 4)
                return numbersMap.THIRD;
        }
        return numbersMap.THIRD;
    }

}
