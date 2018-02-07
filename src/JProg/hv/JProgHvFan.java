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
public class JProgHvFan extends JProgAStatement {

    public static final int TURN_FAN_ON = 1;
    public static final int TURN_FAN_OFF = 2;
    
    static Logger logger = Logger.getLogger(JProgHvFan.class);
    
    private int m_nState;

    public void SetAction( int nNewState) {
        if( nNewState == TURN_FAN_ON || nNewState == TURN_FAN_OFF) {
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
            case TURN_FAN_ON:
                strResult = "HV. Включить вентилятор шкафа.";
            break;
            case TURN_FAN_OFF:
                strResult = "HV. Выключить вентилятор шкафа.";
            break;    
            default:
                strResult = "Неопознанная команда типа HV.FAN";
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
        root.addElement( "Type").addText( "HV.FAN");
        root.addElement( "State").addText( "" + m_nState);
    }
    
    public JProgHvFan() {
        setStatementType( JProgAStatement.STATEMENT_TYPE_HV_FAN);
        m_nState = TURN_FAN_OFF;
    }
    
    public JProgHvFan( Element el) {
        setStatementType( JProgAStatement.STATEMENT_TYPE_HV_FAN);
        Element eValue = el.element( "State");
        
        m_nState = TURN_FAN_OFF;
        try {
            m_nState = Integer.parseInt( eValue.getText());
        }
        catch( NumberFormatException ex) {
            logger.error( "Bad XML-input. Fan operation will be constructed as default [TURN_OFF]");
        }
    }
}
