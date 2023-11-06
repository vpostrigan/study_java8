package advanced_java2_deitel.chapter25_jms.fig16_20;

// Java core packages

import java.awt.*;
import java.awt.event.*;
import java.rmi.*;
import java.util.*;
import java.util.List;

// Java extension packages
import javax.swing.*;
import javax.ejb.*;
import javax.rmi.*;
import javax.naming.*;

// TallyDisplay.java
// TallyDisplay displays the votes from database.
public class TallyDisplay extends JFrame {

    // TallyDisplay constructor
    public TallyDisplay() {
        super("Vote Tallies");

        Container container = getContentPane();

        // displayPanel displays tally results
        JPanel displayPanel = new JPanel();
        displayPanel.setLayout(new GridLayout(0, 1));
        container.add(new JScrollPane(displayPanel));

        // find Candidates and display tallies
        try {
            // look up Candidate EJB
            Context initialContext = new InitialContext();

            Object object = initialContext.lookup("Candidate");
            CandidateHome candidateHome = (CandidateHome) PortableRemoteObject.narrow(object, CandidateHome.class);

            // find all Candidates
            Collection candidates = candidateHome.findAllCandidates();

            // add TallyPanel with candidate name and vote count for each candidate
            Iterator iterator = candidates.iterator();
            while (iterator.hasNext()) {
                Candidate candidate = (Candidate) iterator.next();

                // create TallyPanel for Candidate
                TallyPanel tallyPanel = new TallyPanel(candidate.getCandidateName(), candidate.getVoteCount().intValue());
                displayPanel.add(tallyPanel);
            }
        } catch (FinderException finderException) { // handle exception finding Candidates
            finderException.printStackTrace();
        } catch (NamingException namingException) { // handle exception looking up Candidate EJB
            namingException.printStackTrace();
        } catch (RemoteException remoteException) { // handle exception communicating with Candidate
            remoteException.printStackTrace();
        }
    }

    // launch TallyDisplay application
    public static void main(String args[]) {
        TallyDisplay tallyDisplay = new TallyDisplay();
        tallyDisplay.setDefaultCloseOperation(EXIT_ON_CLOSE);
        tallyDisplay.pack();
        tallyDisplay.setVisible(true);
    }

}
