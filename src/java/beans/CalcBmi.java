/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.text.DecimalFormat;
import javax.ejb.Stateless;

/**
 * Calculates bmi
 * @author middleware
 */
@Stateless
public class CalcBmi {

    public double calcBmi(double height, double weight) {

        double bmi = weight / (height * height);
        // round 2 digits
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        return Double.valueOf(twoDForm.format(bmi));
    }
}
