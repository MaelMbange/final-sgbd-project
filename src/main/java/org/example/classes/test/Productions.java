package org.example.classes.test;

import java.util.Calendar;

public class Productions
{
    private int TypePeriode;
    private double data[];
    private int PeriodeSuperieure;
    public Productions (int t, double d[], int ps) throws InvalidDataProduction
    {
        Calendar c = Calendar.getInstance();
        TypePeriode = t; data = d; PeriodeSuperieure = ps;
        if (data.length != (c.getActualMaximum(TypePeriode)-c.getActualMinimum(TypePeriode)+1)) throw new InvalidDataProduction("Nombre de données incohérent avec le type de période");
    }

    public int getTypePeriode() { return TypePeriode; }
    public void setTypePeriode(int TypePeriode) { this.TypePeriode = TypePeriode; }
    public double[] getData() { return data; }
    public void setData(double[] data) { this.data = data; }
    public int getPeriodeSuperieure() { return PeriodeSuperieure; }
    public void setPeriodeSuperieure(int PeriodeSuperieure)
    { this.PeriodeSuperieure = PeriodeSuperieure; }
}
