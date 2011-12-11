package com.acme.facemash.util;

import com.acme.facemash.util.Functionnal.Tuple;
import java.util.concurrent.ConcurrentHashMap;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 * Bean usign Elo formula to compute faces score.
 * 
 * @author mathieuancelin
 */
public class Elo {
    
    public static final double START_SCORE = 1200.0;
    
    public static Tuple<Double, Double> calcElo(boolean playerAWin, double RA, double RB) {
        double QA = Math.pow(10, RA / 400);
        double QB = Math.pow(10, RB / 400);
        double k = 32;
        double EA = QA / (QA + QB);
        double EB = QB / (QA + QB);
        double SA = 0.5;
        double SB = 0.5;
        if (playerAWin) {
            SA = 1;
            SB = 0;
        } else {
            SA = 0;
            SB = 1;
        }
        return new Tuple<Double, Double>(RA + (k * (SA - EA)), RB + (k * (SB - EB)));
    }
}
