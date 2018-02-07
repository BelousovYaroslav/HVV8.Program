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
public class JProgVacuumWait extends JProgAStatement {
    private final HVV_VacuumDevice m_pDevice;
    public HVV_VacuumDevice GetOperableDevice() { return m_pDevice;}
    
    private final String m_strExpectedParam;
    public String getExpectedParam() { return m_strExpectedParam;}
    
    private final double m_dblExpected;
    public double getExpected() { return m_dblExpected;}
    
    private final double m_dblError;
    public double getError() { return m_dblError;}
    
    private final int m_nApproximation;
    public int getApproximation() { return m_nApproximation;}
    
    public static final int VAC_WAIT_APPROACH_FROM_UPSIDE = 1;
    public static final int VAC_WAIT_APPROACH_FROM_DOWNSIDE = 2;
    public static final int VAC_WAIT_APPROACH_FROM_NEVERMIND = 3;
    
    public JProgVacuumWait( String strDevParam, double dblExpected, double dblError, int nApproximation) throws IllegalArgumentException {
        setStatementType( JProgAStatement.STATEMENT_TYPE_VACUUM_WAIT);
        
        //Контролируемые оборудование.параметр
        String [] split = strDevParam.split( "\\.");
        if( split.length == 2) {
            m_pDevice = ( HVV_VacuumDevice) HVV_VacuumDevices.getInstance().m_devices.get( split[0]);
            m_strExpectedParam = split[1];
        }
        else {
            throw new IllegalArgumentException( "Некорректный входящий параметр описывающий физический ожидаемый параметр.");
        }
        
        //ожидаемое значение
        m_dblExpected = dblExpected;
        
        //погрешность
        m_dblError = dblError;
        
        switch( nApproximation) {
            case VAC_WAIT_APPROACH_FROM_UPSIDE:
            case VAC_WAIT_APPROACH_FROM_DOWNSIDE:
            case VAC_WAIT_APPROACH_FROM_NEVERMIND:
                m_nApproximation = nApproximation;
            break;
                
            default:
                throw new IllegalArgumentException( "Некорректный входящий параметр описывающий приближение к ожидаемому значению.");
        }
    }

    public String Gen_NiceDoubleGen( Double dbl, boolean bPoint) {
        String strResult;
    
        if( dbl.isNaN()) {
            return "NaN";
        }
        
        int nExp = ( int) Math.log10( dbl);
        float dblMant = ( float) ( dbl / Math.pow( 10, nExp));
        if( nExp > -2 && nExp < 2)
            strResult = String.format( "%.02f", dbl);
        else
            if( bPoint)
                strResult = String.format( "%.02f‧10^%d", dblMant, nExp);
            else
                strResult = String.format( "%.02f 10^%d", dblMant, nExp);
        
        strResult = strResult.replace( ",", ".");
        return strResult;
    }
    
    @Override
    public String GetAsString() {
        String strResult;
        
        String strVal = String.format( "%.3e", m_dblExpected);
        if( m_pDevice.getID().equals( "005") || m_pDevice.getID().equals( "04C"))
            strVal = Gen_NiceDoubleGen( m_dblExpected, false);
        
        String strErr = String.format( "%.3e", m_dblError);
        if( m_pDevice.getID().equals( "005") || m_pDevice.getID().equals( "04C"))
            strErr = Gen_NiceDoubleGen( m_dblError, false);
        
        strResult = "VACUUM. Ожидание [" + m_pDevice.getID() + "." + m_strExpectedParam + ". " +
                m_pDevice.getName() + "." + m_pDevice.m_mapParameters.get( m_strExpectedParam) + "]. " +
                "Значение: " + strVal + ". " +
                "Погрешность: " + strErr + ". ";
        
        switch( m_nApproximation) {
            case VAC_WAIT_APPROACH_FROM_UPSIDE:     strResult += "Подход: сверху."; break;
            case VAC_WAIT_APPROACH_FROM_DOWNSIDE:   strResult += "Подход: снизу."; break;
            case VAC_WAIT_APPROACH_FROM_NEVERMIND:  strResult += "Подход: неважно."; break;
        }
        return strResult;
    }

    /*
    @Override
    public Document GetAsXML() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    */

    public JProgVacuumWait( Element el) {
        Element eDeviceId = el.element( "DeviceId");
        String strDeviceId = eDeviceId.getText();
        m_pDevice = ( HVV_VacuumDevice) HVV_VacuumDevices.getInstance().m_devices.get( strDeviceId);
        
        Element eExpParam = el.element( "ExpParam");
        m_strExpectedParam = eExpParam.getText();
        
        Element eExpValue = el.element( "ExpValue");
        m_dblExpected = Double.parseDouble( eExpValue.getText());
        
        Element eError = el.element( "Deviation");
        m_dblError = Double.parseDouble( eError.getText());
        
        Element eAppprox = el.element( "Approximation");
        m_nApproximation = Integer.parseInt( eAppprox.getText());
        
        setStatementType( JProgAStatement.STATEMENT_TYPE_VACUUM_WAIT);
    }
    
    @Override
    public void AddXMLStatement(Element doc) {
        doc.addElement( "Type").addText( "VAC.WAIT");
        doc.addElement( "DeviceId").addText( m_pDevice.getID());
        doc.addElement( "ExpParam").addText( m_strExpectedParam);
        doc.addElement( "ExpValue").addText( "" + m_dblExpected);
        doc.addElement( "Deviation").addText( "" + m_dblError);
        doc.addElement( "Approximation").addText( "" + m_nApproximation);
    }
}
