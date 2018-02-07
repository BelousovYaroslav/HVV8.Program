/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JProg.hv;

import JProg.JProgAStatement;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 *
 * @author yaroslav
 */
public class JProgHvMainSwitcher extends JProgAStatement {

    public static final int TURN_MAIN_SWITCHER_ON = 1;
    public static final int TURN_MAIN_SWITCHER_OFF = 2;
    
    static Logger logger = Logger.getLogger( JProgHvMainSwitcher.class);
    
    private int m_nState;

    public void SetState( int nNewState) {
        if( nNewState == TURN_MAIN_SWITCHER_ON || nNewState == TURN_MAIN_SWITCHER_OFF) {
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
            case TURN_MAIN_SWITCHER_ON:
                strResult = "HV. Включить основной рубильник.";
            break;
            case TURN_MAIN_SWITCHER_OFF:
                strResult = "HV. Выключить основной рубильник.";
            break;    
            default:
                strResult = "Неопознанная команда типа HV.MainSwitcher";
        }
        return strResult;
    }

    /*
    @Override
    public Document GetAsXML() {
        Document document = DocumentHelper.createDocument();
        //Element root = document.addElement( "Condition" );
        Element root = document.addElement( "Operation" );
        root.addElement( "Type").addText( "HV_MAIN_SWITCHER");
        root.addElement( "State").addText( "" + m_nState);
        return document;
    }
    */

    @Override
    public void AddXMLStatement( Element root) {
        root.addElement( "Type").addText( "HV.MAIN_SWITCHER");
        root.addElement( "State").addText( "" + m_nState);
    }
    
    public JProgHvMainSwitcher() {
        setStatementType( JProgAStatement.STATEMENT_TYPE_HV_MAIN_SWITCHER);
        m_nState = TURN_MAIN_SWITCHER_OFF;
    }
    
    public JProgHvMainSwitcher( Element el) {
        setStatementType( JProgAStatement.STATEMENT_TYPE_HV_MAIN_SWITCHER);
        Element eValue = el.element( "State");
        
        m_nState = TURN_MAIN_SWITCHER_OFF;
        try {
            m_nState = Integer.parseInt( eValue.getText());
        }
        catch( NumberFormatException ex) {
            logger.error( "Bad XML-input. MainSwitcher operation will be constructed as default [TURN_OFF]");
        }
    }
}
