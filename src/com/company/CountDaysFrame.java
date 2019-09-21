package com.company;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class CountDaysFrame extends JFrame {
    public CountDaysFrame() {
        setLayout(new GridLayout(2, 1));

        class OptionListener implements ActionListener {
            public void actionPerformed(ActionEvent event) {
                readOptions();
            }
        }
        class ButtonListener implements ActionListener {
            public void actionPerformed(ActionEvent event) {
                count();
            }
        }
        class textListener implements KeyListener {
            public void keyPressed(KeyEvent event) {
                if (event.getKeyCode() == KeyEvent.VK_ENTER) {
                    count();
                }
            }

            public void keyTyped(KeyEvent e) {
            }

            public void keyReleased(KeyEvent e) {
            }

        }
        listener = new OptionListener();
        tListener = new textListener();
        bListener = new ButtonListener();
        createMainPanel();
    }

    /**
     * Create the JPanel that has every other funtional JPanel on it
     */
    private void createMainPanel() {
        mainPanel = new JPanel();
        JPanel labelPanel1 = createStartingLabel();
        JPanel labelPanel2 = createEndingLabel();
        JPanel optionPanel = createCombo();
        JPanel buttonPanel = createButton();
        JPanel textPanel1 = createStartingText();
        JPanel textPanel2 = createEndingText();

        mainPanel.add(labelPanel1);
        mainPanel.add(textPanel1);
        mainPanel.add(labelPanel2);
        mainPanel.add(textPanel2);
        mainPanel.add(optionPanel);


        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.CENTER);
    }

    /**
     * Create a JLabel saying starting date: and add it to a JPanel
     *
     * @return a JPanel with a JLabel on it
     */
    private JPanel createStartingLabel() {
        start = new JLabel("   starting date(MM/DD/YYYY):   ");
        JPanel panel = new JPanel();
        panel.add(start);
        return panel;
    }

    /**
     * Create a JLabel saying ending date: and add it to a JPanel
     *
     * @return a JPanel with a JLabel on it
     */
    private JPanel createEndingLabel() {
        end = new JLabel("   ending date:   ");
        JPanel panel = new JPanel();
        panel.add(end);

        return panel;
    }

    /**
     * Create a JTextFeild for users to type in the date and add it to a JPanel
     *
     * @return a JPanel with a JTextField on it
     */
    private JPanel createStartingText() {
        t1 = new JTextField(10);

        t1.addKeyListener(tListener);

        JPanel panel = new JPanel();
        panel.add(t1);

        return panel;
    }

    /**
     * Create a JTextFeild showing today's date and add it to a JPanel
     *
     * @return a JPanel with a JTextField on it
     */
    private JPanel createEndingText() {

        today = LocalDate.now();
        date += today.getMonthValue() + "/" + today.getDayOfMonth() + "/" + today.getYear();
        t2 = new JTextField(date, 10);

        JPanel panel = new JPanel();
        panel.add(t2);

        return panel;
    }

    /**
     * Create a JComboBox for users to choose the unit to compare and add it to a JPanel
     *
     * @return a JPanel with a JComboBox on it
     */
    private JPanel createCombo() {
        options = new JComboBox();
        options.addItem("Days");
        options.addItem("Months");
        options.addItem("Years");
        options.addActionListener(listener);

        JPanel panel = new JPanel();
        panel.add(options);
        return panel;

    }

    /**
     * Create a JButton saying Count and add it to a JPanel
     *
     * @return a JPanel with a JButton on it
     */
    private JPanel createButton() {

        count = new JButton("Count");
        count.addActionListener(bListener);
        JPanel panel = new JPanel();
        panel.add(count);

        return panel;

    }

    /**
     * Calcuate the difference between 2 dates
     *
     * @param option the item from JComboBox
     * @return the difference
     */
    private int calculate(String option) {
        int y1, y2, m1, m2, d1, d2, diff = 0;
        int daysInMonths[] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (year1 > year2) {
            y1 = year1;
            y2 = year2;
            m1 = month1;
            m2 = month2;
            d1 = day1;
            d2 = day2;
        } else {
            y1 = year2;
            y2 = year1;
            m1 = month2;
            m2 = month1;
            d1 = day2;
            d2 = day1;
        }

        if (option == "Days") {
            if (m1 > m2 && d1 > d2) {
                diff += (365 * (y1 - y2));
                diff += daysInMonths[m2 - 1] - d2;
                for (int i = m2; i < m1; i++) {
                    diff += daysInMonths[i];
                }
            } else if (m1 > m2 && d1 < d2) {
                diff += (365 * (y1 - y2));
                diff += d1 + daysInMonths[m2 - 1] - d2;
            } else if (m1 > m2 && d1 == d2) {
                diff += (365 * (y1 - y2));
                for (int i = m2; i < m1; i++) {
                    diff += daysInMonths[i - 1];
                }
            } else if (m1 <= m2) {
                diff += (365 * (y1 - y2 - 1));
                diff += daysInMonths[m2 - 1] - d2;
                for (int i = m2; i < 12; i++) {
                    diff += daysInMonths[i];
                }
                for (int i = 0; i < m1 - 1; i++) {
                    diff += daysInMonths[i];
                }
                diff += d1;
            }
        } else if (option == "Months") {
            if (y1 == y2) {
                diff = m1 - m2;
            } else if (m1 >= m2 && d1 >= d2) {
                diff = 12 * (y1 - y2) + (m1 - m2);
            } else if (m1 >= m2 && d1 < d2) {
                diff = 12 * (y1 - y2) + (m1 - m2 - 1);
            } else if (m1 < m2 && d1 >= d2) {
                diff = 12 * (y1 - y2 - 1) + 12 - m2 + m1;
            } else {
                diff = 12 * (y1 - y2 - 1) + 12 - m2 + m1 - 1;
            }
        } else {
            if (m1 > m2 || m1 == m2 && d1>=d2) diff = y1 - y2;
            else diff = y1 - y2 - 1;


        }
        return Math.abs(diff);
    }

    /**
     * Read the item of JComboBox and write it to the string option
     */
    private void readOptions() {
        String option = (String) options.getSelectedItem();
    }

    /**
     * Set the event of clicking the Count button
     */
    private void count() {
        try {
            error = " MM/DD/YYYY\n";
            item = options.getSelectedItem().toString();
            temp = t1.getText().split("/");
            year1 = Integer.parseInt(temp[2]);
			day1 = Integer.parseInt(temp[1]);
            month1 = Integer.parseInt(temp[0]);

            temp = t2.getText().split("/");
            year2 = Integer.parseInt(temp[2]);
			day2 = Integer.parseInt(temp[1]);
            month2 = Integer.parseInt(temp[0]);

            //incorrect day
            if ((month1 == 2 && day1 > 28) || ((month1 == 4 || month1 == 6 || month1 == 9 || month1 == 11) && day1 > 30) || (day1 > 31 || day1 < 1)
                    || (month2 == 2 && day2 > 28) || ((month2 == 4 || month2 == 6 || month2 == 9 || month2 == 11) && day2 > 30) || (day2 > 31 || day2 < 1))
                throw new NumberFormatException();
            //incorrect month
            if (month1 < 1 || month1 > 12 || month2 < 1 || month2 > 12) throw new IllegalStateException();
            //incorrect year
            if (year1 < 1000 || year1 > 3000 || year2 < 1000 || year2 > 3000) throw new IllegalArgumentException();

        } catch (ArrayIndexOutOfBoundsException e) {// catch incorrect input
            error += "\nIncorrect input format";
        } catch (NumberFormatException e) {//catch incorrect day
            if (month1 == 2 && day1 > 28 || month2 == 2 && day2 > 28)
                error += "\nDay can not be greater than 28 in February";
            else if ((month1 == 4 || month1 == 6 || month1 == 9 || month1 == 11) && day1 > 30 ||
                    (month2 == 4 || month2 == 6 || month2 == 9 || month2 == 11) && day2 > 30)
                error += "\nDay can not be greater than 30 in Month : " + month1;
            else if (day1 > 31 || day1 < 1 || day2 > 31 || day2 < 1) {
                error += "\nInvalid Day.";
            }

            try {
                //incorrect month
                if (month1 < 1 || month1 > 12 || month2 < 1 || month2 > 12) throw new IllegalStateException();
                //incorrect year
                if (year1 < 1000 || year1 > 3000 || year2 < 1000 || year2 > 3000) throw new IllegalArgumentException();

            } catch (IllegalStateException exception) {
                error += "\nPlease enter month between 1-12";
                try {
                    //incorrect year
                    if (year1 < 1000 || year1 > 3000 || year2 < 1000 || year2 > 3000)
                        throw new IllegalArgumentException();
                } catch (IllegalArgumentException ex) {
                    error += "\nPlease enter year between 1000-3000";
                }
            } catch (IllegalArgumentException ex) {
                //incorrect year
                error += "\nPlease enter year between 1000-3000";
            }
        } catch (IllegalStateException exception) {//catch incorrect month
            error += "\nPlease enter month between 1-12";
            try {
                //incorrect year
                if (year1 < 1000 || year1 > 3000) throw new IllegalArgumentException();
            } catch (IllegalArgumentException ex) {
                error += "\nPlease enter year between 1000-3000";
            }
        } catch (IllegalArgumentException ex) {//catch incorrect month
            error += "\nPlease enter year between 1000-3000";

        }
        //if there is no error print the result
        if (error.equals(" MM/DD/YYYY\n")) {
            JOptionPane.showMessageDialog(null, item + " : " + calculate(item), "Result", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, error, "alert", JOptionPane.ERROR_MESSAGE);
        }
    }


    private JPanel mainPanel;
    private JLabel start;
    private JLabel end;
    private JTextField t1;
    private JTextField t2;
    private JComboBox options;
    private ActionListener listener;
    private KeyListener tListener;
    private ActionListener bListener;
    private JButton count;
    private LocalDate today;
    private String date = "";
    private String input;
    private String[] temp;
    private int day1;
    private int month1;
    private int year1;
    private int day2;
    private int month2;
    private int year2;
    private String item;
    private char test;
    private String error;

}