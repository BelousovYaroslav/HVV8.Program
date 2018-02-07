/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JProg.hv;

/**
 *
 * @author yaroslav
 */
public class JWrapHvSet {
    private final String m_strObject;
    public String GetObject() { return m_strObject; }
    
    private final int m_nValue;
    public int GetValue() { return m_nValue; }
    
    /*
    public JProgHvSet( JProgHvChannels st) {
        st.
    }
    */
    
    /*
    public JProgHvSet( JProgHvRegime st) {
        st.
    }
    */
    
    public JWrapHvSet( JProgHvMainSwitcher st) {
        m_strObject = "MSW.01";
        m_nValue = st.GetState();
    }
    
    public JWrapHvSet( JProgHvPreset st) {
        m_strObject = "PRE.01";
        m_nValue = st.GetPreset();
    }
    
    public JWrapHvSet( JProgHvVibration st) {
        m_strObject = "VIB.01";
        m_nValue = st.GetState();
    }
    
    public JWrapHvSet( JProgHvFan st) {
        m_strObject = "FAN.01";
        m_nValue = st.GetState();
    }    
}
