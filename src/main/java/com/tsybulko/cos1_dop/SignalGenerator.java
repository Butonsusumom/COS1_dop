package com.tsybulko.cos1_dop;

import java.util.ArrayList;
import java.util.List;

public class SignalGenerator {
    private double valuesSum;
    private double sqrValuesSum;
    private int M;
    private double Phase;
    private double avgSqrValueFormula1;
    private double avgSqrValueFormula2;
    private double amplitude;
    private double deltaAvgSqrValue;
    private double deltaAmplitude;
    private double deltaAvgSqrDiviation;
    private int N;

    private List<Double> values;

    public SignalGenerator(int m, int n, double phase) {
        M = m;
        Phase = phase;
        N = n;
        values = new ArrayList<Double>();
    }

    public void Generate() {
        for (int i = 0; i < M; i++) {
            double generatedValue = GenerateNextValue(i);
            valuesSum += generatedValue;
            sqrValuesSum += Math.pow(generatedValue, 2);
            values.add(generatedValue);
        }

    }

    public void CalculateCharacteristics() {
        avgSqrValueFormula1 = Math.sqrt( (1.0 / (M + 1.0)) * sqrValuesSum);
        avgSqrValueFormula2 = Math.sqrt(((1.0 / (M + 1.0)) * sqrValuesSum) - Math.pow(((1.0/ (M + 1.0)) * valuesSum),2));
        CalculateAmplitude();
        CalculateDeltas();
    }

    private void CalculateDeltas() {
        CalculateDeltaAvgSquareValue();
        CalculateDeltaAmplitude();
        CalculateDeltaAvgSqrDiviation();
    }

    public double GenerateNextValue(int index) {
        return Math.sin(((2.0 * Math.PI * (double)index) / (double)N) + Phase);
    }

    public void CalculateAmplitude() {
        double sinSum = 0;
        double cosSum = 0;
        int i = 0;

        for (Double value:
             values) {
            double angle = (double)(2.0 * Math.PI * (double)i / N);
            sinSum += value * Math.sin(angle);
            cosSum += value * Math.cos(angle);
            ++i;
        }

        sinSum *= 2 / (double)values.size();
        cosSum *= 2 / (double)values.size();

        amplitude = Math.sqrt(sinSum * sinSum + cosSum * cosSum);
    }

    public void CalculateDeltaAvgSquareValue() {
        deltaAvgSqrValue = 0.707 - avgSqrValueFormula1;
    }

    public void CalculateDeltaAmplitude() {
        deltaAmplitude = 1.0 - amplitude;
    }

    public void CalculateDeltaAvgSqrDiviation() {
        deltaAvgSqrDiviation = 0.707 - avgSqrValueFormula2;
    }

    public double getValuesSum() {
        return valuesSum;
    }

    public void setValuesSum(double valuesSum) {
        this.valuesSum = valuesSum;
    }

    public double getSqrValuesSum() {
        return sqrValuesSum;
    }

    public void setSqrValuesSum(double sqrValuesSum) {
        this.sqrValuesSum = sqrValuesSum;
    }

    public int getM() {
        return M;
    }

    public void setM(int m) {
        M = m;
    }

    public double getPhase() {
        return Phase;
    }

    public void setPhase(double phase) {
        Phase = phase;
    }

    public double getAvgSqrValueFormula1() {
        return avgSqrValueFormula1;
    }

    public void setAvgSqrValueFormula1(double avgSqrValueFormula1) {
        this.avgSqrValueFormula1 = avgSqrValueFormula1;
    }

    public double getAvgSqrValueFormula2() {
        return avgSqrValueFormula2;
    }

    public void setAvgSqrValueFormula2(double avgSqrValueFormula2) {
        this.avgSqrValueFormula2 = avgSqrValueFormula2;
    }

    public double getAmplitude() {
        return amplitude;
    }

    public void setAmplitude(double amplitude) {
        this.amplitude = amplitude;
    }

    public double getDeltaAvgSqrValue() {
        return deltaAvgSqrValue;
    }

    public void setDeltaAvgSqrValue(double deltaAvgSqrValue) {
        this.deltaAvgSqrValue = deltaAvgSqrValue;
    }

    public double getDeltaAmplitude() {
        return deltaAmplitude;
    }

    public void setDeltaAmplitude(double deltaAmplitude) {
        this.deltaAmplitude = deltaAmplitude;
    }

    public double getDeltaAvgSqrDiviation() {
        return deltaAvgSqrDiviation;
    }

    public void setDeltaAvgSqrDiviation(double deltaAvgSqrDiviation) {
        this.deltaAvgSqrDiviation = deltaAvgSqrDiviation;
    }

    public int getN() {
        return N;
    }

    public void setN(int n) {
        N = n;
    }

    public List<Double> getValues() {
        return values;
    }

    public void setValues(List<Double> values) {
        this.values = values;
    }
}
