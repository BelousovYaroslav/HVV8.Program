/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JProg.hv;

import JProg.JProgAStatement;
import org.apache.log4j.Logger;
import org.dom4j.Element;

/**
 *
 * @author yaroslav
 */
public class JProgHvChannels extends JProgAStatement {

    public static final int ACTION_NO_CHANGE = 0;
    public static final int ACTION_TURN_ON   = 1;
    public static final int ACTION_TURN_OFF  = 2;
    
    public static final int DEVICE1 = 0;
    public static final int DEVICE2 = 1;
    public static final int DEVICE3 = 2;
    public static final int DEVICE4 = 3;
    public static final int DEVICE5 = 4;
    public static final int DEVICE6 = 5;
    public static final int DEVICE7 = 6;
    public static final int DEVICE8 = 7;    
    
    static Logger logger = Logger.getLogger( JProgHvChannels.class);
    
    private int[] m_nAnodesActions;
    private int[] m_nTubulationsActions;
    
    private boolean m_bCheckAdmin;
    public boolean GetCheckAdmin() { return m_bCheckAdmin;}
    public void SetCheckAdmin( boolean bCheckAdmin) { m_bCheckAdmin = bCheckAdmin;}
    
    /*public int GetState() {
        return m_nState;
    }*/
    
    
    @Override
    public String GetAsString() {
        String strResult = "";
        
        boolean bAllSame = true;
        int nAction = m_nAnodesActions[0];
        for( int i=1; i<8; i++) {
            if( nAction != m_nAnodesActions[i]) {
                bAllSame = false;
                break;
            }
        }
        strResult = "HV. ";
        
        boolean bAtLeastOneOn = false;
        strResult += "Аноды: { ";
        if( bAllSame) {
            switch( m_nAnodesActions[0]) {
                case ACTION_NO_CHANGE:  strResult += "все без изменений"; break;
                case ACTION_TURN_ON:    strResult += "все включить"; bAtLeastOneOn = true; break;
                case ACTION_TURN_OFF:   strResult += "все выключить"; break;
            }
        }
        else {
            switch( m_nAnodesActions[0]) {
                case ACTION_NO_CHANGE:  strResult += "--"; break;
                case ACTION_TURN_ON:    strResult += "on"; bAtLeastOneOn = true; break;
                case ACTION_TURN_OFF:   strResult += "off"; break;
            }
            for( int i=1; i<8; i++) {
                switch( m_nAnodesActions[i]) {
                    case ACTION_NO_CHANGE:  strResult += ", --"; break;
                    case ACTION_TURN_ON:    strResult += ", on"; bAtLeastOneOn = true; break;
                    case ACTION_TURN_OFF:   strResult += ", off"; break;
                }
            }
        }
        strResult +="}";
        
        
        
        bAllSame = true;
        nAction = m_nTubulationsActions[0];
        for( int i=1; i<8; i++) {
            if( nAction != m_nTubulationsActions[i]) {
                bAllSame = false;
                break;
            }
        }
        strResult += "  Штенгели: { ";
        if( bAllSame) {
            switch( m_nTubulationsActions[0]) {
                case ACTION_NO_CHANGE:  strResult += "все без изменений"; break;
                case ACTION_TURN_ON:    strResult += "все включить"; bAtLeastOneOn = true; break;
                case ACTION_TURN_OFF:   strResult += "все выключить"; break;
            }
        }
        else {
            switch( m_nTubulationsActions[0]) {
                case ACTION_NO_CHANGE:  strResult += "--"; break;
                case ACTION_TURN_ON:    strResult += "on"; bAtLeastOneOn = true; break;
                case ACTION_TURN_OFF:   strResult += "off"; break;
            }
            for( int i=1; i<8; i++) {
                switch( m_nTubulationsActions[i]) {
                    case ACTION_NO_CHANGE:  strResult += ", --"; break;
                    case ACTION_TURN_ON:    strResult += ", on"; bAtLeastOneOn = true; break;
                    case ACTION_TURN_OFF:   strResult += ", off"; break;
                }
            }
        }
        strResult +="}";
        
        if( m_bCheckAdmin && bAtLeastOneOn)
            strResult += ". Перед включением сделать запрос в адм. модуль.";
        return strResult;
    }

    /*
    @Override
    public Document GetAsXML() {
        Document document = DocumentHelper.createDocument();
        
        //Element root = document.addElement( "Condition" );
        Element root = document.addElement( "Operation" );
        root.addElement( "Type").addText( "HV_CHANNELS");
        root.addElement( "DEV_1_A").addText( "" + m_nAnodesActions[0]);
        root.addElement( "DEV_1_T").addText( "" + m_nTubulationsActions[0]);
        root.addElement( "DEV_2_A").addText( "" + m_nAnodesActions[1]);
        root.addElement( "DEV_2_T").addText( "" + m_nTubulationsActions[1]);
        root.addElement( "DEV_3_A").addText( "" + m_nAnodesActions[2]);
        root.addElement( "DEV_3_T").addText( "" + m_nTubulationsActions[2]);
        root.addElement( "DEV_4_A").addText( "" + m_nAnodesActions[3]);
        root.addElement( "DEV_4_T").addText( "" + m_nTubulationsActions[3]);
        root.addElement( "DEV_5_A").addText( "" + m_nAnodesActions[4]);
        root.addElement( "DEV_5_T").addText( "" + m_nTubulationsActions[4]);
        root.addElement( "DEV_6_A").addText( "" + m_nAnodesActions[5]);
        root.addElement( "DEV_6_T").addText( "" + m_nTubulationsActions[5]);
        root.addElement( "DEV_7_A").addText( "" + m_nAnodesActions[6]);
        root.addElement( "DEV_7_T").addText( "" + m_nTubulationsActions[6]);
        root.addElement( "DEV_8_A").addText( "" + m_nAnodesActions[7]);
        root.addElement( "DEV_8_T").addText( "" + m_nTubulationsActions[7]);
        
        return document;
    }
    */

    @Override
    public void AddXMLStatement( Element root) {
        root.addElement( "Type").addText( "HV.CHANNELS");
        root.addElement( "DEV_1_A").addText( "" + m_nAnodesActions[0]);
        root.addElement( "DEV_1_T").addText( "" + m_nTubulationsActions[0]);
        root.addElement( "DEV_2_A").addText( "" + m_nAnodesActions[1]);
        root.addElement( "DEV_2_T").addText( "" + m_nTubulationsActions[1]);
        root.addElement( "DEV_3_A").addText( "" + m_nAnodesActions[2]);
        root.addElement( "DEV_3_T").addText( "" + m_nTubulationsActions[2]);
        root.addElement( "DEV_4_A").addText( "" + m_nAnodesActions[3]);
        root.addElement( "DEV_4_T").addText( "" + m_nTubulationsActions[3]);
        root.addElement( "DEV_5_A").addText( "" + m_nAnodesActions[4]);
        root.addElement( "DEV_5_T").addText( "" + m_nTubulationsActions[4]);
        root.addElement( "DEV_6_A").addText( "" + m_nAnodesActions[5]);
        root.addElement( "DEV_6_T").addText( "" + m_nTubulationsActions[5]);
        root.addElement( "DEV_7_A").addText( "" + m_nAnodesActions[6]);
        root.addElement( "DEV_7_T").addText( "" + m_nTubulationsActions[6]);
        root.addElement( "DEV_8_A").addText( "" + m_nAnodesActions[7]);
        root.addElement( "DEV_8_T").addText( "" + m_nTubulationsActions[7]);
        if( m_bCheckAdmin)
            root.addElement( "CHK_ADM").addText( "true");
        else
            root.addElement( "CHK_ADM").addText( "false");
    }
    
    public void SetDeviceAnodeAction( int nChannel, int nAction) {
        if( nAction != ACTION_NO_CHANGE && nAction != ACTION_TURN_ON && nAction != ACTION_TURN_OFF)
            return;
        if( nChannel < 0 || nChannel > 7)
            return;
        m_nAnodesActions[ nChannel] = nAction;
    }
    
    public int GetDeviceAnodeAction( int nDevice) {
        if( nDevice < 0 || nDevice > 7) {
            logger.error( "Bad parameter: " + nDevice);
            return ACTION_NO_CHANGE;
        }
        
        return m_nAnodesActions[ nDevice];
    }
    
    public void SetDeviceTubulationAction( int nChannel, int nAction) {
        if( nAction != ACTION_NO_CHANGE && nAction != ACTION_TURN_ON && nAction != ACTION_TURN_OFF)
            return;
        if( nChannel < 0 || nChannel > 7)
            return;
        m_nTubulationsActions[ nChannel] = nAction;
    }
    
    public int GetDeviceTubulationAction( int nDevice) {
        if( nDevice < 0 || nDevice > 7) {
            logger.error( "Bad parameter: " + nDevice);
            return ACTION_NO_CHANGE;
        }
        
        return m_nTubulationsActions[ nDevice];
    }
    
    public JProgHvChannels() {
        setStatementType( JProgAStatement.STATEMENT_TYPE_HV_CHANNELS);
        m_nAnodesActions = new int [] { 0, 0, 0, 0, 0, 0, 0, 0};
        m_nTubulationsActions = new int [] { 0, 0, 0, 0, 0, 0, 0, 0};
        m_bCheckAdmin = false;
    }
    
    public JProgHvChannels( Element el) {
        setStatementType( JProgAStatement.STATEMENT_TYPE_HV_CHANNELS);
        
        m_nAnodesActions = new int [] { 0, 0, 0, 0, 0, 0, 0, 0};
        m_nTubulationsActions = new int [] { 0, 0, 0, 0, 0, 0, 0, 0};
        m_bCheckAdmin = false;
        
        for( int i=1; i<9; i++) {
            Element aValue = el.element( "DEV_" + i + "_A");        
            try {
                m_nAnodesActions[ i-1] = Integer.parseInt( aValue.getText());
            }
            catch( NumberFormatException ex) {
                logger.error( "Bad XML-input. A" + i + " channel will be constructed as default [NO_CHANGE]");
            }
            
            Element tValue = el.element( "DEV_" + i + "_T");        
            try {
                m_nTubulationsActions[ i-1] = Integer.parseInt( tValue.getText());
            }
            catch( NumberFormatException ex) {
                logger.error( "Bad XML-input. T" + i + " channel will be constructed as default [NO_CHANGE]");
            }
        }
        
        Element chkValue = el.element( "CHK_ADM");
        if( chkValue != null) {
            if( "true".equals( chkValue.getText())) {
                m_bCheckAdmin = true;
            }
        }
    }
}
