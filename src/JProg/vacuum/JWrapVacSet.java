/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JProg.vacuum;

/**
 *
 * @author yaroslav
 */
public class JWrapVacSet {
    private final String m_strObject;
    public String GetObject() { return m_strObject; }
    
    private final String m_strParam;
    public String GetParam() { return m_strParam; }
    
    private final double m_dblValue;
    public double GetValue() { return m_dblValue; }
    
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
    
    public JWrapVacSet( JProgVacuumOpen st) {
        m_strObject = st.GetOperableDevice().m_strID;
        m_strParam = "01";
        m_dblValue = 1;
    }
    
    public JWrapVacSet( JProgVacuumClose st) {
        m_strObject = st.GetOperableDevice().m_strID;
        m_strParam = "01";
        m_dblValue = 0;
    }
    
    public JWrapVacSet( JProgVacuumTurnOn st) {
        m_strObject = st.GetOperableDevice().m_strID;
        m_strParam = "01";
        m_dblValue = 1;
    }
    
    public JWrapVacSet( JProgVacuumTurnOff st) {
        m_strObject = st.GetOperableDevice().m_strID;
        m_strParam = "01";
        m_dblValue = 0;
    }
    
    public JWrapVacSet( JProgVacuumSet st) {
        m_strObject = st.GetOperableDevice().m_strID;
        m_strParam = "02";
        m_dblValue = st.getSetValue();
    }
}
