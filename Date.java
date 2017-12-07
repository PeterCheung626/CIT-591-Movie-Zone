/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviezone;

/**
 *
 * @author mayitian
 */
public class Date {
    int year;
    int month;
    int day;

    public Date (String s) {
        String[] info = s.split("-");
        year = Integer.parseInt(info[0]);
        month = Integer.parseInt(info[1]);
        day = Integer.parseInt(info[2]);

    }

    public int compareTo(Date d) {
        if (this.year < d.getYear()) return -1;
        else if (this.year > d.getYear()) return 1;
        else {
            if (this.month < d.getMonth()) return -1;
            else if (this.month > d.getMonth()) return 1;
            else {
                return this.day - d.getDay();
            }
        }

    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }
}
