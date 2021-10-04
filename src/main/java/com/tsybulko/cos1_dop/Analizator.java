package com.tsybulko.cos1_dop;

import java.util.ArrayList;
import java.util.List;

public class Analizator {
    private ArrayList<SignalGenerator> Signals;
    private int M;
    private int K;
    private int N;
    private double Phase;
    private int NMulCoef;

    public Analizator(int n, int k, int mulCoef, double phase)
    {
        Signals = new ArrayList<SignalGenerator>();
        N = n;
        K = k;
        Phase = phase;
        NMulCoef = mulCoef;
    }

    public void GenerateSignals()
    {
        M = N * NMulCoef;

        for (int i = K; i < M; i++)
        {
            SignalGenerator newSignal = new SignalGenerator(i, N, Phase);
            newSignal.Generate();
            newSignal.CalculateCharacteristics();
            Signals.add(newSignal);
        }

    }

    public ArrayList<SignalGenerator> getSignals() {
        return Signals;
    }

    public void setSignals(List<SignalGenerator> signals) {
        Signals = (ArrayList<SignalGenerator>) signals;
    }

    public int getM() {
        return M;
    }

    public void setM(int m) {
        M = m;
    }

    public int getK() {
        return K;
    }

    public void setK(int k) {
        K = k;
    }

    public int getN() {
        return N;
    }

    public void setN(int n) {
        N = n;
    }

    public double getPhase() {
        return Phase;
    }

    public void setPhase(double phase) {
        Phase = phase;
    }

    public int getNMulCoef() {
        return NMulCoef;
    }

    public void setNMulCoef(int NMulCoef) {
        this.NMulCoef = NMulCoef;
    }
}
