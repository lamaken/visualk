/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visualk.nine.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alex
 */
public class NineGen {

    String result;
    String result2;

    Integer iterations;
    Integer diff = 0;
    Integer oldiff = 0;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Integer getDiff() {
        return diff;
    }

    public void setDiff(Integer diff) {
        this.diff = diff;
    }

    public Integer getOldiff() {
        return oldiff;
    }

    public void setOldiff(Integer oldiff) {
        this.oldiff = oldiff;
    }

    private Integer sumDig(Integer number) {
        /* String temp = number + "";
        
        Integer i=0;
        //while(number>9){
            Integer res = 0;
            for (Integer n = 0; n < temp.length(); n++) {
                try {
                    res += Integer.parseInt(temp.substring(n, n + 1));
                    i++;
                    System.out.println(res+",");
                } catch (Exception e) {
                    //e.printStackTrace();
                    System.out.println(e.getMessage());
                }
            }
            number=res;
            temp = number + "";
            
        //}
         */
        if (number == null) {
            number = 0;
        }
        if (number == 0) {
            number = 0;
        }

        String snumber = number + "";
        number = Integer.parseInt(snumber.substring(snumber.length() - 1, snumber.length()));

        if ((snumber.equals("0")) || (snumber.equals("9"))) {
            snumber = "<b>" + snumber + "</b>";
        }
        return number;
    }

    public NineGen(String value) {
        result2 = "";
        String number = value;
        String value2 = "0";
        result = "";
        Integer x0 = 0;
        String numberBefore = "";
        iterations = 0;
        diff = 1;
        ArrayList<Integer> magic = new ArrayList<>();
        ArrayList<Integer> magic2 = new ArrayList<>();
        while (!number.equals("9") && !number.equals("")) {
            x0 = Integer.parseInt(number) * 9;
            number = String.valueOf(x0);
            if (number == null) {
                number = "0";
            }
            numberBefore = number;
            number = number.substring(0, number.length() - 1);
            System.out.println(number);
            oldiff = diff;
            diff = (x0 / 9) - Integer.parseInt(number);
            String next = String.valueOf(Integer.parseInt(number) * 9);
            result += "(" + value + "*" + "9=" + x0 + ")-" + next.concat("=" + number + "," + sumDig(x0) + "</br>");
            result2 += "( " + value + " * " + " 9 = " + x0 + " ) " + " = " + number + " , " + sumDig(x0) + ", (" + x0 + " / " + "9 = " + x0 + ")" + "=" + value + "</br>";
            
                value2 = String.valueOf(Integer.parseInt(value2) + sumDig(x0));
            
            value = String.valueOf(Integer.parseInt(value) - diff);
            iterations++;
            magic.add(sumDig(x0));

        }

        result2 += "</br>" + value2 + "</br>";
        Integer c = 9;
        for (Integer n = 0; n < iterations; n++) {
            result2 += " c" + (n + 1) + " = " + magic.get(n) + ", ";
        }

    }

    @Override
    public String toString() {
        return result2;
    }

    public Integer getIterations() {
        return iterations;
    }

}
