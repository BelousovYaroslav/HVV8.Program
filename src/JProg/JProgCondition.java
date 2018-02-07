/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JProg;

/**
 *
 * @author yaroslav
 */
public class JProgCondition {
    static final int SIGN_LT = 1;
    static final int SIGN_LE = 2;
    static final int SIGN_EQ = 3;
    static final int SIGN_GE = 4;
    static final int SIGN_GT = 5;
    
    //OBJECT
    private String m_strObject;
    public String GetObject() { return m_strObject;}
    public void SetObject( String strNewObject) { m_strObject = strNewObject; }
    
    //SIGN
    private int m_nSign;
    public int GetSign() { return m_nSign;}
    public boolean SetSign( int nNewSign) {
        if( nNewSign < 1 || nNewSign > 5) return false;
        m_nSign = nNewSign;
        return true;
    }
    
    //CONTROL VALUE
    private double m_dblControlValue;
    public double GetControlValue() { return m_dblControlValue;}
    public void SetControlValue( double dblNewControlValue) {
        m_dblControlValue = dblNewControlValue;
    }
    
    public JProgCondition() {
        m_strObject = "";
        m_nSign = SIGN_EQ;
        m_dblControlValue = 0;
    }
}
