/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visualk.nine.model;

/**
 *
 * @author alex
 */
public class NineGen {

    String result;
    Integer iterations;
    Integer diff=0;
    Integer oldiff=0;

    public NineGen(String number) {
        result = "";
        Integer x0 = 0;
        String numberBefore = "";
        iterations = 0;
        
        while (!number.equals("9") && !number.equals("")) {
            x0 = Integer.parseInt(number) * 9;
            number = String.valueOf(x0);
            numberBefore = number;
            number = number.substring(0, number.length() - 1);
            System.out.println(number);
            oldiff = diff;
            diff = (x0 / 9) - Integer.parseInt(number);
            result += number.concat("(") + diff + ") "+(oldiff-diff)+"<br>";
            iterations++;
        }
    }

    @Override
    public String toString() {
        return result;
    }

    public Integer getIterations() {
        return iterations;
    }

}

/*
procedure TForm1.getLMKnumber;
var
begi

  memo2.lines.add(Edit3.text+':> ');
  number:=Edit3.text;
  //iniNumber:=strtoint(number);
  n:=0;
  k:=0;
  strFunc:='';
  repeat
         n:=n+1;
         x0:=strtoint(number)*9;
         number:=inttostr(x0);
         listbox3.Items.add(number);
         numFberbefore:=number;
         if((number[length(number)]='0')or(number[length(number)]='9'))then
         begin
            listbox1.items.add(number[length(number)]+','+inttostr(n));
            memo2.lines[memo2.lines.count-1]:=memo2.lines[memo2.lines.count-1]+'['+number[length(number)]+','+inttostr(n)+'] ';

            strFunc:=strFunc+'X';
            k:=k+1;
            if (n>max)then max:=n;
         end
         else strFunc:=strFunc+'·';
         delete(number,length(number),1);
         listbox2.Items.add(number);
  until (numberbefore='9')or(numberbefore='0');

         (*supermax direction
         if(max>supermax)then strFunc:='+'
         else if (max<supermax)then strFunc:='-'
         else if (max=supermax)then strFunc:='=';
         supermax:=max;
         *)

    if(CheckBox1.checked)then memo2.lines[memo2.lines.count-1]:=strFunc+#9+memo2.lines[memo2.lines.count-1];

  Label6.caption:=inttostr(k);
end;

 */
