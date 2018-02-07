/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JProg;

import JProg.hv.JProgHvChannels;
import JProg.hv.JProgHvMainSwitcher;
import JProg.hv.JProgHvPreset;
import JProg.hv.JProgHvVibration;
import JProg.hv.JProgHvFan;
import JProg.service.JProgServAdminStep;
import JProg.service.JProgServMessageStatement;
import JProg.service.JProgServPauseStatement;
import JProg.service.JProgServSoundStatement;
import JProg.vacuum.JProgVacuumClose;
import JProg.vacuum.JProgVacuumOpen;
import JProg.vacuum.JProgVacuumSet;
import JProg.vacuum.JProgVacuumTurnOff;
import JProg.vacuum.JProgVacuumTurnOn;
import JProg.vacuum.JProgVacuumWait;
import org.apache.log4j.Logger;
import org.dom4j.Element;

/**
 *
 * @author yaroslav
 */
public abstract class JProgAStatement {
    
    /**
     * Константа для обозначения типа команды разрядом "сервис"
     */
    public static final int STATEMENT_TYPE_SERVICE = 0;
    public static final int STATEMENT_TYPE_SERVICE_PAUSE = 1;
    public static final int STATEMENT_TYPE_SERVICE_SOUND = 2;
    public static final int STATEMENT_TYPE_SERVICE_MESSAGE = 3;
    public static final int STATEMENT_TYPE_SERVICE_ADMIN_STEP = 4;
    
    public static final int STATEMENT_TYPE_HV = 100;
    public static final int STATEMENT_TYPE_HV_MAIN_SWITCHER = 101;
    public static final int STATEMENT_TYPE_HV_CHANNELS = 102;
    public static final int STATEMENT_TYPE_HV_VIBRATON = 103;
    public static final int STATEMENT_TYPE_HV_FAN = 104;
    public static final int STATEMENT_TYPE_HV_PRESET = 105;
    
    public static final int STATEMENT_TYPE_VACUUM = 200;
    public static final int STATEMENT_TYPE_VACUUM_TURN_ON = 201;
    public static final int STATEMENT_TYPE_VACUUM_TURN_OFF = 202;
    public static final int STATEMENT_TYPE_VACUUM_OPEN = 203;
    public static final int STATEMENT_TYPE_VACUUM_CLOSE = 204;
    public static final int STATEMENT_TYPE_VACUUM_WAIT = 205;
    public static final int STATEMENT_TYPE_VACUUM_SET = 206;
    
    static Logger logger = Logger.getLogger( JProgAStatement.class);
    
    private JProgCondition m_condition;
    
    private int m_nStatementType;
    public int getStatementType() { return m_nStatementType;}
    public void setStatementType( int nNewStatementType) {
        switch( nNewStatementType) {
            case STATEMENT_TYPE_SERVICE_PAUSE:
            case STATEMENT_TYPE_SERVICE_SOUND:
            case STATEMENT_TYPE_SERVICE_MESSAGE:
            case STATEMENT_TYPE_SERVICE_ADMIN_STEP:
                
            case STATEMENT_TYPE_HV_MAIN_SWITCHER:
            case STATEMENT_TYPE_HV_CHANNELS:
            case STATEMENT_TYPE_HV_VIBRATON:
            case STATEMENT_TYPE_HV_FAN:
            case STATEMENT_TYPE_HV_PRESET:
    
            case STATEMENT_TYPE_VACUUM_TURN_ON:
            case STATEMENT_TYPE_VACUUM_TURN_OFF:
            case STATEMENT_TYPE_VACUUM_OPEN:
            case STATEMENT_TYPE_VACUUM_CLOSE:
            case STATEMENT_TYPE_VACUUM_WAIT:
            case STATEMENT_TYPE_VACUUM_SET:
                m_nStatementType = nNewStatementType;
            break;
                
        }
    }
    
    public static JProgAStatement parse( Element element) {
        JProgAStatement statement = null;
                        
        Element type = element.element( "Type");
        if( type != null) {
            switch( type.getText()) {
                case "SERVICE.PAUSE": statement = new JProgServPauseStatement( element); break;
                case "SERVICE.SOUND": statement = new JProgServSoundStatement( element); break;
                case "SERVICE.MESSAGE": statement = new JProgServMessageStatement( element); break;
                case "SERVICE.ADMIN.STEP": statement = new JProgServAdminStep( element); break;
                
                case "VAC.CLOSE": statement = new JProgVacuumClose( element); break;
                case "VAC.OPEN": statement = new JProgVacuumOpen( element); break;
                case "VAC.TURN.OFF": statement = new JProgVacuumTurnOff( element); break;
                case "VAC.TURN.ON": statement = new JProgVacuumTurnOn( element); break;
                case "VAC.WAIT": statement = new JProgVacuumWait( element); break;
                case "VAC.SET": statement = new JProgVacuumSet( element); break;
                    
                case "HV.MAIN_SWITCHER": statement = new JProgHvMainSwitcher( element); break;
                case "HV.CHANNELS": statement = new JProgHvChannels( element); break;
                case "HV.PRESET": statement = new JProgHvPreset( element); break;
                case "HV.VIBRATION": statement = new JProgHvVibration( element); break;
                case "HV.FAN": statement = new JProgHvFan( element); break;
                    
                default:
                    logger.warn( "XML_parser for '" + type.getText() + "' not ready yet!");
            }
        }                    
        return statement;
    }
    
    public int getStatementRoughType() {
        int nResult = -1;
        switch( m_nStatementType) {
            case STATEMENT_TYPE_SERVICE_PAUSE:
            case STATEMENT_TYPE_SERVICE_SOUND:
            case STATEMENT_TYPE_SERVICE_MESSAGE:
            case STATEMENT_TYPE_SERVICE_ADMIN_STEP:
                nResult = STATEMENT_TYPE_SERVICE;
            break;
                
            case STATEMENT_TYPE_HV_MAIN_SWITCHER:
            case STATEMENT_TYPE_HV_CHANNELS:
            case STATEMENT_TYPE_HV_VIBRATON:
            case STATEMENT_TYPE_HV_PRESET:
                nResult = STATEMENT_TYPE_HV;
            break;
                
            case STATEMENT_TYPE_VACUUM_TURN_ON:
            case STATEMENT_TYPE_VACUUM_TURN_OFF:
            case STATEMENT_TYPE_VACUUM_OPEN:
            case STATEMENT_TYPE_VACUUM_CLOSE:
            case STATEMENT_TYPE_VACUUM_WAIT:
            case STATEMENT_TYPE_VACUUM_SET:
                nResult = STATEMENT_TYPE_VACUUM;
            break;
        }
        return nResult;
    }
    
    public abstract String GetAsString();
    //public abstract Document GetAsXML();
    public abstract void AddXMLStatement( Element doc);
}
