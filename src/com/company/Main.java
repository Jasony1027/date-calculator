/**
 *  Calculates the difference in days/months/years of 2 dates.
 *  Assuming that there is always 28 days in February and 365 days in a year.
 *  Example: Date1: 12/24/2020, Date2: 12/25/2019 , Results => Year: 0, Month: 11, Days: 364
 * @author Jason Yen
 * @version Sep. 21, 2019
 */
package com.company;
import javax.swing.JFrame;
public class Main {

    public static void main(String[] args) {
        JFrame frame = new CountDaysFrame();
        frame.setSize(750,150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Count Days Between Dates");
        frame.setVisible(true);
    }
}
