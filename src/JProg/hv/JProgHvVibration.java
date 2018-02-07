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
public class JProgHvVibration extends JProgAStatement {

    public static final int TURN_VIBRATION_ON = 1;
    public static final int TURN_VIBRATION_OFF = 2;
    
    static Logger logger = Logger.getLogger(JProgHvVibration.class);
    
    private int m_nState;

    public void SetAction( int nNewState) {
        if( nNewState == TURN_VIBRATION_ON || nNewState == TURN_VIBRATION_OFF) {
            m_nState = nNewState;
        }
    }
    
    public int GetState() {
        return m_nState;
    }
    
    
    @Override
    public String GetAsString() {
        String strResult;
        switch( m_nState) {
            case TURN_VIBRATION_ON:
                strResult = "HV. Включить вибрацию.";
            break;
            case TURN_VIBRATION_OFF:
                strResult = "HV. Выключить вибрацию.";
            break;    
            default:
                strResult = "Неопознанная команда типа HV.Vibration";
        }
        return strResult;
    }

    /*
    @Override
    public Document GetAsXML() {
        Document document = DocumentHelper.createDocument();
        //Element root = document.addElement( "Condition" );
        Element root = document.addElement( "Operation" );
        root.addElement( "Type").addText( "HV_VIBRATION");
        root.addElement( "State").addText( "" + m_nState);
        return document;
    }
    */

    @Override
    public void AddXMLStatement( Element root) {
        root.addElement( "Type").addText( "HV.VIBRATION");
        root.addElement( "State").addText( "" + m_nState);
    }
    
    public JProgHvVibration() {
        setStatementType( JProgAStatement.STATEMENT_TYPE_HV_VIBRATON);
        m_nState = TURN_VIBRATION_OFF;
    }
    
    public JProgHvVibration( Element el) {
        setStatementType( JProgAStatement.STATEMENT_TYPE_HV_VIBRATON);
        Element eValue = el.element( "State");
        
        m_nState = TURN_VIBRATION_OFF;
        try {
            m_nState = Integer.parseInt( eValue.getText());
        }
        catch( NumberFormatException ex) {
            logger.error( "Bad XML-input. Vibration operation will be constructed as default [TURN_OFF]");
        }
    }
}
