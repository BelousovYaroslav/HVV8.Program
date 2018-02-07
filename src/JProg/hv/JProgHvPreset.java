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
public class JProgHvPreset extends JProgAStatement {

    static Logger logger = Logger.getLogger( JProgHvPreset.class);
    
    private int m_nPreset;
    public void SetPreset( int nNewPreset) {
        if( nNewPreset >= 300 && nNewPreset <= 3000) {
            m_nPreset = nNewPreset;
        }
    }
    
    public int GetPreset() {
        return m_nPreset;
    }
    
    
    @Override
    public String GetAsString() {
        String strResult;
        strResult = "HV. Задать уставку тока " + m_nPreset + " мкА.";
        return strResult;
    }

    /*
    @Override
    public Document GetAsXML() {
        Document document = DocumentHelper.createDocument();
        //Element root = document.addElement( "Condition" );
        Element root = document.addElement( "Operation" );
        root.addElement( "Type").addText( "HV_PRESET");
        root.addElement( "Value").addText( "" + m_nPreset);
        return document;
    }
    */

    @Override
    public void AddXMLStatement( Element root) {
        root.addElement( "Type").addText( "HV.PRESET");
        root.addElement( "Value").addText( "" + m_nPreset);
    }
    
    public JProgHvPreset() {
        setStatementType( JProgAStatement.STATEMENT_TYPE_HV_PRESET);
        m_nPreset = 1000;
    }
    
    public JProgHvPreset( Element el) {
        setStatementType( JProgAStatement.STATEMENT_TYPE_HV_PRESET);
        Element eValue = el.element( "Value");
        
        m_nPreset = 1000;
        try {
            m_nPreset = Integer.parseInt( eValue.getText());
        }
        catch( NumberFormatException ex) {
            logger.error( "Bad XML-input. Preset will be constructed as default 1000 mcA");
        }
    }
}
