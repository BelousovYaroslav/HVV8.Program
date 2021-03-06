/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JProg.vacuum;

import JProg.JProgAStatement;
import hvv_devices.HVV_VacuumDevice;
import hvv_devices.HVV_VacuumDevices;
import org.dom4j.Document;
import org.dom4j.Element;

/**
 *
 * @author yaroslav
 */
public class JProgVacuumOpen extends JProgAStatement {

    private final HVV_VacuumDevice m_device;
    public HVV_VacuumDevice GetOperableDevice() { return m_device; }
    
    @Override
    public String GetAsString() {
        String strResult;
        strResult = "VACUUM. Открыть [" + m_device.toString() + "].";
        return strResult;
    }

    /*
    @Override
    public Document GetAsXML() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    */

    @Override
    public void AddXMLStatement( Element doc) {
        doc.addElement( "Type").addText( "VAC.OPEN");
        doc.addElement( "DeviceId").addText( m_device.getID());
    }
    
    public JProgVacuumOpen( Element el) {
        Element eMessage = el.element( "DeviceId");
        String strDeviceId = eMessage.getText();
        m_device = ( HVV_VacuumDevice) HVV_VacuumDevices.getInstance().m_devices.get( strDeviceId);
        setStatementType( JProgAStatement.STATEMENT_TYPE_VACUUM_OPEN);
    }
    
    public JProgVacuumOpen( HVV_VacuumDevice dev) {
        setStatementType( JProgAStatement.STATEMENT_TYPE_VACUUM_OPEN);
        m_device = dev;
    }
}
