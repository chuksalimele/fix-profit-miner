package profit;

/****************************************************************************
** Copyright (c) 2001-2014
**
** This file is part of the QuickFIX FIX Engine
**
** This file may be distributed under the terms of the quickfixengine.org
** license as defined by quickfixengine.org and appearing in the file
** LICENSE included in the packaging of this file.
**
** This file is provided AS IS with NO WARRANTY OF ANY KIND, INCLUDING THE
** WARRANTY OF DESIGN, MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE.
**
** See http://www.quickfixengine.org/LICENSE for licensing information.
**
** Contact ask@quickfixengine.org if any conditions of this licensing are
** not clear to you.
**
****************************************************************************/

import profit.miner.ProfitMinerApplication;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.Category;

import quickfix.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.util.TimeZone;

/**
 *  Entry point for the ProfitMiner application.
 */
public class ProfitMiner {

    /** enable logging for this class */
    private static Category log = Category.getInstance(ProfitMiner.class.getName());
    private Initiator initiator = null;
    //private JFrame frame = null;
    private static boolean stop = false;


    public ProfitMiner() throws Exception {

        ProfitMinerApplication application = new ProfitMinerApplication();
        SessionSettings settings = new SessionSettings(new FileInputStream("cfg/fix_smart_trader.cfg"));
        
        MessageStoreFactory messageStoreFactory = new FileStoreFactory(settings);
        LogFactory logFactory = new ScreenLogFactory(settings);
        MessageFactory messageFactory =  new DefaultMessageFactory();

        initiator = new SocketInitiator
                    (application, messageStoreFactory, settings, logFactory, messageFactory);

        /*frame = new FIXTraderFrame(orderTableModel, executionTableModel,
                                application);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);*/
    }

    public void start() throws Exception {
        initiator.start();
    }

    public void stop() {
		stop = true;
		initiator.stop();
	}

   /* public JFrame getFrame() {
        return frame;
    }

    public static void main(String args[]) throws Exception {
        ProfitMiner trader = new ProfitMiner();
        trader.start();
        while( !stop ) {
			Thread.sleep(1000);
		}
		trader.stop();
    }*/
}
