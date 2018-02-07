/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JProg.vacuum;

import JProg.JProgAStatement;
import hvv_devices.HVV_VacuumDevice;
import hvv_devices.HVV_VacuumDevices;
import org.dom4j.Element;

/**
 *
 * @author yaroslav
 */
public class JProgVacuumSet extends JProgAStatement {

    private final HVV_VacuumDevice m_SetDevice;
    public HVV_VacuumDevice GetOperableDevice() { return m_SetDevice; }
    
    private final String m_strSetParam;
    public String getSetParam() { return m_strSetParam;}
    
    private final double m_dblSetValue;
    public double getSetValue() { return m_dblSetValue;}
    
    @Override
    public String GetAsString() {
        String strResult;
        strResult = "VACUUM. Задать значение " + m_dblSetValue + " для " +
                m_SetDevice.getID() + "." + m_SetDevice.getName() +
                "." + m_strSetParam + "." + m_SetDevice.m_mapParameters.get( m_strSetParam);
        return strResult;
    }

    /*
    @Override
    public Document GetAsXML() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    */

    @Override
    public void AddXMLStatement(Element doc) {
        doc.addElement( "Type").addText( "VAC.SET");
        doc.addElement( "DeviceId").addText( m_SetDevice.getID());
        doc.addElement( "Param").addText( m_strSetParam);
        doc.addElement( "Value").addText( ""  + m_dblSetValue);
    }
    
    public JProgVacuumSet( Element el) {
        Element eMessage = el.element( "DeviceId");
        String strDeviceId = eMessage.getText();
        m_SetDevice = ( HVV_VacuumDevice) HVV_VacuumDevices.getInstance().m_devices.get( strDeviceId);
        
        Element eParam = el.element( "Param");
        m_strSetParam = eParam.getText();
        
        Element eValue = el.element( "Value");
        m_dblSetValue = Double.parseDouble( eValue.getText());
        
        setStatementType( JProgAStatement.STATEMENT_TYPE_VACUUM_SET);
    }
    
    public JProgVacuumSet( HVV_VacuumDevice dev, String strParam, double dblValue) {
        setStatementType( JProgAStatement.STATEMENT_TYPE_VACUUM_SET);
        m_SetDevice = dev;
        m_strSetParam = strParam;
        m_dblSetValue = dblValue;
    }
}
